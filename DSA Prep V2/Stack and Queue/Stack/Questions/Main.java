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

    // Leetcode 239
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;

        int[] ngr = new int[n];
        Stack<Integer> st = new Stack<>();

        for(int i=0; i<n; i++){
            while(st.size() > 0 && nums[st.peek()] < nums[i]){
                ngr[st.pop()] = i;
            }

            st.push(i);
        }

        while(st.size() > 0){
            ngr[st.pop()] = n;
        }

        int[] ans = new int[n-k+1];
        int answerIdx = 0;

        for(int idx=0; idx<ans.length; idx++){ // idx = starting point of window
            if(answerIdx < idx){
                answerIdx = idx;
            }

            while(ngr[answerIdx] < idx + k){
                answerIdx = ngr[answerIdx];
            }

            ans[idx] = nums[answerIdx];
        }
        
        return ans;
    }

    // INFIX, PREFIX, POSTFIX Evaluations ==================================================================

    // Infix evaluation

    class Solution {
        public int precendence(char ch){
            if(ch == '/' || ch == '*'){
                return 2;
            } else if(ch == '+' || ch == '-'){
                return 1;
            }
        }

        public int findRes(int v1, int v2, char op){
            if(op == '/'){
                return v1 / v2;
            } else if(op == '*'){
                return v1 * v2;
            } else if(op == '-'){
                return v1 - v2;
            } else {
                return v1 + v2;
            }
        }

        public int evaluateInfix(String[] arr) {
            Stack<Integer> operands = new Stack<>();
            Stack<Character> operators = new Stack<>();

            for(int i=0; i<arr.length; i++){
                char ch = arr[i].charAt(0);

                if(ch == '/' || ch =='*' || ch == '-' || ch == '+'){
                    while(operators.size() > 0 && precendence(operators.peek()) >= precendence(ch)){
                        char op = operators.pop();
                        int v2 = operands.pop();
                        int v1 = operands.pop();

                        int res = findRes(v1,v2,op);

                        operands.push(res);
                    }

                    operators.push(ch);
                } else {
                    operands.push(Integer.parseInt(arr[i]));
                }
            }

            while(operators.size() > 0){
                char op = operators.pop();
                int v2 = operands.pop();
                int v1 = operands.pop();

                int res = findRes(v1,v2,op);

                operands.push(res);
            }

            return operands.peek();
        }
    }

    // Leetcode 227 (Basic calculator)

    class Solution {
        public int precendence(char ch){
            if(ch == '/' || ch == '*'){
                return 2;
            } else if(ch == '+' || ch == '-'){
                return 1;
            }
            return 0;
        }

        public int findRes(int v1, int v2, char op){
            if(op == '/'){
                return v1 / v2;
            } else if(op == '*'){
                return v1 * v2;
            } else if(op == '-'){
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

                if(Character.isDigit(ch)){
                    int j = i;
                    int num = 0;

                    while(j < s.length() && Character.isDigit(s.charAt(j))){
                        num = num*10 + (s.charAt(j) - '0');
                        j++;
                    }

                    operands.push(num);
                    i = j-1;
                } else if(ch == '/' || ch =='*' || ch == '-' || ch == '+'){
                    while(operators.size() > 0 && precendence(operators.peek()) >= precendence(ch)){
                        char op = operators.pop();
                        int v2 = operands.pop();
                        int v1 = operands.pop();

                        int res = findRes(v1,v2,op);

                        operands.push(res);
                    }

                    operators.push(ch);
                }
            }

            while(operators.size() > 0){
                char op = operators.pop();
                int v2 = operands.pop();
                int v1 = operands.pop();

                int res = findRes(v1,v2,op);

                operands.push(res);
            }

            return operands.peek();
        }
    }

    // Infix to prefix conversion
    class Solution {
        public static int precendence(char ch){
            if(ch == '^'){
                return 3;  
            } if(ch == '/' || ch == '*'){
                return 2;
            } else if(ch == '+' || ch == '-'){
                return 1;
            }
            return 0;
        }

        static String infixToPrefix(String s) {
            Stack<String> operands = new Stack<>();
            Stack<Character> operators = new Stack<>();

            for(int i=0; i<s.length(); i++){
                char ch = s.charAt(i);

                if(ch == '(' || ch =='^'){
                    operators.push(ch);
                } else if(ch == '/' || ch =='*' || ch == '-' || ch == '+'){
                    while(operators.size() > 0 && precendence(operators.peek()) >= precendence(ch)){
                        char op = operators.pop();
                        String v2 = operands.pop();
                        String v1 = operands.pop();

                        String res = op + v1 + v2;

                        operands.push(res);
                    }

                    operators.push(ch);
                } else if(ch == ')'){
                    while(operators.peek() != '('){
                        char op = operators.pop();
                        String v2 = operands.pop();
                        String v1 = operands.pop();

                        String res = op + v1 + v2;

                        operands.push(res);
                    }
                    operators.pop(); // removing '('
                } else {
                    operands.push(ch + "");
                }
            }

            while(operators.size() > 0){
                char op = operators.pop();
                String v2 = operands.pop();
                String v1 = operands.pop();

                String res = op + v1 + v2;

                operands.push(res);
            }

            return operands.peek();
        }
    }

    // GFG infix evaluation
    class Solution {
    public int precendence(char ch){
            if(ch == '^'){
                return 3;
            } else if(ch == '/' || ch == '*'){
                return 2;
            } else if(ch == '+' || ch == '-'){
                return 1;
            }
            
            return 0;
        }

        public int findRes(int v1, int v2, char op){
            if(op == '/'){
                return v1 / v2;
            } else if(op == '*'){
                return v1 * v2;
            } else if(op == '-'){
                return v1 - v2;
            } else if(op == '+'){
                return v1 + v2;
            } else {
                return (int)Math.pow(v1,v2);
            }
        }

        public int evaluateInfix(String[] arr) {
            Stack<Integer> operands = new Stack<>();
            Stack<Character> operators = new Stack<>();

            for(int i=0; i<arr.length; i++){
                char ch = arr[i].charAt(0);

                if(ch == '^'){ // power, wait for the next integer
                    operators.push(ch);
                } else if(ch == '/' || ch =='*' || ch == '-' || ch == '+'){
                    while(operators.size() > 0 && precendence(operators.peek()) >= precendence(ch)){
                        char op = operators.pop();
                        int v2 = operands.pop();
                        int v1 = operands.pop();

                        int res = findRes(v1,v2,op);
                        operands.push(res);
                    }

                    operators.push(ch);
                } else {
                    operands.push(Integer.parseInt(arr[i]));
                }
            }

            while(operators.size() > 0){
                char op = operators.pop();
                int v2 = operands.pop();
                int v1 = operands.pop();

                int res = findRes(v1,v2,op);

                operands.push(res);
            }

            return operands.peek();
        }
}

