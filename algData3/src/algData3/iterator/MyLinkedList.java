package algData3.iterator;

public class MyLinkedList {

    int size = 0;
    Node first;
    Node last;

    public class ListItr {

        private Node lastReturned;
        private Node next;
        private int nextIndex;

        ListItr(int index) {
            if(index == size)
                next = null;
            else
                next = node(index);
            nextIndex = index;
        }

        public boolean hasNext() {
            return next != null;
        }

        public Object next() {
            if (next == null) {
            	return null;
            }
            Object i = next.item;
            lastReturned = next;
            next = next.next;
            nextIndex++;
            return i;
        }

        public boolean hasPrevious() {
           return next != null && next.prev != null;
        }

        public Object previous() {
        	if (next == null)
        		return null;
        	Object i = next.prev.item;
        	lastReturned = next.prev;
        	nextIndex--;
            return i;
        }

        public int nextIndex() {
            return nextIndex;
        }

        public int previousIndex() {
            return nextIndex - 1;
        }
    }

    /**
     * Constructs an empty list.
     */
    public MyLinkedList() {
    }

    private static class Node {

        Object item;
        Node next;
        Node  prev;

        Node(Node prev, Object element, Node next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    public boolean add(Object e) {
        linkLast(e);
        return true;
    }

    void linkLast(Object e) {
        final Node l = last;
        final Node newNode = new Node(l, e, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    void linkBefore(Object e, Node succ) {
        // assert succ != null;
        final Node pred = succ.prev;
        final Node newNode = new Node(pred, e, succ);
        succ.prev = newNode;
        if (pred == null) {
            first = newNode;
        } else {
            pred.next = newNode;
        }
        size++;
    }

    public int size() {
        return size;
    }

    public Object get(int index) {
        return node(index).item;
    }

    Node node(int index) {
        // assert isElementIndex(index);

        if (index < (size >> 1)) {
            Node x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        } else {
            Node x = last;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
            return x;
        }
    }

    public ListItr createIterator(int index) {
        return new ListItr(index);
    }
}
