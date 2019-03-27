package com.bgmbox.medievo.util;

import static com.bgmbox.medievo.EventHandlers.PlayerJoin.registerPlayerJoinEvent;
import static com.bgmbox.medievo.EventHandlers.playerChat.registerPlayerChatEvent;

public class EventRegistry {
    public static void registerEvents() {
        registerPlayerJoinEvent();
        registerPlayerChatEvent();
    }
}
