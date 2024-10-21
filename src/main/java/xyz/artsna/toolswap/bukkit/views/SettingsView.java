package xyz.artsna.toolswap.bukkit.views;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.jetbrains.annotations.NotNull;
import xyz.artsna.toolswap.bukkit.Config;
import xyz.artsna.toolswap.bukkit.SwapData;
import xyz.artsna.toolswap.core.inventory.Button;
import xyz.artsna.toolswap.core.inventory.View;
import xyz.artsna.toolswap.core.inventory.ViewContext;

import java.util.List;

public class SettingsView extends View {

    private final Config config;
    private final SwapData data;

    public SettingsView(Config config, SwapData data) {
        super(27, "Tool Swap Settings");

        this.config = config;
        this.data = data;

        setDefaultCancel(true);
    }

    @Override
    public void onRender(@NotNull ViewContext context) {
        var settings = data.getSettings(context.getPlayer());

        var toolSwapToggle = new Button(Material.IRON_PICKAXE);
        var toolSwapToggleMeta = toolSwapToggle.getItemMeta();
        toolSwapToggleMeta.displayName(Component.text("Toggle Tool Swap", NamedTextColor.WHITE));
        toolSwapToggleMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        toolSwapToggleMeta.addItemFlags(ItemFlag.HIDE_ADDITIONAL_TOOLTIP);
        Component toolSwapToggleLore = Component.text("Status: ", NamedTextColor.GRAY);

        if(settings.isEnabled()) {
            toolSwapToggleMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
            toolSwapToggleLore = toolSwapToggleLore.append(Component.text("Enabled", NamedTextColor.GREEN));
        }else {
            toolSwapToggleLore = toolSwapToggleLore.append(Component.text("Disabled", NamedTextColor.RED));
        }

        toolSwapToggleMeta.lore(List.of(toolSwapToggleLore));
        toolSwapToggle.setItemMeta(toolSwapToggleMeta);

        toolSwapToggle.onClick((e, c) -> {
            if(config.getUsePermission() != null && !config.getUsePermission().equalsIgnoreCase("none") && !c.getPlayer().hasPermission(config.getUsePermission())){
                c.getPlayer().sendMessage(Component.text("You don't have permission to use this feature.", NamedTextColor.RED));
                return;
            }
            settings.setEnabled(!settings.isEnabled());
            data.saveSettings(c.getPlayer(), settings);
            if(settings.isEnabled())  context.getPlayer().sendMessage(Component.text("Tool Swap is now enabled.", NamedTextColor.GREEN));
            else context.getPlayer().sendMessage(Component.text("Tool Swap is now disabled.", NamedTextColor.RED));
            update();
        });

        var preferSilkToggle = new Button(Material.BOOK);
        var preferSilkToggleMeta = preferSilkToggle.getItemMeta();
        preferSilkToggleMeta.displayName(Component.text("Prefer Silk Tools", NamedTextColor.WHITE));
        preferSilkToggleMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        Component preferSilkToggleLore = Component.text("Status: ", NamedTextColor.GRAY);

        if(settings.isPreferSilk()) {
            preferSilkToggleMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
            preferSilkToggleLore = preferSilkToggleLore.append(Component.text("Enabled", NamedTextColor.GREEN));
        }else {
            preferSilkToggleLore = preferSilkToggleLore.append(Component.text("Disabled", NamedTextColor.RED));
        }

        preferSilkToggleMeta.lore(List.of(preferSilkToggleLore));
        preferSilkToggle.setItemMeta(preferSilkToggleMeta);

        preferSilkToggle.onClick((e, c) -> {
            if(config.getUsePermission() != null && !config.getUsePermission().equalsIgnoreCase("none") && !c.getPlayer().hasPermission(config.getPreferSilkPermission())){
                c.getPlayer().sendMessage(Component.text("You don't have permission to use this feature.", NamedTextColor.RED));
                return;
            }
            settings.setPreferSilk(!settings.isPreferSilk());
            data.saveSettings(c.getPlayer(), settings);
            if(settings.isPreferSilk()) context.getPlayer().sendMessage(Component.text("Prefer Silk Tools settings is now enabled.", NamedTextColor.GREEN));
            else context.getPlayer().sendMessage(Component.text("Prefer Silk Tools settings is now disabled.", NamedTextColor.RED));
            update();
        });

        setItem(11, toolSwapToggle);
        setItem(15, preferSilkToggle);
    }
}
