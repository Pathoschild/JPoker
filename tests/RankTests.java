import com.jplamondonw.jpoker.framework.Constants;
import com.jplamondonw.jpoker.framework.Rank;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

/**
 * Unit tests for ranks.
 */
public class RankTests {
    // Tests
    //******************************
    /**
     * Assert that each rank has a unique symbol and value.
     */
    @Test
    public void areUnique()
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
     * Assert that rank layouts match the correct size.
     */
    @Test
    public void layoutsAreValid()
    {
        for(Rank rank : Rank.values())
        {
            String[] layout = rank.layout.split("\n");
            Assert.assertEquals("Rank " + rank.symbol + " has an invalid card layout height.", Constants.CARD_LAYOUT_HEIGHT, layout.length);
            for(int i = 0; i < layout.length; i++)
                Assert.assertEquals("Rank " + rank.symbol + " has an invalid card layout width on line " + i + 1 + ".", Constants.CARD_LAYOUT_WIDTH, layout[i].length());
        }
    }

    /**
     * Assert that ranks can correctly check consecutive ranks.
     */
    @Test
    public void detectConsecutiveRanks()
    {
        // consecutive
        Assert.assertTrue("Failed to detect consecutive values with no values.", Rank.areConsecutive(new Rank[0]));
        Assert.assertTrue("Failed to detect consecutive values with a single value.", Rank.areConsecutive(new Rank[] { Rank.A }));
        Assert.assertTrue("Failed to detect consecutive values with all ranks in ascending order.", Rank.areConsecutive(new Rank[] { Rank.A, Rank.TWO, Rank.THREE, Rank.FOUR, Rank.FIVE, Rank.SIX, Rank.SEVEN, Rank.EIGHT, Rank.NINE, Rank.TEN, Rank.JACK, Rank.QUEEN, Rank.KING }));
        Assert.assertTrue("Failed to detect consecutive values with all ranks in ascending order.", Rank.areConsecutive(new Rank[] { Rank.KING, Rank.QUEEN, Rank.JACK, Rank.TEN, Rank.NINE, Rank.EIGHT, Rank.SEVEN, Rank.SIX, Rank.FIVE, Rank.FOUR, Rank.THREE, Rank.TWO, Rank.A }));

        // not consecutive
        Assert.assertFalse("Incorrectly detected consecutive values between Ace and King.", Rank.areConsecutive(new Rank[] { Rank.A, Rank.KING }));
        Assert.assertFalse("Incorrectly detected consecutive values for a list of ranks that begins consecutively.", Rank.areConsecutive(new Rank[] { Rank.A, Rank.TWO, Rank.THREE, Rank.FIVE}));
        Assert.assertFalse("Incorrectly detected consecutive values for a list of ranks that ends consecutively.", Rank.areConsecutive(new Rank[] { Rank.A, Rank.THREE, Rank.FOUR, Rank.FIVE }));
    }
}
