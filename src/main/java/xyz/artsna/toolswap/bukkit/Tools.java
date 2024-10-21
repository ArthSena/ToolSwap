package xyz.artsna.toolswap.bukkit;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class Tools {

    public enum ToolType {
        PICKAXE, AXE, SHOVEL, HOE, SHEARS;

        public static @Nullable ToolType getByToolMaterial(@NotNull Material material) {
            return switch (material) {
                case NETHERITE_PICKAXE, DIAMOND_PICKAXE, GOLDEN_PICKAXE, IRON_PICKAXE, STONE_PICKAXE, WOODEN_PICKAXE -> PICKAXE;
                case NETHERITE_AXE, DIAMOND_AXE, GOLDEN_AXE, IRON_AXE, STONE_AXE, WOODEN_AXE -> AXE;
                case NETHERITE_SHOVEL, DIAMOND_SHOVEL, GOLDEN_SHOVEL, IRON_SHOVEL, STONE_SHOVEL, WOODEN_SHOVEL -> SHOVEL;
                case NETHERITE_HOE, DIAMOND_HOE, GOLDEN_HOE, IRON_HOE, STONE_HOE, WOODEN_HOE -> HOE;
                case SHEARS -> SHEARS;
                default -> null;
            };
        }

        public static @Nullable ToolType getByBlockMaterial(@NotNull Config config, Material material) {
            if(config.getPickaxeInteractions().contains(material))
                return PICKAXE;
            else if(config.getAxeInteractions().contains(material))
                return AXE;
            else if(config.getShovelInteractions().contains(material))
                return SHOVEL;
            else if(config.getHoeInteractions().contains(material))
                return HOE;
            else if(config.getShearsInteractions().contains(material))
                return SHEARS;
            else
                return null;
        }
    }

    public static boolean compareHigher(@NotNull ToolType type, ItemStack item1, ItemStack item2) {
        return switch (type) {
            case PICKAXE -> {
                var materials = Arrays.asList(pickaxes);
                yield materials.indexOf(item1.getType()) > materials.indexOf(item2.getType());
            }
            case AXE -> {
                var materials = Arrays.asList(axes);
                yield materials.indexOf(item1.getType()) > materials.indexOf(item2.getType());
            }
            case SHOVEL -> {
                var materials = Arrays.asList(shovels);
                yield materials.indexOf(item1.getType()) > materials.indexOf(item2.getType());
            }
            case HOE -> {
                var materials = Arrays.asList(hoes);
                yield materials.indexOf(item1.getType()) > materials.indexOf(item2.getType());
            }
            default -> false;
        };
    }

    public static final Material[] pickaxes = new Material[] {
            Material.NETHERITE_PICKAXE,
            Material.DIAMOND_PICKAXE,
            Material.GOLDEN_PICKAXE,
            Material.STONE_PICKAXE,
            Material.WOODEN_PICKAXE
    };

    public static final Material[] axes = new Material[] {
            Material.NETHERITE_AXE,
            Material.DIAMOND_AXE,
            Material.GOLDEN_AXE,
            Material.STONE_AXE,
            Material.WOODEN_AXE
    };

    public static final Material[] shovels = new Material[]{
            Material.NETHERITE_SHOVEL,
            Material.DIAMOND_SHOVEL,
            Material.GOLDEN_SHOVEL,
            Material.STONE_SHOVEL,
            Material.WOODEN_SHOVEL
    };

    public static final Material[] hoes = new Material[]{
            Material.NETHERITE_HOE,
            Material.DIAMOND_HOE,
            Material.GOLDEN_HOE,
            Material.STONE_HOE,
            Material.WOODEN_HOE
    };
}