class twoStacks {
    int[] data;
    int tos1;
    int tos2;
    twoStacks() {
        data = new int[200];
        tos1 = -1;
        tos2 = data.length;
    }

    void push1(int x) {
        if(tos1 + 1 == tos2){ // overflow condition
            return;
        }
        tos1++;
        data[tos1] = x;
    }

    void push2(int x) {
        if(tos1 + 1 == tos2){ // overflow condition
            return;
        }
        tos2--;
        data[tos2] = x;
    }
    

    // Function to remove an element from top of the stack1.
    int pop1() {
        if(tos1 == -1){
            return -1;
        }
        int topEle = data[tos1];
        tos1--;
        
        return topEle;
    }

        
    // Function to remove an element from top of the stack2.
    int pop2() {
        if(tos2 == data.length){
            return -1;
        }
        int topEle = data[tos2];
        tos2++;
        
        return topEle;
    }

    int size1(){
        return tos1 + 1;
    }

    int size2(){
        return data.length - tos2;
    }
}