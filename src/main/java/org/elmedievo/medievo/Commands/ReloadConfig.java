package org.elmedievo.medievo.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.elmedievo.medievo.Medievo;

import static org.elmedievo.medievo.Configuration.ReloadConfig.reloadConfig;
import static org.elmedievo.medievo.util.Generic.NO_PERMISSION;
import static org.elmedievo.medievo.util.Generic.RELOADED_CONFIG;

public class ReloadConfig implements CommandExecutor {

    private final Medievo plugin;

    private ReloadConfig(Medievo instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("reloadconfig") && sender.hasPermission("medievo.reloadconfig")) {
            reloadConfig();
            sender.sendMessage(RELOADED_CONFIG);
        } else {
            sender.sendMessage(NO_PERMISSION);
        }
        return true;
    }

    public static void registerReloadConfigCommand() {
        Medievo.instance.getCommand("reloadconfig").setExecutor(new ReloadConfig(Medievo.instance));
    }
}
