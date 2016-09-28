package com.jplamondonw.jpoker;

import com.jplamondonw.jpoker.framework.ConsoleHelper;

import java.io.IOException;

/**
 * The entry point for the game.
 */
public class Main
{
    // Public methods
    //******************************
    /**
     * The entry point for the game, which displays the test output or launches the game depending on the input arguments.
     * @param args The command-line arguments.
     * @throws IOException An error occurred interacting with the Windows console.
     * @throws InterruptedException An error occurred interacting with the Windows console.
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        // set up console
        ConsoleHelper console = new ConsoleHelper();
        console.initialise();
        console.clear();

        // start & draw game
        new Game(console).run();
    }
}
