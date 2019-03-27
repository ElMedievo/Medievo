package com.bgmbox.medievo.EventHandlers;

import com.bgmbox.medievo.Medievo;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class playerChat implements Listener {

    private final Medievo plugin;

    private playerChat(Medievo instance) {
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
        Bukkit.getPluginManager().registerEvents(new playerChat(Medievo.instance), Medievo.instance);
    }
}
