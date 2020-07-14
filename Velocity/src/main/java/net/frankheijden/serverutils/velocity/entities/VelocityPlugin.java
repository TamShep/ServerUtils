package net.frankheijden.serverutils.velocity.entities;

import com.google.inject.Inject;
import com.velocitypowered.api.plugin.annotation.DataDirectory;

import java.io.File;
import java.nio.file.Path;
import java.util.logging.Logger;

import net.frankheijden.serverutils.common.entities.ServerUtilsPlugin;
import net.frankheijden.serverutils.velocity.ServerUtils;
import net.frankheijden.serverutils.velocity.managers.VelocityPluginManager;
import net.frankheijden.serverutils.velocity.managers.VelocityTaskManager;
import net.frankheijden.serverutils.velocity.managers.VelocityVersionManager;

public class VelocityPlugin extends ServerUtilsPlugin {

    @Inject
    private ServerUtils plugin;

    @Inject
    private Logger logger;

    @Inject
    @DataDirectory
    private Path dataFolder;

    private final VelocityPluginManager pluginManager;
    private final VelocityTaskManager taskManager;
    private final VelocityResourceProvider resourceProvider;
    private final VelocityChatProvider chatProvider;
    private final VelocityVersionManager versionManager;

    /**
     * Creates a new BungeePlugin instance of ServerUtils.
     */
    public VelocityPlugin() {
        this.pluginManager = new VelocityPluginManager();
        this.taskManager = new VelocityTaskManager();
        this.resourceProvider = new VelocityResourceProvider();
        this.chatProvider = new VelocityChatProvider();
        this.versionManager = new VelocityVersionManager();
    }

    @Override
    @SuppressWarnings("unchecked")
    public VelocityPluginManager getPluginManager() {
        return pluginManager;
    }

    @Override
    public VelocityTaskManager getTaskManager() {
        return taskManager;
    }

    @Override
    public VelocityResourceProvider getResourceProvider() {
        return resourceProvider;
    }

    @Override
    public VelocityChatProvider getChatProvider() {
        return chatProvider;
    }

    @Override
    public VelocityVersionManager getVersionManager() {
        return versionManager;
    }

    @Override
    public Logger getLogger() {
        return logger;
    }

    @Override
    public File getDataFolder() {
        return dataFolder.toFile();
    }
}
