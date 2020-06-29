package dev.lukaesebrot.pipe.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

import java.util.Map;
import java.util.Optional;

/**
 * Represents a GUI
 *
 * @author Lukas Schulte Pelkum
 * @version 1.0.0
 * @since 1.0.0
 */
public class GUI {

    // Define local variables
    private final Inventory inventory;
    private final Map<Integer, GUIComponent> components;

    /**
     * Creates a new GUI
     *
     * @param size       The size of the inventory
     * @param title      The title of the inventory
     * @param components The components of the inventory
     */
    public GUI(int size, String title, Map<Integer, GUIComponent> components) {
        this.inventory = Bukkit.createInventory(new GUIInventoryHolder(), size, title);
        this.components = components;

        // Set the inventory contents
        components.forEach((slot, component) -> inventory.setItem(slot, component.getItemStack()));
    }

    /**
     * Opens the GUI to a specific player
     *
     * @param player The player to open the inventory for
     */
    public void open(Player player) {
        player.openInventory(inventory);
    }

    /**
     * Checks whether or not the given inventory belongs to this GUI
     *
     * @param inventory The inventory to check
     * @return Whether or not the given inventory belongs to this GUI
     */
    public boolean isInventory(Inventory inventory) {
        // Check if the inventory exists
        if (inventory == null) {
            return false;
        }

        // Check if the inventory has a holder
        if (inventory.getHolder() == null) {
            return false;
        }

        // Check if the inventory holder is a GUI inventory holder
        if (!(inventory.getHolder() instanceof GUIInventoryHolder)) {
            return false;
        }
        GUIInventoryHolder inventoryHolder = (GUIInventoryHolder) inventory.getHolder();

        // Check if the inventory holder is the same as the one from this GUI
        GUIInventoryHolder thisInventoryHolder = (GUIInventoryHolder) this.inventory.getHolder();
        return inventoryHolder.getUUID().equals(thisInventoryHolder.getUUID());
    }

    /**
     * Retrieves an optional component at a specific slot
     *
     * @param slot The slot to search the component for
     * @return The optional GUI component
     */
    public Optional<GUIComponent> getComponentAt(int slot) {
        return Optional.ofNullable(components.get(slot));
    }

    /**
     * Registers the GUI specific listener for this GUI
     *
     * @param plugin The plugin to register the listener with
     */
    public void registerSpecificListener(Plugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(new GUISpecificListener(this), plugin);
    }

}
