package com.bgmbox.medievo.Commands.Ranks;

import com.bgmbox.medievo.Medievo;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class rank implements CommandExecutor {

    private final Medievo plugin;

    private rank(Medievo instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("rank")) {

        }
        return true;
    }
}
