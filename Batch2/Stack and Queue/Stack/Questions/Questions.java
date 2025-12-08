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



























    public static void main(String[] args){
        String exp1 = "((a+b)+(c+d))";
        String exp2 = "((a+b)+((c+d)))";

        System.out.println(isDuplicate(exp1));
        System.out.println(isDuplicate(exp2));
    }
}