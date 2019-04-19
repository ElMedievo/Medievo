package org.elmedievo.medievo.EventHandlers;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.elmedievo.medievo.Medievo;

public class WeatherChange implements Listener {
    private final Medievo plugin;

    private WeatherChange(Medievo instance) {
        plugin = instance;
    }

    public static void onWeatherChange(WeatherChangeEvent event) {
        event.setCancelled(true);
    }

    public static void registerWeatherChangeEvent() {
        Bukkit.getPluginManager().registerEvents(new WeatherChange(Medievo.instance), Medievo.instance);
    }
}
