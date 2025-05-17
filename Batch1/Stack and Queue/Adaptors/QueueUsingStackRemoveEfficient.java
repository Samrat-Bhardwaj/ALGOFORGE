class Queue {
    Stack<Integer> dataStack;
    public Queue(){
       dataStack = new Stack<>();
    }

    public void push(int value){ 
        Stack<Integer> tempStack = new Stack<>();

        while(dataStack.size() > 0){
            tempStack.push(dataStack.pop());
        }

        dataStack.push(value); // new value will be at the bottom of the stack

        while(tempStack.size() > 0){
            dataStack.push(tempStack.pop());
        }
    }

    public int pop(){ //O(1)
        return dataStack.pop();
    }

    public int peek(){ //O(1)
        return dataStack.peek();
    }

    public boolean empty(){
        return dataStack.size() == 0;
    }

    public int size(){
        return dataStack.size();
    }
}

class QueueUsingStackRemoveEfficient {
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

