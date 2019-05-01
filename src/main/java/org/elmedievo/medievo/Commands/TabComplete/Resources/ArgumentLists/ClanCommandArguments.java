package org.elmedievo.medievo.Commands.TabComplete.Resources.ArgumentLists;

import java.util.Arrays;
import java.util.List;

public class ClanCommandArguments {
    public static List<String> clanArguments0;
    public static List<String> clanArguments1;

    public static void loadClanCommandArguments() {
        String[] args0 = {
                "create",
                "deposit",
                "disband",
                "help",
                "info",
                "invite",
                "leave",
                "list",
                "remove",
                "withdraw",
        };
        String[] args1 = {
                "gold_ingot",
                "gold_block",
                "gold_sword",
                "gold_sword",
                "gold_spade",
                "gold_pickaxe",
                "gold_axe",
                "gold_hoe",
                "gold_helmet",
                "gold_chestplate",
                "gold_leggings",
                "gold_boots",
                "golden_apple",
                "gold_nugget",
                "golden_carrot",
                "gold_barding"
        };
        clanArguments0 = Arrays.asList(args0);
        clanArguments1 = Arrays.asList(args1);
    }
}

