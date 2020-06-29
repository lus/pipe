package dev.lukaesebrot.pipe.command;

import java.util.Arrays;

/**
 * Represents a set of arguments
 *
 * @author Lukas Schulte Pelkum
 * @version 1.0.0
 * @since 1.0.0
 */
public class Arguments {

    // Define local variables
    private final String raw;
    private final Argument[] arguments;

    /**
     * Creates a new arguments object
     *
     * @param raw The raw string to parse the arguments from
     */
    public Arguments(String raw) {
        this.raw = raw;
        this.arguments = Arrays.stream(raw.split("\\s+")).map(Argument::new).toArray(Argument[]::new);
    }

    /**
     * @return All provided arguments as a single one
     */
    public Argument asSingle() {
        return new Argument(raw);
    }

    /**
     * @return The raw arguments string
     */
    public String raw() {
        return raw;
    }

    /**
     * @return The array of parsed arguments
     */
    public Argument[] getArguments() {
        return arguments;
    }

}
