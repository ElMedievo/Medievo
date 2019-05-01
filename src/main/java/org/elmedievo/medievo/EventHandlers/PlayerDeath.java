package org.elmedievo.medievo.EventHandlers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.elmedievo.medievo.Medievo;

import java.util.UUID;

import static org.elmedievo.medievo.Database.Setters.AddDeathToPlayer.addDeathToPlayer;
import static org.elmedievo.medievo.Database.Setters.AddKillToPlayer.addKillToPlayer;
import static org.elmedievo.medievo.Database.Setters.AddKilledToPlayer.addKilledToPlayer;

public class PlayerDeath implements Listener {

    private final Medievo plugin;

    private PlayerDeath(Medievo instance) {
        plugin = instance;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public static void onPlayerDeath(PlayerDeathEvent event) {
        Entity killer = event.getEntity().getKiller();
        Player deadPlayer = event.getEntity();
        UUID deadPlayerUUID = deadPlayer.getUniqueId();

        addDeathToPlayer(deadPlayerUUID);

        if (killer != null && killer != deadPlayer) {
            UUID killerUUID = killer.getUniqueId();
            addKillToPlayer(killerUUID);
            addKilledToPlayer(deadPlayerUUID);
        }
    }

    public static void registerPlayerDeathEvent() {
        Bukkit.getPluginManager().registerEvents(new PlayerDeath(Medievo.instance), Medievo.instance);
    }
}
