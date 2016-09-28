package com.jplamondonw.jpoker;

import com.jplamondonw.jpoker.framework.ConsoleHelper;
import com.jplamondonw.jpoker.framework.Constants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        this.log.add("Welcome to Texas Hold'em heads-up tournament style! We'll be");
        this.log.add("playing with a few house rules. (If you know the standard rules,");
        this.log.add("you'll be fine.)");

        // start game loop
        while(true)
        {
            // confirm game start
            this.log.add("Ready? [y]es [n]o");
            this.draw();
            if (this.getChoice("y", "n").equals("n"))
            {
                this.log.add("Good bye. :)");
                this.draw();
                break;
            }
            this.log.clear();

            // randomly choose dealer
            this.board.isUserTurn = new Random().nextBoolean();
            if(this.board.isUserTurn)
                this.log.add("Rolling the dice... you go first!");
            else
                this.log.add("Rolling the dice... they go first.");

            // end game
            break;
        }
    }

    // Private methods
    //******************************
    /**
     * Draw the current game state.
     */
    private void draw()
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
        this.console.clearFromCursorLine();
        this.console.out.print("> ");
        return this.console.console.readLine();
    }

    /**
     * Get the user's choice.
     * @param choices The available choices. These are not case-sensitive.
     */
    private String getChoice(String... choices)
    {
        // get case-insensitive choices
        List<String> choiceLookup = new ArrayList<>();
        for(String choice : choices)
            choiceLookup.add(choice.toLowerCase());

        // get valid input
        while(true)
        {
            // draw input
            String choice = this.getInput().toLowerCase();
            if(choiceLookup.contains(choice))
                return choice;
        }
    }
}
