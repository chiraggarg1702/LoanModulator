package com.navi.commands;

import com.navi.PrintCommandOutput;
import com.navi.model.ExecutableCommand;
import com.navi.service.LoanModulatorService;

public class PaymentCommand extends Command {

    public static String COMMAND_NAME = "PAYMENT";

    public PaymentCommand(LoanModulatorService loanModulatorService, PrintCommandOutput outputPrinter) {
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
        return command.getParams().size() == 4;
    }

    /**
     * Executes the command.
     *
     * @param command Command to be executed.
     */
    @Override
    public void execute(ExecutableCommand command) {
        int paymentAmount = Integer.parseInt(command.getParams().get(2));
        int emis = Integer.parseInt(command.getParams().get(3));
        try {
            loanModulatorService.doPayment(command.getParams().get(0),command.getParams().get(1),paymentAmount,emis);
        } catch (Exception e) {
            outputPrinter.printWithNewLine(e.getMessage());
        }
    }
}
