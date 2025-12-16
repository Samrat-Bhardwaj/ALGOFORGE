import java.util.LinkedList;
class Main {
    public static void main(String[] args){
        LinkedList<Integer> que = new LinkedList<>();

        que.addLast(10); // push / add
        que.addLast(20);
        que.addLast(30);

        System.out.println(que.getFirst()); // peek
        System.out.println(que.removeFirst()); // pop
        System.out.println(que.removeFirst());
        System.out.println(que.removeFirst());
        System.out.println(que.removeFirst());

    }
}