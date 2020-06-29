package dev.lukaesebrot.pipe.command;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Optional;

/**
 * Represents a single argument
 *
 * @author Lukas Schulte Pelkum
 * @version 1.0.0
 * @since 1.0.0
 */
public class Argument {

    // Define the raw argument string
    private final String raw;

    /**
     * Creates a new argument object
     *
     * @param raw The raw argument string
     */
    public Argument(String raw) {
        this.raw = raw;
    }

    /**
     * @return The raw argument string
     */
    public String raw() {
        return raw;
    }

    /**
     * Tries to parse the argument into a short
     *
     * @return The optional parsed short
     */
    public Optional<Short> asShort() {
        try {
            return Optional.of(Short.parseShort(raw));
        } catch (NumberFormatException ignored) {
            return Optional.empty();
        }
    }

    /**
     * Tries to parse the argument into an integer
     *
     * @return The optional parsed integer
     */
    public Optional<Integer> asInteger() {
        try {
            return Optional.of(Integer.parseInt(raw));
        } catch (NumberFormatException ignored) {
            return Optional.empty();
        }
    }

    /**
     * Tries to parse the argument into a long
     *
     * @return The optional parsed long
     */
    public Optional<Long> asLong() {
        try {
            return Optional.of(Long.parseLong(raw));
        } catch (NumberFormatException ignored) {
            return Optional.empty();
        }
    }

    /**
     * Tries to parse the argument into a float
     *
     * @return The optional parsed float
     */
    public Optional<Float> asFloat() {
        try {
            return Optional.of(Float.parseFloat(raw));
        } catch (NumberFormatException ignored) {
            return Optional.empty();
        }
    }

    /**
     * Tries to parse the argument into a double
     *
     * @return The optional parsed double
     */
    public Optional<Double> asDouble() {
        try {
            return Optional.of(Double.parseDouble(raw));
        } catch (NumberFormatException ignored) {
            return Optional.empty();
        }
    }

    /**
     * Tries to retrieve an online player with the name of the current argument
     *
     * @return The optional player
     */
    public Optional<Player> asPlayer() {
        return Optional.ofNullable(Bukkit.getPlayer(raw));
    }

    /**
     * Tries to retrieve an online player with the exact name of the current argument
     *
     * @return The optional player
     */
    public Optional<Player> asPlayerExact() {
        return Optional.ofNullable(Bukkit.getPlayerExact(raw));
    }

    /**
     * Tries to parse the argument into an enum element
     *
     * @param enumClass The class of the enum to use
     * @param <T>       The type of the enum to use
     * @return The optional enum element
     */
    public <T extends Enum<?>> Optional<T> asEnumElement(Class<T> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants())
                .filter(value -> value.toString().toUpperCase().equals(raw.toUpperCase()))
                .findFirst();
    }

}
