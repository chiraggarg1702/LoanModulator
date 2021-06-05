package com.navi.commands;

import com.navi.PrintCommandOutput;
import com.navi.model.ExecutableCommand;
import com.navi.service.LoanModulatorService;

public abstract class Command {
    protected LoanModulatorService loanModulatorService;
    protected PrintCommandOutput outputPrinter;

    public Command(final LoanModulatorService loanModulatorService, final PrintCommandOutput outputPrinter) {
        this.loanModulatorService = loanModulatorService;
        this.outputPrinter = outputPrinter;
    }

    /**
     * Validates that whether a command is valid to be executed or not.
     *
     * @param command command to be validated.
     * @return Boolean indicating whether command is valid or not.
     */
    public abstract boolean validate(ExecutableCommand command);

    /**
     * Executes the command.
     *
     * @param command Command to be executed.
     */
    public abstract void execute(ExecutableCommand command);
}

