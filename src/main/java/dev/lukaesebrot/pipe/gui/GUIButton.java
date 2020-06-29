package dev.lukaesebrot.pipe.gui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.function.Function;

/**
 * Represents a GUI button
 *
 * @author Lukas Schulte Pelkum
 * @version 1.0.0
 * @since 1.0.0
 */
public class GUIButton extends GUIComponent {

    // Define the onClick function
    private final Function<Player, Boolean> onClick;

    /**
     * Creates a new GUI button
     *
     * @param itemStack The item stack to use for the button
     * @param onClick   The onClick function
     */
    public GUIButton(ItemStack itemStack, Function<Player, Boolean> onClick) {
        super(itemStack);
        this.onClick = onClick;
    }

    @Override
    public void onClick(ItemClickContext context) {
        context.setDraggingAllowed(onClick.apply(context.getPlayer()));
    }

}
