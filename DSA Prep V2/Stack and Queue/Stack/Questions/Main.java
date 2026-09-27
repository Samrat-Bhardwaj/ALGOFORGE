import java.util.Stack;
class Main {
    public static boolean isDuplicateBracket(String str){
        Stack<Character> st = new Stack<>();

        for(int i=0; i<str.length(); i++){
            char ch = str.charAt(i);

            if(ch == ')'){
                // if opening bracket at top, its duplicate
                if(st.peek() == '('){
                    return true;
                }

                // remove all the characters till we find opening bracket
                while(st.peek() != '('){
                    st.pop();
                }
                st.pop(); // removing opening bracket
            } else {
                st.push(ch);
            }
        }

        return false;
    }

    // Leetcode 20 (Valid parentheses)
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();

        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);

            if(ch == '(' || ch =='{' || ch == '['){
                st.push(ch);
            } else if(ch == ')'){
                if(st.size() == 0 || st.peek() != '(') return false;

                st.pop(); // popping '('
            } else if(ch == '}'){
                if(st.size() == 0 || st.peek() != '{') return false;

                st.pop(); // popping '{'
            } else if(ch == ']'){
                if(st.size() == 0 || st.peek() != '[') return false;

                st.pop(); // popping '['
            }
        }

        return st.size() == 0;
    }

    // ================================================== NEXT GREATER ELEMENT ========================================================

    // Next Greater element on right (https://www.geeksforgeeks.org/problems/next-larger-element-1587115620/1)
    public ArrayList<Integer> nextLargerElement(int[] arr) {
        int n = arr.length;

        int[] ngr = new int[n];
        Stack<Integer> st = new Stack<>(); // its better to store indices, we are storing elements for simplicity though
        
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

        ArrayList<Integer> res = new ArrayList<>();
        for(int i=0; i<n; i++) res.add(ngr[i]);

        return res;
    }

    public ArrayList<Integer> nextLargerElement(int[] arr) {
        int n = arr.length;

        int[] ngr = new int[n];
        Stack<Integer> st = new Stack<>();

        for(int i=0; i<n; i++){
            int currElement = arr[i];

            while(st.size() > 0 && arr[st.peek()] < currElement){
                ngr[st.pop()] = currElement;
            }

            st.push(i);
        }

        while(st.size() > 0){
            ngr[st.pop()] = -1;
        }

        ArrayList<Integer> res = new ArrayList<>();
        for(int i=0; i<n; i++) res.add(ngr[i]);

        return res;
    }

    // Next smaller element on left (Moving from left to right)
    public static ArrayList<Integer> prevSmaller(int[] arr) {
        int n = arr.length;
        Stack<Integer> st = new Stack<>();
        st.push(-1);

        int[] nsl = new int[n];

        for(int i=0; i<n; i++){
            int currElement = arr[i];

            while(st.peek()!=-1 && st.peek() >= currElement){ // dont need bigger elements on left
                st.pop();
            }

            nsl[i] = st.peek();
            st.push(currElement);
        }

        ArrayList<Integer> res = new ArrayList<>();
        for(int i=0; i<n; i++) res.add(nsl[i]);

        return res;
    }

    // Next smaller on left (Moving from right to left)
    public static ArrayList<Integer> prevSmaller(int[] arr) {
        int n = arr.length;

        int[] nsl = new int[n];
        Arrays.fill(nsl, -1);
        Stack<Integer> st = new Stack<>();

        for(int i=n-1; i>=0; i--){
            int currElement = arr[i];

            while(st.size() > 0 && arr[st.peek()] > currElement){ // if elements are bigger, then they are on right and larger, so currEle is ans 
                nsl[st.pop()] = currElement;
            }  

            st.push(i);
        }

        ArrayList<Integer> res = new ArrayList<>();
        for(int i=0; i<n; i++) res.add(nsl[i]);

        return res;
    }
























    public static void main(String[] args){
        String str = "((a+(b))+c+d)";

        boolean isDuplicate = isDuplicateBracket(str);

        if(isDuplicate){
            System.out.println("Brackets are duplicate!!!");
        } else {
            System.out.println("Brackets are not duplicate!!!");
        }
    }
}