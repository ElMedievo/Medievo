package org.elmedievo.medievo.Ranks.Methods;

import org.bukkit.entity.Player;
import org.elmedievo.medievo.Ranks.CreateRanksData;

import java.util.List;

public class rankAdd {

    public static void addRank(Player player, String inputRank) {
        String uuid = player.getUniqueId().toString();
        List currentPlayerRanks = CreateRanksData.getRanksData().getList("players." + uuid + ".ranks");
        if (checkRankExistence.rankExists(inputRank)) {
            if (!currentPlayerRanks.contains(inputRank)) {
                currentPlayerRanks.add(inputRank);
                CreateRanksData.getRanksData().set("players." + uuid + ".ranks", currentPlayerRanks);
                CreateRanksData.saveRanksData();
            }
        }
    }
}
