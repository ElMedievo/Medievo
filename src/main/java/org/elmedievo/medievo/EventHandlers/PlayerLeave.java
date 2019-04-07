package org.elmedievo.medievo.EventHandlers;

import org.elmedievo.medievo.Medievo;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import static org.elmedievo.medievo.util.Fixes.LEAVE_MESSAGE_PREFIX;
import static org.elmedievo.medievo.util.Fixes.LEAVE_MESSAGE_SUFFIX;

public class PlayerLeave implements Listener {

    private final Medievo plugin;

    private PlayerLeave(Medievo instance) {
        plugin = instance;
    }

    @EventHandler
    public static void onPlayerDisconnect(PlayerQuitEvent event) {
        String player_name = event.getPlayer().getDisplayName();
        event.setQuitMessage(LEAVE_MESSAGE_PREFIX + player_name + LEAVE_MESSAGE_SUFFIX);
    }

    public static void registerPlayerDisconnectEvent() {
        Bukkit.getPluginManager().registerEvents(new PlayerLeave(Medievo.instance), Medievo.instance);
    }
}
