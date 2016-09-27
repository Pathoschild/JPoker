import com.jplamondonw.jpoker.models.Card;
import com.jplamondonw.jpoker.models.Rank;
import com.jplamondonw.jpoker.models.Suit;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

/**
 * Unit tests for a card.
 */
public class CardTests {
    // Tests
    //******************************
    /**
     * Assert that a new card sets the correct values.
     */
    @Test
    public void newCard_hasCorrectValues() {
        for(Suit suit : Suit.values())
        {
            for(Rank rank : Rank.values())
            {
                Card card = new Card(suit, rank);
                Assert.assertEquals("The suit is unexpectedly different after card creation.", suit.symbol, card.suit.symbol);
                Assert.assertEquals("The rank is unexpectedly different after card creation.", rank.symbol, card.rank.symbol);
            }
        }
    }

    /**
     * Assert that each rank has a unique symbol and value.
     */
    @Test
    public void ranks_areUnique()
    {
        HashMap<String, Boolean> symbols = new HashMap<>();
        HashMap<Integer, Boolean> values = new HashMap<>();

        for(Rank rank : Rank.values())
        {
            Assert.assertFalse("The rank symbol " + rank.symbol + " is used for multiple ranks.", symbols.containsKey(rank.symbol));
            Assert.assertFalse("The rank value " + rank.value + " is used for multiple ranks.", values.containsKey(rank.value));

            symbols.put(rank.symbol, true);
            values.put(rank.value, true);
        }
    }

    /**
     * Assert that each suit has a unique symbol.
     */
    @Test
    public void suits_areUnique()
    {
        HashMap<String, Boolean> symbols = new HashMap<>();

        for(Suit suit : Suit.values())
        {
            Assert.assertFalse("The suit symbol " + suit.symbol + " is used for multiple ranks.", symbols.containsKey(suit.symbol));
            symbols.put(suit.symbol, true);
        }
    }
}
