package com.bgmbox.medievo.Ranks.Methods;

import org.bukkit.entity.Player;

import java.util.List;

import static com.bgmbox.medievo.Ranks.CreateRanksData.getRanksData;
import static com.bgmbox.medievo.Ranks.CreateRanksData.saveRanksData;
import static com.bgmbox.medievo.Ranks.Methods.checkRankExistence.rankExists;

public class rankAdd {

    public static void addRank(Player player, String inputRank) {
        String uuid = player.getUniqueId().toString();
        List currentPlayerRanks = getRanksData().getList("players." + uuid + ".ranks");
        if (rankExists(inputRank)) {
            if (!currentPlayerRanks.contains(inputRank)) {
                currentPlayerRanks.add(inputRank);
                getRanksData().set("players." + uuid + ".ranks", currentPlayerRanks);
                saveRanksData();
            }
        }
    }
}
