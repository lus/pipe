package dev.lukaesebrot.pipe.command;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Helps to build a command
 *
 * @author Lukas Schulte Pelkum
 * @version 1.0.0
 * @since 1.0.0
 */
public class CommandBuilder {

    // Define local variables
    private final String name;
    private final Set<String> aliases;
    private CaseSensitivityPolicy caseSensitivityPolicy;
    private String description;
    private String usage;
    private final Set<String> permissions;
    private PermissionPolicy permissionPolicy;
    private final Set<Command> subCommands;
    private final Set<ExecutionHandler> handlers;

    /**
     * Creates a new command builder
     *
     * @param name The name of the command to build
     */
    public CommandBuilder(String name) {
        this.name = name;
        this.aliases = new HashSet<>();
        this.caseSensitivityPolicy = CaseSensitivityPolicy.SENSITIVE;
        this.description = "";
        this.usage = "";
        this.permissions = new HashSet<>();
        this.permissionPolicy = PermissionPolicy.ALL;
        this.subCommands = new HashSet<>();
        this.handlers = new HashSet<>();
    }

    /**
     * Adds multiple aliases to the command
     *
     * @param aliases The aliases to add
     * @return The new command builder state
     */
    public CommandBuilder addAliases(String... aliases) {
        this.aliases.addAll(Arrays.asList(aliases));
        return this;
    }

    /**
     * Sets the case sensitivity policy of the command
     *
     * @param caseSensitivityPolicy The case sensitivity policy to use
     * @return The new command builder state
     */
    public CommandBuilder setCaseSensitivityPolicy(CaseSensitivityPolicy caseSensitivityPolicy) {
        this.caseSensitivityPolicy = caseSensitivityPolicy;
        return this;
    }

    /**
     * Sets the description of the command
     *
     * @param description The description to use
     * @return The new command builder state
     */
    public CommandBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Sets the usage of the command
     *
     * @param usage The usage to use
     * @return The new command builder state
     */
    public CommandBuilder setUsage(String usage) {
        this.usage = usage;
        return this;
    }

    /**
     * Adds multiple permissions to the command
     *
     * @param permissions The permissions to add
     * @return The new command builder state
     */
    public CommandBuilder addPermissions(String... permissions) {
        this.permissions.addAll(Arrays.asList(permissions));
        return this;
    }

    /**
     * Sets the permission policy of the command
     *
     * @param permissionPolicy The permission policy to use
     * @return The new command builder state
     */
    public CommandBuilder setPermissionPolicy(PermissionPolicy permissionPolicy) {
        this.permissionPolicy = permissionPolicy;
        return this;
    }

    /**
     * Adds a sub command to the command
     *
     * @param subCommand The sub command to add
     * @return The new command builder state
     */
    public CommandBuilder addSubCommand(Command subCommand) {
        this.subCommands.add(subCommand);
        return this;
    }

    /**
     * Adds an execution handler to the command
     *
     * @param handler The handler to add
     * @return The new command builder state
     */
    public CommandBuilder addHandler(ExecutionHandler handler) {
        this.handlers.add(handler);
        return this;
    }

    /**
     * Builds the command
     *
     * @return The built command
     */
    public Command build() {
        return new Command(
                name,
                aliases.toArray(String[]::new),
                caseSensitivityPolicy,
                description,
                usage,
                permissions.toArray(String[]::new),
                permissionPolicy,
                subCommands.toArray(Command[]::new),
                handlers.toArray(ExecutionHandler[]::new)
        );
    }

}
