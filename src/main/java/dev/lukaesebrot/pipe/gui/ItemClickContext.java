package dev.lukaesebrot.pipe.gui;

import org.bukkit.entity.Player;

/**
 * Represents an item click context
 *
 * @author Lukas Schulte Pelkum
 * @version 1.0.0
 * @since 1.0.0
 */
public class ItemClickContext {

    // Define local variables
    private final Player player;
    private boolean draggingAllowed;

    /**
     * Creates a new item click context object
     *
     * @param player The player who clicked the item
     */
    public ItemClickContext(Player player) {
        this.player = player;
        this.draggingAllowed = false;
    }

    /**
     * @return The player who clicked the item
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Defines whether or not the item can get dragged out of the inventory
     *
     * @param draggingAllowed Whether or not the item can get dragged out of the inventory
     */
    public void setDraggingAllowed(boolean draggingAllowed) {
        this.draggingAllowed = draggingAllowed;
    }

    /**
     * @return Whether or not the item can get dragged out of the inventory
     */
    public boolean isDraggingAllowed() {
        return draggingAllowed;
    }

}
