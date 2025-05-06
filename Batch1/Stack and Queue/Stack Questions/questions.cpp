bool checkDuplicate(string& str){
    int n = str.size();

    stack<char> st;

    for(int i=0; i<n; i++){
        char ch = str[i];

        if(ch == ')'){
            if(st.top() == '('){
                return true;
            }

            while(st.top() != '('){
                st.pop();
            }

            st.pop();
        } else {
            st.push(ch);
        }
    }

    return false;
}

// leetcode 20 ======================
bool isValid(string s) {
    stack<char> st;

    for(int i=0; i<s.size(); i++){
        char ch = s[i];
        if(ch == '(' || ch =='{' || ch == '['){
            st.push(ch);
        } else if(ch == ')'){
            if(st.size()== 0 || st.top() != '(') return false;
            st.pop(); // popping '('
        } else if(ch == '}'){
            if(st.size()== 0 || st.top() != '{') return false;
            st.pop(); // popping '{'
        } else if(ch == ']'){
            if(st.size()== 0 || st.top() != '[') return false;
            st.pop();
        }
    }

    if(st.size() != 0) return false;
    return true;
}



// next greater element 
vector<int> nextLargerElement(vector<int>& arr) {
    int n = arr.size(); 

    vector<int> ngr(n, -1);
    
    stack<int> st;

    for(int in-1; i>=0; i--){
        int currEle = arr[i];

        while(st.size()>0 && st.top() <= currEle){
            st.pop();
        }

        if(st.size() == 0){
            ngr[i] = -1;
        } else {
            ngr[i] = st.top();
        }

        st.push(currEle);
    }

    return ngr;
}












void main(){

}