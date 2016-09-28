package com.jplamondonw.jpoker;

import com.jplamondonw.jpoker.framework.ConsoleHelper;
import com.jplamondonw.jpoker.framework.Constants;

import java.io.IOException;

/**
 * Manages an ongoing game.
 */
public class Game
{
    // Properties
    //******************************
    /**
     * The console to which to draw and from which to get input.
     */
    private final ConsoleHelper console;

    /**
     * The underlying game board.
     */
    private final GameBoard board = new GameBoard();

    /**
     * The game log shown to the user.
     */
    private final GameLog log = new GameLog(5);


    // Public methods
    //******************************
    public Game(ConsoleHelper console)
    {
        this.console = console;
        this.board.draw(console, Constants.ScreenLayout.GAME_BOARD.y, Constants.ScreenLayout.GAME_BOARD.x);
    }

    /**
     * Run the game until it completes.
     */
    public void run() throws IOException, InterruptedException
    {
        // start game
        this.log.add("Starting game!");
        this.draw();

        // TODO

        // end game
        this.log.add("Enter anything to end the game.");
        this.draw();
        this.getInput();
    }

    // Private methods
    //******************************
    /**
     * Draw the current game state.
     */
    private void draw() throws IOException, InterruptedException
    {
        // clear screen
        this.console.clear();

        // draw elements
        this.board.draw(console, Constants.ScreenLayout.GAME_BOARD.y, Constants.ScreenLayout.GAME_BOARD.x);
        this.log.draw(console, Constants.ScreenLayout.GAME_LOG.y, Constants.ScreenLayout.GAME_LOG.x);

        // move cursor out of the way
        this.console.setCursor(Constants.ScreenLayout.USER_INPUT);
    }

    /**
     * Get input from the user.
     */
    private String getInput()
    {
        this.console.setCursor(Constants.ScreenLayout.USER_INPUT);
        this.console.out.print("> ");
        return this.console.console.readLine();
    }
}
