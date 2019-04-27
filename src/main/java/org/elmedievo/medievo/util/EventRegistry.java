package org.elmedievo.medievo.util;

import static org.elmedievo.medievo.EventHandlers.PlayerChat.registerPlayerChatEvent;
import static org.elmedievo.medievo.EventHandlers.PlayerDeath.registerPlayerDeathEvent;
import static org.elmedievo.medievo.EventHandlers.PlayerJoin.registerPlayerJoinEvent;
import static org.elmedievo.medievo.EventHandlers.PlayerLeave.registerPlayerDisconnectEvent;
import static org.elmedievo.medievo.EventHandlers.WeatherChange.registerWeatherChangeEvent;

public class EventRegistry {
    public static void registerEvents() {
        registerPlayerJoinEvent();
        registerPlayerChatEvent();
        registerPlayerDisconnectEvent();
        registerWeatherChangeEvent();
        registerPlayerDeathEvent();
    }
}
