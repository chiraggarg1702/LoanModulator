package com.navi.commands;

import com.navi.PrintCommandOutput;
import com.navi.model.ExecutableCommand;
import com.navi.service.LoanModulatorService;

public class ExitCommand extends Command{
    public static String COMMAND_NAME = "EXIT";

    public ExitCommand(
            final LoanModulatorService loanModulatorService, final PrintCommandOutput outputPrinter) {
        super(loanModulatorService, outputPrinter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validate(final ExecutableCommand command) {
        return command.getParams().isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(final ExecutableCommand command) {
        outputPrinter.end();
    }
}
