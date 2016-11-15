package algData3.iterator;


public class Main {

    public static void main(String[] args) {
        MyLinkedList ll = new MyLinkedList();
        for (int i = 0; i <= 20; i++) {
            ll.add(i);
        }

        MyLinkedList.ListItr li = ll.createIterator(4);

        System.out.println("Print forward from index 4");
        while (li.hasNext()) {
            System.out.print(li.next() + " ");
        }
        System.out.println("\nPrint backwards from index 18");
        li = ll.createIterator(18);
        while (li.hasPrevious()) {
            System.out.print(li.previous() + " ");
        }
    }
}
