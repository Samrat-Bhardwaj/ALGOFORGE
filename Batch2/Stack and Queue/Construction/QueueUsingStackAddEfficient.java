class MyQueue {
    Stack<Integer> data;

    public MyQueue(){}

    // O(1)
    public void add(int val){}

    public int remove(){}

    public int peek(){}
}

class QueueUsingStackAddEfficient {
    public static void main(String[] args){
        MyQueue que = new MyQueue();

        que.add(10);
        que.add(20);
        que.add(30);
        que.add(40);

        System.out.println(que.remove()); // 10
        System.out.println(que.peek()); // 20

        que.add(50);
        que.add(60);

        System.out.println(que.remove()); // 20
        System.out.println(que.remove()); // 30
    }
}