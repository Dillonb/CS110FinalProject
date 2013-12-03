package CS110FinalProject;

/**
 * Represents a central pile of cards. Is face up and uses a stack instead of a queue.
 */
class CentralPile 
{
    private StackReferenceBased cards;
    public CentralPile()
    {
        cards = new StackReferenceBased();
    }

    /**
     * Adds a card to the top of the pile.
     * @param c The card to add.
     */
    public void addToTop(Card c)
    {
        this.cards.push(c);
    }
    /**
     * Takes a card from the top of the pile.
     * @return The card that was taken.
     */
    public Card takeFromTop()
    {
        return (Card)this.cards.pop();
    }
    /**
     * Peeks at the top card (returns it without removing it from the pile)
     * @return The card at the top.
     */
    public Card peekAtTop()
    {
        return (Card)this.cards.peek();
    }
    /**
     * Checks whether the pile is empty.
     * @return Whether the pile is empty.
     */
    public boolean isEmpty()
    {
        return this.cards.isEmpty();
    }
}
