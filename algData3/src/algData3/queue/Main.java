package algData3.queue;


public class Main {

    public static void main(String[] args) {
        
        QueueList listQueue = new QueueList();
        QueueArray arrayQueue = new QueueArray(30);
        
        for (int i = 0; i <= 20; i++) {
            double ele = 1000 * Math.random();
            listQueue.offer(ele);
            arrayQueue.offer(ele);
        }

        while (!listQueue.empty()) {
            System.out.println("LinkedList Queue: " + listQueue.remove());
            System.out.println("Array Queue     : " + arrayQueue.remove());

        }
    }
}
