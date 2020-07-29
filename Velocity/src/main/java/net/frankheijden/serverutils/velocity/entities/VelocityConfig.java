package net.frankheijden.serverutils.velocity.entities;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import net.frankheijden.serverutils.common.config.AbstractConfig;
import net.frankheijden.serverutils.common.entities.Pair;

public class VelocityConfig extends AbstractConfig {

    private final Gson gson = new Gson();
    private final JsonObject json;
    private File file = null;

    public VelocityConfig(File file) throws IOException {
        this.json = read(new FileReader(file));
        this.file = file;
    }

    public VelocityConfig(InputStream in) {
        this.json = read(new InputStreamReader(in));
    }

    public VelocityConfig(JsonObject json) {
        this.json = json;
    }

    @Override
    public JsonElement get(String fullPath) {
        return get(getPaths(fullPath));
    }

    public JsonElement get(String[] paths) {
        JsonElement obj = json;
        for (String path : paths) {
            try {
                obj = obj.getAsJsonObject().get(path);
            } catch (IllegalStateException ex) {
                return null;
            }
            if (obj == null) return null;
        }
        return obj;
    }

    @Override
    public Map<String, Object> getMap(String fullPath) {
        JsonElement object = get(fullPath);
        if (object == null) return new HashMap<>();
        return gson.fromJson(object, new TypeToken<Map<String, Object>>(){}.getType());
    }

    @Override
    public void set(String fullPath, Object value) {
        Stack<Pair<String, JsonObject>> pairs = new Stack<>();
        pairs.push(new Pair<>(null, json));

        String[] paths = getPaths(fullPath);
        for (int i = 0; i < paths.length; i++) {
            String path = paths[i];

            JsonObject obj = pairs.peek().getB();
            if (i == paths.length - 1) {
                obj.add(path, gson.toJsonTree(value));
                break;
            }

            JsonElement childObj = obj.get(path);
            if (childObj == null) {
                childObj = new JsonObject();
                obj.add(path, obj);
            }
            pairs.push(new Pair<>(path, childObj.getAsJsonObject()));
        }

        while (!pairs.empty()) {
            Pair<String, JsonObject> pair = pairs.pop();
            if (!pairs.isEmpty()) {
                Pair<String, JsonObject> parent = pairs.peek();
                parent.getB().add(pair.getA(), pair.getB());
            }
        }
    }

    @Override
    public String getString(String fullPath) {
        JsonElement element = get(fullPath);
        if (element == null) return null;
        return element.getAsString();
    }

    @Override
    public boolean getBoolean(String fullPath) {
        JsonElement element = get(fullPath);
        if (element == null) return false;
        return get(fullPath).getAsBoolean();
    }

    @Override
    public Collection<? extends String> getKeys() {
        return json.keySet();
    }

    @Override
    public void save() throws IOException {
        gson.toJson(json, new JsonWriter(new FileWriter(file)));
    }

    public JsonObject read(Reader reader) {
        return gson.fromJson(new JsonReader(reader), JsonObject.class);
    }

    public static String[] getPaths(String path) {
        return path.split("\\.");
    }
}
