package net.frankheijden.serverutils.velocity.entities;

import com.google.inject.Inject;

import java.io.File;
import java.io.InputStream;

import net.frankheijden.serverutils.common.config.AbstractConfig;
import net.frankheijden.serverutils.common.providers.ResourceProvider;
import net.frankheijden.serverutils.velocity.ServerUtils;

public class VelocityResourceProvider implements ResourceProvider {

    @Inject
    private ServerUtils plugin;

    @Override
    public InputStream getResource(String resource) {
        return plugin.getClass().getClassLoader().getResourceAsStream(resource);
    }

    @Override
    public AbstractConfig load(InputStream is) {
        return null;
    }

    @Override
    public AbstractConfig load(File file) {
        return null;
    }
}
