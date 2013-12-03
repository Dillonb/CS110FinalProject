package cs110finalproject;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a generic collection of cards.
 */
public abstract class CardCollection
{
    private QueueReferenceBased cards;

    private static ArrayList<Card> deck = null;

    private boolean faceUp;

    public CardCollection()
    {
        this.cards = new QueueReferenceBased();
        this.faceUp = false; // By default the deck is not face up.
    }

    /**
     * An alternative constructor that allows the deck to be set face up.
     */
    public CardCollection(boolean faceUp)
    {
        this.faceUp = faceUp;
    }

    /**
     * Gets a fresh deck.
     * @return A fresh, shuffled deck of cards.
     */
    private ArrayList<Card> getFreshDeck()
    {
        // If the deck hasn't been initialized yet...
        if (deck == null)
        {
            deck = new ArrayList<Card>();
            // Loop through every possible combination of suit and value and add them to the deck.
            for (Suit s : Suit.values())
            {
                for (Value v : Value.values())
                {
                    deck.add(new Card(s,v));
                }
            }
        }
        // Create a deep copy of the deck.
        ArrayList<Card> freshDeck = new ArrayList<Card>();
        for (Card c : deck)
        {
            freshDeck.add(new Card(c)); // Add a copied card to the fresh deck.
        }
        Collections.shuffle(freshDeck); // Shuffle the deck.
        return freshDeck; // Return the fresh deck.
    }

    /**
     * Causes the CardCollection to become a shuffled deck.
     */
    public void becomeShuffledDeck()
    {
        this.cards = new QueueReferenceBased();
        for (Card c : this.getFreshDeck())
        {
            this.cards.enqueue(c); // Append the card to the end of the deck
        }
    }
    /**
     * Adds a card to the end of the collection.
     * @param c The card to add.
     */
    public void addToEnd(Card c)
    {
        this.cards.enqueue(c);
    }
    /**
     * Draws a card.
     * @return the drawn card.
     */
    public Card draw()
    {
        return (Card)this.cards.dequeue();
    }

    /**
     * Peeks at the top card if the collection is face up.
     */
    public Card peek()
    {
        if (faceUp)
            return (Card)this.cards.front();
        else
            return null; // Return null if the deck is face down.
    }

    /**
     * Checks whether the collection is empty.
     * @return Whether or not the collection is empty.
     */
    public boolean isEmpty()
    {
        return this.cards.isEmpty();
    }
}
