MyStack {
    public MyStack(){}

    public void push(int val){}

    public int pop(){}

    public int peek(){}

    public int size(){}
}

class Main {
    public static void main(String[] args){
        MyStack st = new MyStack();

        st.push(2);
        st.push(3);
        st.push(4);
        st.push(5);

        System.out.println(st.pop());
        System.out.println(st.peek());
        System.out.println(st.size());
        System.out.println(st);
    }
}