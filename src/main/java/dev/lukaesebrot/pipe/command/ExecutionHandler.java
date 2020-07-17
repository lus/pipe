package dev.lukaesebrot.pipe.command;

import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a command execution handler
 *
 * @author Lukas Schulte Pelkum
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ExecutionHandler {

    /**
     * Calls the current command execution handler
     *
     * @param command   The command which called this handler
     * @param sender    The sender which sent the command
     * @param arguments The arguments of the command
     */
    default void call(Command command, CommandSender sender, Arguments arguments) {
        // Call the general execution hook
        handleExecution(command, sender, arguments);

        // Call the corresponding specific execution hook
        if (sender instanceof ConsoleCommandSender) {
            handleConsoleExecution(command, (ConsoleCommandSender) sender, arguments);
        } else if (sender instanceof BlockCommandSender) {
            handleBlockExecution(command, (BlockCommandSender) sender, arguments);
        } else if (sender instanceof Player) {
            handlePlayerExecution(command, (Player) sender, arguments);
        }
    }

    /**
     * Gets called whenever a linked command gets called
     *
     * @param command   The command which called this handler
     * @param sender    The sender which sent the command
     * @param arguments The arguments of the command
     */
    default void handleExecution(Command command, CommandSender sender, Arguments arguments) {
        // Empty by default
    }

    /**
     * Gets called whenever a linked command gets called by a console command sender
     *
     * @param command   The command which called this handler
     * @param sender    The console command sender which sent the command
     * @param arguments The arguments of the command
     */
    default void handleConsoleExecution(Command command, ConsoleCommandSender sender, Arguments arguments) {
        // Empty by default
    }

    /**
     * Gets called whenever a linked command gets called by a block command sender
     *
     * @param command   The command which called this handler
     * @param sender    The block command sender which sent the command
     * @param arguments The arguments of the command
     */
    default void handleBlockExecution(Command command, BlockCommandSender sender, Arguments arguments) {
        // Empty by default
    }

    /**
     * Gets called whenever a linked command gets called by a player
     *
     * @param command   The command which called this handler
     * @param player    The player who send the command
     * @param arguments The arguments of the command
     */
    default void handlePlayerExecution(Command command, Player player, Arguments arguments) {
        // Empty by default
    }

    /**
     * Gets called whenever a linked command gets tab completed
     *
     * @param sender The sender of the tab completion event
     * @param alias  The alias the sender used
     * @param args   The arguments the sender specified
     * @return A list of tab completions
     */
    default List<String> handleTabComplete(Command command, CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        String firstArgument = args.length != 0 ? args[0] : "";
        return Arrays.stream(command.getSubCommands())
                .map(Command::getName)
                .filter(name -> name.toLowerCase().startsWith(firstArgument.toLowerCase()))
                .collect(Collectors.toList());
    }

}
