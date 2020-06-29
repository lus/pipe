package dev.lukaesebrot.pipe.gui;

import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Represents a GUI builder
 *
 * @author Lukas Schulte Pelkum
 * @version 1.0.0
 * @since 1.0.0
 */
public class GUIBuilder {

    // Define local variables
    private int size;
    private String title;
    private final Map<Integer, GUIComponent> components;
    private ItemDraggingPolicy defaultItemDraggingPolicy;

    /**
     * Creates a new GUI builder with default values
     */
    public GUIBuilder() {
        this.size = 9;
        this.title = "GUI";
        this.components = new HashMap<>();
        this.defaultItemDraggingPolicy = ItemDraggingPolicy.DRAGGING_DENIED;
    }

    /**
     * Sets the size of the GUI
     *
     * @param size The size of the GUI
     * @return The new GUI builder state
     */
    public GUIBuilder setSize(int size) {
        this.size = size;
        return this;
    }

    /**
     * Sets the title of the GUI
     *
     * @param title The title of the GUI
     * @return The new GUI builder state
     */
    public GUIBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Sets the default item dragging policy
     *
     * @param defaultItemDraggingPolicy The default item dragging policy
     * @return The new GUI builder state
     */
    public GUIBuilder setDefaultItemDraggingPolicy(ItemDraggingPolicy defaultItemDraggingPolicy) {
        this.defaultItemDraggingPolicy = defaultItemDraggingPolicy;
        return this;
    }

    /**
     * Fills the GUI with a specific item stack
     *
     * @param itemStack The item stack to fill the GUI with
     * @return The new GUI builder state
     */
    public GUIBuilder fill(ItemStack itemStack) {
        IntStream.of(size).forEach(index ->
                components.put(index, new GUIItem(itemStack, defaultItemDraggingPolicy))
        );
        return this;
    }

    /**
     * Sets the component at a specific slot
     *
     * @param slot      The slot to set the component to
     * @param component The component to set
     * @return The new GUI builder state
     */
    public GUIBuilder set(int slot, GUIComponent component) {
        components.put(slot, component);
        return this;
    }

    /**
     * @return The built GUI
     */
    public GUI build() {
        return new GUI(
                size,
                title,
                components
        );
    }

}
