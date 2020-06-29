package dev.lukaesebrot.pipe.gui;

import org.bukkit.inventory.ItemStack;

/**
 * Represents a GUI component
 *
 * @author Lukas Schulte Pelkum
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class GUIComponent {

    // Define the item stack linked to this component
    private final ItemStack itemStack;

    /**
     * Creates a new GUI component
     *
     * @param itemStack The item stack linked to this component
     */
    public GUIComponent(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /**
     * @return The item stack linked to this component
     */
    public ItemStack getItemStack() {
        return itemStack;
    }

    /**
     * Gets called when the item gets clicked
     *
     * @param context The corresponding item click context
     */
    abstract void onClick(ItemClickContext context);

}
