package com.bgmbox.medievo.Commands.Chat.Methods;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Objects;

import static com.bgmbox.medievo.Database.Getters.PlayerClanGetter.getPlayerClan;

public class SendClanMessage {
    public static void sendMessageInClanChat(Player sender, String msg) {
        for (Player playerInstance : Bukkit.getServer().getOnlinePlayers()) {
            String senderClan = getPlayerClan(sender.getUniqueId());
            String playerInstanceClan = getPlayerClan(playerInstance.getUniqueId());
            if (Objects.requireNonNull(senderClan).equals(playerInstanceClan)) {
                playerInstance.sendMessage("[" + ChatColor.AQUA + playerInstanceClan + ChatColor.RESET +  "] " + sender.getDisplayName() + ChatColor.RESET + ": " + msg);
            }
        }
    }
}
