package com.jplamondonw.jpoker;

import com.jplamondonw.jpoker.framework.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Manages an ongoing game.
 */
public class Game
{
    // Properties
    //******************************
    /**
     * The console to which to draw and from which to get input.
     */
    private final ConsoleHelper console;


    // Public methods
    //******************************
    public Game(ConsoleHelper console)
    {
        this.console = console;
    }

    /**
     * Run the game until it completes.
     */
    public void run() throws IOException, InterruptedException
    {
        // initialise
        Player bot = new Player(false, "They", Constants.INITIAL_CHIPS);
        Player human = new Player(true, "You", Constants.INITIAL_CHIPS);
        GameLog log = new GameLog(5);
        GameBoard board = new GameBoard(bot, human, false);
        boolean isHumanDealer = new Random().nextBoolean();

        // start game
        log.add("Welcome to Texas Hold'em heads-up tournament style! We'll be");
        log.add("playing with a few house rules. (If you know the standard rules,");
        log.add("you'll be fine.)");

        // start game loop
        while(true)
        {
            // confirm game start
            log.add("Play a new hand? (y)es (n)o");
            this.draw(board, log);
            if (this.getChoice("y", "n").equals("n"))
            {
                log.add("Good bye. :)");
                this.draw(board, log);
                break;
            }
            log.clear();

            // initial state
            bot.hand.clear();
            human.hand.clear();
            board = new GameBoard(bot, human, true);
            Deck deck = board.deck;
            log.add("You have $" + human.chips + " in chips. We'll start the big blind at $" + board.bigBlind + ".");

            // alternate dealer
            isHumanDealer = !isHumanDealer;
            Player dealer = isHumanDealer ? human : bot;
            Player nonDealer = isHumanDealer ? bot : human;
            if(isHumanDealer)
                log.add("You have the dealer coin!");
            else
                log.add("They have the dealer coin.");
            this.draw(board, log);

            // place initial bets & distribute cards
            log.add("Placing initial bets.");
            log.add("Dealing hole cards.");

            dealer.bet(board.smallBlind);
            nonDealer.bet(board.bigBlind);
            human.hand.add(Arrays.asList(deck.drawCard(), deck.drawCard()));
            bot.hand.add(Arrays.asList(deck.drawCard(), deck.drawCard()));
            this.draw(board, log);
            Player winner = null;

            // the preflop
            winner = this.runBettingRound(board, log, board.bigBlind, dealer);
            this.draw(board, log);

            // the flop
            if(winner == null)
            {
                log.add("The flop!");
                board.communityCards.add(Arrays.asList(deck.drawCard(), deck.drawCard(), deck.drawCard()));
                winner = this.runBettingRound(board, log, board.bigBlind, dealer);
            }

            // the turn
            if(winner == null)
            {
                log.add("The turn!");
                board.communityCards.add(deck.drawCard());
                winner = this.runBettingRound(board, log, board.bigBlind, dealer);
            }

            // the river
            if(winner == null)
            {
                log.add("The river!");
                board.communityCards.add(deck.drawCard());
                winner = this.runBettingRound(board, log, board.bigBlind, dealer);
            }

            // showdown
            if(winner == null)
            {
                log.add("Showdown!");
                HandType botHandType = bot.hand.getHandType();
                HandType userHandType = human.hand.getHandType();

                // winning hand type
                if(botHandType.value != userHandType.value)
                {
                    if(botHandType.value > userHandType.value)
                    {
                        log.add("They have a " + botHandType.name + "!");
                        winner = bot;
                    }
                    else
                    {
                        log.add("You have a " + userHandType.name + "!");
                        winner = human;
                    }
                }

                // else highest total face value
                else
                {
                    int botHandValue = bot.hand.getTotalFaceValue();
                    int userHandValue = human.hand.getTotalFaceValue();
                    if(botHandValue > userHandValue)
                    {
                        log.add("They have the highest combined face value.");
                        winner = bot;
                    }
                    else if(userHandValue > botHandValue)
                    {
                        log.add("You have the highest combined face value.");
                        winner = human;
                    }
                }
            }

            // handle winner
            bot.showHand();

            int pot = human.bet + bot.bet;
            if(winner != null)
            {
                if(winner == human)
                    log.add("You won! You get $" + pot + ".");
                else
                    log.add("You lost! They get $" + pot + ".");
                winner.chips += pot;
            }
            else
            {
                int winnings = pot / 2;
                log.add("Looks like a tie; you each get $" + winnings + ".");
                human.chips += winnings;
                bot.chips += winnings;
            }
            this.draw(board, log);
        }
    }

