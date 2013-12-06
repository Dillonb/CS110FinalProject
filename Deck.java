/**
 * Dillon Beliveau: CS110
 * 12/6/13
 * Deck - A class to represent a deck of cards.
 */
package CS110FinalProject;

/**
 * When instantiated, is a fresh, shuffled deck.
 */
public class Deck extends CardCollection
{
    /**
     * Default constructor. Constructs a shuffled deck.
     */
    public Deck()
    {
        super();
        super.becomeShuffledDeck();
    }
}
