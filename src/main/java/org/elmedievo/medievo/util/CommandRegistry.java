package org.elmedievo.medievo.util;

import static org.elmedievo.medievo.Commands.Chat.admin.registerAdminCommand;
import static org.elmedievo.medievo.Commands.Chat.global.registerGlobalCommand;
import static org.elmedievo.medievo.Commands.Chat.team.registerTeamCommand;
import static org.elmedievo.medievo.Commands.Clans.accept.registerAcceptCommand;
import static org.elmedievo.medievo.Commands.Clans.clan.registerClanCommand;
import static org.elmedievo.medievo.Commands.Clans.clans.registerClansCommand;
import static org.elmedievo.medievo.Commands.Clans.decline.registerDeclineCommand;
import static org.elmedievo.medievo.Commands.Clans.materials.registerMaterialsCommand;
import static org.elmedievo.medievo.Commands.Coords.registerCoordsCommand;
import static org.elmedievo.medievo.Commands.Market.Market.registerMarketCommand;
import static org.elmedievo.medievo.Commands.Market.ReloadMarket.registerReloadMarketCommand;
import static org.elmedievo.medievo.Commands.Ranks.rank.registerRankCommand;
import static org.elmedievo.medievo.Commands.ReloadConfig.registerReloadConfigCommand;
import static org.elmedievo.medievo.Commands.Stats.registerStatsCommand;

public class CommandRegistry {
    public static void registerCommands() {
            registerCoordsCommand();
            registerRankCommand();
            registerClanCommand();
            registerAcceptCommand();
            registerDeclineCommand();
            registerReloadConfigCommand();
            registerClansCommand();
            registerStatsCommand();
            registerMaterialsCommand();
            registerMarketCommand();
            registerReloadMarketCommand();

            //--- Chat Commands ---//
            registerAdminCommand();
            registerGlobalCommand();
            registerTeamCommand();
            //---              ---//
    }
}
