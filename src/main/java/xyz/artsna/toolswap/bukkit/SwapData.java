package xyz.artsna.toolswap.bukkit;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.artsna.toolswap.core.file.YamlFile;

public class SwapData {

    private final YamlFile data;

    public SwapData(@NotNull ToolSwapPlugin plugin) {
        this.data = new YamlFile(plugin.getDataFolder(), "player-settings.yml");
    }

    public SwapSettings getSettings(@NotNull Player player) {
            SwapSettings settings;

            if(data.contains("settings." + player.getUniqueId())) {
                boolean enabled = data.getBoolean("settings." + player.getUniqueId()  + ".enabled");
                boolean preferSilk = data.getBoolean("settings." + player.getUniqueId()  + ".preferSilk");
                settings = new SwapSettings(enabled, preferSilk);
            } else {
                settings = new SwapSettings(true, false);
                saveSettings(player, settings);
            }

        return settings;
    }

    public void saveSettings(Player player, SwapSettings settings) {
        data.set("settings." + player.getUniqueId() + ".enabled", settings.isEnabled());
        data.set("settings." + player.getUniqueId() + ".preferSilk", settings.isPreferSilk());
        data.save();
    }

}
