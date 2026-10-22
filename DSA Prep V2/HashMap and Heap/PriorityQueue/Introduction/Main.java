import java.util.PriorityQueue;
import java.util.Collections;

class Main {
    public static void main(String[] args){
        PriorityQueue<Integer> minPQ = new PriorityQueue<>();

        minPQ.add(10); 
        minPQ.add(101);
        minPQ.add(7);
        minPQ.add(15);

        System.out.println(minPQ.poll()); // 7

        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Collections.reverseOrder());

        maxPQ.add(10);
        maxPQ.add(101);
        maxPQ.add(7);
        maxPQ.add(15);

        System.out.println(maxPQ.poll()); // 101
        System.out.println(maxPQ.poll());
        System.out.println(maxPQ.poll());
        System.out.println(maxPQ.poll());
        System.out.println(maxPQ.poll());
    }
}