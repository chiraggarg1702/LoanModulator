package com.navi.mode;

import com.navi.PrintCommandOutput;
import com.navi.commands.Command;
import com.navi.commands.CommandFactory;
import com.navi.exceptions.InvalidCommandException;
import com.navi.model.ExecutableCommand;

import java.io.IOException;

public abstract class Mode {
    private CommandFactory commandFactory;
    protected PrintCommandOutput outputPrinter;

    public Mode(CommandFactory commandFactory, PrintCommandOutput outputPrinter) {
        this.commandFactory = commandFactory;
        this.outputPrinter = outputPrinter;
    }

    /**
     * Helper method to process a command. It basically uses {@link CommandExecutor} to run the given
     * command.
     *
     * @param command Command to be processed.
     */
    protected void processCommand(final ExecutableCommand command) {
        final Command commandExecutor = commandFactory.getCommandExecutor(command);
        if (commandExecutor.validate(command)) {
            commandExecutor.execute(command);
        } else {
            throw new InvalidCommandException();
        }
    }

    /**
     * Abstract method to process the mode. Each mode will process in its own way.
     *
     * @throws IOException
     */
    public abstract void process() throws IOException;
}
