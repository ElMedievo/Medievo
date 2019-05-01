package org.elmedievo.medievo.Commands.Clans;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.elmedievo.medievo.Medievo;

import static org.elmedievo.medievo.util.Generic.NO_CONSOLE;
import static org.elmedievo.medievo.util.Generic.TOO_MANY_ARGS;

public class materials implements CommandExecutor {

    private final Medievo plugin;

    private materials(Medievo instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("materials") && sender instanceof Player) {
            if (args.length == 0) {
                Player player = (Player) sender;
                player.sendMessage(ChatColor.RED + "########## " + ChatColor.AQUA + ChatColor.BOLD + " VALUABLE MATERIALS " + ChatColor.RED + "##########" + "\n"
                        + ChatColor.GREEN + "» " + ChatColor.YELLOW + "gold_ingot" + "\n"
                        + ChatColor.GREEN + "» " + ChatColor.YELLOW + "gold_block" + "\n"
                        + ChatColor.GREEN + "» " + ChatColor.YELLOW + "gold_nugget" + "\n"
                        + ChatColor.GREEN + "» " + ChatColor.YELLOW + "golden_carrot" + "\n"
                        + ChatColor.GREEN + "» " + ChatColor.YELLOW + "gold_barding (Horse Armor)" + "\n"
                        + ChatColor.GREEN + "» " + ChatColor.YELLOW + "gold_sword" + "\n"
                        + ChatColor.GREEN + "» " + ChatColor.YELLOW + "gold_pickaxe" + "\n"
                        + ChatColor.GREEN + "» " + ChatColor.YELLOW + "gold_axe" + "\n"
                        + ChatColor.GREEN + "» " + ChatColor.YELLOW + "gold_hoe" + "\n"
                        + ChatColor.GREEN + "» " + ChatColor.YELLOW + "gold_helmet" + "\n"
                        + ChatColor.GREEN + "» " + ChatColor.YELLOW + "gold_chestplate" + "\n"
                        + ChatColor.GREEN + "» " + ChatColor.YELLOW + "gold_leggings" + "\n"
                        + ChatColor.GREEN + "» " + ChatColor.YELLOW + "gold_boots" + "\n"
                        + ChatColor.GREEN + "» " + ChatColor.YELLOW + "golden_apple" + "\n"
                        + ChatColor.RED + "##########################################"
                );
            } else {
                sender.sendMessage(TOO_MANY_ARGS);
            }
        } else {
         sender.sendMessage(NO_CONSOLE);
        }
        return true;
    }

    public static void registerMaterialsCommand() {
        Medievo.instance.getCommand("materials").setExecutor(new materials(Medievo.instance));
    }
}
