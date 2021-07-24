package net.frankheijden.serverutils.bukkit.listeners;

import net.frankheijden.serverutils.bukkit.entities.BukkitCommandSender;
import net.frankheijden.serverutils.bukkit.entities.BukkitPlugin;
import net.frankheijden.serverutils.common.listeners.PlayerListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class BukkitPlayerListener extends PlayerListener<BukkitPlugin, BukkitCommandSender> implements Listener {

    public BukkitPlayerListener(BukkitPlugin plugin) {
        super(plugin);
    }

    /**
     * Called when a player joins the server.
     * Used for sending an update message to the player, if enabled and has permission.
     * @param event The PlayerJoinEvent.
     */
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerJoin(PlayerJoinEvent event) {
        handleUpdate(plugin.getChatProvider().get(event.getPlayer()));
    }
}