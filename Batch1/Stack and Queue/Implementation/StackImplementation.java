// static stack 
class Stack {
    int maxSize = 10;
    int[] arr;
    int tos;

    public Stack(){
        arr = new int[maxSize];
        tos = -1;
    }

    public int peek(){
        if(tos == -1){
            System.out.println("Stack empty Exception!!");
            return -1;
        }

        return arr[tos];
    }

    public int pop(){
        if(tos == -1){
            System.out.println("Stack empty Exception!!");
            return -1;
        }

        int peekValue = arr[tos];
        tos--;

        return peekValue;
    }

    // if our stack is full, throw error of stackOverflow
    public void push(int value){
        if(tos + 1 >= maxSize){
            System.out.println("Stack Overflow!!!!");
            return;
        }

        tos++;
        arr[tos] = value;
    }

    public int size(){
        return tos + 1;
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