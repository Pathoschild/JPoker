package com.jplamondonw.jpoker;

import com.jplamondonw.jpoker.framework.ConsoleHelper;
import com.jplamondonw.jpoker.framework.Constants;
import com.jplamondonw.jpoker.framework.Deck;

import java.io.IOException;
import java.util.Arrays;

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
        if(Arrays.asList(args).contains("test"))
            Main.drawTestMode(console);
        else
            new Game(console).run();
    }

    // Private methods
    //******************************
    /**
     * Draw test output to the screen.
     * @param console The console to which to draw.
     */
    private static void drawTestMode(ConsoleHelper console)
    {
        GameBoard board = new GameBoard();
        Deck deck = board.deck;
        board.bot.hand.add(Arrays.asList(deck.drawCard(), deck.drawCard()));
        board.user.hand.add(Arrays.asList(deck.drawCard(), deck.drawCard()));
        board.communityCards.add(Arrays.asList(deck.drawCard(), deck.drawCard(), deck.drawCard(), deck.drawCard(), deck.drawCard()));
        board.draw(console, Constants.ScreenLayout.GAME_BOARD.y, Constants.ScreenLayout.GAME_BOARD.x);
        console.setCursor(Constants.ScreenLayout.USER_INPUT);
    }
}
