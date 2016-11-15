package algData3.queue;


public class QueueList {

    private Entry header;
    private Entry tail;

    private int size;

    public QueueList() {
        size = 0;
    }

   /**
   * Inserts the specified element into this queue, if possible.
   * 
   * @param o the element to insert.
   * @return true if it was possible to add the element to this queue, else
   *         false
   */
    public boolean offer(Object o) {
	  // ToDo
        
        return true;
    }

   /**
   * Retrieves and removes the head of this queue.
   * 
   * @return the head of this queue, or null if this queue is empty.
   */
    public Object poll() {
	  // ToDo
        
        return null;
    }

   /**
   * Retrieves and removes the head of this queue.
   * 
   * @return the head of this queue.
   * @throws NoSuchElementException if this queue is empty.
   */
    public Object remove() {
	  // ToDo
        
        return null;
    }

   /**
   * Retrieves, but does not remove, the head of this queue.
   * 
   * @return the head of this queue, or null if this queue is empty.
   */
    public Object peek() {
        // ToDo
        
        return null;
    }

   /**
   * Retrieves, but does not remove, the head of this queue.
   * 
   * @return the head of this queue.
   * @throws NoSuchElementException if this queue is empty.
   */
    public Object element() {
	  // ToDo
        
        return null;
    }

    public boolean empty() {
        return size == 0;
    }

    private static class Entry {

        Object element;
        Entry next;

        Entry(Object element, Entry next) {
            this.element = element;
            this.next = next;
        }
    }
}
