package net.frankheijden.serverutils.velocity.entities;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class VelocityConfigTest {

    private final Gson gson = new Gson();

    private JsonObject json;
    private VelocityConfig config;

    @BeforeEach
    void setup() {
        InputStream in = getClass().getClassLoader().getResourceAsStream("velocity-config-test.json");
        json = gson.fromJson(new InputStreamReader(in), JsonObject.class);
        config = new VelocityConfig(json);
    }

    @Test
    void getSimple() {
        JsonElement element = config.get("key");
        assertDoesNotThrow((Executable) element::getAsString);
        assertThat(element.getAsString()).isEqualTo("value");
    }

    @Test
    void getNested() {
        JsonElement element = config.get("long.nested.key.with");
        assertDoesNotThrow((Executable) element::getAsString);
        assertThat(element.getAsString()).isEqualTo("value");
    }

    @Test
    void getUnknownKey() {
        assertThat(config.get("key.which.does.not.exist")).isNull();
    }

    @Test
    void getMap() {
        Map<String, Object> expected = new HashMap<>();
        expected.put("with", "value");
        expected.put("and", 10.0); // Integer is lost
        assertThat(config.getMap("long.nested.key")).containsAllEntriesOf(expected);
    }

    @Test
    void setSimple() {
        config.set("key2", "newValue");

        JsonElement element = json.get("key2");
        assertDoesNotThrow((Executable) element::getAsString);
        assertThat(element.getAsString()).isEqualTo("newValue");
    }

    @Test
    void setSimpleOverwrite() {
        config.set("key", "newValue");

        JsonElement element = config.get("key");
        assertDoesNotThrow((Executable) element::getAsString);
        assertThat(element.getAsString()).isEqualTo("newValue");
    }

    @Test
    void setNestedExisting() {
        config.set("long.nested.key.with", "newValue");
        assertThat(config.getString("long.nested.key.with")).isEqualTo("newValue");
    }

    @Test
    void setNestedNotExisting() {
        config.set("new.key.with", "newValue");
        assertThat(config.getString("new.key.with")).isEqualTo("newValue");
    }

    @Test
    void getString() {
        assertThat(config.getString("long.nested.key.with")).isEqualTo("value");
    }

    @Test
    void getStringUnknownKey() {
        assertThat(config.getString("unknown.key")).isEqualTo(null);
    }

    @Test
    void getBoolean() {
        assertThat(config.getBoolean("long.nested.anotherKey")).isEqualTo(true);
    }

    @Test
    void getBooleanUnknownKey() {
        assertThat(config.getBoolean("unknown.key")).isEqualTo(false);
    }

    @Test
    @SuppressWarnings("unchecked")
    void getKeys() {
        assertThat((Set<String>) config.getKeys()).containsExactlyInAnyOrder("key", "long");
    }
}