package com.bgmbox.medievo.EventHandlers;

import com.bgmbox.medievo.Medievo;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

import static com.bgmbox.medievo.Database.Entires.PlayerEntry.registerPlayerInRanksDatabase;
import static com.bgmbox.medievo.Database.Entires.PlayerEntry.registerPlayerInSQLDatabase;
import static com.bgmbox.medievo.Ranks.Methods.rankAdd.addRank;

public class PlayerJoin implements Listener {

    private final Medievo plugin;

    private PlayerJoin(Medievo instance) {
        plugin = instance;
    }

    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID player_uuid = player.getUniqueId();
        String player_name = player.getName();
        int gold = 0;

        registerPlayerInSQLDatabase(player_uuid, player_name, gold);
        registerPlayerInRanksDatabase(player_uuid, player_name);
    }

    public static void registerPlayerJoinEvent() {
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(Medievo.instance), Medievo.instance);
    }
}
