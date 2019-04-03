package com.bgmbox.medievo.Commands.Chat;

import com.bgmbox.medievo.Medievo;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.bgmbox.medievo.Commands.Chat.Methods.SendGlobalMessage.sendMessageInGlobal;
import static com.bgmbox.medievo.Queues.Methods.ChatQueue.globalChatEnabled;
import static com.bgmbox.medievo.Queues.Methods.ChatQueue.setPlayerChatMode;
import static com.bgmbox.medievo.util.Generic.ALREADY_IN_GLOBAL_CHAT;
import static com.bgmbox.medievo.util.Generic.NO_CONSOLE;
import static com.bgmbox.medievo.util.Methods.ConjoinCommandArgs.buildMessageFromCommandArgs;

public class global implements CommandExecutor {

    private final Medievo plugin;

    private global(Medievo instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("global")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (args.length == 0) {
                    if (!globalChatEnabled(player)) {
                        setPlayerChatMode(player, "global");
                    } else {
                        sender.sendMessage(ALREADY_IN_GLOBAL_CHAT);
                    }
                } else {
                    String msg = buildMessageFromCommandArgs(args, 0);
                    sendMessageInGlobal(player, msg);
                }
            } else {
                sender.sendMessage(NO_CONSOLE);
            }
        }
        return true;
    }

    public static void registerGlobalCommand() {
        Medievo.instance.getCommand("global").setExecutor(new global(Medievo.instance));
    }
}
