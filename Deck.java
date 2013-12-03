package cs110finalproject;

/**
 * When instantiated, is a fresh, shuffled deck.
 */
class Deck extends CardCollection
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
