import java.util.PriorityQueue;
import java.util.Collections;

class Main {
    public static void main(String[] args){
        // PriorityQueue<Integer> minPQ = new PriorityQueue<>();

        // minPQ.add(15);
        // minPQ.add(10);
        // minPQ.add(5);
        // minPQ.add(8);

        // System.out.println(minPQ.remove());
        // System.out.println(minPQ.remove());

        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Collections.reverseOrder());
        maxPQ.add(15);
        maxPQ.add(10);
        maxPQ.add(5);
        maxPQ.add(8);

        System.out.println(maxPQ.remove());
        System.out.println(maxPQ.remove());
    }
}