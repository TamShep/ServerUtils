package net.frankheijden.serverutils.common.providers;

import java.io.File;
import java.io.InputStream;

import net.frankheijden.serverutils.common.config.AbstractConfig;

public interface ResourceProvider {

    InputStream getResource(String resource);

    AbstractConfig load(InputStream is);

    AbstractConfig load(File file);
}
