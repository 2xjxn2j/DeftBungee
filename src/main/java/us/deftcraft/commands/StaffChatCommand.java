package us.deftcraft.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import us.deftcraft.DeftBungee;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class StaffChatCommand extends Command {

    public StaffChatCommand() {
        super("sc", "deftbungee.staffchat");
    }

    public void execute(CommandSender commandSender, String[] strings) {
        if (!(commandSender instanceof ProxiedPlayer)) {
            commandSender.sendMessage(new TextComponent(ChatColor.RED + "You must be a player"));
            return;
        }
        ProxiedPlayer player = (ProxiedPlayer) commandSender;

        if (strings.length == 0) {
            player.sendMessage(new TextComponent(ChatColor.RED + "Usage: /sc <message:toggle>"));
            return;
        }

        if (strings[0].equalsIgnoreCase("toggle")) {

            if(!DeftBungee.staffchat.containsKey(player)) {
                DeftBungee.staffchat.put(player,true);
                player.sendMessage(new TextComponent(ChatColor.GREEN + "Staff chat enabled!"));
                return;
            }

            if (!DeftBungee.staffchat.get(player)) {
                player.sendMessage(new TextComponent(ChatColor.GREEN + "Staff chat enabled!"));
                DeftBungee.staffchat.put(player,true);
            } else {
                player.sendMessage(new TextComponent(ChatColor.RED + "Staff chat disabled!"));
                DeftBungee.staffchat.put(player,false);
            }
            return;
        }

        StringBuilder message = new StringBuilder();

        for (int i = 0; i < strings.length; i++) {
            message.append(strings[i] + " ");
        }

        Map<String, ServerInfo> servers = DeftBungee.getInstance().getProxy().getServers();

        for (Map.Entry<String, ServerInfo> en : servers.entrySet()) {
            ServerInfo proxiedPlayers = DeftBungee.getInstance().getProxy().getServerInfo(en.getKey());

            Collection<ProxiedPlayer> players = proxiedPlayers.getPlayers();

            for (ProxiedPlayer player1 : players) {


                if (player.hasPermission("deftbungee.staffchat.see")) {
                    player1.sendMessage(
                            new TextComponent(
                                    ChatColor.GREEN + "[" + player.getServer().getInfo().getName() + "] " +
                                    ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "Staff" + ChatColor.GOLD + "Chat" + ChatColor.DARK_GRAY + "] "
                                            + ChatColor.DARK_RED + player.getName() + ": " + ChatColor.RED + message));
                }
            }
        }
    }
}
