// static queue 
class Queue {
    private int maxSize = 8;
    private int front;
    private int size;
    private int[] data;

    public Queue(){
       front = 0;
       size = 0;
       data = new int[maxSize];
    }

    public void add(int value){
        if(size == maxSize){
            System.out.println("Queue overflow!!");
            return;
        }

        int idx = ( front + size ) % data.length;
        data[idx] = value;
        size++;
    }

    public int remove(){
        if(size == 0){
            System.out.println("Queue Empty!!");
            return -1;
        }

        int frontValue = data[front];
        front = (front + 1) % data.length;
        size--;

        return frontValue;
    }

    public int peek(){
        if(size == 0){
            System.out.println("Queue Empty!!");
            return -1;
        }

        return data[front];
    }

    public int size(){
        return size;
    }

    public void display(){
        for(int idx = 0; idx<size; idx++){
            System.out.print(data[(front+idx)%data.length]+" ,");
        }
        System.out.println();
    }
}

class QueueImplementation {
    public static void main(String[] args){
        Queue que = new Queue();

        que.add(2);
        que.add(23);

        System.out.println(que.peek()); // 2,23
        System.out.println(que.remove()); // 23
        System.out.println(que.peek()); // 23
        que.add(39); // 23,39
        que.display();
        System.out.println(que.remove());
        System.out.println(que.remove());
        System.out.println(que.size());
    }
}