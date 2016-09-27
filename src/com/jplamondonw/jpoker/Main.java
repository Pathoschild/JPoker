package com.jplamondonw.jpoker;

import java.io.IOException;
import java.util.Arrays;

/**
 * The entry point for the poker game.
 */
public class Main {
    /**
     * The entry point for the poker game.
      * @param args The command-line arguments.
     * @throws IOException An error occurred interacting with the Windows console.
     * @throws InterruptedException An error occurred interacting with the Windows console.
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        // set up console
        ConsoleHelper console = new ConsoleHelper();
        console.initialise();
        console.clear();

        // run console tests
        if(Arrays.asList(args).contains("test"))
        {
            console.out.println("┌───────┐\n│ ♦♣ ♥♠ │\n└───────┘");
            console.out.println("Print test done.");
            return;
        }
    }
}
