package us.deftcraft.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import us.deftcraft.DeftBungee;

public class PingCommand extends Command
{

    public PingCommand() {
        super("ping", "deftcraft.ping");
    }

    public void execute(CommandSender commandSender, String[] strings) {

        if(!(commandSender instanceof ProxiedPlayer)) {
            commandSender.sendMessage(new TextComponent(ChatColor.RED + "You must be a player!"));
            return;
        }
        ProxiedPlayer p = (ProxiedPlayer) commandSender;

        if(strings.length == 0) {
            p.sendMessage(new TextComponent(ChatColor.GRAY + "Your latency is " + ChatColor.GOLD + p.getPing() + ChatColor.GRAY + "ms."));
            return;
        }

        if(strings.length == 1) {
            ProxiedPlayer pp = DeftBungee.getInstance().getProxy().getPlayer(strings[0]);

            if(pp == null) {
                p.sendMessage(new TextComponent(ChatColor.RED + "That player doesn't exist!"));
                return;
            }

            p.sendMessage(new TextComponent(ChatColor.GOLD + pp.getName() + "'s latency is " + ChatColor.GOLD + pp.getPing() + ChatColor.GRAY + "ms."));

        }
    }
}
