package org.elmedievo.medievo.Commands.Market.Methods;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.elmedievo.medievo.Medievo;

import java.util.ArrayList;
import java.util.List;

import static org.elmedievo.medievo.Commands.Clans.Mercantilism.Valuables.CURRENCY_SYMBOL;
import static org.elmedievo.medievo.Commands.Market.Methods.CreateMarketData.getMarketData;

public class GUI implements Listener {
    private GUI(Medievo instance) {
        Medievo plugin = instance;
    }

    private static String title = ChatColor.translateAlternateColorCodes('&', getMarketData().getString("interface.title"));
    private static Integer rows = 9 * getMarketData().getInt("interface.rows");

    public static Inventory getMarketGUI() {
        Inventory marketInterface = Bukkit.createInventory(null, rows, title);
        getMarketData().getConfigurationSection("interface.stock").getKeys(false).forEach(node -> {
            String currentNodePath = "interface.stock." + node + ".";
            Material material = Material.getMaterial(getMarketData().getString(currentNodePath + "material"));
            Short damage = Short.parseShort(getMarketData().getString(currentNodePath + "damage"));
            Integer amount = getMarketData().getInt(currentNodePath + "amount");
            String name = ChatColor.translateAlternateColorCodes('&', getMarketData().getString(currentNodePath + "name"));
            String lore = ChatColor.translateAlternateColorCodes('&', getMarketData().getString(currentNodePath + "lore"));
            boolean special = getMarketData().getBoolean(currentNodePath + "special");
            int position = getMarketData().getInt(currentNodePath + "position");
            float price = Float.parseFloat(getMarketData().getString(currentNodePath + "price"));
            ArrayList<String> instanceItemStackLore = new ArrayList<>();
            instanceItemStackLore.add(lore);
            instanceItemStackLore.add(ChatColor.GOLD + CURRENCY_SYMBOL + Float.toString(price) + ChatColor.RESET);

            ItemStack itemStackInstance = new ItemStack(material, amount, damage);
            ItemMeta itemStackInstanceMeta = itemStackInstance.getItemMeta();
            itemStackInstanceMeta.setDisplayName(name);
            if (special) {
                itemStackInstanceMeta.addEnchant(Enchantment.DURABILITY, 3, true);
            }
            if (getMarketData().isConfigurationSection("interface.stock." + node + ".enchantments")) {
                getMarketData().getConfigurationSection("interface.stock." + node + ".enchantments").getKeys(false).forEach(enchantment -> {
                    Enchantment enchantmentInstance = Enchantment.getByName(enchantment);
                    int level = getMarketData().getInt(currentNodePath + "enchantments." + enchantment + ".level");
                    itemStackInstanceMeta.addEnchant(enchantmentInstance, level, true);
                });
            }
            itemStackInstanceMeta.setLore(instanceItemStackLore);
            itemStackInstance.setItemMeta(itemStackInstanceMeta);
            marketInterface.setItem(position, itemStackInstance);
        });
        return marketInterface;
    }

    static ItemStack buildItemStackFromMarketData(String node) {
        Material purchasedItemMaterial = Material.getMaterial(getMarketData().getString("interface.stock." + node + ".material"));
        Integer purchasedItemAmount = getMarketData().getInt("interface.stock." + node + ".amount");
        Byte purchasedItemDamage = (byte) getMarketData().getInt("interface.stock." + node + ".damage");
        ItemStack purchasedItem = new ItemStack(purchasedItemMaterial, purchasedItemAmount, purchasedItemDamage);
        ItemMeta purchasedItemMeta = purchasedItem.getItemMeta();
        purchasedItemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', getMarketData().getString("interface.stock." + node + ".name")));
        if (getMarketData().getBoolean("interface.stock." + node + ".special")) {
            purchasedItemMeta.addEnchant(Enchantment.DURABILITY, 3, true);
        }
        if (getMarketData().isConfigurationSection("interface.stock." + node + ".enchantments")) {
            getMarketData().getConfigurationSection("interface.stock." + node + ".enchantments").getKeys(false).forEach(enchantment -> {
                Enchantment enchantmentInstance = Enchantment.getByName(enchantment);
                int level = getMarketData().getInt("interface.stock." + node + ".enchantments." + enchantment + ".level");
                purchasedItemMeta.addEnchant(enchantmentInstance, level, true);
            });
        }
        ArrayList<String> purchasedItemLore = new ArrayList<>();
        purchasedItemLore.add(ChatColor.translateAlternateColorCodes('&', getMarketData().getString("interface.stock." + node + ".lore")));
        purchasedItemMeta.setLore(purchasedItemLore);
        purchasedItem.setItemMeta(purchasedItemMeta);
        return purchasedItem;
    }
}
