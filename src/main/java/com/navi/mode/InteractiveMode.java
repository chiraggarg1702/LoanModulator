package com.navi.mode;

import com.navi.PrintCommandOutput;
import com.navi.commands.CommandFactory;
import com.navi.commands.ExitCommand;
import com.navi.model.ExecutableCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InteractiveMode extends Mode {
    public InteractiveMode(CommandFactory commandFactory, PrintCommandOutput outputPrinter) {
        super(commandFactory, outputPrinter);
    }

    /**
     * Abstract method to process the mode. Each mode will process in its own way.
     *
     * @throws IOException
     */
    @Override
    public void process() throws IOException {
        outputPrinter.welcome();
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            final String input = reader.readLine();
            final ExecutableCommand command = new ExecutableCommand(input);
            processCommand(command);
            if (command.getCommandName().equals(ExitCommand.COMMAND_NAME)) {
                break;
            }
        }
    }
}
