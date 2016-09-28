package com.jplamondonw.jpoker;

import com.jplamondonw.jpoker.framework.*;

import java.awt.Point;
import java.util.Arrays;
import java.util.List;

/**
 * Manages and draws the game board (i.e. community cards, chips, and hands).
 */
public class GameBoard implements Drawable
{
    // Public properties
    //******************************
    /**
     * The bot player.
     */
    public final Player bot = new Player(false, "They", Constants.INITIAL_CHIPS);

    /**
     * The user player.
     */
    public final Player user = new Player(true, "You", Constants.INITIAL_CHIPS);

    /**
     * The deck of cards available to draw.
     */
    public final Deck deck = new Deck();

    /**
     * The community cards that can be used in the players' hands.
     */
    public final Hand communityCards = new Hand();

    /**
     * The current big blind (minimum bet).
     */
    public int bigBlind = Constants.INITIAL_BIG_BLIND;

    /**
     * The chips that have been bet so far.
     */
    public int pot;

    /**
     * Whether it's the user's turn to play.
     */
    public boolean isUserTurn;


    // Private properties
    //******************************
    /**
     * A face-down card used to draw the pile.
     */
    private Card facedownCard = new Card(Suit.CLUBS, Rank.A, true);


    // Public methods
    //******************************
    /**
     * Draw the element at the specified position.
     * @param console The console to which to draw.
     * @param row The top row position.
     * @param col The left column position.
     */
    public void draw(ConsoleHelper console, int row, int col)
    {
        Point origin = new Point(col, row);

        // draw players
        this.drawPlayer(console, this.bot, origin, Constants.BoardLayout.BOT_NAME_AND_BET, Constants.BoardLayout.BOT_HAND);
        this.drawPlayer(console, this.user, origin, Constants.BoardLayout.USER_NAME_AND_BET, Constants.BoardLayout.USER_HAND);

        // draw pile
        {
            Point position = this.getRelativePoint(origin, Constants.BoardLayout.CARD_PILE);
            int pileSize = this.deck.size();
            if(pileSize >= 3)
                this.facedownCard.draw(console, position.y, position.x);
            if(pileSize >= 2)
                this.facedownCard.draw(console, position.y, position.x + 1);
            if(pileSize >= 1)
                this.facedownCard.draw(console, position.y, position.x + 2);
        }

        // draw community cards
        {
            Point position = this.getRelativePoint(origin, Constants.BoardLayout.COMMUNITY_CARDS);
            this.communityCards.draw(console, position.y, position.x);
        }

        // draw pot & betting info
        this.drawInfoBox(console, origin, Constants.BoardLayout.BET_INFO_BOX);
    }

    // Private methods
    //******************************
    /**
     * Get a coordinate relative to the game board's origin.
     * @param origin The board origin.
     * @param coordinate The relative coordinate.
     */
    private Point getRelativePoint(Point origin, Point coordinate)
    {
        return new Point(origin.x + coordinate.x, origin.y + coordinate.y);
    }

    /**
     * Draw the player area for a player.
     * @param console The console to which to draw.
     * @param player The player whose area to draw.
     * @param origin The top-left coordinates of the game board.
     * @param nameAndBetPos The position of the player's name and bet within the board.
     * @param handPos The position of the player's hand within the board.
     */
    private void drawPlayer(ConsoleHelper console, Player player, Point origin, Point nameAndBetPos, Point handPos)
    {
        // draw bot name + bet
        console.setCursor(this.getRelativePoint(origin, nameAndBetPos));
        console.out.print(player.name + " bet $" + player.bet);

        // draw bot hand
        Point handPosAbs = this.getRelativePoint(origin, handPos);
        player.hand.draw(console, handPosAbs.y, handPosAbs.x);
    }

    /**
     * Draw the info box which shows the current pot, blinds, and player chips.
     * @param console The console to which to draw.
     * @param origin The top-left coordinates of the game board.
     * @param position The position of the info box within the board.
     */
    private void drawInfoBox(ConsoleHelper console, Point origin, Point position)
    {
        Point boxPos = this.getRelativePoint(origin, position);

        // get text to display
        List<String> lines = Arrays.asList(
                "   Pot: $" + this.pot,
                "Blinds: $" + (this.bigBlind / 2) + " – $" + this.bigBlind,
                " Your chips: $" + this.user.chips,
                "Their chips: $" + this.bot.chips
        );
        int innerBorderAt = 3;

        // get box dimensions
        int textWidth = 0;
        for(String line : lines)
            textWidth = Math.max(textWidth, line.length());

        // draw box
        {
            final int firstCol = boxPos.x;
            final int firstRow = boxPos.y;
            int curRow = 0;

            // top border
            console.setCursor(firstRow + curRow, firstCol);
            console.out.print(ConsoleHelper.SET_GRAY_COLOR);
            console.out.print(ConsoleHelper.SET_GRAY_COLOR + "┌─");
            for(int i = 0; i < textWidth; i++)
                console.out.print("─");
            console.out.print("─┐");
            console.out.print(ConsoleHelper.RESET_COLOR);
            curRow++;

            // text
            for(String line : lines)
            {
                // text line
                console.setCursor(firstRow + curRow, firstCol);
                console.out.print(ConsoleHelper.SET_GRAY_COLOR + "│ "  + ConsoleHelper.RESET_COLOR + line);
                console.setCursor(firstRow + curRow, firstCol + 2 + textWidth);
                console.out.print(ConsoleHelper.SET_GRAY_COLOR + " │" + ConsoleHelper.RESET_COLOR);
                curRow++;

                // inner border
                if(curRow == innerBorderAt)
                {
                    console.setCursor(firstRow + curRow, firstCol);
                    console.out.print(ConsoleHelper.SET_GRAY_COLOR);
                    console.out.print("├─");
                    for(int i = 0; i < textWidth; i++)
                        console.out.print("─");
                    console.out.print("─┤");
                    console.out.print(ConsoleHelper.RESET_COLOR);
                    curRow++;
                }
            }

            // bottom border
            console.setCursor(firstRow + curRow, firstCol);
            console.out.print(ConsoleHelper.SET_GRAY_COLOR);
            console.out.print("└─");
            for(int i = 0; i < textWidth; i++)
                console.out.print("─");
            console.out.print("─┘");
            console.out.print(ConsoleHelper.RESET_COLOR);
        }
    }
}
