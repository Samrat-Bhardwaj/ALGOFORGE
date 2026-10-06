import java.util.Stack;
class MyQueue {
    Stack<Integer> data;

    public MyQueue(){
        data = new Stack<>();
    }

    // O(1)
    public void add(int val){
        data.push(val);
    }

    // O(N)
    public int remove(){
        if(data.size() == 0){
            System.out.println("Queue is empty!!");
            return -1;
        }

        Stack<Integer> temp = new Stack<>();

        // remove everything to get to bottom value in stack
        while(data.size() > 1){
            temp.push(data.pop());
        }

        int queueFrontValue = data.pop();

        // fill original stack again
        while(temp.size() > 0){
            data.push(temp.pop());
        }

        return queueFrontValue;
    }

    // O(N)
    public int peek(){
        if(data.size() == 0){
            System.out.println("Queue is empty!!");
            return -1;
        }

        Stack<Integer> temp = new Stack<>();

        // remove everything to get to bottom value in stack
        while(data.size() > 1){
            temp.push(data.pop());
        }

        int queueFrontValue = data.peek(); // do not remove as its peek
        
        // fill original stack again
        while(temp.size() > 0){
            data.push(temp.pop());
        }

        return queueFrontValue;
    }
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