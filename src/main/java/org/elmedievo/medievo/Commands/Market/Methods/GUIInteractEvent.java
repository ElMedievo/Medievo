package org.elmedievo.medievo.Commands.Market.Methods;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.elmedievo.medievo.Medievo;

import java.util.UUID;

import static org.elmedievo.medievo.Commands.Clans.Mercantilism.Valuables.CURRENCY_SYMBOL;
import static org.elmedievo.medievo.Commands.Market.Methods.CreateMarketData.getMarketData;
import static org.elmedievo.medievo.Commands.Market.Methods.GUI.buildItemStackFromMarketData;
import static org.elmedievo.medievo.Database.Getters.ClanAlfonsosGetter.getClanAlfonsos;
import static org.elmedievo.medievo.Database.Getters.ClanLeaderGetter.getClanLeaderUUID;
import static org.elmedievo.medievo.Database.Getters.ClanMaterialsGetter.getClanMaterialAmount;
import static org.elmedievo.medievo.Database.Getters.PlayerClanGetter.getPlayerClan;
import static org.elmedievo.medievo.Database.Setters.AddAlfonsosToClan.addAlfonsosToClan;
import static org.elmedievo.medievo.Database.Setters.AddGoldToClan.addGoldToClan;
import static org.elmedievo.medievo.util.Generic.NOT_ENOUGH_ALFONSOS;
import static org.elmedievo.medievo.util.Generic.NOT_ENOUGH_GOLD_INGOTS;
import static org.elmedievo.medievo.util.Generic.ONLY_LEADER_PURCHASABLE;

public class GUIInteractEvent implements Listener {
    private GUIInteractEvent(Medievo instance) {
        Medievo plugin = instance;
    }

    private static boolean canEffectuatePaymentToCentralBank(Player player, Float alfonsosInput) {
        UUID playerUUID = player.getUniqueId();
        String playerClan = getPlayerClan(playerUUID);
        String clanLeaderUUID = getClanLeaderUUID(playerClan);
        Float playerClanAlfonsos = getClanAlfonsos(playerClan);
        Integer playerClanGoldIngots = getClanMaterialAmount(playerClan, "GOLD_INGOT");

        if (playerUUID.toString().equals(clanLeaderUUID)) {
            if (playerClanAlfonsos >= alfonsosInput) {
                if (playerClanGoldIngots >= alfonsosInput) {
                    return true;
                } else {
                    player.sendMessage(NOT_ENOUGH_GOLD_INGOTS);
                }
            } else {
                player.sendMessage(NOT_ENOUGH_ALFONSOS);
            }
        } else {
            player.sendMessage(ONLY_LEADER_PURCHASABLE);
        }

        return false;
    }

    private static void effectuatePaymentToCentralBank(Player player, Float alfonsosInput) {
        UUID playerUUID = player.getUniqueId();
        String playerClan = getPlayerClan(playerUUID);
        Integer goldInput = Math.round(alfonsosInput);

        addAlfonsosToClan(playerClan, -alfonsosInput);
        addGoldToClan(playerClan, "GOLD_INGOT", -goldInput);

        addAlfonsosToClan("Medieval Bank", alfonsosInput);
        addGoldToClan("Medieval Bank", "GOLD_INGOT", goldInput);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public static void onInterfaceItemClick(InventoryClickEvent event) {
        Inventory eventInventory = event.getInventory();
        Player player = (Player) event.getWhoClicked();
        if (eventInventory.getName().equals(getMarketData().getString("interface.title"))) {
            event.setCancelled(true);
            if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR || !event.getCurrentItem().hasItemMeta()) {
                return;
            }
            getMarketData().getConfigurationSection("interface.stock").getKeys(false).forEach(node -> {
                String nameInstance = ChatColor.translateAlternateColorCodes('&', getMarketData().getString("interface.stock." + node + ".name"));
                if (event.getCurrentItem().getItemMeta().getDisplayName().equals(nameInstance)) {
                    Float price = Float.parseFloat(getMarketData().getString("interface.stock." + node + ".price"));
                    if (canEffectuatePaymentToCentralBank(player, price)) {
                        effectuatePaymentToCentralBank(player, price);
                        player.getInventory().addItem(buildItemStackFromMarketData(node));
                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,0);
                        player.sendMessage(ChatColor.GREEN + "Purchase Complete " + ChatColor.AQUA + "» " + ChatColor.WHITE + ChatColor.UNDERLINE + ChatColor.ITALIC + CURRENCY_SYMBOL + "-" + price + " Alfonsos" + ChatColor.RESET);
                    } else {
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BASS, 1,1);
                    }
                    player.closeInventory();
                }
            });
        }
    }

    public static void registerGUIInteractEvent() {
        Bukkit.getPluginManager().registerEvents(new GUIInteractEvent(Medievo.instance), Medievo.instance);
    }
}
