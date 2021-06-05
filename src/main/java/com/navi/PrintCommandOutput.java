package com.navi;

public class PrintCommandOutput {

    public void welcome() {
        printWithNewLine("Welcome to Loan Modulator application.");
    }

    public void end() {
        printWithNewLine("Thanks for using Loan Modulator application.");
    }

    public void notFound() {
        printWithNewLine("Not found");
    }

    public void invalidFile() {
        printWithNewLine("Invalid file given.");
    }

    public void printWithNewLine(final String msg) {
        System.out.println(msg);
    }
}
