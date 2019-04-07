package org.elmedievo.medievo.Commands.Chat;

import org.elmedievo.medievo.Medievo;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.elmedievo.medievo.Commands.Chat.Methods.SendAdminMessage;
import org.elmedievo.medievo.Queues.Methods.ChatQueue;

import static org.elmedievo.medievo.util.Generic.ALREADY_IN_ADMIN_CHAT;
import static org.elmedievo.medievo.util.Generic.NO_CONSOLE;
import static org.elmedievo.medievo.util.Generic.NO_PERMISSION;
import static org.elmedievo.medievo.util.Methods.ConjoinCommandArgs.buildMessageFromCommandArgs;

public class admin implements CommandExecutor {

    private final Medievo plugin;

    private admin(Medievo instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("admin") && sender.hasPermission("medievo.chat.admin")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (args.length == 0) {
                    if (!ChatQueue.adminChatEnabled(player)) {
                        ChatQueue.setPlayerChatMode(player, "admin");
                    } else {
                        sender.sendMessage(ALREADY_IN_ADMIN_CHAT);
                    }
                } else {
                    String msg = buildMessageFromCommandArgs(args, 0);
                    SendAdminMessage.sendMessageInAdminChat(player, msg);
                }
            } else {
                sender.sendMessage(NO_CONSOLE);
            }
        } else {
            sender.sendMessage(NO_PERMISSION);
        }
        return true;
    }

    public static void registerAdminCommand() {
        Medievo.instance.getCommand("admin").setExecutor(new admin(Medievo.instance));
    }
}
