import java.util.PriorityQueue;
import java.util.Collections;
class Introduction {
    public static void main(String[] args){
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Collections.reverseOrder());

        pq.add(1);
        pq.add(98);
        pq.add(19);
        pq.add(45);

        // System.out.println(pq.peek());
        // System.out.println(pq.remove());
        // System.out.println(pq.remove());

        maxPQ.add(1);
        maxPQ.add(98);
        maxPQ.add(19);
        maxPQ.add(45);

        System.out.println(maxPQ.peek());
        System.out.println(maxPQ.remove());
        System.out.println(maxPQ.remove());


    }
}