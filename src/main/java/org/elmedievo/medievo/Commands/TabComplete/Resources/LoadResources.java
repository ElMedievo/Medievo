package org.elmedievo.medievo.Commands.TabComplete.Resources;

import static org.elmedievo.medievo.Commands.TabComplete.Resources.ArgumentLists.ClanCommandArguments.loadClanCommandArguments;
import static org.elmedievo.medievo.Commands.TabComplete.Resources.ArgumentLists.RankCommandArguments.LoadRankCommandArguments;

public class LoadResources {
    public static void loadTabCompleteArguments() {
        loadClanCommandArguments();
        LoadRankCommandArguments();
    }
}
