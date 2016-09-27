import com.jplamondonw.jpoker.models.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Unit tests for a player's hand.
 */
public class HandTests {
    // Unit tests
    //******************************
    /**
     * Assert that a hand correctly adds and peeks cards.
     */
    @Test
    public void managesCardsCorrectly()
    {
        // test empty hand
        Hand hand = new Hand();
        Assert.assertEquals("The empty hand returned an unexpected number of cards.", 0, hand.peek().length);

        // test first card
        hand.add(new Card(Suit.HEARTS, Rank.A));
        Assert.assertEquals("The hand returned an unexpected number of cards after adding one card.", 1, hand.peek().length);

        // test second card
        hand.add(new Card(Suit.HEARTS, Rank.A));
        Assert.assertEquals("The hand returned an unexpected number of cards after adding two cards.", 2, hand.peek().length);

        // test hand with community cards
        Hand fullHand = hand.getFullHand(Arrays.asList(new Card(Suit.DIAMONDS, Rank.EIGHT), new Card(Suit.CLUBS, Rank.KING), new Card(Suit.SPADES, Rank.JACK)));
        Assert.assertNotNull("The hand returned a null full hand.", fullHand);
        Assert.assertEquals("The hand incorrectly changes its own card count when constructing a full hand.", 2, hand.peek().length);
        Assert.assertEquals("The hand with community cards returned an unexpected number of cards.", 5, fullHand.peek().length);
    }

    /**
     * Assert that a hand correctly calculates the total face value of its cards.
     */
    @Test
    public void calculatesTotalFaceValue()
    {
        Hand hand = this.getHand(
            new Card(Suit.CLUBS, Rank.A),
            new Card(Suit.CLUBS, Rank.TWO),
            new Card(Suit.CLUBS, Rank.THREE),
            new Card(Suit.CLUBS, Rank.FOUR),
            new Card(Suit.CLUBS, Rank.FIVE),
            new Card(Suit.CLUBS, Rank.SIX),
            new Card(Suit.CLUBS, Rank.SEVEN),
            new Card(Suit.CLUBS, Rank.EIGHT),
            new Card(Suit.CLUBS, Rank.NINE),
            new Card(Suit.CLUBS, Rank.TEN),
            new Card(Suit.CLUBS, Rank.JACK),
            new Card(Suit.CLUBS, Rank.QUEEN),
            new Card(Suit.CLUBS, Rank.KING)
        );

        Assert.assertEquals("The hand calculated wrong total face value.", 91, hand.getTotalFaceValue());
    }

    /**
     * Assert that the hand correctly detects a royal flush.
     */
    @Test
    public void detectsRoyalFlush()
    {
        Hand hand = this.getHand(
            new Card(Suit.HEARTS, Rank.A),
            new Card(Suit.HEARTS, Rank.QUEEN),
            new Card(Suit.HEARTS, Rank.TEN),
            new Card(Suit.HEARTS, Rank.KING),
            new Card(Suit.HEARTS, Rank.JACK)
        );
        Assert.assertEquals("The wrong hand type was detected.", HandType.RoyalFlush, hand.getHandType());
    }

    /**
     * Assert that the hand correctly detects a straight flush.
     */
    @Test
    public void detectsStraightFlush()
    {
        Hand hand = this.getHand(
            new Card(Suit.HEARTS, Rank.A),
            new Card(Suit.HEARTS, Rank.TWO),
            new Card(Suit.HEARTS, Rank.THREE),
            new Card(Suit.HEARTS, Rank.FOUR),
            new Card(Suit.HEARTS, Rank.FIVE)
        );
        Assert.assertEquals("The wrong hand type was detected.", HandType.StraightFlush, hand.getHandType());
    }

    /**
     * Assert that the hand correctly detects a four of a kind.
     */
    @Test
    public void detectsFourOfAKind()
    {
        Hand hand = this.getHand(
            new Card(Suit.HEARTS, Rank.A),
            new Card(Suit.HEARTS, Rank.A),
            new Card(Suit.HEARTS, Rank.A),
            new Card(Suit.HEARTS, Rank.A),
            new Card(Suit.HEARTS, Rank.FIVE)
        );
        Assert.assertEquals("The wrong hand type was detected.", HandType.FourOfAKind, hand.getHandType());
    }

