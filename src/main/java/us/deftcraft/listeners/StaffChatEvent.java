package us.deftcraft.listeners;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import us.deftcraft.DeftBungee;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class StaffChatEvent implements Listener {

    @EventHandler
    public void onChat(ChatEvent e) {
        ProxiedPlayer p = (ProxiedPlayer) e.getSender();

        if (p.hasPermission("deftbungee.staffchat"))
        {
            if(!DeftBungee.staffchat.containsKey(p))
            {
                return;
            }

            if(e.getMessage().contains ("sc toggle")) {
                return;
            }

            if(e.getMessage().startsWith("/")) {
                return;
            }

            if (DeftBungee.staffchat.get(p)) {

                Map<String, ServerInfo> servers = DeftBungee.getInstance().getProxy().getServers();

                for (Map.Entry<String, ServerInfo> en : servers.entrySet()) {
                    ServerInfo proxiedPlayers = DeftBungee.getInstance().getProxy().getServerInfo(en.getKey());

                    Collection<ProxiedPlayer> players = proxiedPlayers.getPlayers();

                    for (ProxiedPlayer player1 : players) {


                        if (p.hasPermission("deftbungee.staffchat.see")) {
                            player1.sendMessage(
                                    new TextComponent(
                                            ChatColor.GREEN + "[" + p.getServer().getInfo().getName() + "] " +
                                                    ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "Staff" + ChatColor.GOLD + "Chat" + ChatColor.DARK_GRAY + "] "
                                                    + ChatColor.DARK_RED + p.getName() + ": " + ChatColor.RED + e.getMessage()));
                        }
                    }
                }
                e.setCancelled(true);
            }
        }
    }

}
