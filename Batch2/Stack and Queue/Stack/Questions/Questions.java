import java.util.*;
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
    public int largestRectangleArea2(int[] heights) {
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

    // ================================== QUESTIONS ON POSTFIX INFIX PREFIX EXPRESSIONS ==============================

    // Infix evaluation =================
    public static int precedence(char ch){
        if(ch == '+' || ch == '-'){
            return 1;
        } else if(ch == '/' || ch == '*'){ 
            return 2;
        } else {
            return 0;
        }
    }

    public static int evaluate(int v1, int v2, char op){
        if(op == '+'){
            return v1 + v2;
        } else if(op == '-'){
            return v1 - v2;
        } else if(op == '*'){
            return v1 * v2;
        } else {
            return v1 / v2;
        }
    }

    public static int evaluateInfixExp(String exp){
        Stack<Integer> operands = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for(int i=0; i<exp.length(); i++){
            char ch = exp.charAt(i); 

            if(ch >= '0' && ch <= '9'){
                operands.push(ch - '0');
            } else if(ch == '('){
                operators.push(ch);
            } else if(ch == '+' || ch == '-' || ch =='/' || ch == '*'){

                while(operators.size() > 0 && precedence(operators.peek()) >= precedence(ch)){
                    char op = operators.pop();
                    int v2 = operands.pop();
                    int v1 = operands.pop();

                    int res = evaluate(v1,v2,op);

                    operands.push(res);
                }

                operators.push(ch);
            } else if(ch == ')'){
                
                while(operators.peek() != '('){
                    char op = operators.pop();
                    int v2 = operands.pop();
                    int v1 = operands.pop();

                    int res = evaluate(v1,v2,op);

                    operands.push(res);
                }

                operators.pop(); // popping '('
            } 
        }

        while(operators.size() > 0){
            char op = operators.pop();
            int v2 = operands.pop();
            int v1 = operands.pop();

            int res = evaluate(v1,v2,op);

            operands.push(res);
        }

        return operands.peek();
    }

    // Infix to postfix and prefix expressions ============================

    public static void convertInfixToPrePost(String exp){
        Stack<Character> operators = new Stack<>();
        Stack<String> prefix = new Stack<>();
        Stack<String> postfix = new Stack<>();

        for(int i=0; i<exp.length(); i++){
            char ch = exp.charAt(i);

            if(ch >= '0' && ch <= '9'){
                prefix.push(ch + "");
                postfix.push(ch + "");
            } else if(ch == '('){
                operators.push(ch);
            } else if(ch == '+' || ch == '-' || ch == '/' || ch == '*'){

                while(operators.size() > 0 && precedence(operators.peek()) >= precedence(ch)){

                    char op = operators.pop();

                    // create prefix
                    String v2 = prefix.pop();
                    String v1 = prefix.pop();

                    String preRes = op + v1 + v2;
                    prefix.push(preRes);

                    // create postfix
                    String v2 = postfix.pop();
                    String v1 = postfix.pop();

                    String postRes = v1 + v2 + op;
                    postfix.push(postRes);
                }

                operators.push(ch);
            } else if(ch == ')'){

                while(operators.peek() != '('){
                    char op = operators.pop();

                    // create prefix
                    String v2 = prefix.pop();
                    String v1 = prefix.pop();

                    String preRes = op + v1 + v2;
                    prefix.push(preRes);

                    // create postfix
                    String v2 = postfix.pop();
                    String v1 = postfix.pop();

                    String postRes = v1 + v2 + op;
                    postfix.push(postRes);
                }
            }
        }

        while(operators.size() > 0){
            char op = operators.pop();

            // create prefix
            String v2 = prefix.pop();
            String v1 = prefix.pop();

            String preRes = op + v1 + v2;
            prefix.push(preRes);

            // create postfix
            String v2 = postfix.pop();
            String v1 = postfix.pop();

            String postRes = v1 + v2 + op;
            postfix.push(postRes);
        }

        System.out.println("Prefix expression is: " + prefix.peek());
        System.out.println("Postfix expression is: " + postfix.peek());
    }

    // to submit on GFG (^ dont have left to right precedence but right to left)
    class Solution {
    public int precedence(char ch){
        if(ch == '^'){
            return 3;
        }else if(ch == '+' || ch == '-'){
            return 1;
        } else if(ch == '/' || ch == '*'){ 
            return 2;
        } else {
            return 0;
        }
    }
    
    public String infixToPrefix(String exp) {
        // code here
        Stack<Character> operators = new Stack<>();
        Stack<String> prefix = new Stack<>();

        for(int i=0; i<exp.length(); i++){
            char ch = exp.charAt(i);

            if(ch == '('){
                operators.push(ch);
            } else if(ch == '+' || ch == '-' || ch == '/' || ch == '*' || ch =='^'){

                while(operators.size() > 0 && (precedence(operators.peek()) > precedence(ch) || (precedence(operators.peek()) == precedence(ch) && ch !='^'))){

                    char op = operators.pop();

                    // create prefix
                    String v2 = prefix.pop();
                    String v1 = prefix.pop();

                    String preRes = op + v1 + v2;
                    prefix.push(preRes);
                }

                operators.push(ch);
            } else if(ch == ')'){

                while(operators.peek() != '('){
                    char op = operators.pop();

                    // create prefix
                    String v2 = prefix.pop();
                    String v1 = prefix.pop();

                    String preRes = op + v1 + v2;
                    prefix.push(preRes);
                }
                
                operators.pop();
            } else {
                prefix.push(ch + "");
            }
        }

        while(operators.size() > 0){
            char op = operators.pop();

            // create prefix
            String v2 = prefix.pop();
            String v1 = prefix.pop();

            String preRes = op + v1 + v2;
            prefix.push(preRes);
        }

        return prefix.peek();
    }
}



























    public static void main(String[] args){
        // String exp1 = "((a+b)+(c+d))";
        // String exp2 = "((a+b)+((c+d)))";

        // System.out.println(isDuplicate(exp1));
        // System.out.println(isDuplicate(exp2));

        String infix =  "2 + 3 * 5 - 8 + ( 4 / 3 + 5 ) * 9";
        System.out.println(evaluateInfixExp(infix));
    }
}