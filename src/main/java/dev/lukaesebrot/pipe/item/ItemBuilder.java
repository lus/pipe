package dev.lukaesebrot.pipe.item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.util.Arrays;

/**
 * Helps to build an {@link ItemStack}
 *
 * @author Lukas Schulte Pelkum
 * @version 1.0.0
 * @since 1.0.0
 */
public class ItemBuilder {

    // Define local variables
    private final ItemStack itemStack;
    private final ItemMeta itemMeta;

    /**
     * Creates a new item builder
     *
     * @param material The material to use
     */
    public ItemBuilder(Material material) {
        this.itemStack = new ItemStack(material, 1);
        this.itemMeta = itemStack.getItemMeta();
    }

    /**
     * Sets the amount of the item stack
     *
     * @param amount The amount of the item stack
     * @return The new item builder state
     */
    public ItemBuilder setAmount(int amount) {
        itemStack.setAmount(amount);
        return this;
    }

    /**
     * Sets the durability of the item stack
     *
     * @param durability The durability of the item stack
     * @return The new item builder state
     */
    @Deprecated
    public ItemBuilder setDurability(short durability) {
        itemStack.setDurability(durability);
        return this;
    }

    /**
     * Sets the material data of the item stack
     *
     * @param data The material data of the item stack
     * @return The new item builder state
     */
    @Deprecated
    public ItemBuilder setData(MaterialData data) {
        itemStack.setData(data);
        return this;
    }

    /**
     * Sets the display name of the item stack
     *
     * @param displayName The display name of the item stack
     * @return The new item builder state
     */
    public ItemBuilder setDisplayName(String displayName) {
        itemMeta.setDisplayName(displayName);
        return this;
    }

    /**
     * Sets the lore of the item stack
     *
     * @param lore The lore of the item stack
     * @return The new item builder state
     */
    public ItemBuilder setLore(String... lore) {
        itemMeta.setLore(Arrays.asList(lore));
        return this;
    }

    /**
     * Builds the final item stack
     *
     * @return The built item stack
     */
    public ItemStack build() {
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}
