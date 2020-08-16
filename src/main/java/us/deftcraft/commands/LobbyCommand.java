package us.deftcraft.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class LobbyCommand extends Command {

    public LobbyCommand() {
        super("hub");
    }

    public void execute(CommandSender commandSender, String[] strings) {
        ServerInfo info = ProxyServer.getInstance().getServerInfo("lobby");
        ProxiedPlayer p = (ProxiedPlayer) commandSender;

        if(p == null) {
            commandSender.sendMessage(new TextComponent(ChatColor.RED + "You must be a player!"));
            return;
        }

        p.connect(info);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        p.chat("/spawn");
    }
}
