package net.frankheijden.serverutils.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;

@Plugin(
        id = "serverutils",
        name = "ServerUtils",
        version = "${version}",
        authors = "FrankHeijden"
)
public class ServerUtils {

    private static ServerUtils instance;
    private static final String CONFIG_RESOURCE = "velocity-config.yml";
    private static final String MESSAGES_RESOURCE = "velocity-messages.yml";

    @Inject
    private ProxyServer proxy;

    @Subscribe
    public void onEnable(ProxyInitializeEvent event) {
        instance = this;
        System.out.println(getVersion());
    }

    public static ServerUtils getInstance() {
        return instance;
    }

    public String getVersion() {
        return "${version}";
    }
}
