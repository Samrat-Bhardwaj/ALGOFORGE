import java.util.Stack;

class Main {
    // duplicate parenthesis
    public static boolean checkDuplicate(String str){
        int n = str.length();

        Stack<Character> st = new Stack<>();

        for(int i=0; i<n; i++){
            char ch = str.charAt(i);

            if(ch == ')'){
                if(st.peek() == '('){ // nothing in between opening and closing bracket => duplicate
                    return true;
                }

                while(st.peek() != '('){
                    st.pop();
                }

                st.pop();
            } else {
                st.push(ch);
            }
        }

        return false;
    }

    // leetcode 20 
    public boolean isValid(String s) {
        int n = s.length();
        Stack<Character> st = new Stack<>();

        for(int i=0; i<n; i++){
            char ch = s.charAt(i);

            if(ch == '(' || ch == '{' || ch == '['){
                st.push(ch);
            } else if(ch == ')'){
                if(st.size()== 0 || st.peek() != '(') return false;
                st.pop(); // popping '('
            } else if(ch == '}'){
                if(st.size()== 0 || st.peek() != '{') return false;
                st.pop(); // popping '{'
            } else if(ch == ']'){
                if(st.size()== 0 || st.peek() != '[') return false;
                st.pop();
            }
        }

        return st.size() == 0;
    }

    // next greater element
    public int[] nextLargerElement(int[] arr) {
        int n = arr.length;

        int[] ngr = new int[n];
        Stack<Integer> st = new Stack<>();

        for(int i=n-1; i>=0; i--){
            int currentEle = arr[i];

            while(st.size() > 0 && st.peek() <= currentEle){
                st.pop();
            }

            if(st.size() == 0){
                ngr[i] = -1;
            } else {
                ngr[i] = st.peek();
            }

            st.push(currentEle);
        }

        return ngr;
    }


























    public static void main(String[] args){
        String str = "((a+(b))+(c+d))";
        boolean ans = checkDuplicate(str);

        if(ans){
            System.out.println("Duplicate brackets!!!!!!");
        } else {
            System.out.println("No Duplicate brackets");
        }
    }
}