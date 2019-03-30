package com.bgmbox.medievo.util;

import static com.bgmbox.medievo.Commands.Clans.clan.registerClanCommand;
import static com.bgmbox.medievo.Commands.Coords.registerCoordsCommand;
import static com.bgmbox.medievo.Commands.Ranks.rank.registerRankCommand;

public class CommandRegistry {
    public static void registerCommands() {
            registerCoordsCommand();
            registerRankCommand();
            registerClanCommand();
    }
}
