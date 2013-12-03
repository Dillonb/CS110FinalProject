/**
 * Dillon Beliveau: CS110
 * 11/13/13
 * QueueInterface - Defines an interface for Queues
 */

package cs110finalproject;

public interface QueueInterface
{
    /**
     * Adds a new item to the back of the queue.
     */
    public void enqueue(Object o) throws QueueException;
    /**
     * Pops the frontmost item off of the queue and returns it.
     */
    public Object dequeue() throws QueueException;

    /**
     * Returns the front object without dequeueing it.
     */
    public Object front() throws QueueException;
    
    /**
     * Gets whether or not the list is empty.
     */
    public boolean isEmpty();

    /**
     * Gets the size of the list
     */
    public int size();
}
