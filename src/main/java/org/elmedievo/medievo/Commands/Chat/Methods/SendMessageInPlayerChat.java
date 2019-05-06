package org.elmedievo.medievo.Commands.Chat.Methods;

import org.bukkit.entity.Player;

import static org.elmedievo.medievo.Commands.Chat.Methods.SendAdminMessage.sendMessageInAdminChat;
import static org.elmedievo.medievo.Commands.Chat.Methods.SendClanMessage.sendMessageInClanChat;
import static org.elmedievo.medievo.Commands.Chat.Methods.SendGlobalMessage.sendMessageInGlobal;
import static org.elmedievo.medievo.Queues.Methods.ChatQueue.getPlayerChatMode;

public class SendMessageInPlayerChat {
    public static void SendMessageToCorrespondingChat(Player player, String msg) {
        String chatMode = getPlayerChatMode(player.getName());
        switch (chatMode) {
            case "admin":
                sendMessageInAdminChat(player, msg);
                break;
            case "global":
                sendMessageInGlobal(player, msg);
                break;
            case "clan":
                sendMessageInClanChat(player, msg);
        }
    }
}
