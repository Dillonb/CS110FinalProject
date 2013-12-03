package cs110finalproject;

/**
 * Represents a game.
 * Can be ran (has a main method, a CLI interface to the game) but also can be used to implement another interface, such as a GUI.
 */
public class Game
{
    private Hand playerHand;
    private Hand computerHand;

    private CentralPile playerCentralPile;
    private CentralPile computerCentralPile;

    public Game()
    {
        reset();
    }
    // Resets the game.
    public void reset()
    {

        this.playerHand = new Hand();
        this.computerHand = new Hand();
        this.playerCentralPile = new CentralPile();
        this.computerCentralPile = new CentralPile();
    }

    /**
     * Deals the cards.
     */
    public void deal()
    {
        // Dealing means the start of a new game.
        reset();
        // Create a temporary deck.
        Deck deck = new Deck();
        // A tracking variable to remember who we are dealing to.
        boolean onPlayerHand = true;

        // Deal out the cards (loop until deck is empty)
        while (!deck.isEmpty())
        {
            // Deal to correct hand.
            if (onPlayerHand)
                playerHand.addToEnd(deck.draw());
            else
                computerHand.addToEnd(deck.draw());

            // Flip value of tracking variable
            onPlayerHand = !onPlayerHand;
        }
    }

    /**
     * Flip the top card from both the player and the computer's deck.
     * This is the first part of a new turn, or part of a war.
     */
    public void flip()
    {
        // Draws the top card from each player's hand and adds it to their central pile.
        playerCentralPile.addToTop(playerHand.draw());
        computerCentralPile.addToTop(computerHand.draw());
    }

    /**
     * Gets the state of the game based on the current top two cards.
     * @ return a negative number if the computer won, 0 if a war needs to be played, a positive number if the player won.
     */
    public int getWinState()
    {
        // Compare the player's top card to the computer's top card and return the result.
        return playerCentralPile.peekAtTop().compareTo(computerCentralPile.peekAtTop());
    }
    /**
     * Gets the state of the game.
     * @return A negative number if the computer won, 0 if the game is still going on, a positive number if the player won.
     */
    public int getGameState()
    {
        if (computerHand.isEmpty())
            return 1;
        if (playerHand.isEmpty())
            return -1;
        return 0;
    }
    /**
     * Processes a turn. Should be called directly after flip().
     */
    public void processTurn()
    {
        if (getWinState() == 0)
        {
            //TODO: Implement war functionality.
        }
        else if (getWinState() > 0)
        {
            // The player won.
            // Take both piles and add them to the bottom of the player's hand.
            while (!playerCentralPile.isEmpty())
            {
                playerHand.addToEnd(playerCentralPile.takeFromTop());
            }
            while (!computerCentralPile.isEmpty())
            {
                playerHand.addToEnd(computerCentralPile.takeFromTop());
            }
        }
        else // getWinState() can only be < 0
        {
            // The computer won.
            // Take both piles and add them to the bottom of the computer's hand.
            while (!playerCentralPile.isEmpty())
            {
                computerHand.addToEnd(playerCentralPile.takeFromTop());
            }
            while (!computerCentralPile.isEmpty())
            {
                computerHand.addToEnd(computerCentralPile.takeFromTop());
            }
        }
    }
}
