package com.bgmbox.medievo.Commands.Chat.Methods;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class SendGlobalMessage {
    public static void sendMessageInGlobal(Player sender, String msg) {
        String colored_msg = (ChatColor.translateAlternateColorCodes ('&', msg));
        if (sender.hasPermission("medievo.chat.colors")) {
            Bukkit.broadcastMessage("<" + sender.getDisplayName() + ChatColor.RESET + ">: " + colored_msg);
            return;
        }
        Bukkit.broadcastMessage("<" + sender.getDisplayName() + ChatColor.RESET + ">: " + msg);
    }
}
