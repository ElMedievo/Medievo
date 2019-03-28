package com.bgmbox.medievo.EventHandlers;

import com.bgmbox.medievo.Medievo;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChat implements Listener {

    private final Medievo plugin;

    private PlayerChat(Medievo instance) {
        plugin = instance;
    }

    @EventHandler
    public static void onPlayerChat(AsyncPlayerChatEvent event) {
        event.setCancelled(true);
        String player_name = event.getPlayer().getDisplayName();
        String msg = event.getMessage();
        Bukkit.broadcastMessage("<" + player_name + ChatColor.RESET + ">: " + msg);
    }

    public static void registerPlayerChatEvent() {
        Bukkit.getPluginManager().registerEvents(new PlayerChat(Medievo.instance), Medievo.instance);
    }
}
