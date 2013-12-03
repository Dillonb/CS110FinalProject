/**
 * Dillon Beliveau: CS110
 * 12/2/13
 * NoCardsException - Thrown when there are no cards on the table and an operation requiring them is done.
 */

package CS110FinalProject;

class NoCardsException extends java.lang.RuntimeException
{
    public NoCardsException(String message)
    {
        super(message);
    }
}
