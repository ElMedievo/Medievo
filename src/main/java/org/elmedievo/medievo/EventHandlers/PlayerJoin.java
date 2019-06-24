package org.elmedievo.medievo.EventHandlers;

import org.bukkit.event.EventPriority;
import org.elmedievo.medievo.Medievo;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

import static org.elmedievo.medievo.Database.Entires.PlayerEntry.registerPlayerInRanksDatabase;
import static org.elmedievo.medievo.Database.Entires.PlayerEntry.registerPlayerInSQLDatabase;
import static org.elmedievo.medievo.Database.Setters.PlayerNameSetter.updatePlayerNameInSQL;
import static org.elmedievo.medievo.Queues.CreateQueues.chatQueue;
import static org.elmedievo.medievo.Ranks.DeliverRanks.deliverRanks;
import static org.elmedievo.medievo.util.Fixes.JOIN_MESSAGE_PREFIX;
import static org.elmedievo.medievo.util.Fixes.JOIN_MESSAGE_SUFFIX;

public class PlayerJoin implements Listener {

    private final Medievo plugin;

    private PlayerJoin(Medievo instance) {
        plugin = instance;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public static void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID player_uuid = player.getUniqueId();
        String player_name = player.getName();
        int gold = 0;

        registerPlayerInSQLDatabase(player_uuid, player_name, gold, "none");
        updatePlayerNameInSQL(player_uuid, player_name);
        registerPlayerInRanksDatabase(player_uuid, player_name);
        deliverRanks(player);

        event.setJoinMessage(JOIN_MESSAGE_PREFIX +  player.getDisplayName() + JOIN_MESSAGE_SUFFIX);
        chatQueue.putIfAbsent(player.getName(), "global");
    }

    public static void registerPlayerJoinEvent() {
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(Medievo.instance), Medievo.instance);
    }
}
