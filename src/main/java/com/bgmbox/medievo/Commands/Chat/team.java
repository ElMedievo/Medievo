package com.bgmbox.medievo.Commands.Chat;

import com.bgmbox.medievo.Medievo;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

import static com.bgmbox.medievo.Commands.Chat.Methods.SendClanMessage.sendMessageInClanChat;
import static com.bgmbox.medievo.Database.Getters.PlayerClanGetter.getPlayerClan;
import static com.bgmbox.medievo.Queues.Methods.ChatQueue.clanChatEnabled;
import static com.bgmbox.medievo.Queues.Methods.ChatQueue.setPlayerChatMode;
import static com.bgmbox.medievo.util.Generic.ALREADY_IN_CLAN_CHAT;
import static com.bgmbox.medievo.util.Generic.NOT_IN_A_CLAN;
import static com.bgmbox.medievo.util.Generic.NO_CONSOLE;
import static com.bgmbox.medievo.util.Methods.ConjoinCommandArgs.buildMessageFromCommandArgs;

public class team implements CommandExecutor {

    private final Medievo plugin;

    private team(Medievo instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("team")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (!Objects.requireNonNull(getPlayerClan(player.getUniqueId())).equals("none")) {
                    if (args.length == 0) {
                        if (!clanChatEnabled(player)) {
                            setPlayerChatMode(player, "clan");
                        } else {
                            sender.sendMessage(ALREADY_IN_CLAN_CHAT);
                        }
                    } else {
                        String msg = buildMessageFromCommandArgs(args, 0);
                        sendMessageInClanChat(player, msg);
                    }
                } else {
                    sender.sendMessage(NOT_IN_A_CLAN);
                }
            } else {
                sender.sendMessage(NO_CONSOLE);
            }
        }
        return true;
    }

    public static void registerTeamCommand() {
        Medievo.instance.getCommand("team").setExecutor(new team(Medievo.instance));
    }
}
