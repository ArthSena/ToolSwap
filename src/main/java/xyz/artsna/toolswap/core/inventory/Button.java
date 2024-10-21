package xyz.artsna.toolswap.core.inventory;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class Button extends ItemStack {
  private Action action = null;
  
  public Button(Material material) {
    this(material, 1);
  }
  
  public Button(Material material, int amount) {
    super(material, amount);
  }
  
  public Button(ItemStack item) {
	  super(item);
	  item.setAmount(1);
  }
  
  public Button(ItemStack item, int amount) {
	  super(item);
	  item.setAmount(amount);
  }
  
  public Button onClick(Action action) {
    this.action = action;
    return this;
  }
  
  protected Action getAction() {
    return this.action;
  }
  
  public interface Action {
    void run(InventoryClickEvent event, ViewContext context);
  }
}
