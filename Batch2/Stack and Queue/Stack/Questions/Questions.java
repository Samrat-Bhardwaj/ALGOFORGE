import java.util.Stack;
class Questions {
    // check for duplicate bracket 
    public static boolean isDuplicate(String exp){
        Stack<Character> st = new Stack<>();

        for(int i=0; i<exp.length(); i++){
            char ch = exp.charAt(i);

            if(ch == ')'){
                // if "(" is on top -> we have duplicate brackets
                if(st.peek() == '('){
                    return true;
                }

                // pop everything till '('
                while(st.peek() != '('){
                    st.pop();
                }
                st.pop(); // popping '('
            } else {
                st.push(ch);
            }
        }

        return false;
    }

    // leetcode 20 (valid parentheses) =================================
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();

        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);

            if(ch == '(' || ch == '{' || ch == '['){
                st.push(ch);
            } else if(ch == ')'){
                if(st.size() == 0 || st.peek() != '('){
                    return false;
                }
                st.pop(); // pop '('
            } else if(ch == '}'){
                if(st.size() == 0 || st.peek() != '{'){
                    return false;
                }
                st.pop(); // pop '{'
            } else if(ch  == ']'){
                if(st.size() == 0 || st.peek() != '['){
                    return false;
                }
                st.pop(); // pop '['
            }
        }

        if(st.size() != 0){ //making sure every opening bracket had one closing bracket
            return false;
        }

        return true;
    }

    // next greater element on right (Moving from right to left)
    public ArrayList<Integer> nextLargerElement(int[] arr) {
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

        ArrayList<Integer> ans = new ArrayList<>();
        for(int i=0; i<n; i++) ans.add(ngr[i]);

        return ans;
    }

    // stock span (Next greater on left) (https://www.geeksforgeeks.org/problems/stock-span-problem-1587115621/1) =============================
    public ArrayList<Integer> calculateSpan(int[] arr) {
        ArrayList<Integer> ans = new ArrayList<>();
        Stack<Integer> st = new Stack<>();

        st.push(-1);

        for(int i=0; i<arr.length; i++){
            while(st.peek()!=-1 && arr[st.peek()] <= arr[i]){
                st.pop();
            }

            ans.add(i-st.peek());

            st.push(i);
        }

        return ans;
    }

    // Leetcode 84 (Largest area histogram) ======================================
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;

        int[] nsl = new int[n];
        int[] nsr = new int[n];

        Stack<Integer> st = new Stack<>();
        st.push(-1);

        for(int i=0; i<n; i++){
            while(st.peek()!=-1 && heights[st.peek()] >= heights[i]){
                st.pop();
            }
            
            nsl[i] = st.peek();

            st.push(i);
        }

        st = new Stack<>(); // emptying stack
        st.push(n);

        for(int i=n-1; i>=0; i--){
            while(st.peek() != n && heights[st.peek()] >= heights[i]){
                st.pop();
            }

            nsr[i] = st.peek();

            st.push(i);
        }

        int maxArea = 0;

        for(int i=0; i<n; i++){
            int h = heights[i];
            int w = nsr[i] - nsl[i] - 1;

            maxArea = Math.max(maxArea, h*w);
        }

        return maxArea;
    }

    // leetcode 84 (Largest area histogram)
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Stack<Integer> st = new Stack<>();
        st.push(-1);

        int maxArea = 0;
        
        for(int i=0; i<n; i++){
            while(st.peek()!=-1 && heights[st.peek()] > heights[i]){
                int poppedIdx = st.pop();

                int h = heights[poppedIdx];
                int nsr = i;
                int nsl = st.peek();

                maxArea = Math.max(maxArea, h*(nsr - nsl - 1));
            }

            st.push(i);
        }

        while(st.peek() != -1){
            int poppedIdx = st.pop();

            int h = heights[poppedIdx];
            int nsr = n;
            int nsl = st.peek();

            maxArea = Math.max(maxArea, h*(nsr - nsl - 1));
        }

        return maxArea;
    }



























    public static void main(String[] args){
        String exp1 = "((a+b)+(c+d))";
        String exp2 = "((a+b)+((c+d)))";

        System.out.println(isDuplicate(exp1));
        System.out.println(isDuplicate(exp2));
    }
}