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

    /**
     * Whether the card should be drawn face-down.
     */
    public final boolean isFaceDown;


    // Public methods
    //******************************
    /**
     * Construct an instance.
     * @param suit The card suit.
     * @param rank The card rank.
     */
    public Card(Suit suit, Rank rank)
    {
        this(suit, rank, false);
    }

    /**
     * Construct an instance.
     * @param suit The card suit.
     * @param rank The card rank.
     * @param isFaceDown Whether the card should be drawn face-down.
     */
    public Card(Suit suit, Rank rank, boolean isFaceDown)
    {
        this.suit = suit;
        this.rank = rank;
        this.isFaceDown = isFaceDown;
    }

    /**
     * Draw the element at the specified position.
     * @param console The console to which to draw.
     * @param row The top row position.
     * @param col The left column position.
     */
    public void draw(ConsoleHelper console, int row, int col)
    {
        // top border
        console.setCursor(row, col);
        console.out.print("┌─");
        for(int i = 0; i < Constants.CARD_LAYOUT_WIDTH; i++)
            console.out.print("─");
        console.out.print("─┐");

        // card layout
        if(this.isFaceDown)
        {
            for(int layoutRow = 0; layoutRow < Constants.CARD_LAYOUT_HEIGHT; layoutRow++)
            {
                console.setCursor(row + 1 + layoutRow, col);
                console.out.print("│ ");
                for(int layoutCol = 0; layoutCol < Constants.CARD_LAYOUT_WIDTH; layoutCol++)
                    console.out.print("▒");
                console.out.print(" │");
            }
        }
        else
        {
            String layout = "│ " + this.rank.layout.replace("%", this.suit.symbol).replace("\n", " │\n│ ") + " │";
            String[] lines = layout.split("\n");
            for (int i = 0; i < lines.length; i++) {
                console.setCursor(row + i + 1, col);
                console.out.print(lines[i]);
            }
        }

        // bottom border
        console.setCursor(row + Constants.CARD_LAYOUT_HEIGHT + 1, col);
        console.out.print("└─");
        for(int i = 0; i < Constants.CARD_LAYOUT_WIDTH; i++)
            console.out.print("─");
        console.out.print("─┘");
    }
}
