package cs110finalproject;

/**
 * Represents a card.
 */
public class Card
{
    private Suit suit;
    private Value value;

    /**
     * The main constructor. Takes a suit and a value.
     * @param suit The suit of the card.
     * @param value The value of the card.
     */
    public Card(Suit suit, Value value)
    {
        this.suit = suit;
        this.value = value;
    }

    /**
     * Copy constructor
     * @param other The card to copy
     */
    public Card(Card other)
    {
        this.suit = other.suit;
        this.value = other.value;
    }

    /**
     * @return the suit
     */
    public Suit getSuit()
    {
        return suit;
    }

    /**
     * @return the value
     */
    public Value getValue()
    {
        return value;
    }

    /**
     * Compares a card to another card.
     */
    public int compareTo(Card other)
    {
        // Use the ordinal() method on enums to get their numeric value and then subtract them.
        return this.value.ordinal() - other.getValue().ordinal();
    }

    /**
     * Gets a string representation of the object.
     */
    public String toString()
    {
        String suit, value;

        // Get string value of suit
        switch (this.suit)
        {
            case Clubs:
                suit = "Clubs";
                break;
            case Spades:
                suit = "Spades";
                break;
            case Diamonds:
                suit = "Diamonds";
                break;
            case Hearts:
                suit = "Hearts";
                break;
            default:
                suit = "N/A";
                break;
        }

        // Get string value of card value
        switch (this.value)
        {
            case Two:
                value = "Two";
                break;
            case Three:
                value = "Three";
                break;
            case Four:
                value = "Four";
                break;
            case Five:
                value = "Five";
                break;
            case Six:
                value = "Six";
                break;
            case Seven:
                value = "Seven";
                break;
            case Eight:
                value = "Eight";
                break;
            case Nine:
                value = "Nine";
                break;
            case Ten:
                value = "Ten";
                break;
            case Jack:
                value = "Jack";
                break;
            case Queen:
                value = "Queen";
                break;
            case King:
                value = "King";
                break;
            default:
                value = "N/A";
        }

        // Return a human readable representation of the card.
        return value + " of " + suit;
    }
}
