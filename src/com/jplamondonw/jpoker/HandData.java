package com.jplamondonw.jpoker;

import com.jplamondonw.jpoker.models.Card;
import com.jplamondonw.jpoker.models.Rank;
import com.jplamondonw.jpoker.models.Suit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Calculates metrics and data for a set of cards.
 */
public class HandData {
    // Properties
    //******************************
    /**
     * The ranks in the hand.
     */
    public final Rank[] ranks;

    /**
     * The suits in the hand.
     */
    public final Suit[] suits;

    /**
     * The number of unique ranks.
     */
    public final int totalRanks;

    /**
     * The number of unique suits.
     */
    public final int totalSuits;

    /**
     * The number of cards in each distinct rank.
     */
    public final int[] rankCounts;

    /**
     * The total face value.
     */
    public final int totalValue;


    // Public methods
    //******************************
    /**
     * Construct an instance.
     * @param cards The cards to analyse.
     */
    public HandData(Card[] cards) {
        // get rank & suit counts
        HashMap<Rank, Integer> ranks = new HashMap<>();
        HashMap<Suit, Integer> suits = new HashMap<>();
        for(Card card : cards)
        {
            ranks.put(card.rank, ranks.containsKey(card.rank) ? ranks.get(card.rank) + 1 : 1);
            suits.put(card.suit, suits.containsKey(card.suit) ? suits.get(card.suit) + 1 : 1);
        }

        // save data
        {
            List<Rank> orderedRanks = new ArrayList<>(ranks.keySet());
            orderedRanks.sort((a, b) -> a.value - b.value); // sort by ascending value
            this.ranks = orderedRanks.toArray(new Rank[0]);
        }
        {
            List<Suit> orderedSuits = new ArrayList<>(suits.keySet());
            orderedSuits.sort((a, b) -> a.symbol.compareTo(b.symbol)); // sort by ascending symbol for consistent order
            this.suits = orderedSuits.toArray(new Suit[0]);
        }

        // save metrics
        this.totalRanks = ranks.size();
        this.totalSuits = suits.size();

        // save total value
        {
            int totalValue = 0;
            for (Card card : cards)
                totalValue += card.rank.value;
            this.totalValue = totalValue;
        }

        // save rank counts
        {
            List<Integer> values = new ArrayList<>(ranks.values());
            values.sort((a, b) -> b - a); // sort in descending order
            System.out.println(values);

            int size = values.size();
            this.rankCounts = new int[size];
            for(int i = 0; i < size; i++)
                this.rankCounts[i] = values.get(i);
        }
    }
}
