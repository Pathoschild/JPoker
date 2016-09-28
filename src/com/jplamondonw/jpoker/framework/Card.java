package com.jplamondonw.jpoker.framework;

/**
 * The display and game logic for a card.
 */
public class Card implements Drawable
{
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
     * Draw the element at the specified position.
     * @param console The console to which to draw.
     * @param row The top row position.
     * @param col The left column position.
     */
    public void draw(ConsoleHelper console, int row, int col)
    {
        String layout = "┌───────┐\n│ " + this.rank.layout.replace("%", this.suit.symbol).replace("\n", " │\n│ ") + " │\n└───────┘";
        String[] lines = layout.split("\n");

        for(int i = 0; i < lines.length; i++)
        {
            console.setCursor(row + i, col);
            console.out.print(lines[i]);
        }
    }
}
