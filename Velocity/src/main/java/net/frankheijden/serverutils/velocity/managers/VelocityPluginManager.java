package net.frankheijden.serverutils.velocity.managers;

import java.io.File;
import java.util.List;
import java.util.Set;

import net.frankheijden.serverutils.common.entities.CloseableResult;
import net.frankheijden.serverutils.common.entities.LoadResult;
import net.frankheijden.serverutils.common.entities.Result;
import net.frankheijden.serverutils.common.managers.AbstractPluginManager;

public class VelocityPluginManager extends AbstractPluginManager<Object> {

    @Override
    public LoadResult<Object> loadPlugin(String pluginFile) {
        return null;
    }

    @Override
    public LoadResult<Object> loadPlugin(File file) {
        return null;
    }

    @Override
    public Result enablePlugin(Object plugin) {
        return null;
    }

    @Override
    public CloseableResult reloadPlugin(String pluginName) {
        return null;
    }

    @Override
    public CloseableResult reloadPlugin(Object plugin) {
        return null;
    }

    @Override
    public CloseableResult unloadPlugin(String pluginName) {
        return null;
    }

    @Override
    public CloseableResult unloadPlugin(Object plugin) {
        return null;
    }

    @Override
    public File getPluginsFolder() {
        return null;
    }

    @Override
    public List<Object> getPlugins() {
        return null;
    }

    @Override
    public String getPluginName(Object plugin) {
        return null;
    }

    @Override
    public File getPluginFile(Object plugin) {
        return null;
    }

    @Override
    public Set<String> getCommands() {
        return null;
    }
}
