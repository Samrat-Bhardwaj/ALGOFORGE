import java.util.LinkedList;
class MyStack {
    private LinkedList<Integer> data;

    public MyStack(){
        data = new LinkedList<>();
    }

    // O(1)
    public void push(int val){
        data.addLast(val);
    }

    // O(N)
    public int pop(){
        if(data.size() == 0){
            System.out.println("Stack empty Exception!!");
            return -1;
        }

        LinkedList<Integer> newData = new LinkedList<>();
        while(data.size() > 1){
            int frontValue = data.removeFirst();
            newData.addLast(frontValue);
        }

        int stackTopValue = data.removeFirst();
        data = newData;

        return stackTopValue;
    }

    // O(N)
    public int peek(){
        if(data.size() == 0){
            System.out.println("Stack empty Exception!!");
            return -1;
        }

        LinkedList<Integer> newData = new LinkedList<>();
        while(data.size() > 1){
            int frontValue = data.removeFirst();
            newData.addLast(frontValue);
        }

        int stackTopValue = data.removeFirst();
        newData.addLast(stackTopValue);
        data = newData;
        
        return stackTopValue;
    }
}

class StackUsingQueuePushEfficient {
    public static void main(String[] args){
        MyStack st = new MyStack();

        st.push(10);
        st.push(20);
        st.push(30);
        st.push(40);

        System.out.println(st.peek());
        System.out.println(st.pop());
        System.out.println(st.pop());

        st.push(100);
        st.push(110);

        System.out.println(st.pop());
    }
}