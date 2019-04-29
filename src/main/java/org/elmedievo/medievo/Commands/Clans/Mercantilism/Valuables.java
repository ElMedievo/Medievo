package org.elmedievo.medievo.Commands.Clans.Mercantilism;

import org.bukkit.Material;

public class Valuables {

    public static String CURRENCY_SYMBOL = "£";
    public static String CURRENCY_NAME = "Alfonso";
    public static String CURRENCY_NAME_PLURAL = "Alfonsos";

    public static int valueInMarket(Material material, Boolean forDeposit) {
        int GOLD_VALUE = (forDeposit) ? 45 : -45;
        int STICK_VALUE = 10;
        int APPLE_VALUE = 15;

        switch (material) {
            case GOLD_INGOT:
                return GOLD_VALUE;
            case GOLD_BLOCK:
                return 9 * GOLD_VALUE;
            case GOLD_SWORD:
                return 2 * GOLD_VALUE + STICK_VALUE;
            case GOLD_SPADE:
                return GOLD_VALUE + 2 * STICK_VALUE;
            case GOLD_PICKAXE:
                return 3 * GOLD_VALUE + 2 * STICK_VALUE;
            case GOLD_AXE:
                return 3 * GOLD_VALUE + 2 * STICK_VALUE;
            case GOLD_HOE:
                return 2 * GOLD_VALUE + 2 * STICK_VALUE;
            case GOLD_HELMET:
                return 5 * GOLD_VALUE;
            case GOLD_CHESTPLATE:
                return 8 * GOLD_VALUE;
            case GOLD_LEGGINGS:
                return 7 * GOLD_VALUE;
            case GOLD_BOOTS:
                return 4 * GOLD_VALUE;
            case GOLDEN_APPLE:
                return 8 * GOLD_VALUE + APPLE_VALUE;
            case GOLD_NUGGET:
                return GOLD_VALUE / 9;
            case GOLDEN_CARROT:
                return 8 * (GOLD_VALUE / 9);
            case GOLD_BARDING:
                return 20 * GOLD_VALUE;
        }
        return 0;
    }
}
