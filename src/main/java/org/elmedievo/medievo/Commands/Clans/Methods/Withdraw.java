package org.elmedievo.medievo.Commands.Clans.Methods;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Objects;

import static org.elmedievo.medievo.Commands.Clans.Mercantilism.Valuables.CURRENCY_NAME_PLURAL;
import static org.elmedievo.medievo.Commands.Clans.Mercantilism.Valuables.CURRENCY_SYMBOL;
import static org.elmedievo.medievo.Commands.Clans.Mercantilism.Valuables.valueInMarket;
import static org.elmedievo.medievo.Database.Getters.ClanMaterialsGetter.getClanMaterialAmount;
import static org.elmedievo.medievo.Database.Getters.ClanMembersToList.getClanMembers;
import static org.elmedievo.medievo.Database.Getters.PlayerClanGetter.getPlayerClan;
import static org.elmedievo.medievo.Database.Setters.AddAlfonsosToClan.addAlfonsosToClan;
import static org.elmedievo.medievo.Database.Setters.AddGoldToClan.addGoldToClan;
import static org.elmedievo.medievo.util.Generic.*;
import static org.elmedievo.medievo.util.Methods.PlayerIsOnline.playerIsOnline;

public class Withdraw {
    public static void withdrawGoldFromClan(Player player, Material material, int amountInput) {
        String playerClan = getPlayerClan(player.getUniqueId());
        int alfonsos = valueInMarket(material, false);
        if (!Objects.requireNonNull(playerClan).equals("none")) {
            int materialAmount = getClanMaterialAmount(playerClan, material.toString().toLowerCase());
            if (materialAmount == 0) {
                player.sendMessage(INVALID_MATERIAL_COUNT);
                return;
            } else if (amountInput < materialAmount) {
                addAlfonsosToClan(playerClan, alfonsos * amountInput);
                addGoldToClan(playerClan, material.toString().toLowerCase(), -amountInput);
                List<String> clanMembers = getClanMembers(playerClan);
                ItemStack gold = new ItemStack(material, amountInput);
                player.getInventory().addItem(gold);
                Objects.requireNonNull(clanMembers).forEach(member -> {
                    if (playerIsOnline(member)) {
                        Player member_player = Bukkit.getServer().getPlayer(member);
                        member_player.sendMessage(member_player.getDisplayName()  + ChatColor.AQUA + " » " + WITHDRAW_COMPLETE + ChatColor.AQUA + " » " + ChatColor.WHITE + ChatColor.UNDERLINE + ChatColor.ITALIC + CURRENCY_SYMBOL + alfonsos * amountInput + " " + CURRENCY_NAME_PLURAL);
                    }
                });
            } else {
                player.sendMessage(NOT_ENOUGH_GOLD);
            }
        } else {
            player.sendMessage(NOT_IN_A_CLAN);
        }
    }
}
