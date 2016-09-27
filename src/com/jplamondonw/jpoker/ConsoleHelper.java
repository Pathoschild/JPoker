package com.jplamondonw.jpoker;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

/**
 * A minimal class for interacting with the game console.
 */
class ConsoleHelper {
    // Properties
    //******************************
    /**
     * The console input stream.
     */
    public final InputStream in;

    /**
     * The console output stream.
     */
    public final PrintStream out;

    /**
     * Whether to use Windows console commands.
     */
    private final boolean isWindows;


    // Public methods
    //******************************
    /**
     * Construct an instance.
     * @throws UnsupportedEncodingException The console doesn't support Unicode output.
     */
    public ConsoleHelper() throws UnsupportedEncodingException {
        this.in = System.in;
        this.out = new PrintStream(System.out, true, "UTF-8");
        this.isWindows = System.getProperty("os.name").contains("Windows");
    }

    /**
     * Initialise the console for output.
     * @throws IOException An error occurred initialising the Windows console.
     * @throws InterruptedException An error occurred initialising the Windows console.
     */
    public void initialise() throws IOException, InterruptedException {
        // switch console to Unicode
        if(isWindows)
            new ProcessBuilder("cmd", "/c", "chcp 65001").inheritIO().start().waitFor();
    }

    /**
     * Clear the console output.
     * @throws IOException An error occurred clearing the Windows console.
     * @throws InterruptedException An error occurred clearing the Windows console.
     */
    public void clear() throws IOException, InterruptedException {
        if(this.isWindows)
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); // run 'cls' command
        else
            this.out.println("\033[H\033[2J"); // ANSI clear screen + home
    }
}
