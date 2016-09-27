package com.jplamondonw.jpoker.models;

import java.util.Collections;
import java.util.Stack;

/**
 * The display and game logic for a deck of card.
 */
public class Deck {
    // Properties
    //******************************
    /**
     * The cards in the deck.
     */
    private final Stack<Card> cards;


    // Public methods
    //******************************
    /**
     * Construct an instance.
     */
    public Deck()
    {
        // construct standard deck
        this.cards = new Stack<>();
        for(Rank rank : Rank.values()) {
            for(Suit suit : Suit.values())
                this.cards.add(new Card(suit, rank));
        }
    }

    /**
     * Get the number of cards in the deck.
     */
    public int size()
    {
        return this.cards.size();
    }

    /**
     * Get whether the deck is empty.
     */
    public boolean empty()
    {
        return this.cards.empty();
    }

    /**
     * Shuffle the cards in the deck.
     */
    public void shuffle()
    {
        Collections.shuffle(this.cards);
    }

    /**
     * Draw the top card from the deck.
     */
    public Card drawCard()
    {
        return this.cards.pop();
    }
}