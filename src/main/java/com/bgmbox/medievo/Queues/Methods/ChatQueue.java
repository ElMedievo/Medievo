package com.bgmbox.medievo.Queues.Methods;

import org.bukkit.entity.Player;

import static com.bgmbox.medievo.Queues.CreateQueues.chatQueue;
import static com.bgmbox.medievo.util.Generic.*;

public class ChatQueue {

    public static String getPlayerChatMode(String playerName) {
        return chatQueue.get(playerName);
    }

    public static void setPlayerChatMode(Player player, String chatMode) {
        if (chatMode.equals("admin") || chatMode.equals("clan") || chatMode.equals("global")) {
            chatQueue.put(player.getName(), chatMode);
            switch (chatMode) {
                case "admin":
                    player.sendMessage(ADMIN_CHAT_SET);
                    break;
                case "clan":
                    player.sendMessage(CLAN_CHAT_SET);
                    break;
                case "global":
                    player.sendMessage(GLOBAL_CHAT_SET);
                    break;
            }
        } else {
            player.sendMessage(NO_CHAT_MATCHED_QUERY);
        }
    }

    public static boolean adminChatEnabled(Player player) {
        return chatQueue.get(player.getName()).equals("admin");
    }

    public static boolean globalChatEnabled(Player player) {
        return chatQueue.get(player.getName()).equals("global");
    }

    public static boolean clanChatEnabled(Player player) {
        return chatQueue.get(player.getName()).equals("clan");
    }
}