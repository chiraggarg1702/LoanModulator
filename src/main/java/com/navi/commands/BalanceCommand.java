package com.navi.commands;

import com.navi.PrintCommandOutput;
import com.navi.model.ExecutableCommand;
import com.navi.service.LoanModulatorService;

public class BalanceCommand extends Command{

    public static String COMMAND_NAME = "BALANCE";
    public BalanceCommand(LoanModulatorService loanModulatorService, PrintCommandOutput outputPrinter) {
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
        return command.getParams().size() == 3;
    }

    /**
     * Executes the command.
     *
     * @param command Command to be executed.
     */
    @Override
    public void execute(ExecutableCommand command) {
        int emiNumber = Integer.parseInt(command.getParams().get(2));
        String outputLine = "";
        try{
            outputLine = loanModulatorService.getBalance(command.getParams().get(0),command.getParams().get(1),emiNumber);
        }catch (Exception ex){
            outputPrinter.printWithNewLine(ex.getMessage());
        }
        outputPrinter.printWithNewLine(outputLine);
    }
}