// Infix evaluation
class Solution {
    public int findRes(int v1, int v2, char op){
        if(op == '/'){
            double d = v1/(v2*1.0);
            return (int)Math.floor(d);
        } else if(op == '*'){
            return v1 * v2;
        } else if(op == '-'){
            return v1 - v2;
        } else if(op == '+'){
            return v1 + v2;
        } else {
            return (int)Math.pow(v1,v2);
        }
    }
    
    public int evaluatePrefix(String[] arr) {
        Stack<Integer> operands = new Stack<>();

        for(int i=arr.length-1; i>=0; i--){
            char ch = arr[i].charAt(0);
            
            if(Character.isDigit(ch) || (arr[i].length() > 1 && ch == '-')){
                operands.push(Integer.parseInt(arr[i]));
            } else if(ch == '/' || ch =='*' || ch == '-' || ch == '+' ||ch == '^'){
                int v1 = operands.pop();
                int v2 = operands.pop();
    
                int res = findRes(v1,v2,ch);
    
                operands.push(res);
            }
        }
        
        
        return operands.peek();
    }
}

    // Leetcode 56 (Merge intervals)
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (int[] a, int[] b)-> {
            // if(a[0] == b[0]){
            //     return b[1] - a[1]; // if 0th index equal, greater first idx will come before
            // }
            return a[0] - b[0]; // increasing order sort

            // return b[0] - a[0]; -> decreasing order sort
        });

        Stack<int[]> st = new Stack<>();
        st.push(intervals[0]);

        for(int i=1; i<intervals.length; i++){
            int[] prev = st.peek();
            int[] curr = intervals[i];

            if(prev[1] >= curr[0]){ // prev ka end time >= curr ka start time
                prev[1] = Math.max(prev[1], curr[1]); // max of prev and curr end time
            } else {
                st.push(curr);
            }
        }

        int[][] res = new int[st.size()][2];
        
        for(int i=0; i<res.length; i++){
            res[i][0] = st.peek()[0];
            res[i][1] = st.peek()[1];

            st.pop();
        }

        // we don't care about order, if you do, reverse the res array
        return res;
    }

    // Min Stack (Leetcode 155) ===========================
    class MinStack {
        Stack<Integer> data;
        Stack<Integer> minData;

        public MinStack() {
            data = new Stack<>();
            minData = new Stack<>();    
        }
        
        public void push(int value) {
            if(data.size() == 0 || minData.peek() >= value){
                minData.push(value);
            }

            data.push(value);
        }
        
        public void pop() {
            if(data.peek().equals(minData.peek())){ // comparing "Integers" and not int
                minData.pop();
            }

            data.pop();
        }
        
        public int top() {
            return data.peek();
        }
        
        public int getMin() {
            return minData.peek();
        }
    }

