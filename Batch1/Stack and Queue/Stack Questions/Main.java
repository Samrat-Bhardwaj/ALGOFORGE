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

    // next greater element from left to right
    public int[] nextLargerElement2(int[] arr){
        int n = arr.length;

        int[] ngr = new int[n]; // next greater right
        Arrays.fill(ngr, -1);

        Stack<Integer> st = new Stack<>();

        for(int i=0; i<n; i++){
            while(st.size() > 0 && arr[st.peek()] < arr[i]){
                ngr[st.pop()] = arr[i];
            }

            st.push(i);
        }

        return ngr;
    }


    // next smaller on left 
    public int[] leftSmaller(int[] arr) {
        int n = arr.length;

        int nsl[] = new int[n];

        Stack<Integer> st = new Stack<>();
        st.push(-1);

        for(int i=0; i<n; i++){
            while(st.peek()!=-1 && st.peek() >= arr[i]){
                st.pop();
            }

            nsl[i] = st.peek();

            st.push(arr[i]);
        }

        return nsl;
    }


    // https://www.geeksforgeeks.org/problems/stock-span-problem-1587115621/1
    public ArrayList<Integer> calculateSpan(int[] arr) {
        ArrayList<Integer> ans = new ArrayList<>();

        Stack<Integer> st = new Stack<>();
        st.push(-1);

        for(int i=0; i<arr.length; i++){
            while(st.peek()!=-1 && arr[st.peek()] <= arr[i]){
                st.pop();
            }

            ans.add(i- st.peek());

            st.push(i);
        }

        return ans;
    }

    // leetcode 84 =======================================
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

        st = new Stack<>();
        st.push(n);
        for(int i=n-1; i>=0; i--){
            while(st.peek()!=n && heights[st.peek()] >= heights[i]){
                st.pop();
            }

            nsr[i] = st.peek();

            st.push(i);
        }

        int maxArea = 0;
        for(int i=0; i<n; i++){
            int h = heights[i];
            int w = nsr[i] - nsl[i] - 1;

            maxArea = Math.max(maxArea, h * w);
        }

        return maxArea;
    }


    // leetcode 84 optimized ====================
    public int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        int n = heights.length;
        Stack<Integer> st = new Stack<>();

        st.push(-1);

        for(int i=0; i<n; i++){
            // doesn't matter if you pop equal elements or not
            while(st.peek()!=-1 && heights[st.peek()] >= heights[i]){
                int idxPopped = st.pop();
                int h = heights[idxPopped];
                
                int nsr = i; // next smaller on right
                int nsl = st.peek(); // next smaller on left

                maxArea = Math.max(area, h * (nsr - nsl - 1));
            }

            st.push(i);
        }

        while(st.peek()!=-1){
            int idxPopped = st.pop();
            int h = heights[idxPopped];
            
            int nsr = n; // next smaller on right
            int nsl = st.peek(); // next smaller on left

            maxArea = Math.max(area, h * (nsr - nsl - 1));
        }

        return maxArea;
    }

    // https://www.geeksforgeeks.org/problems/fun-with-expresions2523/1
    public int precendence(char ch){
        if(ch=='/' || ch=='*'){
            return 2;
        } else if(ch=='+' || ch=='-'){
            return 1;
        }
    }

    public int calculate(int v1, int v2, char ch){
        if(ch == '/'){
            return v1 / v2;
        } else if(ch == '*'){
            return v1 * v2;
        } else if(ch == '-'){
            return v1 - v2;
        } else {
            return v1 + v2;
        }
    }

    public int calculate(String s) {
        Stack<Integer> operands = new Stack<>();
        Stack<Character> operators = new Stack<>();
        

        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);

            if(Character.isDigit(ch)){ // character is digit
                int num = 0;
                int j = i;
                while(Character.isDigit(j)){
                    num = num*10 + s.charAt(j) - '0';
                    j++;
                }
                operands.push(num);
                i = j-1;
            } else if(ch == '('){
                operators.push(ch);
            } else if(ch == '+' || ch == '-' || ch == '*' || ch == '/'){
                while(operators.size() > 0 && operators.peek()!= '(' && precendence(operators.peek()) >= precendence(ch)){
                    int v2 = operands.pop();
                    int v1 = operands.pop();

                    int op = operators.pop();

                    char newValue = calculate(v1,v2,op);

                    operands.push(newValue);
                }

                operators.push(ch);
            } else if(ch == ')'){
                while(operators.peek() != '('){
                    int v2 = operands.pop();
                    int v1 = operands.pop();

                    int op = operators.pop();

                    char newValue = calculate(v1,v2,op);

                    operands.push(newValue);
                }
                operators.pop();
            }
        }


        while(operators.size()>0){
            int v2 = operands.pop();
            int v1 = operands.pop();

            char op = operators.pop();

            int newValue = calculate(v1,v2,op);

            operands.push(newValue);
        }

        return operands.peek();
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