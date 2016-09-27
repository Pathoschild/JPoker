package com.jplamondonw.jpoker.models;

/**
 * The possible card ranks.
 */
public enum Rank {
    // Constants
    //******************************
    A     ("A",  "A    \n     \n  %  \n     \n    A"),
    TWO   ("2",  "2    \n  %  \n     \n  %  \n    2"),
    THREE ("3",  "3    \n  %  \n  %  \n  %  \n    3"),
    FOUR  ("4",  "4    \n%   %\n     \n%   %\n    4"),
    FIVE  ("5",  "5    \n%   %\n  %  \n%   %\n    5"),
    SIX   ("6",  "6    \n%   %\n%   %\n%   %\n    6"),
    SEVEN ("7",  "7    \n%   %\n% % %\n%   %\n    7"),
    EIGHT ("8",  "8    \n% % %\n%   %\n% % %\n    8"),
    NINE  ("9",  "9    \n% % %\n% % %\n% % %\n    9"),
    TEN   ("10", "10  %\n% % %\n% % %\n% % %\n   10"),
    JACK  ("J",  "J    \n     \nJACK \n     \n    J"),
    QUEEN ("Q",  "Q    \n     \nQUEEN\n     \n    Q"),
    KING  ("K",  "K    \n     \nKING \n     \n    K");


    // Properties
    //******************************
    /**
     * The display symbol.
     */
    public final String symbol;

    /**
     * The card layout on a five-by-five grid, where % represents the suit symbol.
     */
    public final String layout;


    // Public methods
    //******************************
    /**
     * Construct an instance.
     * @param symbol The display symbol.
     * @param layout The card layout on a five-by-five grid, where % represents the suit symbol.
     */
    Rank(String symbol, String layout) {
        this.symbol = symbol;
        this.layout = layout;
    }
}
