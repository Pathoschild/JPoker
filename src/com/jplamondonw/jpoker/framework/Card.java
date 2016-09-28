package com.jplamondonw.jpoker.framework;

/**
 * The display and game logic for a card.
 */
public class Card {
    // Properties
    //******************************
    /**
     * The card rank.
     */
    public final Rank rank;

    /**
     * The card suit.
     */
    public final Suit suit;


    // Public methods
    //******************************

    /**
     * Construct an instance.
     * @param suit The card suit.
     * @param rank The card rank.
     */
    public Card(Suit suit, Rank rank)
    {
        this.suit = suit;
        this.rank = rank;
    }

    /**
     * Get the layout representing the card.
     * @return Returns each line of the card layout.
     */
    public String[] getLayout()
    {
        String layout =
            "┌───────┐\n│ "
            + this.rank.layout.replace("%", this.suit.symbol).replace("\n", " │\n│ ")
            + " │\n└───────┘";
        return layout.split("\n");
    }
}
