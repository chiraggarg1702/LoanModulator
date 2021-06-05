package com.navi;

import com.navi.commands.CommandFactory;
import com.navi.exceptions.InvalidModeException;
import com.navi.mode.FileBasedMode;
import com.navi.mode.InteractiveMode;
import com.navi.service.LoanModulatorService;

import java.io.IOException;

public class Main {
    public static void main(final String[] args) throws IOException {
        final PrintCommandOutput outputPrinter = new PrintCommandOutput();
        final LoanModulatorService loanModulatorService = new LoanModulatorService();
        final CommandFactory commandFactory = new CommandFactory(loanModulatorService);
        if(isInteractiveMode(args)){
            new InteractiveMode(commandFactory,outputPrinter).process();
        }else if (isFileInputMode(args)){
            new FileBasedMode(commandFactory,outputPrinter,args[0]).process();
        }else{
            throw new InvalidModeException();
        }
    }


    /**
     * Checks whether the program is running using file input mode.
     *
     * @param args Command line arguments.
     * @return Boolean indicating whether in file input mode.
     */
    private static boolean isFileInputMode(final String[] args) {
        return args.length == 1;
    }

    /**
     * Checks whether the program is running using interactive shell mode.
     *
     * @param args Command line arguments.
     * @return Boolean indicating whether in interactive shell mode.
     */
    private static boolean isInteractiveMode(final String[] args) {
        return args.length == 0;
    }

}
