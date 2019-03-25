package com.bgmbox.medievo.Commands;

import com.bgmbox.medievo.Medievo;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import static com.bgmbox.medievo.util.Generic.NO_CONSOLE;
import static com.bgmbox.medievo.util.Methods.ConjoinCommandArgs.buildMessageFromCommandArgs;

public class Coords implements CommandExecutor {

    private final Medievo plugin;

    private Coords(Medievo instance) {
        plugin = instance;
    }

    private void broadcastPlayerCoords(Player player, @Nullable String message) {
        Location player_location = player.getLocation();
        int x = player_location.getBlockX();
        int y = player_location.getBlockY();
        int z = player_location.getBlockZ();
        Bukkit.broadcastMessage(
                ChatColor.YELLOW + "" + ChatColor.BOLD + player.getDisplayName() + ChatColor.GRAY + ": " + ChatColor.WHITE + message + "\n"
                + ChatColor.YELLOW + "X: " + ChatColor.GRAY + x + "\n"
                + ChatColor.YELLOW + "Y: " + ChatColor.GRAY + y + "\n"
                + ChatColor.YELLOW + "Z: " + ChatColor.GRAY + z
        );
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("coordinates") && sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                broadcastPlayerCoords(player, "");
            } else {
                String msg = buildMessageFromCommandArgs(args, 0);
                broadcastPlayerCoords(player, msg);
            }
        } else {
            sender.sendMessage(NO_CONSOLE);
        }
        return true;
    }

    public static void registerCoordsCommand() {
        Medievo.instance.getCommand("coordinates").setExecutor(new Coords(Medievo.instance));
    }
}
