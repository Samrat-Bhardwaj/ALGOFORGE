// static stack 
class Stack {
    int maxSize = 10;

    public Stack(){

    }

    public int peek(){

    }

    public int pop(){

    }

    // if our stack is full, throw error of stackOverflow
    public void push(int value){

    }

    public int size(){

    }
}

class StackImplementation {
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