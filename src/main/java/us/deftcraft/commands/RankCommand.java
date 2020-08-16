package us.deftcraft.commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import us.deftcraft.DeftBungee;

public class RankCommand extends Command {

    public RankCommand() {
        super("rank", "deftbungee.rank");
    }

    public void execute(CommandSender commandSender, String[] strings) {
        if(!(commandSender instanceof ProxiedPlayer)) {
            commandSender.sendMessage(new TextComponent(ChatColor.RED + "You must be a player!"));
            return;
        }
        ProxiedPlayer p = (ProxiedPlayer) commandSender;

        if(!p.hasPermission("deftbungee.rank")) {
            p.sendMessage(new TextComponent(ChatColor.BLUE + "Permissions> " + ChatColor.GRAY + "You do not have permission to do that!"));
            return;
        }

        if(strings.length != 2) {
            p.sendMessage(new TextComponent(ChatColor.RED + "Usage: /rank <rank> <player>"));
            return;
        }

        String rank = strings[0];

        ProxiedPlayer target = DeftBungee.getInstance().getProxy().getPlayer(strings[1]);

        if(target == null) {
            p.sendMessage(new TextComponent(ChatColor.RED + "Invalid player!"));
            return;
        }
        DeftBungee.getInstance().getProxy().broadcast(new TextComponent(ChatColor.BLUE + "Ranks> " + ChatColor.GRAY + "User " + ChatColor.GOLD + target.getName() + ChatColor.GRAY + " was promoted to "
                + ChatColor.GOLD + convert(rank)));

        DeftBungee.getInstance().getProxy().getPluginManager()
                .dispatchCommand(DeftBungee.getInstance().getProxy().getConsole(),"/lpb user " + target.getName() + " parent set " + rank);

    }

    static String convert(String str)
    {

        // Create a char array of given String
        char ch[] = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {

            // If first character of a word is found
            if (i == 0 && ch[i] != ' ' ||
                    ch[i] != ' ' && ch[i - 1] == ' ') {

                // If it is in lower-case
                if (ch[i] >= 'a' && ch[i] <= 'z') {

                    // Convert into Upper-case
                    ch[i] = (char)(ch[i] - 'a' + 'A');
                }
            }

            // If apart from first character
            // Any one is in Upper-case
            else if (ch[i] >= 'A' && ch[i] <= 'Z')

                // Convert into Lower-Case
                ch[i] = (char)(ch[i] + 'a' - 'A');
        }

        // Convert the char array to equivalent String
        String st = new String(ch);
        return st;
    }
}
