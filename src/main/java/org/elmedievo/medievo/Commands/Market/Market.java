package org.elmedievo.medievo.Commands.Market;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.elmedievo.medievo.Medievo;

import static org.elmedievo.medievo.Commands.Market.Methods.GUI.getMarketGUI;
import static org.elmedievo.medievo.util.Generic.NO_CONSOLE;
import static org.elmedievo.medievo.util.Generic.TOO_MANY_ARGS;

public class Market implements CommandExecutor {

    private final Medievo plugin;

    private Market(Medievo instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("market") && sender instanceof Player) {
            if (args.length == 0) {
                Player player = (Player) sender;
                Inventory marketGUI = getMarketGUI();
                player.openInventory(marketGUI);
            } else {
                sender.sendMessage(TOO_MANY_ARGS);
            }
        } else {
            sender.sendMessage(NO_CONSOLE);
        }
        return true;
    }

    public static void registerMarketCommand() {
        Medievo.instance.getCommand("market").setExecutor(new Market(Medievo.instance));
    }
}
