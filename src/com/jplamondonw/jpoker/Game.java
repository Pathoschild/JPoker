package com.jplamondonw.jpoker;

import com.jplamondonw.jpoker.framework.ConsoleHelper;
import com.jplamondonw.jpoker.framework.Constants;

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
    public void run()
    {
        board.draw(console, Constants.ScreenLayout.GAME_BOARD.y, Constants.ScreenLayout.GAME_BOARD.x);
        this.getInput("Enter anything to end the game.");
    }

    // Private methods
    //******************************
    /**
     * Get input from the user.
     * @param question The question to print.
     */
    private String getInput(String question)
    {
        this.console.setCursor(Constants.ScreenLayout.USER_INPUT);
        this.console.out.println(question);
        this.console.setCursor(Constants.ScreenLayout.USER_INPUT.y + 1, Constants.ScreenLayout.USER_INPUT.x);
        return this.console.console.readLine();
    }
}
