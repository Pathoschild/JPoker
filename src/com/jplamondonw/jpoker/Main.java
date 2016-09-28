package com.jplamondonw.jpoker;

import com.jplamondonw.jpoker.framework.ConsoleHelper;
import com.jplamondonw.jpoker.framework.Deck;
import com.jplamondonw.jpoker.framework.Hand;

import java.io.IOException;
import java.util.Arrays;

/**
 * The entry point for the poker game.
 */
public class Main
{
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
            // show hand test
            {
                console.out.println("Hand draw test");
                console.out.println("---------------------------------");
                Deck deck = new Deck();
                Hand hand = new Hand();
                hand.add(Arrays.asList(deck.drawCard(), deck.drawCard(), deck.drawCard(), deck.drawCard(), deck.drawCard()));
                hand.draw(console, 3, 1);
            }

            // end game
            return;
        }
    }
}
