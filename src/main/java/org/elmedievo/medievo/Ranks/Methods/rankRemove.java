package org.elmedievo.medievo.Ranks.Methods;

import org.bukkit.entity.Player;

import java.util.List;

import static org.elmedievo.medievo.Ranks.CreateRanksData.getRanksData;
import static org.elmedievo.medievo.Ranks.CreateRanksData.saveRanksData;

public class rankRemove {

    public static void removeRank(Player player, String inputRank) {
        String uuid = player.getUniqueId().toString();
        List currentPlayerRanks = getRanksData().getList("players." + uuid + ".ranks");
        if (currentPlayerRanks.contains(inputRank)) {
            currentPlayerRanks.remove(inputRank);
            getRanksData().set("players." + uuid + ".ranks", currentPlayerRanks);
            saveRanksData();
        }
    }
}
