class Queue {
    Stack<Integer> dataStack;
    public Queue(){
       dataStack = new Stack<Integer>();
    }

    public void push(int value){ // O(1)
        dataStack.push(value);
    }

    public int pop(){
        Stack<Integer> newDataStck = new Stack<>();

        while(dataStack.size () > 1){
            newDataStck.push(dataStack.pop());
        }

        int frontValue = dataStack.pop();

        while(newDataStck.size() > 0){
            dataStack.push(newDataStck.pop());
        }

        return frontValue;
    }

    public int peek(){
        Stack<Integer> newDataStck = new Stack<>();

        while(dataStack.size () > 1){
            newDataStck.push(dataStack.pop());
        }

        int frontValue = dataStack.pop();
        newDataStck.push(frontValue);

        while(newDataStck.size() > 0){
            dataStack.push(newDataStck.pop());
        }

        return frontValue;
    }

    public boolean empty(){
        return dataStack.size() == 0;
    }

    public int size(){
        return dataStack.size();
    }
}

class QueueUsingStackAddEfficient {
    public static void main(String[] args){
        Queue que = new Queue();

        que.add(2);
        que.add(23);
        que.add(2321);
        que.add(2123);

        
        que.add(2213);
        que.add(2213);

        System.out.println(que.peek()); 
        System.out.println(que.remove()); 
        System.out.println(que.peek());
        que.add(39);
        System.out.println(que.remove());
        System.out.println(que.remove());
        System.out.println(que.size());
    }
}