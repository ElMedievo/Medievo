package com.bgmbox.medievo.Commands.Chat.Methods;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class SendGlobalMessage {
    public static void sendMessageInGlobal(Player sender, String msg) {
        Bukkit.broadcastMessage("<" + sender.getDisplayName() + ChatColor.RESET + ">: " + msg);
    }
}
