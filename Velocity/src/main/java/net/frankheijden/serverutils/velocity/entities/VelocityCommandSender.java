package net.frankheijden.serverutils.velocity.entities;

import com.velocitypowered.api.command.CommandSource;
import net.frankheijden.serverutils.common.entities.ServerCommandSender;
import net.kyori.text.TextComponent;

public class VelocityCommandSender implements ServerCommandSender {

    private final CommandSource source;

    public VelocityCommandSender(CommandSource source) {
        this.source = source;
    }

    @Override
    public void sendMessage(String message) {
        source.sendMessage(TextComponent.of(message));
    }

    @Override
    public boolean hasPermission(String permission) {
        return source.hasPermission(permission);
    }
}
