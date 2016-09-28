package com.jplamondonw.jpoker;

import com.jplamondonw.jpoker.framework.Card;
import com.jplamondonw.jpoker.framework.ConsoleHelper;
import com.jplamondonw.jpoker.framework.Deck;
import com.jplamondonw.jpoker.framework.Hand;

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

        // set up cards
        Deck deck = new Deck();

        // run console tests
        if(Arrays.asList(args).contains("test"))
        {
            // show unicode test
            console.out.println("Unicode test");
            console.out.println("---------------------------------");
            console.out.println("┌───────┐\n│ ♦♣ ♥♠ │\n└───────┘");
            console.out.println();

            // show hand test
            console.out.println("Hand draw test");
            console.out.println("---------------------------------");
            Hand hand = new Hand();
            hand.add(Arrays.asList(deck.drawCard(), deck.drawCard(), deck.drawCard(), deck.drawCard()));
            for(String line : hand.getLayout())
                console.out.println(line);

            // show card test
            console.out.println("Shuffled cards");
            console.out.println("---------------------------------");
            console.out.println("cards: " + deck.size());
            while(!deck.empty())
            {
                Card card = deck.drawCard();
                for(String line : card.getLayout())
                    console.out.println(line);
            }

            // end game
            return;
        }
    }
}
