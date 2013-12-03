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
}
