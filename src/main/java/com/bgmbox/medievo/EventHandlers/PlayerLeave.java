package com.bgmbox.medievo.EventHandlers;

import com.bgmbox.medievo.Medievo;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import static com.bgmbox.medievo.util.Fixes.LEAVE_MESSAGE_PREFIX;
import static com.bgmbox.medievo.util.Fixes.LEAVE_MESSAGE_SUFFIX;

public class PlayerLeave implements Listener {

    private final Medievo plugin;

    private PlayerLeave(Medievo instance) {
        plugin = instance;
    }

    @EventHandler
    public static void onPlayerDisconnect(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.setQuitMessage(LEAVE_MESSAGE_PREFIX + player + LEAVE_MESSAGE_SUFFIX);
    }

    public static void registerPlayerDisconnectEvent() {
        Bukkit.getPluginManager().registerEvents(new PlayerLeave(Medievo.instance), Medievo.instance);
    }
}
