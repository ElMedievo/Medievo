package com.bgmbox.medievo.util;

import static com.bgmbox.medievo.EventHandlers.PlayerChat.registerPlayerChatEvent;
import static com.bgmbox.medievo.EventHandlers.PlayerJoin.registerPlayerJoinEvent;
import static com.bgmbox.medievo.EventHandlers.PlayerLeave.registerPlayerDisconnectEvent;

public class EventRegistry {
    public static void registerEvents() {
        registerPlayerJoinEvent();
        registerPlayerChatEvent();
        registerPlayerDisconnectEvent();
    }
}
