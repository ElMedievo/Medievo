package com.bgmbox.medievo.Ranks;

import org.bukkit.entity.Player;

import static com.bgmbox.medievo.Ranks.CreateRanksData.getRanksData;

public class DeliverRanks {

    public static void deliverRanks(Player player) {
        String playerUUID = player.getUniqueId().toString();
        String playerName = player.getName();

        if (getRanksData().isConfigurationSection("players." + playerUUID)) {

        }
    }
}
