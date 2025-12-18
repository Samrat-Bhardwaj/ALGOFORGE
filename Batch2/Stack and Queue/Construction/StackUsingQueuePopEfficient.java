import java.util.LinkedList;
class MyStack {
    private LinkedList<Integer> data;

    public MyStack(){
        data = new LinkedList<>();
    }

    public void push(int val){
        LinkedList<Integer> newData = new LinkedList<>();
        newData.addLast(val);

        // push old data
        while(data.size() > 0){
            newData.addLast(data.removeFirst());
        }

        data = newData;
    }

    // O(1)
    public int pop(){
        if(data.size() == 0){
            System.out.println("Stack empty Exception!!");
            return -1;
        }

        return data.removeFirst();
    }

    // O(1)
    public int peek(){
        if(data.size() == 0){
            System.out.println("Stack empty Exception!!");
            return -1;
        }

        return data.getFirst();
    }
}

class StackUsingQueuePopEfficient {
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