    /**
     * Assert that the hand correctly detects a full house.
     */
    @Test
    public void detectsFullHouse()
    {
        Hand hand = this.getHand(
                new Card(Suit.HEARTS, Rank.A),
                new Card(Suit.SPADES, Rank.A),
                new Card(Suit.CLUBS, Rank.A),
                new Card(Suit.HEARTS, Rank.FIVE),
                new Card(Suit.HEARTS, Rank.FIVE)
        );
        Assert.assertEquals("The wrong hand type was detected.", HandType.FullHouse, hand.getHandType());
    }

    /**
     * Assert that the hand correctly detects a flush.
     */
    @Test
    public void detectsFlush()
    {
        Hand hand = this.getHand(
                new Card(Suit.HEARTS, Rank.A),
                new Card(Suit.HEARTS, Rank.TWO),
                new Card(Suit.HEARTS, Rank.FOUR),
                new Card(Suit.HEARTS, Rank.FIVE),
                new Card(Suit.HEARTS, Rank.SIX)
        );
        Assert.assertEquals("The wrong hand type was detected.", HandType.Flush, hand.getHandType());
    }

    /**
     * Assert that the hand correctly detects a straight.
     */
    @Test
    public void detectsStraight()
    {
        Hand hand = this.getHand(
                new Card(Suit.HEARTS, Rank.A),
                new Card(Suit.SPADES, Rank.TWO),
                new Card(Suit.HEARTS, Rank.THREE),
                new Card(Suit.HEARTS, Rank.FOUR),
                new Card(Suit.HEARTS, Rank.FIVE)
        );
        Assert.assertEquals("The wrong hand type was detected.", HandType.Straight, hand.getHandType());
    }

    /**
     * Assert that the hand correctly detects a three of a kind.
     */
    @Test
    public void detectsThreeOfAKind()
    {
        Hand hand = this.getHand(
                new Card(Suit.HEARTS, Rank.A),
                new Card(Suit.SPADES, Rank.A),
                new Card(Suit.HEARTS, Rank.A),
                new Card(Suit.HEARTS, Rank.FOUR),
                new Card(Suit.HEARTS, Rank.FIVE)
        );
        Assert.assertEquals("The wrong hand type was detected.", HandType.ThreeOfAKind, hand.getHandType());
    }

    /**
     * Assert that the hand correctly detects a three of a kind.
     */
    @Test
    public void detectsTwoPair()
    {
        Hand hand = this.getHand(
                new Card(Suit.HEARTS, Rank.A),
                new Card(Suit.SPADES, Rank.A),
                new Card(Suit.HEARTS, Rank.FOUR),
                new Card(Suit.HEARTS, Rank.FOUR),
                new Card(Suit.HEARTS, Rank.FIVE)
        );
        Assert.assertEquals("The wrong hand type was detected.", HandType.TwoPair, hand.getHandType());
    }

    /**
     * Assert that the hand correctly detects a one pair.
     */
    @Test
    public void detectsOnePair()
    {
        Hand hand = this.getHand(
                new Card(Suit.HEARTS, Rank.A),
                new Card(Suit.SPADES, Rank.A),
                new Card(Suit.HEARTS, Rank.FOUR),
                new Card(Suit.HEARTS, Rank.FIVE),
                new Card(Suit.HEARTS, Rank.SIX)
        );
        Assert.assertEquals("The wrong hand type was detected.", HandType.OnePair, hand.getHandType());
    }

    /**
     * Assert that the hand correctly defaults to high card.
     */
    @Test
    public void defaultsToHighCard()
    {
        Hand hand = this.getHand(
                new Card(Suit.HEARTS, Rank.A),
                new Card(Suit.SPADES, Rank.THREE),
                new Card(Suit.CLUBS, Rank.FIVE),
                new Card(Suit.DIAMONDS, Rank.SEVEN),
                new Card(Suit.HEARTS, Rank.NINE)
        );
        Assert.assertEquals("The wrong hand type was detected.", HandType.HighCard, hand.getHandType());
    }

    // Private methods
    //******************************
    /**
     * Build a hand containing the given cards.
     * @param cards The cards which the hand should contain.
     */
    private Hand getHand(Card... cards)
    {
        Hand hand = new Hand();
        for(Card card : cards)
            hand.add(card);
        return hand;
    }
}
