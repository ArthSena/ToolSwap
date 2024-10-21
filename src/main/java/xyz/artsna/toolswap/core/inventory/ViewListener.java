package xyz.artsna.toolswap.core.inventory;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class ViewListener implements Listener {
  public static void Register(Plugin plugin) {
    Bukkit.getPluginManager().registerEvents(new ViewListener(), plugin);
  }
  
  @EventHandler
  public void onClick(@NotNull InventoryClickEvent e) {
    if (!(e.getWhoClicked() instanceof org.bukkit.entity.Player) || e.getCurrentItem() == null)
      return; 
    if (e.getInventory().getHolder() != null && e.getInventory().getHolder() instanceof View window) {
        e.setCancelled(window.isDefaultCancel());
      if (window.getButtons().containsKey(e.getRawSlot()))
        window.getButtons().get(e.getRawSlot()).getAction().run(e, window.getContext());
    } 
  }
  
  @EventHandler
  public void onClose(@NotNull InventoryCloseEvent e) {
    if (!(e.getPlayer() instanceof org.bukkit.entity.Player))
      return; 
    if (e.getInventory().getHolder() != null && e.getInventory().getHolder() instanceof View window) {
        window.onClose(window.getContext());
    } 
  }
}
