/**
 * Dillon Beliveau: CS110
 * 11/13/13
 * StackReferenceBased - A linked list implementation of a stack.
 */

package cs110finalproject;

class StackReferenceBased implements StackInterface
{
    private Node first;
    public StackReferenceBased()
    {
        first = null;
    }
    public void push(Object o)
    {
        if (first == null)
        {
            first = new Node(o);
            return;
        }
        // Save old first node
        Node n = first;
        // Set new first node
        first = new Node(o);
        // Set the next of the new first to the old first
        first.setNext(n);
    }
    public Object pop() throws StackException
    {
        if (first == null)
        {
            throw new StackException("Pop from empty stack.");
        }
        // Save old first
        Node n = first;
        // Set new first to first's next node
        first = first.getNext();
        // Return saved old first
        return n.getItem();
    }
    public Object peek()
    {
        return first.getItem();
    }
    public boolean isEmpty()
    {
        // If first is none, then stack is empty.
        return (first == null);
    }
}
