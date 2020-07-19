package dev.lukaesebrot.pipe.messages;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

/**
 * Helps to build {@link TextComponent}s
 *
 * @author Lukas Schulte Pelkum
 * @version 1.0.0
 * @since 1.0.0
 */
public class TextComponentBuilder {

    // Define the text component
    private final TextComponent textComponent;

    /**
     * Creates a new text component builder without a base string
     */
    public TextComponentBuilder() {
        this("");
    }

    /**
     * Creates a new text component builder with a base string
     *
     * @param base The base string
     */
    public TextComponentBuilder(String base) {
        this.textComponent = new TextComponent(TextComponent.fromLegacyText(base));
    }

    /**
     * Appends another text component to the current
     *
     * @param component The component to append
     * @return The new text component builder state
     */
    public TextComponentBuilder append(TextComponent component) {
        textComponent.addExtra(component);
        return this;
    }

    /**
     * Appends another string to the current
     *
     * @param text The string to append
     * @return The new text component builder state
     */
    public TextComponentBuilder append(String text) {
        textComponent.addExtra(new TextComponent(TextComponent.fromLegacyText(text)));
        return this;
    }

    /**
     * Appends another newline and a text component to the current
     *
     * @param component The component to append
     * @return The new text component builder state
     */
    public TextComponentBuilder appendLine(TextComponent component) {
        return append("\n").append(component);
    }

    /**
     * Appends another newline and a string to the current
     *
     * @param text The string to append
     * @return The new text component builder state
     */
    public TextComponentBuilder appendLine(String text) {
        return append("\n").append(text);
    }

    /**
     * Sets the text to show when hovering over the component
     *
     * @param text The text to show when hovering over the component
     * @return The new text component builder state
     */
    public TextComponentBuilder setHoverText(String text) {
        textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(text)));
        return this;
    }

    /**
     * Sets the command to execute when clicking on the component
     *
     * @param command The command to execute when clicking on the component
     * @return The new text component builder state
     */
    public TextComponentBuilder runCommand(String command) {
        textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
        return this;
    }

    /**
     * Sets the command to suggest when clicking on the component
     *
     * @param command The command to suggest when clicking on the component
     * @return The new text component builder state
     */
    public TextComponentBuilder suggestCommand(String command) {
        textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, command));
        return this;
    }

    /**
     * Builds the text component
     *
     * @return The built text component
     */
    public TextComponent build() {
        return textComponent;
    }

}
