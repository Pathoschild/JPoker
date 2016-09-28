import com.jplamondonw.jpoker.framework.Card;
import com.jplamondonw.jpoker.framework.Rank;
import com.jplamondonw.jpoker.framework.Suit;
import org.junit.Assert;
import org.junit.Test;

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
}
