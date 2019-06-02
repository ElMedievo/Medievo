package org.elmedievo.medievo.EventHandlers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.elmedievo.medievo.Medievo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static org.elmedievo.medievo.Commands.Market.SpecialItems.SmeltingPickaxe.smeltingPickEnchantment;

public class BlockBreak implements Listener {

    private final Medievo plugin;

    private BlockBreak(Medievo instance) {
        plugin = instance;
    }

    private static HashMap<HashMap<Material, ItemStack[]>, Integer> itemDrops;
    public static void loadSmelterMaterials() {
        itemDrops = new HashMap<>();
        HashMap<Material, ItemStack[]> ironDrops = new HashMap<>();
        HashMap<Material, ItemStack[]> goldDrops = new HashMap<>();
        HashMap<Material, ItemStack[]> sandDrops = new HashMap<>();
        HashMap<Material, ItemStack[]> potatoDrops = new HashMap<>();
        HashMap<Material, ItemStack[]> clayDrops = new HashMap<>();
        HashMap<Material, ItemStack[]> cobbleDrops = new HashMap<>();
        HashMap<Material, ItemStack[]> cactusDrops = new HashMap<>();
        HashMap<Material, ItemStack[]> netherrackDrops = new HashMap<>();
        HashMap<Material, ItemStack[]> mossycobbleDrops = new HashMap<>();
        HashMap<Material, ItemStack[]> logDrops = new HashMap<>();

        ItemStack[] ironDropsList = {new ItemStack(Material.IRON_INGOT)};
        Integer ironExperience = 2;
        ironDrops.put(Material.IRON_ORE, ironDropsList);
        itemDrops.put(ironDrops, ironExperience);

        ItemStack[] goldDropsList = {new ItemStack(Material.GOLD_INGOT)};
        Integer goldExperience = 3;
        goldDrops.put(Material.GOLD_ORE, goldDropsList);
        itemDrops.put(goldDrops, goldExperience);

        ItemStack[] sandDropsList = {new ItemStack(Material.GLASS)};
        Integer sandExperience = 2;
        sandDrops.put(Material.SAND, sandDropsList);
        itemDrops.put(sandDrops, sandExperience);

        ItemStack[] potatoDropsList = {new ItemStack(Material.BAKED_POTATO)};
        Integer potatoExperience = 2;
        potatoDrops.put(Material.POTATO, potatoDropsList);
        itemDrops.put(potatoDrops, potatoExperience);

        ItemStack[] clayDropsList = {new ItemStack(Material.HARD_CLAY)};
        Integer clayExperience = 2;
        clayDrops.put(Material.CLAY, clayDropsList);
        itemDrops.put(clayDrops, clayExperience);

        ItemStack[] cobbleDropsList = {new ItemStack(Material.STONE)};
        Integer cobbleExperience = 1;
        cobbleDrops.put(Material.COBBLESTONE, cobbleDropsList);
        itemDrops.put(cobbleDrops, cobbleExperience);

        ItemStack[] cactusDropsList = {new ItemStack(Material.INK_SACK, 1, (byte) 2)};
        Integer cactusExperience = 1;
        cactusDrops.put(Material.CACTUS, cactusDropsList);
        itemDrops.put(cactusDrops, cactusExperience);

        ItemStack[] netherrackDropsList = {new ItemStack(Material.NETHER_BRICK)};
        Integer netherrackExperience = 1;
        netherrackDrops.put(Material.NETHERRACK, netherrackDropsList);
        itemDrops.put(netherrackDrops, netherrackExperience);

        ItemStack[] mossycobbleDropsList = {new ItemStack(Material.STONE), new ItemStack(Material.VINE)};
        Integer mossycobbleExperience = 1;
        mossycobbleDrops.put(Material.MOSSY_COBBLESTONE, mossycobbleDropsList);
        itemDrops.put(mossycobbleDrops, mossycobbleExperience);

        ItemStack[] logDropsList = {new ItemStack(Material.COAL, 1, (byte) 1)};
        Integer logExperience = 1;
        logDrops.put(Material.LOG, logDropsList);
        logDrops.put(Material.LOG_2, logDropsList);
        itemDrops.put(logDrops, logExperience);
    }

    @SuppressWarnings("deprecation")
    @EventHandler(priority = EventPriority.MONITOR)
    public static void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        if (event.getPlayer().getItemInHand() == null || event.getPlayer().getItemInHand().getType() == Material.AIR) {
            return;
        }
        ItemStack itemInPlayerHand = player.getItemInHand();
        if (itemInPlayerHand.getItemMeta().getLore() == null) {
            return;
        }
        if (itemInPlayerHand.getItemMeta().getLore().toString().contains(smeltingPickEnchantment())) {
            itemDrops.keySet().forEach(itemDropInstance -> itemDropInstance.keySet().forEach(material -> {
                if (event.getBlock().getType() == material) {
                    List<ItemStack> itemDropList = Arrays.asList(itemDropInstance.get(material));
                    itemDropList.forEach(itemDrop -> {
                        int amount = (material == Material.POTATO) ? new Random().nextInt(3) + 1 : 1;
                        itemDrop.setAmount(amount);
                        event.setDropItems(false);
                        world.dropItemNaturally(event.getBlock().getLocation(), itemDrop);
                    });
                    event.setExpToDrop(itemDrops.get(itemDropInstance)); // applies the relative experience level stored within the origin map.
                }
            }));
        }
    }

    public static void registerBlockBreakEvent() {
        Bukkit.getPluginManager().registerEvents(new BlockBreak(Medievo.instance), Medievo.instance);
    }
}
