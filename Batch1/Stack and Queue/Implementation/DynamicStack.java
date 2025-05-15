// static stack 
class Stack {
    int initialSize = 4;
    int[] arr;
    int tos;

    public Stack(){
        arr = new int[initialSize];
        tos = -1;
    }

    public int peek(){ // O(1)
        if(tos == -1){
            System.out.println("Stack empty Exception!!");
            return -1;
        }

        return arr[tos];
    }

    public int pop(){ // O(1)
        if(tos == -1){
            System.out.println("Stack empty Exception!!");
            return -1;
        }

        int peekValue = arr[tos];
        tos--;

        return peekValue;
    }

    // avergae O(1)
    public void push(int value){ // for N push, new array will be created log(N) times
        if(tos + 1 == arr.length){
            System.out.println("Creating new array");
            int[] newArray = new int[arr.length * 2];

            for(int i=0; i<arr.length; i++){
                newArray[i] = arr[i];
            }

            arr = newArray;
        }

        tos++;
        arr[tos] = value;
    }

    public int size(){
        return tos + 1;
    }
}

class DynamicStack {
    public static void main(String[] args){
        Stack st = new Stack();

        st.push(2);
        st.push(23);
        st.push(23);
        st.push(2123);
        st.push(2321);
        st.push(2323);
        st.push(223);
        st.push(13);
        st.push(123);
        st.push(2113);

        System.out.println(st.peek());
        System.out.println(st.pop());
        System.out.println(st.pop());
        System.out.println(st.peek());
        System.out.println(st.size());
    }
}