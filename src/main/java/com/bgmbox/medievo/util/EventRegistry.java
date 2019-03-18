package com.bgmbox.medievo.util;

import static com.bgmbox.medievo.EventHandlers.PlayerJoin.registerPlayerJoinEvent;

public class EventRegistry {
    public static void registerEvents() {
        registerPlayerJoinEvent();
    }
}
