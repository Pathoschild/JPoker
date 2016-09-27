package com.jplamondonw.jpoker.models;

/**
 * The possible winning hands.
 */
public enum HandType {
    // Constants
    //******************************
    /**
     * Ace, King, Queen, Jack and 10 of the same suit.
     */
    RoyalFlush(900, "royal flush"),

    /**
     * Five consecutive cards of the same suit.
     */
    StraightFlush(800, "straight flush"),

    /**
     * Four cards with the same rank.
     */
    FourOfAKind(700, "four of a kind"),

    /**
     * Three cards of the same rank together with two cards of the same rank.
     */
    FullHouse(600, "full house"),

    /**
     * Five cards of the same suit (if not consecutive).
     */
    Flush(500, "flush"),

    /**
     * Five consecutive cards (if not the same suit).
     */
    Straight(400, "straight"),

    /**
     * Three cards of the same rank.
     */
    ThreeOfAKind(300, "three of a kind"),

    /**
     * Two cards of the same rank together with two other cards of the same rank.
     */
    TwoPair(200, "two pair"),

    /**
     * Two cards of the same rank.
     */
    OnePair(100, "one pair"),

    /**
     * Highest card value.
     */
    HighCard(0, "high card");


    // Properties
    //******************************
    /**
     * An arbitrary value used to compare hands, where higher values win.
     */
    public final int value;

    /**
     * The hand's display name.
     */
    public final String name;


    // Public methods
    //******************************
    /**
     * Construct an instance.
     * @param value An arbitrary value used to compare hands, where higher values win.
     * @param name The hand's display name.
     */
    HandType(int value, String name) {
        this.value = value;
        this.name = name;
    }
}
