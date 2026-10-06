class MyQueue {
    private int[] data;
    private int front;
    private int size;

    public MyQueue(){
        data = new int[4];
        front = 0;
        size = 0;
    }

    public void add(int val){
        if(size == data.length){
            int[] newData = new int[data.length * 2];

            for(int i=0; i<size; i++){
                int olderDataIdx = (front + i) % data.length;
                newData[i] = data[olderDataIdx];
            }

            data = newData;
            front = 0;
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

class DynamicQueueConstruction {
    public static void main(String[] args){
        MyQueue que = new MyQueue();

        que.add(10);
        que.add(20);
        que.add(30);
        que.add(40);
        que.add(50);

        System.out.println(que.pop());
        System.out.println(que.pop());
        // System.out.println(que.peek());
        System.out.println(que.pop());
        System.out.println(que.pop());
        System.out.println(que.pop());
        System.out.println(que.pop());
    }
}