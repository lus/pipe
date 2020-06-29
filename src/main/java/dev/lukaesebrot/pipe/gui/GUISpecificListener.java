package dev.lukaesebrot.pipe.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

/**
 * Represents the GUI specific listener
 *
 * @author Lukas Schulte Pelkum
 * @version 1.0.0
 * @since 1.0.0
 */
public class GUISpecificListener implements Listener {

    // Define the GUI this listener corresponds to
    private final GUI gui;

    /**
     * Creates a new GUI specific listener
     *
     * @param gui The GUI this listener corresponds to
     */
    public GUISpecificListener(GUI gui) {
        this.gui = gui;
    }

    @EventHandler
    public void handleInventoryClose(InventoryCloseEvent event) {
        // Check if the inventory belongs to the GUI
        Inventory inventory = event.getInventory();
        if (!gui.isInventory(inventory)) {
            return;
        }

        // Unregister this listener
        HandlerList.unregisterAll(this);
    }

    @EventHandler
    public void handleInventoryClick(InventoryClickEvent event) {
        // Check if the inventory belongs to the GUI
        Inventory inventory = event.getClickedInventory();
        if (!gui.isInventory(inventory)) {
            return;
        }

        // Check if the slot is allowed to be dragged out
        gui.getComponentAt(event.getSlot()).ifPresent(component -> {
            ItemClickContext context = new ItemClickContext((Player) event.getWhoClicked());
            component.onClick(context);
            event.setCancelled(!context.isDraggingAllowed());
        });
    }

}
