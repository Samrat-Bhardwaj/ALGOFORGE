class Stack {
    LinkedList<Integer> dataQue;

    public Stack(){
        dataQue = new LinkedList<>();
    }

    public void push(int value){ // O(1)
        dataQue.addLast(value);
    }

    public int peek(){ // O(N)
        LinkedList<Integer> newDataQue = new LinkedList<Integer>();

        while(dataQue.size() > 1){
            int removedValue = dataQue.removeFirst();
            newDataQue.addLast(removedValue);
        }

        int peekValue = dataQue.removeFirst();
        newDataQue.add(peekValue);

        dataQue = newDataQue;
        return peekValue;
    }

    public int pop(){ // O(N)
        LinkedList<Integer> newDataQue = new LinkedList<Integer>();

        while(dataQue.size() > 1){
            int removedValue = dataQue.removeFirst();
            newDataQue.addLast(removedValue);
        }

        int peekValue = dataQue.removeFirst();

        dataQue = newDataQue;
        return peekValue;
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