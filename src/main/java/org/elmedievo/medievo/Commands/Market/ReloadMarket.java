package org.elmedievo.medievo.Commands.Market;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.elmedievo.medievo.Medievo;

import static org.elmedievo.medievo.Commands.Market.Methods.CreateMarketData.reloadMarketData;
import static org.elmedievo.medievo.util.Generic.*;

public class ReloadMarket implements CommandExecutor {
    private final Medievo plugin;

    private ReloadMarket(Medievo instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("reloadmarket") && sender.hasPermission("medievo.reloadmarket")) {
            if (args.length == 0) {
                reloadMarketData();
                sender.sendMessage(RELOADED_CONFIG);
            } else {
                sender.sendMessage(TOO_MANY_ARGS);
            }
        } else {
            sender.sendMessage(NO_PERMISSION);
        }
        return true;
    }

    public static void registerReloadMarketCommand() {
        Medievo.instance.getCommand("reloadmarket").setExecutor(new ReloadMarket(Medievo.instance));
    }
}
