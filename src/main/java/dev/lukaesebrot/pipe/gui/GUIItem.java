package dev.lukaesebrot.pipe.gui;

import org.bukkit.inventory.ItemStack;

/**
 * Represents a GUI item
 *
 * @author Lukas Schulte Pelkum
 * @version 1.0.0
 * @since 1.0.0
 */
public class GUIItem extends GUIComponent {

    // Define the item dragging policy
    private final ItemDraggingPolicy itemDraggingPolicy;

    /**
     * Creates a new GUI item
     *
     * @param itemStack          The item stack to use for the item
     * @param itemDraggingPolicy The item dragging policy
     */
    public GUIItem(ItemStack itemStack, ItemDraggingPolicy itemDraggingPolicy) {
        super(itemStack);
        this.itemDraggingPolicy = itemDraggingPolicy;
    }

    @Override
    void onClick(ItemClickContext context) {
        context.setDraggingAllowed(itemDraggingPolicy == ItemDraggingPolicy.DRAGGING_ALLOWED);
    }

}
