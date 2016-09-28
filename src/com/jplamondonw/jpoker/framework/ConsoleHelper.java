package com.jplamondonw.jpoker.framework;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

/**
 * A minimal class for interacting with the game console.
 */
public class ConsoleHelper
{
    // Public properties
    //******************************
    /**
     * The console input stream.
     */
    public final InputStream in;

    /**
     * The console output stream.
     */
    public final PrintStream out;

    // Private properties
    //******************************
    /**
     * Whether to use Windows console commands.
     */
    private final boolean isWindows;

    /**
     * The ANSI character which begins a terminal command code.
     */
    private static final char START_TERMINAL_CODE = 0x1B;

    /**
     * The ANSI sequence which moves the cursor to (0, 0).
     */
    private static final String GO_TO_HOME = START_TERMINAL_CODE + "[H";

    /**
     * The ANSI sequence which clears all text from the current line to the bottom of the screen.
     */
    private static final String ERASE_DOWN = START_TERMINAL_CODE + "[J";

    /**
     * The ANSI sequence which moves the cursor to the specified (x,y) coordinate. Should be formatted with the Y and X
     * position (in that order).
     */
    private static final String GO_TO = START_TERMINAL_CODE + "[%d;%df";


    // Public methods
    //******************************
    /**
     * Construct an instance.
     * @throws UnsupportedEncodingException The console doesn't support Unicode output.
     */
    public ConsoleHelper() throws UnsupportedEncodingException
    {
        this.in = System.in;
        this.out = new PrintStream(System.out, true, "UTF-8");
        this.isWindows = System.getProperty("os.name").contains("Windows");
    }

    /**
     * Initialise the console for output.
     * @throws IOException An error occurred initialising the Windows console.
     * @throws InterruptedException An error occurred initialising the Windows console.
     */
    public void initialise() throws IOException, InterruptedException
    {
        // switch console to Unicode
        if(isWindows)
            new ProcessBuilder("cmd", "/c", "chcp 65001").inheritIO().start().waitFor();
    }

    /**
     * Clear the console output.
     * @throws IOException An error occurred clearing the Windows console.
     * @throws InterruptedException An error occurred clearing the Windows console.
     */
    public void clear() throws IOException, InterruptedException
    {
        this.out.print(GO_TO_HOME + ERASE_DOWN);
    }

    /**
     * Move the cursor to the specified position.
     * @param row The row position (starting at 1).
     * @param col The column position (starting at 1).
     */
    public void setCursor(int row, int col)
    {
        this.out.print(String.format(GO_TO, row, col));
    }
}