// Min Stack in O(1) Space (Leetcode 155)
class MinStack {

    Stack<Long> data;
    long min;
    public MinStack() {
        data = new Stack<>();
        min = -1;
    }
    
    public void push(int value) {
        if(data.size() > 0 && value < min){
            data.push(value - (min - value)); // 2*value - min
            min = value;
        } else if(data.size() == 0){
            min = value;
            data.push((long)value);
        } else {
            data.push((long)value);
        }
    }
    
    public void pop() {
        if(data.peek() < min){
            long curr_min = min;
            long prev_min = 2*curr_min - data.peek();

            min = prev_min;
        }

        data.pop();
    }
    
    public int top() {
        if(data.peek() < min){
            return (int)(min);
        }

        return Math.toIntExact(data.peek());
    }
    
    public int getMin() {
        return (int)(min);
    }
}

    // Leetcode 503 (NGR on circular array) 
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;

        int[] res = new int[n];

        Stack<Integer> st = new Stack<>();

        for(int i=0; i<2*n; i++){
            while(st.size() > 0 && nums[st.peek()] < nums[i%n]){
                res[st.pop()] = nums[i%n];
            }

            if(i < n){ // second iteration is also to find answers, not add as questions
                st.push(i);
            }
        }

        while(st.size() > 0){
            res[st.pop()] = -1;
        }

        return res;
    }

    // 132 Pattern in O(N^2) =======================================
    public int[] getMinSoFar(int[] nums){
        int n = nums.length;

        int[] minSoFar = new int[n];
        minSoFar[0] = nums[0];

        for(int i=1; i<n; i++){
            minSoFar[i] = Math.min(nums[i], minSoFar[i-1]);
        }

        return minSoFar;
    }

    public boolean find132pattern(int[] nums) {
        int n = nums.length;

        int[] minSoFar = getMinSoFar(nums);

        for(int j=1; j<n; j++){
            int firstNum = minSoFar[j-1];
            int secondNum = nums[j];

            if(firstNum >= secondNum){ 
                continue;
            }

            for(int k = j+1; k < n; k++){
                if(nums[k] < secondNum && nums[k] > firstNum){
                    return true;
                }
            }
        }

        return false;
    }

    // 132 Pattern in O(N) =============================
    public int[] getMinSoFar(int[] nums){
        int n = nums.length;

        int[] minSoFar = new int[n];
        minSoFar[0] = nums[0];

        for(int i=1; i<n; i++){
            minSoFar[i] = Math.min(nums[i], minSoFar[i-1]);
        }

        return minSoFar;
    }

    public boolean find132pattern(int[] nums) {
        int n = nums.length;

        int[] minSoFar = getMinSoFar(nums);

        Stack<Integer> possibleKValues = new Stack<>();
        possibleKValues.push(nums[n-1]);

        for(int j=n-2; j>=1; j--){
            int firstNum = minSoFar[j-1];
            
            // removing elements smaller than firstNum
            while(possibleKValues.size() > 0 && possibleKValues.peek() <= firstNum){
                possibleKValues.pop();
            }

            // possibleKValues.peek() is greater than first num and smaller than nums[j]
            if(possibleKValues.size() > 0 && possibleKValues.peek() < nums[j]){
                return true;
            }

            possibleKValues.push(nums[j]); // you can be k in next iteration
        }

        return false;
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