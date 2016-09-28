package com.jplamondonw.jpoker.framework;

/**
 *  The possible card suits.
 */
public enum Suit {
    // Constants
    //******************************
    CLUBS("♣", "black"),
    DIAMONDS("♦", "red"),
    HEARTS("♥", "red"),
    SPADES("♠", "black");


    // Properties
    //******************************
    /**
     * The display symbol.
     */
    public final String symbol;

    /**
     * The suit color.
     */
    public final String color;


    // Public methods
    //******************************
    /**
     * Construct an instance.
     * @param symbol The display symbol.
     * @param color The suit color.
     */
    Suit(String symbol, String color) {
        this.symbol = symbol;
        this.color = color;
    }
}
