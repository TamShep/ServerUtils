package net.frankheijden.serverutils.bungee.entities;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import net.frankheijden.serverutils.bungee.ServerUtils;
import net.frankheijden.serverutils.common.config.AbstractConfig;
import net.frankheijden.serverutils.common.providers.ResourceProvider;

public class BungeeResourceProvider implements ResourceProvider {

    private final ServerUtils plugin;

    public BungeeResourceProvider(ServerUtils plugin) {
        this.plugin = plugin;
    }

    @Override
    public InputStream getResource(String resource) {
        return plugin.getResourceAsStream(resource);
    }

    @Override
    public AbstractConfig load(InputStream is) {
        return new BungeeConfig(is);
    }

    @Override
    public AbstractConfig load(File file) {
        try {
            return new BungeeConfig(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
