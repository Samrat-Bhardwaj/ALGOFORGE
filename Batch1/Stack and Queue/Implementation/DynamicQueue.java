// dynamic queue 
class Queue {
    private int intialSize = 4;
    private int front;
    private int size;
    private int[] data;

    public Queue(){
       front = 0;
       size = 0;
       data = new int[intialSize];
    }

    public void add(int value){
        if(size == data.length){
            int[] newData = new int[data.length * 2];

            for(int i=0; i<size; i++){
                int idxForOldData = (front + i) % data.length;
                newData[i] = data[idxForOldData];
            }

            data = newData;
            front = 0;
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

class DynamicQueue {
    public static void main(String[] args){
        Queue que = new Queue();

        que.add(2);
        que.add(23);
        que.add(2321);
        que.add(2123);

        que.display();
        
        que.add(2213);
        que.add(2213);
        que.display();

        // System.out.println(que.peek()); // 2,23
        // System.out.println(que.remove()); // 23
        // System.out.println(que.peek()); // 23
        // que.add(39); // 23,39
        // que.display();
        // System.out.println(que.remove());
        // System.out.println(que.remove());
        // System.out.println(que.size());
    }
}