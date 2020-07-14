package net.frankheijden.serverutils.velocity.entities;

import com.google.inject.Inject;
import com.velocitypowered.api.proxy.ProxyServer;
import net.frankheijden.serverutils.common.entities.ServerCommandSender;
import net.frankheijden.serverutils.common.providers.ChatProvider;
import net.frankheijden.serverutils.common.utils.HexUtils;
import net.frankheijden.serverutils.velocity.utils.VelocityUtils;
import net.kyori.text.TextComponent;

public class VelocityChatProvider extends ChatProvider {

    private static final char ALT_CHARACTER = '&';
    private static final char COLOR_CHARACTER = '§';
    private static final String COLOR_CODES = "0123456789AaBbCcDdEeFfKkLlMmNnOoRrXx";

    @Inject
    private ProxyServer proxy;

    @Override
    public ServerCommandSender getConsoleSender() {
        return VelocityUtils.wrap(proxy.getConsoleCommandSource());
    }

    @Override
    public String color(String str) {
        char[] charArr = HexUtils.convertHexString(str).toCharArray();
        for (int i = 0; i < charArr.length - 1; i++) {
            if (charArr[i] == ALT_CHARACTER && COLOR_CODES.indexOf(charArr[i + 1]) > -1) {
                charArr[i] = COLOR_CHARACTER;
                charArr[i + 1] = Character.toLowerCase(charArr[i + 1]);
            }
        }
        return new String(charArr);
    }

    @Override
    public void broadcast(String permission, String message) {
        proxy.getAllPlayers().stream()
                .filter(p -> p.hasPermission(permission))
                .forEach(p -> p.sendMessage(TextComponent.of(message)));
    }
}
