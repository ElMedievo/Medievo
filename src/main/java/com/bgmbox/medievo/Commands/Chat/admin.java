package com.bgmbox.medievo.Commands.Chat;

import com.bgmbox.medievo.Medievo;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.bgmbox.medievo.Commands.Chat.Methods.SendAdminMessage.sendMessageInAdminChat;
import static com.bgmbox.medievo.Queues.Methods.ChatQueue.adminChatEnabled;
import static com.bgmbox.medievo.Queues.Methods.ChatQueue.setPlayerChatMode;
import static com.bgmbox.medievo.util.Generic.ALREADY_IN_ADMIN_CHAT;
import static com.bgmbox.medievo.util.Generic.NO_CONSOLE;
import static com.bgmbox.medievo.util.Generic.NO_PERMISSION;
import static com.bgmbox.medievo.util.Methods.ConjoinCommandArgs.buildMessageFromCommandArgs;

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
                    if (!adminChatEnabled(player)) {
                        setPlayerChatMode(player, "admin");
                    } else {
                        sender.sendMessage(ALREADY_IN_ADMIN_CHAT);
                    }
                } else {
                    String msg = buildMessageFromCommandArgs(args, 0);
                    sendMessageInAdminChat(player, msg);
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
