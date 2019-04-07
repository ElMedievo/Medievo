package org.elmedievo.medievo.util;

import org.elmedievo.medievo.EventHandlers.PlayerChat;
import org.elmedievo.medievo.EventHandlers.PlayerJoin;
import org.elmedievo.medievo.EventHandlers.PlayerLeave;

public class EventRegistry {
    public static void registerEvents() {
        PlayerJoin.registerPlayerJoinEvent();
        PlayerChat.registerPlayerChatEvent();
        PlayerLeave.registerPlayerDisconnectEvent();
    }
}
