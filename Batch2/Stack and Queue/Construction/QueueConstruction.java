class MyQueue {
    final int maxSize = 7;

    public MyQueue(){}

    public void add(int val){}

    public int pop(){}

    public int peek(){}

    public int size(){}
}

class QueueConstruction {
    public static void main(String[] args){
        MyQueue que = new MyQueue();

        que.add(10);
        que.add(20);
        que.add(30);
        que.add(40);

        System.out.println(que.pop());
        System.out.println(que.pop());
        System.out.println(que.peek());
        System.out.println(que.pop());
    }
}