    // Private methods
    //******************************
    // Game logic
    //**************
    /**
     * Run a round of betting.
     * @param board The game board.
     * @param log The game log.
     * @param minBet The minimum bet.
     * @param dealer The player with the dealer coin.
     * @return Returns the round winner (if a player won).
     */
    private Player runBettingRound(GameBoard board, GameLog log, int minBet, Player dealer)
    {
        Player human = board.human;
        Player bot = board.bot;

        boolean botCanBet = true;
        boolean humanCanBet = true;
        while(botCanBet && humanCanBet)
        {
            // run bot action (if first)
            if(bot == dealer)
                botCanBet = this.runBotBet(bot, human, minBet, log);

            // calculate minimum player bet
            int minPlayerBet = minBet - (human.bet % minBet);
            if(human.chips < minPlayerBet)
            {
                log.add("You don't have enough chips to bet.");
                humanCanBet = false;
            }
            else
            {
                // get available player actions
                String question;
                List<String> choices = new ArrayList<>();
                {
                    List<String> labels = new ArrayList<>();

                    // fold
                    labels.add("(f)old");
                    choices.add("f");

                    // call (or check)
                    if(bot.bet > human.bet)
                    {
                        labels.add("(c)all");
                        choices.add("c");
                    }
                    else
                    {
                        labels.add("(c)heck");
                        choices.add("c");
                    }

                    // raise (or bet)
                    if((bot.bet + human.bet) > 0)
                    {
                        labels.add("(r)aise");
                        choices.add("r");
                    }
                    else
                    {
                        labels.add("(b)et");
                        choices.add("b");
                    }

                    // build question
                    question = "Do you want to " + String.join(" or ", labels) + "?";
                }

                // perform player action
                log.add(question);
                this.draw(board, log);
                switch(this.getChoice(choices.toArray(new String[0])))
                {
                    // fold
                    // ends betting round
                    case "f":
                        log.add("You folded.");
                        return bot;

                    // call/check
                    case "c":
                        // call
                        if(bot.bet > human.bet)
                        {
                            int bet = bot.bet - human.bet;
                            human.bet(bet);
                            log.add("You called for $" + bet + ".");
                        }

                        // check
                        else
                            log.add("You checked.");
                        break;

                    // raise/bet
                    case "b":
                    case "r":
                        String verb = (bot.bet + human.bet) > 0 ? "raise" : "bet";

                        // get raise amount
                        int bet;
                        log.add("How much do you want to " + verb + "?");
                        while(true)
                        {
                            this.draw(board, log);

                            // get amount
                            String input = this.getInput();
                            if(!input.chars().allMatch(Character::isDigit))
                            {
                                log.add("Please enter a numeric " + verb + ".");
                                continue;
                            }
                            bet = Integer.parseInt(input);

                            // validate
                            if(bet < minPlayerBet)
                                log.add("The minimum " + verb + " is $" + minBet + ".");
                            else if(bet > human.chips)
                                log.add("You don't have that many chips to " + verb + ".");
                            else
                                break;
                        }

                        // round to nearest interval
                        bet -= bet % minBet;

                        // place bet
                        log.add("You bet $" + bet + ".");
                        human.bet(bet);
                        break;

                    // shouldn't happen
                    default:
                        log.add("Uh oh. That choice isn't valid. Let's pretend that didn't happen.");
                        break;
                }
            }

            // let bot bet if they're second
            if(human == dealer)
                botCanBet = this.runBotBet(bot, human, minBet, log);

            // check round end condition
            if(!botCanBet && bot.bet < human.bet)
                return human;
            if(human.bet == bot.bet)
                return null;
        }
        return null;
    }

    /**
     * Perform the bot's action during a betting round.
     * @param bot The bot player.
     * @param user The human player.
     * @param minBet The minimum bet.
     * @param log The game log.
     */
    private boolean runBotBet(Player bot, Player user, int minBet, GameLog log)
    {
        // get minimum bet allowed
        int bet = Math.max(Math.max(user.bet - bot.bet, bot.bet - minBet), 0);

        // place bet
        if(bet == 0)
        {
            log.add("They called.");
            return true;
        }
        if(bot.bet(bet))
        {
            log.add("They bet $" + bet + ".");
            return true;
        }
        else
        {
            log.add("They don't have enough chips to bet.");
            return false;
        }
    }

    // Drawing & UI
    //**************
    /**
     * Draw the current game state.
     * @param board The game board to draw.
     * @param log The game log.
     */
    private void draw(GameBoard board, GameLog log)
    {
        // clear screen
        this.console.clear();

        // draw elements
        board.draw(console, Constants.ScreenLayout.GAME_BOARD.y, Constants.ScreenLayout.GAME_BOARD.x);
        log.draw(console, Constants.ScreenLayout.GAME_LOG.y, Constants.ScreenLayout.GAME_LOG.x);

        // move cursor out of the way
        this.console.setCursor(Constants.ScreenLayout.USER_INPUT);
    }

    /**
     * Get input from the user.
     */
    private String getInput()
    {
        this.console.setCursor(Constants.ScreenLayout.USER_INPUT);
        this.console.clearFromCursorLine();
        this.console.out.print("> ");
        return this.console.console.readLine();
    }

    /**
     * Get the user's choice.
     * @param choices The available choices. These are not case-sensitive.
     */
    private String getChoice(String... choices)
    {
        // get case-insensitive choices
        List<String> choiceLookup = new ArrayList<>();
        for(String choice : choices)
            choiceLookup.add(choice.toLowerCase());

        // get valid input
        while(true)
        {
            // draw input
            String choice = this.getInput().toLowerCase();
            if(choiceLookup.contains(choice))
                return choice;
        }
    }
}
