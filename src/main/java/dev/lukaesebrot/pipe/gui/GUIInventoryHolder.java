package dev.lukaesebrot.pipe.gui;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.UUID;

/**
 * Represents the GUI inventory holder
 *
 * @author Lukas Schulte Pelkum
 * @version 1.0.0
 * @since 1.0.0
 */
public class GUIInventoryHolder implements InventoryHolder {

    // Define the UUID of this inventory holder
    private final UUID uuid = UUID.randomUUID();

    /**
     * @return The UUID of this inventory holder
     */
    public UUID getUUID() {
        return uuid;
    }

    @Override
    public Inventory getInventory() {
        return null;
    }

}
