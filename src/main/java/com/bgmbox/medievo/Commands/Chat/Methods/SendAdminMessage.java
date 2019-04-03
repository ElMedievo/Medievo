package com.bgmbox.medievo.Commands.Chat.Methods;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import static com.bgmbox.medievo.util.Fixes.ADMIN_CHAT_PREFIX;

public class SendAdminMessage {

    public static void sendMessageInAdminChat(Player sender, String msg) {
        for (Player playerInstance : Bukkit.getServer().getOnlinePlayers()) {
            if (playerInstance.hasPermission("medievo.chat.admin")) {
                playerInstance.sendMessage(ADMIN_CHAT_PREFIX + sender.getDisplayName() + ChatColor.RESET + ": " + msg);
            }
        }
    }
}
