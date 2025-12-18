import java.util.Stack;
class MyQueue {
    Stack<Integer> data;

    public MyQueue(){
        data = new Stack<>();
    }

    // O(N)
    public void add(int val){
        Stack<Integer> temp = new Stack<>();

        // store values somewhere else
        while(data.size() > 0){
            temp.push(data.pop());
        }

        data.push(val);

        // replenish data values
        while(temp.size() > 0){
            data.push(temp.pop());
        }
    }

    // O(1)
    public int remove(){
        if(data.size() == 0){
            System.out.println("Queue is empty!!");
            return -1;
        }

        return data.pop();
    }

    // O(1)
    public int peek(){
        if(data.size() == 0){
            System.out.println("Queue is empty!!");
            return -1;
        }

        return data.peek();
    }
}

class QueueUsingStackRemoveEfficient {
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