/**
 * Dillon Beliveau: CS110
 * 11/13/13
 * QueueReferenceBased - A linked list implementation of a queue 
 */

package cs110finalproject;

class QueueReferenceBased implements QueueInterface
{
    private Node first;
    private Node last;
    public QueueReferenceBased()
    {
        this.first = null;
        this.last = null;
    }
    public Object dequeue() throws QueueException
    {
        if (first == null)
            throw new QueueException("Dequeue from empty queue.");

        // Save first node
        Node n = first;
        // Set the new first node
        first = first.getNext();
        // Return saved first node
        return n.getItem();
    }
    public Object front() throws QueueException
    {
        if (first == null)
            throw new QueueException("Peek on empty queue.");
        return first.getItem();
    }
    public void enqueue(Object o) throws QueueException
    {
        if (first == null)
        {
            Node n = new Node(o);
            first = n;
            last = n;
            return;
        }
        if (last == null)
        {
            throw new QueueException("Lost end of queue.");
        }
        // Add on to the end of the list.
        Node n = new Node(o);
        last.setNext(n);
        // And update the last var to point to the NEW end.
        last = n;
    }
    public boolean isEmpty()
    {
        // If the first node is null, then the queue is empty
        return (first == null);
    }
    public int size()
    {
        int count = 0;
        Node n = first;
        while (n != null)
        {
            n.getNext();
            count++;
        }
        return count;
    }
}
