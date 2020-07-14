package net.frankheijden.serverutils.velocity.entities;

import java.io.IOException;
import java.util.Collection;

import net.frankheijden.serverutils.common.config.YamlConfig;

public class VelocityYamlConfig implements YamlConfig {

    @Override
    public Object get(String path) {
        return null;
    }

    @Override
    public void set(String path, Object value) {

    }

    @Override
    public String getString(String path) {
        return null;
    }

    @Override
    public boolean getBoolean(String path) {
        return false;
    }

    @Override
    public Collection<? extends String> getKeys() {
        return null;
    }

    @Override
    public void save() throws IOException {

    }
}
