package dev.lukaesebrot.pipe.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Represents a basic command
 *
 * @author Lukas Schulte Pelkum
 * @version 1.0.0
 * @since 1.0.0
 */
public class Command {

    // Define local variables
    private final String name;
    private final String[] aliases;
    private final CaseSensitivityPolicy caseSensitivityPolicy;
    private final String description;
    private final String usage;
    private final String[] permissions;
    private final PermissionPolicy permissionPolicy;
    private final Command[] subCommands;
    private final ExecutionHandler[] handlers;

    /**
     * Creates a new command object
     *
     * @param name                  The name of the command
     * @param aliases               The aliases of the command
     * @param caseSensitivityPolicy The case sensitivity policy of the command
     * @param description           The description of the command
     * @param usage                 The usage of the command
     * @param permissions           The permissions corresponding to this command
     * @param permissionPolicy      The permission policy of this command
     * @param subCommands           The sub commands of this command
     * @param handlers              The execution handlers linked to this command
     */
    public Command(String name,
                   String[] aliases,
                   CaseSensitivityPolicy caseSensitivityPolicy,
                   String description,
                   String usage,
                   String[] permissions,
                   PermissionPolicy permissionPolicy,
                   Command[] subCommands,
                   ExecutionHandler[] handlers) {
        this.name = name;
        this.aliases = aliases;
        this.caseSensitivityPolicy = caseSensitivityPolicy;
        this.description = description;
        this.usage = usage;
        this.permissions = permissions;
        this.permissionPolicy = permissionPolicy;
        this.subCommands = subCommands;
        this.handlers = handlers;
    }

    /**
     * Checks the permissions of the given player with the local {@link PermissionPolicy}
     *
     * @param sender The command sender to check the permissions on
     * @return Whether or not the player has the required permission(s)
     */
    public boolean checkPermissions(CommandSender sender) {
        switch (permissionPolicy) {
            case ALL:
                return Arrays.stream(permissions).allMatch(sender::hasPermission);
            case ANY:
                return Arrays.stream(permissions).anyMatch(sender::hasPermission);
            case NONE:
                return Arrays.stream(permissions).noneMatch(sender::hasPermission);
            default:
                return true;
        }
    }

    /**
     * @return The name of the command
     */
    public String getName() {
        return name;
    }

    /**
     * Checks whether or not the current command corresponds to the first string of the given array
     *
     * @param strings The array to check
     * @return Whether or not the current command corresponds to the first string of the given array
     */
    public boolean corresponds(String[] strings) {
        // Validate the string we want to check
        if (strings.length == 0) {
            return false;
        }
        String toCheck = strings[0];

        // Check whether or not this command corresponds to the given string
        boolean ignoreCase = caseSensitivityPolicy == CaseSensitivityPolicy.INSENSITIVE;
        boolean nameCorresponds = ignoreCase ? toCheck.equalsIgnoreCase(name) : toCheck.equals(name);
        boolean aliasesCorrespond = Arrays.stream(aliases).anyMatch(ignoreCase ? toCheck::equalsIgnoreCase : toCheck::equals);
        return nameCorresponds || aliasesCorrespond;
    }

    /**
     * Emits the current command
     *
     * @param sender    The sender which sent the command
     * @param arguments The arguments of the command
     */
    public void emit(CommandSender sender, String[] arguments) {
        // Check if a sub command corresponds to the given arguments and trigger it
        Optional<Command> subCommand = Arrays.stream(subCommands).filter(command -> command.corresponds(arguments)).findFirst();
        if (subCommand.isPresent()) {
            subCommand.get().emit(sender, Arrays.copyOfRange(arguments, 1, arguments.length));
            return;
        }

        // Trigger all registered execution handlers
        Arrays.stream(handlers).forEach(handler -> handler.call(this, sender, new Arguments(String.join(" ", arguments))));
    }

    /**
     * Registers the current command as a root command
     */
    public void registerAsRootCommand() {
        try {
            // Define the needed reflection method to register a command
            Method getCommandMap = Bukkit.getServer().getClass().getMethod("getCommandMap", null);
            Object commandMap = getCommandMap.invoke(Bukkit.getServer(), null);
            Method registerCommand = commandMap.getClass().getMethod("register", String.class, org.bukkit.command.Command.class);

            // Register the current command using Bukkits command map
            registerCommand.invoke(commandMap, name, new org.bukkit.command.Command(name, description, usage, Arrays.asList(aliases)) {

                @Override
                public boolean execute(CommandSender sender, String label, String[] args) {
                    // Emit the current command
                    emit(sender, args);

                    // Return a successful execution to not print the usage message
                    return true;
                }

                @Override
                public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
                    return Arrays.stream(subCommands).map(Command::getName).collect(Collectors.toList());
                }

            });
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException exception) {
            exception.printStackTrace();
        }
    }

}
