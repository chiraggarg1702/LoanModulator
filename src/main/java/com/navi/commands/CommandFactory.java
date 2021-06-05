package com.navi.commands;

import com.navi.PrintCommandOutput;
import com.navi.exceptions.InvalidCommandException;
import com.navi.model.ExecutableCommand;
import com.navi.service.LoanModulatorService;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private Map<String, Command> commands = new HashMap();

    public CommandFactory(final LoanModulatorService loanModulatorService) {
        final PrintCommandOutput outputPrinter = new PrintCommandOutput();
        commands.put(
                LoanCommand.COMMAND_NAME,
                new LoanCommand(loanModulatorService,outputPrinter)
        );
        commands.put(
                PaymentCommand.COMMAND_NAME,
                new PaymentCommand(loanModulatorService,outputPrinter)
        );
        commands.put(
                BalanceCommand.COMMAND_NAME,
                new BalanceCommand(loanModulatorService,outputPrinter)
        );
        commands.put(
                ExitCommand.COMMAND_NAME,
                new ExitCommand(loanModulatorService, outputPrinter));
    }

    /**
     * Gets {@link Command} for a particular command. It basically uses name of command to
     * fetch its corresponding executor.
     *
     * @param command Command for which executor has to be fetched.
     * @return Command executor.
     */
    public Command getCommandExecutor(final ExecutableCommand command) {
        final Command commandExecutor = commands.get(command.getCommandName());
        if (commandExecutor == null) {
            throw new InvalidCommandException();
        }
        return commandExecutor;
    }

}
