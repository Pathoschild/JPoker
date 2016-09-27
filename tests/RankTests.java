import com.jplamondonw.jpoker.models.Rank;
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
     * Assert that ranks can correctly check consecutive ranks.
     */
    @Test
    public void detectConsecutiveRanks()
    {
        // consecutive
        Assert.assertTrue("Failed to detect consecutive values with no values.", Rank.areConsecutive());
        Assert.assertTrue("Failed to detect consecutive values with a single value.", Rank.areConsecutive(Rank.A));
        Assert.assertTrue("Failed to detect consecutive values with all ranks in ascending order.", Rank.areConsecutive(Rank.A, Rank.TWO, Rank.THREE, Rank.FOUR, Rank.FIVE, Rank.SIX, Rank.SEVEN, Rank.EIGHT, Rank.NINE, Rank.TEN, Rank.JACK, Rank.QUEEN, Rank.KING));
        Assert.assertTrue("Failed to detect consecutive values with all ranks in ascending order.", Rank.areConsecutive(Rank.KING, Rank.QUEEN, Rank.JACK, Rank.TEN, Rank.NINE, Rank.EIGHT, Rank.SEVEN, Rank.SIX, Rank.FIVE, Rank.FOUR, Rank.THREE, Rank.TWO, Rank.A));

        // not consecutive
        Assert.assertFalse("Incorrectly detected consecutive values between Ace and King.", Rank.areConsecutive(Rank.A, Rank.KING));
        Assert.assertFalse("Incorrectly detected consecutive values for a list of ranks that begins consecutively.", Rank.areConsecutive(Rank.A, Rank.TWO, Rank.THREE, Rank.FIVE));
        Assert.assertFalse("Incorrectly detected consecutive values for a list of ranks that ends consecutively.", Rank.areConsecutive(Rank.A, Rank.THREE, Rank.FOUR, Rank.FIVE));
    }
}
