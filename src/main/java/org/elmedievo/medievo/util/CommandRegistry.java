package org.elmedievo.medievo.util;

import org.elmedievo.medievo.Commands.Chat.admin;
import org.elmedievo.medievo.Commands.Chat.global;
import org.elmedievo.medievo.Commands.Chat.team;
import org.elmedievo.medievo.Commands.Clans.accept;
import org.elmedievo.medievo.Commands.Clans.clan;
import org.elmedievo.medievo.Commands.Clans.decline;
import org.elmedievo.medievo.Commands.Coords;
import org.elmedievo.medievo.Commands.Ranks.rank;

import static org.elmedievo.medievo.Commands.Clans.clans.registerClansCommand;
import static org.elmedievo.medievo.Commands.Clans.materials.registerMaterialsCommand;
import static org.elmedievo.medievo.Commands.ReloadConfig.registerReloadConfigCommand;
import static org.elmedievo.medievo.Commands.Stats.registerStatsCommand;

public class CommandRegistry {
    public static void registerCommands() {
            Coords.registerCoordsCommand();
            rank.registerRankCommand();
            clan.registerClanCommand();
            accept.registerAcceptCommand();
            decline.registerDeclineCommand();
            registerReloadConfigCommand();
            registerClansCommand();
            registerStatsCommand();
            registerMaterialsCommand(); // Temp command until tab completion implementation.

            //--- Chat Commands ---//
            admin.registerAdminCommand();
            global.registerGlobalCommand();
            team.registerTeamCommand();
            //---              ---//
    }
}
