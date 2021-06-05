package com.navi.mode;

import com.navi.PrintCommandOutput;
import com.navi.commands.CommandFactory;
import com.navi.model.ExecutableCommand;

import java.io.*;

public class FileBasedMode extends Mode {
    private String fileName;

    public FileBasedMode(CommandFactory commandFactory, PrintCommandOutput outputPrinter, String fileName) {
        super(commandFactory, outputPrinter);
        this.fileName = fileName;
    }

    /**
     * Abstract method to process the mode. Each mode will process in its own way.
     *
     * @throws IOException
     */
    @Override
    public void process() throws IOException {
        final File file = new File(fileName);
        final BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            outputPrinter.invalidFile();
            return;
        }

        String input = reader.readLine();
        while (input != null) {
            final ExecutableCommand command = new ExecutableCommand(input);
            processCommand(command);
            input = reader.readLine();
        }
    }
}
