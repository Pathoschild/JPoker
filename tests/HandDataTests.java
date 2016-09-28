import com.jplamondonw.jpoker.framework.HandData;
import com.jplamondonw.jpoker.framework.Card;
import com.jplamondonw.jpoker.framework.Rank;
import com.jplamondonw.jpoker.framework.Suit;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for the hand analyser.
 */
public class HandDataTests {
    /**
     * Assert that the hand analyser returns the expected metrics.
     */
    @Test
    public void calculatesCorrectData()
    {
        // analyse sample cards
        HandData data = new HandData(new Card[] {
            new Card(Suit.HEARTS, Rank.A),
            new Card(Suit.CLUBS, Rank.JACK),
            new Card(Suit.CLUBS, Rank.JACK),
            new Card(Suit.CLUBS, Rank.A),
            new Card(Suit.HEARTS, Rank.EIGHT)
        });

        // assert data
        Assert.assertArrayEquals("The ranks don't match the expected values, or aren't sorted by ascending value.", new Rank[] { Rank.A, Rank.EIGHT, Rank.JACK }, data.ranks);
        Assert.assertArrayEquals("The suits don't match the expected values, or aren't sorted by ascending symbol.", new Suit[] { Suit.CLUBS, Suit.HEARTS }, data.suits);

        // assert metrics
        Assert.assertEquals("The rank count is incorrect.", 3, data.totalRanks);
        Assert.assertEquals("The suit count is incorrect.", 2, data.totalSuits);
        Assert.assertEquals("The total value is incorrect.", 32, data.totalValue);

        // assert rank counts
        Assert.assertEquals("The number of rank counts is incorrect.", 3, data.rankCounts.length);
        Assert.assertEquals("The first rank count is incorrect.", 2, data.rankCounts[0]);
        Assert.assertEquals("The second rank count is incorrect.", 2, data.rankCounts[1]);
        Assert.assertEquals("The third rank count is incorrect.", 1, data.rankCounts[2]);
    }
}
