package com.jplamondonw.jpoker.framework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * A player's hand of cards.
 */
public class Hand {
    // Properties
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
     * Add a card to the player's hand.
     * @param card The card to add.
     */
    public void add(Card card)
    {
        this.cards.add(card);
        this.sort();
        this.data = null;
    }

    /**
     * Add cards to the player's hand.
     * @param cards The cards to add.
     */
    public void add(List<Card> cards)
    {
        this.cards.addAll(cards);
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
        Hand hand = new Hand();
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
     * Get the layout representing the card.
     * @return Returns each line of the card layout.
     */
    public String[] getLayout()
    {
        // initialise lines
        List<String> layout = new ArrayList<>();
        for(int i = 0; i < Constants.CARD_LAYOUT_HEIGHT + Constants.CARD_BORDER_WIDTH; i++)
            layout.add("");

        // add card layouts
        int cardHeight = Constants.CARD_LAYOUT_HEIGHT + Constants.CARD_BORDER_WIDTH;
        for(Card card : cards)
        {
            String[] cardLayout = card.getLayout();
            for(int i = 0; i < cardLayout.length && i < cardHeight; i++)
                layout.set(i, layout.get(i) + cardLayout[i]);
        }
        return layout.toArray(new String[0]);
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
