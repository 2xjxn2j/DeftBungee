package us.deftcraft.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.Title;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class AnnounceCommand extends Command {

    public AnnounceCommand() {
        super("announce", "deftcraft.announce");
    }

    public void execute(CommandSender commandSender, String[] strings) {
        if (strings.length == 0) {
            commandSender.sendMessage(new TextComponent(ChatColor.RED + "Usage: /announce <message>"));
            return;
        }
        StringBuilder announcement = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            announcement.append(strings[i]).append(" ");
        }
        String message = ChatColor.translateAlternateColorCodes('&', announcement.toString());
        ProxyServer.getInstance().broadcast(ChatColor.GOLD + "Announcement> " + ChatColor.RESET + message);
        Title title = ProxyServer.getInstance().createTitle();
        title.reset();
        title.title(new TextComponent(ChatColor.GOLD + "" + ChatColor.BOLD + "Announcement"));
        title.subTitle(new TextComponent(ChatColor.translateAlternateColorCodes('&', message)));
        for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
            title.send(player);
        }
    }
}
