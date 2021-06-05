package com.navi.commands;

import com.navi.PrintCommandOutput;
import com.navi.model.ExecutableCommand;
import com.navi.service.LoanModulatorService;

public class LoanCommand extends Command {
    public static String COMMAND_NAME = "LOAN";

    public LoanCommand(LoanModulatorService loanModulatorService, PrintCommandOutput outputPrinter) {
        super(loanModulatorService, outputPrinter);
    }

    /**
     * Validates that whether a command is valid to be executed or not.
     *
     * @param command command to be validated.
     * @return Boolean indicating whether command is valid or not.
     */
    @Override
    public boolean validate(ExecutableCommand command) {
        return command.getParams().size() == 5;
    }

    /**
     * Executes the command.
     *
     * @param command Command to be executed.
     */
    @Override
    public void execute(ExecutableCommand command) {
        int principal = Integer.parseInt(command.getParams().get(2));
        int years = Integer.parseInt(command.getParams().get(3));
        int rate = Integer.parseInt(command.getParams().get(4));
        try{
            loanModulatorService.createAccount(command.getParams().get(0),command.getParams().get(1),principal,rate,years);
        }catch (Exception ex){
            outputPrinter.printWithNewLine(ex.getMessage());
        }

    }
}
