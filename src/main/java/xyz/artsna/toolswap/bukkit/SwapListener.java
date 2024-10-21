package xyz.artsna.toolswap.bukkit;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

public class SwapListener implements Listener {

    private final SwapData data;
    private final SwapHandler handler;

    public SwapListener(@NotNull ToolSwapPlugin plugin) {
        this.data = plugin.getSwapData();
        this.handler = plugin.getSwapHandler();
    }

    @EventHandler
    public void onInteractEvent(@NotNull PlayerInteractEvent e) {
        if(e.getPlayer().getGameMode() == GameMode.SURVIVAL && e.getAction().isLeftClick()) {
            if(e.getClickedBlock() == null) return;
            SwapSettings settings = data.getSettings(e.getPlayer());
            if(!settings.isEnabled()) return;
            handler.handleTool(e.getPlayer(), e.getClickedBlock().getType(), settings.isPreferSilk());
        }
    }

}
