package org.elmedievo.medievo.Commands.TabComplete;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.elmedievo.medievo.Medievo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.elmedievo.medievo.Commands.TabComplete.Resources.ArgumentLists.RankCommandArguments.rankArguments0;
import static org.elmedievo.medievo.Ranks.Methods.checkRankExistence.getRanksList;

public class RanksTabComplete implements TabCompleter {

    private final Medievo plugin;

    public RanksTabComplete(Medievo instance) {
        plugin = instance;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (command.getName().equalsIgnoreCase("rank")) {
            if (args.length == 1) {
                ArrayList<String> argumentOptions = new ArrayList<>();
                if (!args[0].equals("")) {
                    for (String instance : rankArguments0) {
                        if (instance.startsWith(args[0])) {
                            argumentOptions.add(instance);
                        }
                    }
                } else {
                    argumentOptions.addAll(rankArguments0);
                }
                return argumentOptions;
            } else if (args.length == 2) {
                ArrayList<String> argumentoptions = new ArrayList<>();
                for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                    if (player.getName().startsWith(args[1])) {
                        argumentoptions.add(player.getName());
                    }
                }
                return argumentoptions;
            } else if (args.length == 3) {
                ArrayList<String> argumentOptions = new ArrayList<>();
                if (args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("remove")) {
                    for (String instance : Objects.requireNonNull(getRanksList())) {
                        if (instance.startsWith(args[2])) {
                            argumentOptions.add(instance);
                        }
                    }
                }
                return argumentOptions;
            }
        }
        return null;
    }
}
