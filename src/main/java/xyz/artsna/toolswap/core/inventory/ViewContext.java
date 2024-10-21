package xyz.artsna.toolswap.core.inventory;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ViewContext {
  private final Player player;
  
  private final View currentWindow;
  
  private View previousWindow;
  
  public ViewContext(Player player, View currentWindow) {
    this(player, currentWindow, null);
  }
  
  public ViewContext(Player player, View currentWindow, View previousWindow) {
    this.player = player;
    this.currentWindow = currentWindow;
    this.previousWindow = previousWindow;
  }
  
  public View getCurrentWindow() {
    return this.currentWindow;
  }
  
  public void previousPage() {
    this.currentWindow.previousPage();
  }
  
  public void nextPage() {
    this.currentWindow.nextPage();
  }
  
  public void closeCurrentWindow() {
    this.currentWindow.close();
  }
  
  public View getPreviousWindow() {
    return this.previousWindow;
  }
  
  public void setPreviousWindow(View previousWindow) {
    this.previousWindow = previousWindow;
  }
  
  public boolean hasPreviousWindow() {
    return (this.previousWindow != null);
  }
  
  public void openPreviousWindow() {
    if (!this.previousWindow.getContext().hasPreviousWindow())
      this.previousWindow.getContext().setPreviousWindow(this.currentWindow); 
    this.previousWindow.render();
    this.player.openInventory(this.previousWindow.getInventory());
  }
  
  public void open(@NotNull View window) {
    window.setContext(new ViewContext(this.player, window, this.currentWindow));
    window.render();
    this.player.openInventory(window.getInventory());
  }
  
  public Player getPlayer() {
    return this.player;
  }
}