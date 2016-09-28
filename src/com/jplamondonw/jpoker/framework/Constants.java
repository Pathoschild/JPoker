package com.jplamondonw.jpoker.framework;

import java.awt.Point;

/**
 * Defines assumptions in the code.
 */
public class Constants
{
    // Cards
    //******************************
    /**
     * The number of lines within a card layout (excluding the border).
     */
    public static final int CARD_LAYOUT_HEIGHT = 5;

    /**
     * The number of characters within a card layout line (excluding the border).
     */
    public static final int CARD_LAYOUT_WIDTH = 5;

    /**
     * The number of characters along the edge of each card which includes the border and inner padding.
     */
    public static final int CARD_BORDER_WIDTH = 2;


    // Gameplay
    //******************************
    /**
     * The number of chips each player begins with.
     */
    public static final int INITIAL_CHIPS = 1000;

    /**
     * The minimum bet when the game starts.
     */
    public static final int INITIAL_BIG_BLIND = INITIAL_CHIPS / 100;


    // Overall layout
    //******************************
    public static class ScreenLayout
    {
        /**
         * The position at which the game board should be drawn.
         */
        public static final Point GAME_BOARD = new Point(1, 1);


        /**
         * The position at which messages to the user and their input should be drawn.
         */
        public static final Point USER_INPUT = new Point(5, 37);
    }


    // Game board
    //******************************
    /**
     * The coordinates of elements on the board relative to its origin.
     */
    public static class BoardLayout
    {
        /**
         * The position at which to draw the betting info box.
         */
        public static final Point BET_INFO_BOX = new Point(78, 5);

        /**
         * The position at which to draw the bot's name and bet.
         */
        public static final Point BOT_NAME_AND_BET = new Point(5, 1);

        /**
         * The position at which to draw the bot's hand.
         */
        public static final Point BOT_HAND = new Point(5, 2);

        /**
         * The position at which to draw the community cards.
         */
        public static final Point COMMUNITY_CARDS = new Point(5, 14);

        /**
         * The position at which to draw the user's name and bet.
         */
        public static final Point USER_NAME_AND_BET = new Point(5, 26);

        /**
         * The position at which to draw the user's hand.
         */
        public static final Point USER_HAND = new Point(5, 27);
    }
}
