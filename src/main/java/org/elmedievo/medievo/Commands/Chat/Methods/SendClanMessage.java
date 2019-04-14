package org.elmedievo.medievo.Commands.Chat.Methods;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.elmedievo.medievo.Database.Getters.PlayerClanGetter;

import java.util.Objects;

public class SendClanMessage {
    public static void sendMessageInClanChat(Player sender, String msg) {
        for (Player playerInstance : Bukkit.getServer().getOnlinePlayers()) {
            String senderClan = PlayerClanGetter.getPlayerClan(sender.getUniqueId());
            String playerInstanceClan = PlayerClanGetter.getPlayerClan(playerInstance.getUniqueId());
            String colored_msg = (ChatColor.translateAlternateColorCodes ('&', msg));
            if (Objects.requireNonNull(senderClan).equals(playerInstanceClan)) {
                if (sender.hasPermission("medievo.chat.color")) {
                    playerInstance.sendMessage("[" + ChatColor.AQUA + playerInstanceClan + ChatColor.RESET +  "] " + sender.getDisplayName() + ChatColor.RESET + ": " + colored_msg);
                } else {
                    playerInstance.sendMessage("[" + ChatColor.AQUA + playerInstanceClan + ChatColor.RESET +  "] " + sender.getDisplayName() + ChatColor.RESET + ": " + msg);
                }
            }
        }
    }
}
