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
