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

import static org.elmedievo.medievo.Commands.TabComplete.Resources.ArgumentLists.ClanCommandArguments.clanArguments0;
import static org.elmedievo.medievo.Commands.TabComplete.Resources.ArgumentLists.ClanCommandArguments.clanArguments1;
import static org.elmedievo.medievo.Database.Getters.ClansToListGetter.getClansList;

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
                    for (String material : clanArguments1) {
                        if (material.startsWith(args[1])) {
                            argumentOptions.add(material);
                        }
                    }
                } else if (args[0].equalsIgnoreCase("invite") || args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("disband")) {
                    for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                        if (player.getName().startsWith(args[1])) {
                            argumentOptions.add(player.getName());
                        }
                    }
                } else if (args[0].equalsIgnoreCase("info")) {
                    List<String> clans = getClansList();
                    for (String clan : Objects.requireNonNull(clans)) {
                        if (clan.startsWith(args[1])) {
                            argumentOptions.add(clan);
                        }
                    }
                }
                return argumentOptions;
            }
        }
        return null;
    }
}
