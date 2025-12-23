#include<bits/stdc++.h>

// leetcode 20 =====================
bool isValid(string s) {
    stack<char> st;
    
    for(int i=0; i<s.size(); i++){
        char ch = s[i];

        if(ch == '(' || ch == '{' || ch == '['){
            st.push(ch);
        } else if(ch == ')'){
            if(st.size() == 0 || st.top() != '('){
                return false;
            }
            st.pop(); // pop '('
        } else if(ch == '}'){
            if(st.size() == 0 || st.top() != '{'){
                return false;
            }
            st.pop(); // pop '{'
        } else if(ch  == ']'){
            if(st.size() == 0 || st.top() != '['){
                return false;
            }
            st.pop(); // pop '['
        }
    }

    return st.size() == 0;
}

// next greater on right (moving from right to left) (https://www.geeksforgeeks.org/problems/next-larger-element-1587115620/1)
vector<int> nextLargerElement(vector<int>& arr) {
    int n = arr.size();
    vector<int> ngr(n,-1);
    stack<int> st;

    for(int i=n-1; i>=0; i--){
        int currentEle = arr[i];

        while(st.size() > 0 && st.top() <= currentEle){
            st.pop();
        }

        if(st.size() != 0){
            ngr[i] = st.top();
        }

        st.push(currentEle);
    }

    return ngr;
}

// next greater on right (moving from left to right) (https://www.geeksforgeeks.org/problems/next-larger-element-1587115620/1)
vector<int> nextLargerElement(vector<int>& arr) {
    int n = arr.length;

    vector<int> ngr(n,-1);
    stack<int> st;

    for(int i=0; i<n; i++){
        int currentEle = arr[i];

        while(st.size() > 0 && arr[st.top()] < currentEle){
            ngr[st.top()] = currentEle;
            st.pop();
        }

        st.push(i);
    }

    return ngr;    
}

// Leetcode 503(Next greater element in circular array) ===========================
vector<int> nextGreaterElements(vector<int>& nums) {
    int n = nums.size();
    stack<int> st;

    vector<int> ngr(n,-1);   

    for(int i=0; i<2*n; i++){
        while(st.size() && nums[st.top()] < nums[i%n]){
            ngr[st.top()] = nums[i%n]; st.pop();
        }

        if(i < n){
            st.push(i);
        }
    }     

    return ngr;
}

// leetcode 921 (Minimum add to make parentheses valid) ============================
int minAddToMakeValid(string s) {
    int opening_bracket_req = 0;
    int extra_opening_bracket = 0;

    for(int i=0; i<s.size(); i++){
        if(s[i] == '('){
            extra_opening_bracket++;
        } else {
            if(extra_opening_bracket == 0){
                opening_bracket_req++;
            } else {
                extra_opening_bracket--;
            }
        }
    }

    return extra_opening_bracket + opening_bracket_req; 
}
