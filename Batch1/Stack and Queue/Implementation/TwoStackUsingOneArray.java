// Two stack in one array (https://www.geeksforgeeks.org/problems/implement-two-stacks-in-an-array/1)
class TwoStacks {
    int maxSize = 7; // For gfg, it should be 100
    int[] data;
    int tos1;
    int tos2;

    TwoStacks() {
        data = new int[maxSize];
        tos1 = -1;
        tos2 = data.length;
    }

    // Function to push an integer into the stack1.
    void push1(int value) {
        if(tos1 + 1 == tos2){
            System.out.println("Stack overflow");
            return;
        }
        
        tos1++;
        data[tos1] = value;
    }

    // Function to push an integer into the stack2.
    void push2(int value) {
        if(tos2 - 1 == tos1){
            System.out.println("Stack overflow");
            return;
        }
        
        tos2--;
        data[tos2] = value;
    }

    // Function to pop an integer into the stack1.
    int pop1() {
        if(tos1 == -1){
            System.out.println("Stack Empty!!");
            return -1;
        }

        int topValue = data[tos1];
        tos1--;

        return topValue;
    }

    // Function to pop an integer into the stack2.
    int pop2() {
        if(tos2 == data.length){
            System.out.println("Stack Empty!!!!");
            return -1;
        }

        int topValue = data[tos2];
        tos2++;

        return topValue;
    }

    int size1() {
        return tos1 + 1;
    }

    int size2() {
        return data.length - tos2;
    }
}

class TwoStackUsingOneArray {
    public static void main(String[] args){

    }
}