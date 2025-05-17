class Stack {
    LinkedList<Integer> dataQue;

    public Stack(){
        dataQue = new LinkedList<>();
    }

    public void push(int value){ // O(N)
        LinkedList<Integer> newDataQue = new LinkedList<Integer>();

        newDataQue.addLast(value);

        while(dataQue.size() > 0){
            int removedValue = dataQue.removeFirst();
            newDataQue.addLast(removedValue);
        }

        dataQue = newDataQue;
    }

    public int peek(){ // O(1)
        return dataQue.getFirst();
    }

    public int pop(){ // O(1)
        return dataQue.removeFirst();
    }

    public int size(){
        return que.size();
    }
}

class StackUsingQueuePushEfficient {
    public static void main(String[] args){
        Stack st = new Stack();

        st.push(2);
        st.push(23);

        System.out.println(st.peek());
        System.out.println(st.pop());
        System.out.println(st.pop());
        System.out.println(st.peek());
        System.out.println(st.size());
    }
}