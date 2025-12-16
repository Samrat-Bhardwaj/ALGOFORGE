class MyQueue {
    final int maxSize = 7;
    private int[] data;
    private int front;
    private int size;

    public MyQueue(){
        data = new int[maxSize];
        front = 0;
        size = 0;
    }

    public void add(int val){
        if(size == maxSize){
            System.out.println("Queue Overflow");
            return;
        }

        int idx = (front + size) % data.length;
        data[idx] = val;

        size++;
    }

    public int pop(){
        if(size == 0){
            System.out.println("Queue empty Exception!!");
            return -1;
        }

        int frontEle = data[front];

        front = (front + 1) % data.length;
        size--;

        return frontEle;
    }

    public int peek(){
        if(size == 0){
            System.out.println("Queue empty Exception!!");
            return -1;
        }

        return data[front];
    }

    public int size(){
        return size;
    }
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