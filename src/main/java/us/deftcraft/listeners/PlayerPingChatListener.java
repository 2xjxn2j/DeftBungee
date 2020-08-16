package us.deftcraft.listeners;

import net.md_5.bungee.api.Title;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import us.deftcraft.DeftBungee;

import java.util.Map;

public class PlayerPingChatListener implements Listener {

    //IN PROGRESS

    @EventHandler
    public void onChat(ChatEvent e) {
        ProxiedPlayer p = (ProxiedPlayer) e.getSender();

        for (ProxiedPlayer ping : p.getServer().getInfo().getPlayers()) {
            if (e.getMessage().toUpperCase().contains(ping.getName().toUpperCase())) {

                }
            }

        }
    }
