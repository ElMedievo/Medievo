package org.elmedievo.medievo.EventHandlers;

import org.elmedievo.medievo.Medievo;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static org.elmedievo.medievo.Commands.Chat.Methods.SendMessageInPlayerChat.SendMessageToCorrespondingChat;

public class PlayerChat implements Listener {

    private final Medievo plugin;

    private PlayerChat(Medievo instance) {
        plugin = instance;
    }

    @EventHandler
    public static void onPlayerChat(AsyncPlayerChatEvent event) {
        event.setCancelled(true);
        Player player = event.getPlayer();
        String msg = event.getMessage();
        SendMessageToCorrespondingChat(player, msg);
    }

    public static void registerPlayerChatEvent() {
        Bukkit.getPluginManager().registerEvents(new PlayerChat(Medievo.instance), Medievo.instance);
    }
}
