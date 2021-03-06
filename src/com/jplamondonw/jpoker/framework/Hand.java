package com.jplamondonw.jpoker.framework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * A player's hand of cards.
 */
public class Hand implements Drawable
{
    // Public properties
    //******************************
    /**
     * Whether the hand's cards should be drawn face-down.
     */
    public final boolean isFaceDown;


    // Private Properties
    //******************************
    /**
     * The cards in the hand.
     */
    private final List<Card> cards = new ArrayList<>();

    /**
     * The cached hand data.
     */
    private HandData data;


    // Public methods
    //******************************
    /**
     * Construct an instance.
     */
    public Hand()
    {
        this(false);
    }

    /**
     * Construct an instance.
     * @param isFaceDown Whether the hand's cards should be drawn face-down.
     */
    public Hand(boolean isFaceDown)
    {
        this.isFaceDown = isFaceDown;
    }

    /**
     * Add a card to the player's hand.
     * @param card The card to add.
     */
    public void add(Card card)
    {
        this.cards.add(new Card(card.suit, card.rank, this.isFaceDown));
        this.sort();
        this.data = null;
    }

    /**
     * Add cards to the player's hand.
     * @param cards The cards to add.
     */
    public void add(List<Card> cards)
    {
        for(Card card : cards)
            this.cards.add(new Card(card.suit, card.rank, this.isFaceDown));
        this.sort();
        this.data = null;
    }

    /**
     * Get a list of cards in the hand.
     */
    public Card[] peek()
    {
        return cards.toArray(new Card[0]);
    }

    /**
     * Get the player's full hand, including community cards.
     * @param communityCards The available community cards.
     */
    public Hand getFullHand(List<Card> communityCards)
    {
        Hand hand = new Hand(this.isFaceDown);
        hand.add(this.cards);
        hand.add(communityCards);
        return hand;
    }

    /**
     * Get the type of the player's hand (e.g. royal flush).
     */
    public HandType getHandType()
    {
        // none possible
        if(this.cards.size() < 5)
            return HandType.HighCard;

        // get data
        HandData data = this.getMetadata();
        HashSet<Rank> ranks = new HashSet<>(Arrays.asList(data.ranks));

        // get hand type
        if(data.totalSuits == 1 && ranks.contains(Rank.A) && ranks.contains(Rank.KING) && ranks.contains(Rank.QUEEN) && ranks.contains(Rank.JACK) && ranks.contains(Rank.TEN))
            return HandType.RoyalFlush;
        if(data.totalSuits == 1 && Rank.areConsecutive(data.ranks))
            return HandType.StraightFlush;
        if(data.rankCounts[0] == 4)
            return HandType.FourOfAKind;
        if(data.totalRanks == 2 && data.rankCounts[0] == 3 && data.rankCounts[1] == 2)
            return HandType.FullHouse;
        if(data.totalSuits == 1)
            return HandType.Flush;
        if(Rank.areConsecutive(data.ranks))
            return HandType.Straight;
        if(data.rankCounts[0] == 3)
            return HandType.ThreeOfAKind;
        if(data.totalRanks >= 2 && data.rankCounts[0] == 2 && data.rankCounts[1] == 2)
            return HandType.TwoPair;
        if(data.rankCounts[0] == 2)
            return HandType.OnePair;
        else
            return HandType.HighCard;
    }

    /**
     * Get the total face value of the cards in the hand.
     */
    public int getTotalFaceValue()
    {
        return this.getMetadata().totalValue;
    }

    /**
     * Show all cards in the hand.
     */
    public void showCards()
    {
        for(Card card : this.cards)
            card.isFaceDown = false;
    }

    /**
     * Remove all cards from the hand.
     */
    public void clear()
    {
        this.cards.clear();
    }

    /**
     * Draw the element at the specified position.
     * @param console The console to which to draw.
     * @param row The top row position.
     * @param col The left column position.
     */
    public void draw(ConsoleHelper console, int row, int col)
    {
        int leftOffset = 0;
        for(int i = 0, len = cards.size(); i < len; i++)
        {
            cards.get(i).draw(console, row, col + leftOffset);
            leftOffset += Constants.CARD_LAYOUT_WIDTH + Constants.CARD_BORDER_WIDTH * 2;
        }
    }


    // Private methods
    //******************************
    /**
     * Sort the cards in the hand.
     */
    private void sort()
    {
        this.cards.sort((a, b) -> b.rank.value - a.rank.value); // sort cards by higher to lower value
    }

    /**
     * Get metadata about the hand.
     */
    private HandData getMetadata()
    {
        if(this.data == null)
            this.data = new HandData(this.cards.toArray(new Card[0]));
        return this.data;
    }
}
