package xyz.artsna.toolswap.core.inventory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

public abstract class View implements InventoryHolder {
  private final Map<Integer, Button> buttons = new HashMap<>();
  
  private String title = null;
  
  private ViewContext context = null;
  
  protected Inventory inventory = null;
  
  private int page = 1;
  
  private boolean defaultCancel;
  
  public View(int size) {
    this.inventory = Bukkit.createInventory(this, size);
  }
  
  public View(int size, String title) {
    this.inventory = Bukkit.createInventory(this, size, title);
    this.title = title;
  }
  
  public void setDefaultCancel(boolean defaultCancel) {
    this.defaultCancel = defaultCancel;
  }
  
  public boolean isDefaultCancel() {
    return this.defaultCancel;
  }
  
  public @NotNull Inventory getInventory() {
    return this.inventory;
  }
  
  public ViewContext getContext() {
    return this.context;
  }
  
  protected void setContext(ViewContext context) {
    this.context = context;
  }
  
  public abstract void onRender(ViewContext context);
  
  public void onClose(ViewContext context) {}
  
  public void setItem(int slot, Button button) {
    this.buttons.put(slot, button);
    this.inventory.setItem(slot, button);
  }
  
  public void removeItem(int slot) {
    this.buttons.remove(slot);
    this.inventory.clear(slot);
  }
  
  public Button getItem(int slot) {
    return this.buttons.get(slot);
  }
  
  public void open(Player player) {
    setContext(new ViewContext(player, this));
    render();
    player.openInventory(getInventory());
  }
  
  public Map<Integer, Button> getButtons() {
    return Collections.unmodifiableMap(this.buttons);
  }
  
  protected void render() {
    onRender(this.context);
  }
  
  public void update() {
    onRender(this.context);
    this.context.getPlayer().openInventory(getInventory());
  }
  
  public void close() {
    this.context.getPlayer().closeInventory();
    onClose(this.context);
  }
  
  public int getPage() {
    return this.page;
  }
  
  public void nextPage() {
    this.page++;
    update();
  }
  
  public void previousPage() {
    this.page--;
    update();
  }
  
  public void setTitle(String title) {
    this.inventory = Bukkit.createInventory(this, this.inventory.getSize(), title);
    this.title = title;
  }
  
  public void updateTitle(String title) {
    setTitle(title);
    update();
  }
  
  public String getTitle() {
    return this.title;
  }
}
