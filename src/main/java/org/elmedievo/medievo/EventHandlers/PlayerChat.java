package org.elmedievo.medievo.EventHandlers;

import org.elmedievo.medievo.Medievo;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static org.elmedievo.medievo.Commands.Chat.Methods.SendAdminMessage.sendMessageInAdminChat;
import static org.elmedievo.medievo.Commands.Chat.Methods.SendClanMessage.sendMessageInClanChat;
import static org.elmedievo.medievo.Commands.Chat.Methods.SendGlobalMessage.sendMessageInGlobal;
import static org.elmedievo.medievo.Queues.Methods.ChatQueue.getPlayerChatMode;

public class PlayerChat implements Listener {

    private final Medievo plugin;

    private PlayerChat(Medievo instance) {
        plugin = instance;
    }

    @EventHandler
    public static void onPlayerChat(AsyncPlayerChatEvent event) {
        event.setCancelled(true);
        Player player = event.getPlayer();
        String player_name = event.getPlayer().getName();
        String msg = event.getMessage();
        String chatMode = getPlayerChatMode(player_name);

        switch (chatMode) {
            case "admin":
                sendMessageInAdminChat(player, msg);
                break;
            case "global":
                sendMessageInGlobal(player, msg);
                break;
            case "clan":
                sendMessageInClanChat(player, msg);
        }
    }

    public static void registerPlayerChatEvent() {
        Bukkit.getPluginManager().registerEvents(new PlayerChat(Medievo.instance), Medievo.instance);
    }
}
