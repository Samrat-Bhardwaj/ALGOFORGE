#include<iostream>
#include<bits/stdc++.h>
using namespace std;

// leetcode 20 (valid parentheses)
bool isValid(string s) {
    stack<char> st;

    for(int i=0; i<s.size(); i++){
        char ch = s[i];

        if(ch == '(' || ch =='{' || ch == '['){
            st.push(ch);
        } else if(ch == ')'){
            if(st.size() == 0 || st.top() != '(') return false;

            st.pop(); // popping '('
        } else if(ch == '}'){
            if(st.size() == 0 || st.top() != '{') return false;

            st.pop(); // popping '{'
        } else if(ch == ']'){
            if(st.size() == 0 || st.top() != '[') return false;

            st.pop(); // popping '['
        }
    }

    return st.size() == 0;
}

// Next greater element (Moving from left to right)
vector<int> nextLargerElement(vector<int>& arr) {
    int n = arr.size();

    vector<int> ngr(n,-1);
    stack<int> st;

    for(int i=0; i<n; i++){
        int currElement = arr[i];

        while(st.size() > 0 && arr[st.top()] < currElement){
            ngr[st.top()] = currElement;
            st.pop();
        }

        st.push(i);
    }

    return ngr;
}

// Next smaller on left (Moving from right to left)
vector<int> prevSmaller(vector<int>& arr) {
    int n = arr.size();

    vector<int> nsl(n,-1);
    stack<int> st;

    for(int i=n-1; i>=0; i--){
        int currElement = arr[i];

        while(st.size() > 0 && arr[st.top()] > currElement){ // if elements are bigger, then they are on right and larger, so currEle is ans 
            nsl[st.top()] = currElement;
            st.pop();
        }  

        st.push(i);
    }

    return nsl;
}

    // Leetcode 503 (NGR on circular array) 
    vector<int> nextGreaterElements(vector<int>& nums) {
        int n = nums.size();

        vector<int> res(n, -1);

        stack<int> st;

        for(int i=0; i<2*n; i++){
            while(st.size() > 0 && nums[st.top()] < nums[i%n]){
                res[st.top()] = nums[i%n];
                st.pop();
            }

            if(i < n){ // second iteration is also to find answers, not add as questions
                st.push(i);
            }
        }

        return res;
    }





























void main(){

}