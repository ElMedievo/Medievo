package com.bgmbox.medievo.Ranks.Methods;

import org.bukkit.entity.Player;

import static com.bgmbox.medievo.Ranks.CreateRanksData.getRanksData;
import static com.bgmbox.medievo.Ranks.Methods.checkRankExistence.rankExists;

public class rankAdd {

    public static void addRank(Player player, String inputRank) {
        String uuid = player.getUniqueId().toString();
        if (rankExists(inputRank)) {
            if (!getRanksData().isConfigurationSection("players." + uuid)) {
                getRanksData().set("players.", uuid);
            }
        }
    }
}
