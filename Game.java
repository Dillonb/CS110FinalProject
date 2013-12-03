/**
 * Dillon Beliveau: CS110
 * 12/2/13
 * Game - A class to hold game logic.
 */

package cs110finalproject;

import java.util.Scanner;

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
     * Peeks at the player's top card.
     * @return null if there is no top card, otherwise returns the top card on the player's pile.
     */
    public Card playerTopCard()
    {
        if (playerCentralPile.isEmpty())
            return null;
        else
            return playerCentralPile.peekAtTop();
    }
    /**
     * Peeks at the computer's top card.
     * @return null if there is no top card, otherwise returns the top card on the computer's pile.
     */
    public Card computerTopCard()
    {
        if (computerCentralPile.isEmpty())
            return null;
        else
            return computerCentralPile.peekAtTop();
    }

    /**
     * Gets the size of the player's hand.
     * @return The size of the player's hand.
     */
    public int playerHandSize()
    {
        return this.playerHand.size();
    }
    /**
     * Gets the size of the computer's hand.
     * @return The size of the computer's hand.
     */
    public int computerHandSize()
    {
        return this.computerHand.size();
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
     * @return a negative number if the computer won, 0 if a war needs to be played, a positive number if the player won.
     */
    public int getWinState()
    {
        if (playerTopCard() == null || computerTopCard() == null)
            throw new NoCardsException("Tried to check the win state when there were no cards on the table.");
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
     * Processes a turn. Should be called directly after flip(). Does NOT handle wars.
     * To handle wars, check if this method returned 0 and call the method war().
     * @return a negative number if the computer won, 0 if a war needs to be played, a positive number if the player won.
     */
    public int processTurn()
    {
        if (getWinState() == 0)
        {
            return 0; // Game implementation will handle wars.
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
            return 1;
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
            return -1;
        }
    }

    /**
     * Processes a war.
     * @return true if the war was successful and decided a winner, false if the war needs to be done again.
     */
    public boolean war()
    {
        // Flip the first card "face down"
        flip();
        // If someone is out of cards, end the war.
        if (getGameState() != 0)
        {
            return true;
        }
        // Otherwise, flip again, this time "face up."
        flip();
        if (getWinState() == 0) // If a war is necessary, this war did not decide a victor.
        {
            if (getGameState() == 0) // If the game is also not over, it's time for another war.
                return false;
            else
                return true; // The war is over, did not decide a winner and one player is out of cards. Someone won.
        }
        return true; // If the winstate is anything but 0, someone won the war, but both players could still have cards.
    }

    // CLI mode war game.
    public static void main(String[] args)
    {
        Game g = new Game();
        Scanner sc = new Scanner(System.in);
        int turnResult;

        Card playerTopCard, computerTopCard;

        System.out.println("Dealing...");
        g.deal(); // Begin the game by dealing.
        System.out.println("Finished dealing.");
        while (g.getGameState() == 0) // Loop while game is still going on.
        {
            playerTopCard = g.playerTopCard();
            computerTopCard = g.computerTopCard();

            // Display cards.
            //if (playerTopCard == null)
                //System.out.println("No card up for player.");
            //else
                //System.out.println("Player's card: " + playerTopCard);
            //if (computerTopCard == null)
                //System.out.println("No card up for computer.");
            //else
                //System.out.println("Computer's card: " + computerTopCard);
            g.playerHandSize();
            System.out.println("You have " + g.playerHandSize() + " cards.\nThe computer has " + g.computerHandSize());


            System.out.println("Press enter to flip a card.");
            //sc.nextLine(); // Wait for user to press enter.
            g.flip();
            System.out.println("You flipped " + g.playerTopCard() + ".\nThe computer flipped: " + g.computerTopCard());
            
            turnResult = g.processTurn();

            if (turnResult < 0)
            {
                // Computer won
                System.out.println("The computer wins the round.");
            }
            else if (turnResult > 0)
            {
                // Player wins
                System.out.println("You win the round!");
            }
            else // turnresult == 0 is only possible case
            {
                System.out.println("******\n*WAR!*\n******\nPress enter to begin.");
                //sc.nextLine(); // Wait for user to press enter.
                while (!g.war()) // Continue having wars until a winner is decided.
                {
                    System.out.println("You flipped a card face down.\nThe computer flipped a card face down.");
                    System.out.println("You flipped: " + g.playerTopCard() + "\nThe computer flipped " + g.computerTopCard());
                    System.out.println("There was no winner! Press enter to continue the war.");
                    sc.nextLine();
                }
                System.out.println("You flipped a card face down.\nThe computer flipped a card face down.");
                System.out.println("You flipped: " + g.playerTopCard() + "\nThe computer flipped " + g.computerTopCard());
                // The war was decided. Determine what kind of victory it was.
                if (g.computerHandSize() == 0)
                {
                    // It was a case of running out of cards.
                    System.out.println("The computer ran out of cards!");
                }
                else if (g.playerHandSize() == 0)
                {
                    // Same, but the player ran out of cards.
                    System.out.println("You ran out of cards.");
                }
                else // The war ended without ending the game.
                {
                    turnResult = g.processTurn();
                    if (turnResult < 0)
                    {
                        System.out.println("The computer wins the war.");
                    }
                    else // turnResult > 0 (turnResult cannot be 0 here because the war ended)
                    {
                        System.out.println("You win the war!");
                    }
                    System.out.println("Press enter to continue.");
                    //sc.nextLine();
                }
            }
        }
        sc.close();
    }
}
