package us.deftcraft.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import us.deftcraft.DeftBungee;

public class SendCommand extends Command {

    public SendCommand() {
        super("send", "bungee.send");
    }

    public void execute(CommandSender commandSender, String[] strings) {

        if(strings.length != 2) {
            commandSender.sendMessage(new TextComponent(ChatColor.RED + "Usage: /send <player> <server>"));
            return;
        }

        ProxiedPlayer p = DeftBungee.getInstance().getProxy().getPlayer(strings[0]);

        if(p == null) {
            commandSender.sendMessage(new TextComponent(ChatColor.RED + "Invalid player"));
            return;
        }

        String server = strings[1];

        ServerInfo target = ProxyServer.getInstance().getServerInfo(server);
        p.connect(target);
        p.sendMessage(new TextComponent(ChatColor.GRAY + "You have been sent to " + ChatColor.GOLD + server));
        commandSender.sendMessage(new TextComponent(ChatColor.GRAY + "Successfully sent " + ChatColor.GOLD + p.getName() +
                ChatColor.GRAY + " to " + ChatColor.GOLD + server));

    }
}
