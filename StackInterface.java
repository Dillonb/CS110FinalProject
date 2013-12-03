/**
 * Dillon Beliveau: CS110
 * 11/13/13
 * StackInterface - Defines an interface for Stack data structures.
 */

package CS110FinalProject;

public interface StackInterface
{
    /**
     * Pushes a new item onto the stack
     */
    public void push(Object o);
    /**
     * Pops an item off of the top of the stack.
     */
    public Object pop() throws StackException;
    /**
     * Returns the item on top of the stack without popping it.
     */
    public Object peek();
    /**
     * Returns whether or not the stack is empty.
     */
    public boolean isEmpty();
}
