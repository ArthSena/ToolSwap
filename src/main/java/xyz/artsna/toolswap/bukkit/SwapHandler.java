package xyz.artsna.toolswap.bukkit;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SwapHandler {

    private final Config config;

    public SwapHandler(@NotNull ToolSwapPlugin plugin) {
        this.config = plugin.getConfig();
    }

    public void handleTool(@NotNull Player player, Material clickedBlock, boolean preferSilk) {
        //Check if have an item in hand.
        ItemStack currentTool = player.getInventory().getItemInMainHand();
        if(currentTool.getType() == Material.AIR) return;

        //Check if the item is an tool.
        Tools.ToolType currentToolType = Tools.ToolType.getByToolMaterial(currentTool.getType());
        if(currentToolType == null) return;

        //Check the required tool type of the clicked block and if the current tool type is already selected
        Tools.ToolType requiredType = Tools.ToolType.getByBlockMaterial(config, clickedBlock);
        if(requiredType == null || currentToolType == requiredType) return;

        //handle swapping
        handleSwap(player, findTool(player, requiredType, preferSilk));
    }

    public void handleSwap(@NotNull Player player, ItemStack newTool) {
        var items = player.getInventory().getContents();
        var oldTool = player.getInventory().getItemInMainHand();

        //Gets newTool index, sets oldTool to the newTool index and put the newTool in the hand
        for(int i = 0; i < items.length; i++) {
            if(items[i] != null && items[i].equals(newTool)) {
                player.getInventory().setItem(i, oldTool);
                break;
            }
        }

        player.getInventory().setItemInMainHand(newTool);
        player.updateInventory();
    }

    public static @Nullable ItemStack findTool(@NotNull Player player, Tools.ToolType type, boolean preferSilk) {
        var items = player.getInventory().getContents();
        List<ItemStack> tools = new ArrayList<>();

        // Find all tools of the specified type in the inventory
        for(ItemStack item : items) {
            if(item!= null && Tools.ToolType.getByToolMaterial(item.getType()) == type) {
                tools.add(item);
            }
        }

        if(tools.isEmpty()) return null;

        // Sort the tools based on the specified priority (silk touch > fortune)
        if (preferSilk) {
            tools.sort((item1, item2) -> {
                if(item1.containsEnchantment(Enchantment.SILK_TOUCH))
                    return -1;
                else if(item2.containsEnchantment(Enchantment.SILK_TOUCH))
                    return 1;
                else
                    return 0;
            });
        } else {
            tools.sort((item1, item2) -> Integer.compare(item2.getEnchantmentLevel(Enchantment.FORTUNE), item1.getEnchantmentLevel(Enchantment.FORTUNE)));
        }

        // Sort the tools based on the material type
        tools.sort((item1, item2) -> {
            if(Tools.compareHigher(type, item1, item2))
                return -1;
            else if(Tools.compareHigher(type, item2, item1))
                return 1;
            else
                return 0;
        });

        // return the first tool found (which should be the one with the highest priority)
        return tools.getFirst();
    }
}