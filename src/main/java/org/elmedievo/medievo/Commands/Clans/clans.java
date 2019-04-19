package org.elmedievo.medievo.Commands.Clans;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.elmedievo.medievo.Medievo;

import static org.elmedievo.medievo.Commands.Clans.Methods.displayClansList.sendClansListToPlayer;
import static org.elmedievo.medievo.util.Generic.NO_CONSOLE;

public class clans implements CommandExecutor {

    private final Medievo plugin;

    private clans(Medievo instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("clans") && sender instanceof Player) {
            Player player = (Player) sender;
            sendClansListToPlayer(player);
        } else {
            sender.sendMessage(NO_CONSOLE);
        }
        return true;
    }

    public static void registerClansCommand() {
        Medievo.instance.getCommand("clans").setExecutor(new clans(Medievo.instance));
    }
}
