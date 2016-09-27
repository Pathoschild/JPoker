import com.jplamondonw.jpoker.models.Card;
import com.jplamondonw.jpoker.models.Deck;
import com.jplamondonw.jpoker.models.Rank;
import com.jplamondonw.jpoker.models.Suit;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Unit tests for a deck of cards.
 */
public class DeckTests {
    // Constants
    //******************************
    /**
     * The number of cards in a standard deck.
     */
    private static final int DECK_SIZE = 52;

    /**
     * The number of cards per suit in a standard deck.
     */
    private static final int CARDS_PER_SUIT = 13;

    /**
     * The number of cards per rank in a standard deck.
     */
    private static final int CARDS_PER_RANK = 4;


    // Tests
    //******************************
    /**
     * Assert that a new deck has the correct initial values, returns valid cards, and updates its values as you draw.
     */
    @Test
    public void newDeck_drawsCorrectly()
    {
        Deck deck = new Deck();

        //  test initial values
        Assert.assertEquals("The new deck has the wrong number of cards.", DECK_SIZE, deck.size());
        Assert.assertEquals("The new deck is unexpectedly empty.", false, deck.empty());

        // draw cards
        int totalDrawn = 0;
        while(!deck.empty())
        {
            Card card = deck.drawCard();
            totalDrawn++;

            Assert.assertNotNull("The deck unexpectedly returned a null card.", card);
            Assert.assertEquals("The deck returned an unexpected count after drawing cards.", DECK_SIZE - totalDrawn, deck.size());
        }

        // validate end values
        Assert.assertEquals("The deck has the wrong number of cards after drawing all of them.", 0, deck.size());
        Assert.assertEquals("The new deck is not empty after drawing all cards.", deck.empty(), true);
    }

    /**
     * Assert that a new deck has the expected composition.
     */
    @Test
    public void newDeck_isStandard()
    {
        // set up tracking
        HashMap<Suit, Integer> suits = new HashMap<>();
        HashMap<Rank, Integer> ranks = new HashMap<>();
        for(Suit suit : Suit.values())
            suits.put(suit, 0);
        for(Rank rank : Rank.values())
            ranks.put(rank, 0);
        int total = 0;

        // draw every card from the deck
        Deck deck = new Deck();
        Assert.assertEquals("The new deck has the wrong number of cards.", DECK_SIZE, deck.size());
        while(!deck.empty())
        {
            Card card = deck.drawCard();
            Assert.assertNotNull("The deck unexpectedly returned a null card.", card);

            total++;
            suits.put(card.suit, suits.get(card.suit) + 1);
            ranks.put(card.rank, ranks.get(card.rank) + 1);
        }

        // test composition
        Assert.assertEquals("The deck returned an unexpected number of cards.", DECK_SIZE, total);
        for(Suit suit : Suit.values())
            Assert.assertEquals("The deck has the wrong number of cards in the " + suit.symbol + " suit.", (long)CARDS_PER_SUIT, (long)suits.get(suit));
        for(Rank rank : Rank.values())
            Assert.assertEquals("The deck has the wrong number of cards with the " + rank.symbol + " rank.", (long)CARDS_PER_RANK, (long)ranks.get(rank));
    }

    /**
     * Assert that a shuffle randomly reorders the deck.
     * Since we use the Java shuffle logic under the hood, checking the quality of the shuffle is beyond the scope of
     * this unit test; instead we just need to ensure that they were shuffled at all.
     */
    @Test
    public void newDeck_isShuffled()
    {
        // get left deck
        ArrayList<String> leftValues = new ArrayList<>();
        {
            Deck deck = new Deck();
            while(!deck.empty())
            {
                Card card = deck.drawCard();
                leftValues.add(card.suit.symbol + card.rank.symbol);
            }
        }

        // get right deck
        ArrayList<String> rightValues = new ArrayList<>();
        {
            Deck deck = new Deck();
            while(!deck.empty())
            {
                Card card = deck.drawCard();
                rightValues.add(card.suit.symbol + card.rank.symbol);
            }
        }

        // compare decks
        Assert.assertThat("Two shuffled decks are unexpectedly equivalent.", leftValues, IsNot.not(IsEqual.equalTo(rightValues)));
    }
}
