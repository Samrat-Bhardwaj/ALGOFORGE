import java.util.*;

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
    public int largestRectangleArea2(int[] heights) {
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

                maxArea = Math.max(maxArea, h * (nsr - nsl - 1));
            }

            st.push(i);
        }

        while(st.peek()!=-1){
            int idxPopped = st.pop();
            int h = heights[idxPopped];
            
            int nsr = n; // next smaller on right
            int nsl = st.peek(); // next smaller on left

            maxArea = Math.max(maxArea, h * (nsr - nsl - 1));
        }

        return maxArea;
    }

    // https://www.geeksforgeeks.org/problems/fun-with-expresions2523/1
    public static int precendence(char ch){
        if(ch=='/' || ch=='*'){
            return 2;
        } else if(ch=='+' || ch=='-'){
            return 1;
        }
        return 0;
    }

    public static int calculate(int v1, int v2, char ch){
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

                    char op = operators.pop();

                    int newValue = calculate(v1,v2,op);

                    operands.push(newValue);
                }

                operators.push(ch);
            } else if(ch == ')'){
                while(operators.peek() != '('){
                    int v2 = operands.pop();
                    int v1 = operands.pop();

                    char op = operators.pop();

                    int newValue = calculate(v1,v2,op);

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


    // convert infix expressions to postfix and prefix 
    public static void infixToPostPre(String infix){ // no spaces in given string
        Stack<String> prefix = new Stack<>();
        Stack<String> postfix = new Stack<>();

        Stack<Character> operators = new Stack<>();

        for(int i=0; i<infix.length(); i++){
            char ch = infix.charAt(i);

            if(ch=='+' || ch =='-' || ch == '*' || ch =='/' || ch=='^'){
                while(operators.size()>0 && operators.peek()!='(' && precendence(operators.peek()) >= precendence(ch)){
                    String preV2 = prefix.pop();
                    String preV1 = prefix.pop();

                    String postV2 = postfix.pop();
                    String postV1 = postfix.pop();

                    char op = operators.pop();

                    calculateAndPush(preV1, preV2, postV1, postV2, op, prefix, postfix);
                }
                operators.push(ch);
            } else if(ch=='('){
                operators.push(ch);
            } else if(ch == ')'){
                while(operators.peek() != '('){
                    String preV2 = prefix.pop();
                    String preV1 = prefix.pop();

                    String postV2 = postfix.pop();
                    String postV1 = postfix.pop();

                    char op = operators.pop();

                    calculateAndPush(preV1, preV2, postV1, postV2, op, prefix, postfix);
                }

                operators.pop();
            } else {
                prefix.push(ch+"");
                postfix.push(ch+"");
            }

        }

        while(operators.size() > 0){
            String preV2 = prefix.pop();
            String preV1 = prefix.pop();

            String postV2 = postfix.pop();
            String postV1 = postfix.pop();

            char op = operators.pop();

            calculateAndPush(preV1, preV2, postV1, postV2, op, prefix, postfix);
        }

        System.out.println(prefix.peek());
        System.out.println(postfix.peek());
    }

    public static void calculateAndPush(String preV1, String preV2, String postV1, String postV2, char op, Stack<String> prefix, Stack<String> postfix){
        prefix.push(op + preV1 + preV2);
        postfix.push(postV1 + postV2 + op);
    }


    // prefix evaluation and conversion ====================================
    public static void prefixConversionAndEvaluation(String prefix){
        Stack<Integer> res = new Stack<>();
        Stack<String> infix = new Stack<>();
        Stack<String> postfix = new Stack<>();

        for(int i=prefix.length()-1; i>=0; i--){
            char ch = prefix.charAt(i);

            if(ch == '+' || ch =='-' || ch =='*' || ch=='/'){
                int op1 = res.pop();
                int op2 = res.pop();

                int value = calculate(op1,op2,ch);
                res.push(value);

                String infV1 = infix.pop();
                String infV2 = infix.pop();

                infix.push("(" + infV1 + ch + infV2 + ")");

                String postV1 = postfix.pop();
                String postV2 = postfix.pop();

                postfix.push(postV1 + postV2 + ch);
            } else {
                res.push(ch-'0');
                infix.push(ch+"");
                postfix.push(ch+"");
            }
        }

        System.out.println("Result is " + res.pop());
        System.out.println("Infix expression is " + infix.pop());
        System.out.println("Postfix expression is " + postfix.pop());
    }


























    // leetcode 239 (Sliding window maximum) ================================================

    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;

        int[] ans = new int[n - k + 1];

        int[] ngr = new int[n];
        Arrays.fill(ngr, n);
        Stack<Integer> st = new Stack<>();

        for(int i=0; i<n; i++){
            while(st.size()>0 && nums[st.peek()] < nums[i]){
                ngr[st.pop()] = i;
            }

            st.push(i);
        }

        int ansIndex = 0;

        for(int idx=0; idx<ans.length; idx++){ // idx = window starting point
            if(ansIndex < idx){
                ansIndex = idx;
            }

            while(ngr[ansIndex] < idx + k){
                ansIndex = ngr[ansIndex];
            }

            ans[idx] = nums[ansIndex];
        }

        return ans;
    }


    // merge intervals (Leetcode 56) ======================================================
    class Pair {
        int startTime;
        int endTime;

        public Pair(int startTime, int endTime){
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

    public int[][] merge(int[][] intervals) {
        int n = intervals.length;

        // nlogn
        Arrays.sort(intervals, (int[] a, int[] b) -> { // this and other will be compared
            if(a[0] == b[0]){
                return a[1] - b[1];
            }
            return a[0] - b[0]; // increasing order sort, decreasing order (b - a);
        });

        Stack<Pair> st = new Stack<>();
        Pair[] intervalPairs = new Pair[n];

        for(int i=0; i<n; i++){
            intervalPairs[i] = new Pair(intervals[i][0], intervals[i][1]);
        }

        for(int i=0; i<n; i++){
            Pair current = intervalPairs[i];

            while(st.size()>0 && st.peek().endTime >= current.startTime){
                Pair top = st.pop();

                current.startTime = top.startTime;
                current.endTime = Math.max(current.endTime, top.endTime);
            }

            st.push(current);
        }

        int[][] ans = new int[st.size()][2];
        for(int i=0; i<ans.length; i++){
            ans[i][0] = st.peek().startTime;
            ans[i][1] = st.peek().endTime;
            st.pop();
        }   

        // not neccessory for leetcode
        // for(int i=0; i<ans.length/2; i++){
        //     ans[i][0] = ans[ans.length - i -1][0];
        //     ans[i][1] = ans[ans.length - i -1][1];
        // }


        return ans;
    }

    // leetcode 155 (min stack) =================================
    class MinStack {
        Stack<Integer> st;
        Stack<Integer> minStack;
    
        public MinStack() {
            st = new Stack<>();
            minStack = new Stack<>();
        }
        
        public void push(int val) {
            if(minStack.size() == 0 || minStack.peek() > val){
                minStack.push(val);
            }

            st.push(val);
        }
        
        public void pop() {
            if(st.peek() == minStack.peek()){
                minStack.pop();
            }

            return st.pop();
        }
        
        public int top() {
            return st.peek();
        }
        
        public int getMin() {
            return minStack.peek();
        }
    }

    // leetcode 155 (min stack in O(1) space) =================================
    class MinStack {
        Stack<Long> st;
        long min = -1;
    
        public MinStack() {
            st = new Stack<>();
        }
        
        public void push(int x) {
            if(st.size() == 0){
                st.push((long)x);
                min = x;
            }else if(x < min){
                long updatedValue = (2 * (long)x) - min;
                st.push(updatedValue);
                min = x;
            } else {
                st.push((long)x);
            }
        }
        
        public void pop() { // you need to return current_min
            if(st.peek() < min){
                long prev_min = 2*min - st.peek();
                min = prev_min;
            } 

            st.pop(); 
        }
        
        public int top() {
            if(st.peek() < min){
                return (int)min;
            } 

            return Math.toIntExact(st.peek());
        }
        
        public int getMin() {
            return (int)min;
        }
    }


    // leetcode 503 (NGR in circular array) ===============================================
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        Stack<Integer> st = new Stack<>();

        int[] ngr = new int[n];
        Arrays.fill(ngr,-1);

        for(int i=0; i<2*n; i++){
            while(st.size()>0 && nums[st.peek()] < nums[i%n]){
                ngr[st.pop()] = nums[i%n];
            }

            if(i<n){
                st.push(i);
            }
        }

        return ngr;
    }

    // leetcode 946 ==========================================================
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> st = new Stack<>();
        int n = pushed.length;

        int j = 0;

        for(int i=0; i<n; i++){
            while(st.size()>0 && st.peek() == popped[j]){
                st.pop();
                j++;
            }

            st.push(pushed[i]);
        }

        while(st.size()>0 && j<n && st.peek() == popped[j]){
            st.pop();
            j++;
        }

        return j == n;
    }

    // leetcode 921  ========================================
    public int minAddToMakeValid(String s) {
        int extra_closing_brackets = 0;
        int extra_opening_brackets = 0;

        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);

            if(ch == '('){
                extra_opening_brackets++;
            } else {
                if(extra_opening_brackets == 0){ // closing bracket without any opening 
                    extra_closing_brackets++;
                } else { // closing bracket for some opening bracket
                    extra_opening_brackets--;
                }
            }
        }

        return extra_closing_brackets + extra_opening_brackets;
    }


    // leetcode 1021
    public String removeOuterParentheses(String s) {
        StringBuilder sb = new StringBuilder();
        // String ans = "";

        int ob = 0;
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == '('){
                if(ob != 0){
                    sb.append('('); // O(1)
                    // ans += "("; // O(N);
                }
                ob++;
            } else {
                if(ob != 1){
                    sb.append(')');
                }
                ob--;
            }
        }

        return sb.toString();    
    }

    // leetcode 1541 ====================================
    public int minInsertions(String s) {
        int n = s.length();
        int extra_closing_brackets = 0;
        int extra_opening_brackets = 0;
        int closing_brackets_required = 0;

        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);

            if(ch == '('){
                extra_opening_brackets++; 
            } else {
                if(i+1 < n && s.charAt(i+1) == ')'){
                    i++;
                } else {
                    closing_brackets_required++;
                }

                if(extra_opening_brackets == 0){
                    extra_closing_brackets++;
                } else {
                    extra_opening_brackets--;
                }
            }
        }

        return extra_opening_brackets*2 + extra_closing_brackets + closing_brackets_required;
    }


    // leetcode 678 ============================================
    public boolean checkValidString(String s) {
        int max = 0;
        int min = 0;

        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            if(ch == '('){
                max++;
                min++;
            } else if(ch == ')'){
                max--;
                min--;
            } else {
                max++; // i will consider this as '('
                min--; // i will consider this as ')'
            }

            if(max < 0) return false;
            if(min < 0) min = 0;
        }
        // min > 0 -> ob is extra 
        // max < 0 -> cb is extra;

        return min == 0;
    }

    // leetcode 856 =====================================================
    public int scoreOfParentheses(String s) {
        Stack<Integer> st = new Stack<>();

        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            if(ch == '('){
                st.push(-1);
            } else {
                if(st.peek() == -1){
                    st.pop();
                    st.push(1);
                } else {
                    int currentScore = 0;
                    while(st.peek() != -1){
                        currentScore += st.pop();
                    }

                    st.pop();
                    st.push(2*currentScore);
                }
            }
        }

        int finalScore = 0;
        while(st.size() > 0){
            finalScore += st.pop();
        }        

        return finalScore;
    }

    // leetcode 856 O(1) Space =========================================
    public int scoreOfParentheses(String s) {
        int extra_opening_brackets = 0;
        int finalScore = 0;

        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            if(ch == '('){
                extra_opening_brackets++;
            } else {
                extra_opening_brackets--;
            }

            if(ch == ')'  && s.charAt(i-1) == '('){
                // finalScore += (1 << extra_opening_brackets);
                finalScore += (int)(Math.pow(2, extra_opening_brackets));
            }
        }

        return finalScore;
    }

    // leetcode 456 =============================================
    public boolean find132pattern(int[] nums) {
        int n = nums.length;

        int[] minSoFar = new int[n];
        minSoFar[0] = nums[0];

        for(int i=1; i<n; i++){
            minSoFar[i] = Math.min(minSoFar[i-1], nums[i]);
        }

        Stack<Integer> possibleKValue = new Stack<>();
        possibleKValue.push(nums[n-1]);

        for(int j=n-2; j>=0; j--){
            int min = minSoFar[j];

            while(possibleKValue.size()>0 && min >= possibleKValue.peek()){
                possibleKValue.pop();
            }

            if(possibleKValue.size()>0 && nums[j] > possibleKValue.peek()){
                return true;
            }

            possibleKValue.push(nums[j]);
        }

        return false;
    }

    // leetcode 735 ===================================
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> st = new Stack<>();

        for(int i=0; i<asteroids.length; i++){
            int val = asteroids[i];

            if(val > 0){
                st.push(val);
            } else {
                boolean canPush = true;
                while(st.size() > 0 && st.peek()>0){
                    if(st.peek() < -(val)){
                        canPush = true;
                        st.pop();
                    } else if(st.peek() == -(val)){
                        canPush = false;
                        st.pop();
                        break;
                    } else {
                        canPush = false;
                        break;
                    }
                }

                if(canPush){
                    st.push(val);
                }
            }
        }

        int[] ans = new int[st.size()];

        for(int i=ans.length-1; i>=0; i--){
            ans[i] = st.pop();
        }

        return ans;
    }

    // leetcode 402 ===================================
    public String removeKdigits(String num, int k) {
        Stack<Character> st = new Stack<>();

        for(int i=0; i<num.length(); i++){
            char currVal = num.charAt(i);

            while(k>0 && st.size()>0 && st.peek() > currVal){
                st.pop();
                k--;
            }

            if(st.size() == 0 && currVal == '0') continue; // leading zeros
            st.push(currVal);
        }

        while(k-- > 0 && st.size()>0){
            st.pop();
        }
        if(st.size() == 0) return "0";

        StringBuilder sb = new StringBuilder();
        while(st.size()>0){
            sb.append(st.pop());
        }

        sb.reverse();
        return sb.toString();
    }

    // Trapping rain water (leetcode 42) =======================
    public int trap(int[] height) {
        int n = height.length;

        int[] lmax = new int[n]; // max from 0 to i-1
        int[] rmax = new int[n]; // max to from i+1 to n-1

        for(int i=1; i<n; i++){
            lmax[i] = Math.max(lmax[i-1], height[i-1]);
        }

        for(int i=n-2; i>=0; i--){
            rmax[i] = Math.max(rmax[i+1], height[i+1]);
        }

        int totalWater = 0;

        for(int i=1; i<n-1; i++){
            int maxHeightAllowed = Math.min(lmax[i], rmax[i]);

            totalWater = totalWater + Math.max(maxHeightAllowed - height[i],0);
        }

        return totalWater;
    }

    // method 2
    public int trap(int[] height) {
        int n = height.length;

        int[] lmax = new int[n]; // max from 0 to i
        int[] rmax = new int[n]; // max to from i to n-1

        lmax[0] = height[0];
        for(int i=1; i<n; i++){
            lmax[i] = Math.max(lmax[i-1], height[i]);
        }

        rmax[n-1] = height[n-1];
        for(int i=n-2; i>=0; i--){
            rmax[i] = Math.max(rmax[i+1], height[i]);
        }

        int totalWater = 0;

        for(int i=0; i<n; i++){
            int maxHeightAllowed = Math.min(lmax[i], rmax[i]);

            totalWater = totalWater + maxHeightAllowed - height[i];
        }

        return totalWater;
    }

    // Trapping rain water in one iteration =============================
    public int trap(int[] height) {
        Stack<Integer> st = new Stack<>();

        int totalWater = 0;

        for(int i=0; i<height.length; i++){
            while(st.size()>0 && height[st.peek()] < height[i]){
                int currentIdx = st.pop();
                if(st.size() == 0) break;

                int right = i;
                int left = st.peek();

                int allowedHeight = Math.min(height[right], height[left]);

                int h = allowedHeight - height[currentIdx];

                int w = right - left - 1;

                totalWater += h*w;
            }

            st.push(i);
        }

        return totalWater;
    }

    // Trapping rainwater O(1) space ================================
    public int trap(int[] height) {
        int n = height.length;

        int i=0;
        int j=n-1;

        int lmax = 0; 
        int rmax = 0; 

        int totalWater = 0;

        while(i < j){
            lmax = Math.max(lmax, height[i]);
            rmax = Math.max(rmax, height[j]);
        
            if(lmax <= rmax){
                totalWater += lmax - height[i];
                i++;
            } else {
                totalWater += rmax - height[j];
                j--;
            } 
        }

        return totalWater;
    }


























    public static void main(String[] args){
        // String str = "((a+(b))+(c+d))";
        // boolean ans = checkDuplicate(str);

        // if(ans){
        //     System.out.println("Duplicate brackets!!!!!!");
        // } else {
        //     System.out.println("No Duplicate brackets");
        // }

        // String str = "(a+b)*(c+d)";
        // infixToPostPre(str);
        String prefix = "-+7*45+20";
        prefixConversionAndEvaluation(prefix);
    }
}