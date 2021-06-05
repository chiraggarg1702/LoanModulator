package com.navi;

import com.navi.exceptions.InvalidCommandException;
import com.navi.exceptions.InvalidModeException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class MainTest {

    private InputStream sysInBackup;
    private PrintStream sysOutBackup;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() throws Exception {
        sysInBackup = System.in; // backup System.in to restore it later
        sysOutBackup = System.out; // backup System.out to restore it later
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void tearDown() throws Exception {
        System.setIn(sysInBackup);
        System.setOut(sysOutBackup);
    }

    @Test
    public void testInteractiveMode() throws IOException {
        final String commands =
                "LOAN IDIDI Dale 10000 5 4\n" +
                        "LOAN MBI Harry 2000 2 2\n"+
                        "BALANCE IDIDI Dale 5\n" +
                        "BALANCE IDIDI Dale 40\n" +
                        "BALANCE MBI Harry 12\n" +
                        "BALANCE MBI Harry 0\n"+
                        "exit\r\n";

        final String expectedOutput =
                "Welcome to Loan Modulator application.\n"+
                        "IDIDI Dale 1000 55\n" +
                        "IDIDI Dale 8000 20\n" +
                        "MBI Harry 1044 12\n" +
                        "MBI Harry 0 24\n" +
                        "Thanks for using Loan Modulator application.\n";

        final ByteArrayInputStream in = new ByteArrayInputStream(commands.getBytes());
        System.setIn(in);

        Main.main(new String[] {});
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test(expected = InvalidCommandException.class)
    public void testInvalidCommandParams() throws IOException {
        final String commands = "abcd\r\n";
        final ByteArrayInputStream in = new ByteArrayInputStream(commands.getBytes());
        System.setIn(in);

        Main.main(new String[] {});
    }

    @Test
    public void testFileMode() throws IOException {
        final String expectedOutput =
                "IDIDI Dale 1326 9\n" +
                        "IDIDI Dale 3652 4\n" +
                        "UON Shelly 15856 3\n" +
                        "MBI Harry 9044 10\n";
        Main.main(new String[] {"data_file.txt"});
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testFileModeWithInvalidFile() throws IOException {
        final String expectedOutput = "Invalid file given.\n";
        Main.main(new String[] {"some_random_file.txt"});
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test(expected = InvalidModeException.class)
    public void testInvalidMode() throws IOException {
        Main.main(new String[] {"data_file.txt","some-other-input"});
    }

    @Test
    public void testDuplicateAccount() throws IOException {
        final String commands =
                "LOAN IDIDI Dale 10000 5 4\n" +
                        "LOAN IDIDI Dale 10000 5 4\n"+
                        "exit\r\n";

        final String expectedOutput =
                "Welcome to Loan Modulator application.\n"+
                        "Dale has already taken a loan from bank IDIDI\n" +
                        "Thanks for using Loan Modulator application.\n";

        final ByteArrayInputStream in = new ByteArrayInputStream(commands.getBytes());
        System.setIn(in);

        Main.main(new String[] {});
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testWithMoreData() throws IOException {
        final String commands =
                "LOAN IDIDI Dale 5000 1 6\n" +
                        "LOAN MBI Harry 10000 3 7\n" +
                        "LOAN UON Shelly 15000 2 9\n" +
                        "PAYMENT IDIDI Dale 1000 5\n" +
                        "PAYMENT MBI Harry 5000 10\n" +
                        "PAYMENT UON Shelly 7000 12\n" +
                        "BALANCE IDIDI Dale 3\n" +
                        "BALANCE IDIDI Dale 6\n" +
                        "BALANCE UON Shelly 12\n" +
                        "BALANCE MBI Harry 12\n"+
                        "exit\r\n";

        final String expectedOutput =
                "Welcome to Loan Modulator application.\n"+
                        "IDIDI Dale 1326 9\n" +
                        "IDIDI Dale 3652 4\n" +
                        "UON Shelly 15856 3\n" +
                        "MBI Harry 9044 10\n" +
                        "Thanks for using Loan Modulator application.\n";

        final ByteArrayInputStream in = new ByteArrayInputStream(commands.getBytes());
        System.setIn(in);

        Main.main(new String[] {});
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testBankAndLoanAccountMismatch() throws IOException {
        final String commands =
                "LOAN IDIDI Dale 5000 1 6\n" +
                        "PAYMENT IDIDI Dale 1000 5\n" +
                        "PAYMENT MBI Harry 5000 10\n" +
                        "BALANCE IDIDI Dale 3\n" +
                        "BALANCE IDIDI Dale 6\n" +
                        "BALANCE UON Shelly 12\n" +
                        "BALANCE MBI Harry 12\n"+
                        "exit\r\n";

        final String expectedOutput =
                "Welcome to Loan Modulator application.\n"+
                        "MBI either is not a bank or has not given any loan\n" +
                        "IDIDI Dale 1326 9\n" +
                        "IDIDI Dale 3652 4\n" +
                        "UON is invalid\n\n" +
                        "MBI is invalid\n\n" +
                        "Thanks for using Loan Modulator application.\n";

        final ByteArrayInputStream in = new ByteArrayInputStream(commands.getBytes());
        System.setIn(in);

        Main.main(new String[] {});
        assertEquals(expectedOutput, outContent.toString());
    }
}