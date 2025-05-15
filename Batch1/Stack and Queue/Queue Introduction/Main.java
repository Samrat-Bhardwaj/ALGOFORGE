import java.util.LinkedList;
class Main {
    public static void main(String[] args){
        LinkedList<Integer> que = new LinkedList<>();

        // adding to queue
        que.addLast(10);
        que.addLast(20);
        que.addLast(30);

        // peek for queue
        System.out.println(que.getFirst());

        // remove for queue
        System.out.println(que.removeFirst());
        System.out.println(que.removeFirst());
        System.out.println(que.removeFirst());
        System.out.println(que.removeFirst());
    }
}