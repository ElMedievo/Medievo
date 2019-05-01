package org.elmedievo.medievo.Commands.TabComplete;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.elmedievo.medievo.Medievo;

import java.util.ArrayList;
import java.util.List;

import static org.elmedievo.medievo.Commands.TabComplete.Resources.ArgumentLists.ClanCommandArguments.clanArguments0;
import static org.elmedievo.medievo.Commands.TabComplete.Resources.ArgumentLists.ClanCommandArguments.clanArguments1;

public class ClanTabComplete implements TabCompleter {

    private final Medievo plugin;

    public ClanTabComplete(Medievo instance) {
        plugin = instance;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (command.getName().equalsIgnoreCase("clan")) {
            if (args.length == 1) {
                ArrayList<String> argumentOptions = new ArrayList<>();
                if (!args[0].equals("")) {
                    for (String instance : clanArguments0) {
                        if (instance.startsWith(args[0])) {
                            argumentOptions.add(instance);
                        }
                    }
                } else {
                    argumentOptions.addAll(clanArguments0);
                }
                return argumentOptions;
            } else if (args.length == 2) {
                ArrayList<String> argumentOptions = new ArrayList<>();
                if (args[0].equals("withdraw")) {
                    argumentOptions.addAll(clanArguments1);
                } else if (args[0].equalsIgnoreCase("invite") || args[0].equalsIgnoreCase("remove")) {
                    for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                        argumentOptions.add(player.getName());
                    }
                }
                return argumentOptions;
            }
        }
        return null;
    }
}
