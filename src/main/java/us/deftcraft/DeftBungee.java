package us.deftcraft;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;
import us.deftcraft.commands.LobbyCommand;
import us.deftcraft.commands.RankCommand;
import us.deftcraft.commands.SendCommand;
import us.deftcraft.commands.StaffChatCommand;
import us.deftcraft.listeners.StaffChatEvent;

import java.util.HashMap;
import java.util.Map;

public class DeftBungee extends Plugin {
    private static DeftBungee instance;

    public static DeftBungee getInstance() {
        return instance;
    }

    public static Map<ProxiedPlayer, Boolean> staffchat = new HashMap<ProxiedPlayer, Boolean>();

    @Override
    public void onEnable() {
        instance = this;

        getProxy().getPluginManager().registerCommand(this, new SendCommand());
        getProxy().getPluginManager().registerCommand(this, new LobbyCommand());
        getProxy().getPluginManager().registerCommand(this, new RankCommand());
        getProxy().getPluginManager().registerCommand(this, new StaffChatCommand());

        getProxy().getPluginManager().registerListener(this, new StaffChatEvent());

    }
}
