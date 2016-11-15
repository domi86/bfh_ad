package algData3.queue;

/**
 * Queue implementation based on an Object Array
 * with fixed length (= capacity)
 */

public class QueueArray  {
  private Object[] container;
  private int header;
  private int tail;
  private int capacity;

  private int size;

  public QueueArray(int capacity) {

  }
  
  public QueueArray() {
    this.capacity = 50;

  }
  
  /**
   * Inserts the specified element at the end of this queue, if possible.
   */
  public boolean offer(Object o) {
     //TODO

      return true;
  }
  
  
  /**
   * Retrieves and removes the head element of this queue.
   */
  public Object poll() {
     //TODO

    return null;
  }

  
  /**
   * Retrieves and removes the head element of this queue.
   */
  public Object remove() {
    //TODO
    return null;
  }
  /**
   * Retrieves, but does not remove, the head element of this queue.
   */
  public Object peek() {
     //TODO
     return null;
  }

  /**
   * Retrieves, but does not remove, the head element of this queue.
   */
  public Object element() {
    if (size == 0)
      throw new NoSuchElementException();
    return container[header];
  }

  public boolean empty() {
    return size == 0;
  }
}

