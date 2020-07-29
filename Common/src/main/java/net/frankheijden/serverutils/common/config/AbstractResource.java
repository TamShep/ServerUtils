package net.frankheijden.serverutils.common.config;

import java.io.File;
import java.io.InputStream;

import net.frankheijden.serverutils.common.ServerUtilsApp;
import net.frankheijden.serverutils.common.entities.ServerUtilsPlugin;
import net.frankheijden.serverutils.common.providers.ResourceProvider;

/**
 * A class which provides functionality for loading and setting defaults of Yaml Configurations.
 */
public abstract class AbstractResource {

    private static final ServerUtilsPlugin plugin = ServerUtilsApp.getPlugin();

    private final AbstractConfig config;

    /**
     * Creates a new YamlResource instance.
     * Loads the resource from the jar file.
     * @param fileName The destination file.
     * @param resource The resource from the jar file.
     */
    public AbstractResource(String fileName, String resource) {
        ResourceProvider provider = plugin.getResourceProvider();
        InputStream is = provider.getResource(resource);
        File file = plugin.copyResourceIfNotExists(fileName, resource);
        config = AbstractConfig.init(provider.load(is), provider.load(file));
    }

    /**
     * Retrieves the YamlConfig of this resource.
     * @return The YamlConfig.
     */
    public AbstractConfig getConfig() {
        return config;
    }
}
