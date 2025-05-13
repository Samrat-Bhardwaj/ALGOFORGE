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

    for(int i = n-1; i>=0; i--){
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


vector<int> nextLargerElement2(vector<int>& arr) {
    int n = arr.size(); 

    vector<int> ngr(n, -1);
    
    stack<int> st;

    for(int i=0; i<n; i++){
        while(st.size() && arr[st.top()] < arr[i]){
            ngr[st.top()] = arr[i];
            st.pop();
        }

        st.push(i);
    }

    return ngr;
}

// next smaller on left 
vector<int> leftSmaller(vector<int> arr) {
    vector<int> nsl;
    stack<int> st;
    st.push(-1);

    for(int i=0; i<arr.size(); i++){
        while(st.top()!=-1 && st.top()>=arr[i]){
            st.pop();
        }

        nsl.push_back(st.top());

        st.push(arr[i]);
    }
    return nsl;    
}

// https://www.geeksforgeeks.org/problems/stock-span-problem-1587115621/1
vector<int> calculateSpan(vector<int>& arr) {
    vector<int> ans;
    stack<int> st;
    st.push(-1);

    for(int i=0; i<arr.size(); i++){
        while(st.top()!=-1 && arr[st.top()] <= arr[i]){
            st.pop();
        }

        ans.push_back(i - st.top());
        st.push(i);
    }

    return ans;    
}

// leetcode 84 ==================================
int largestRectangleArea(vector<int>& heights) {
    int n = heights.size();
    vector<int> nsl(n,-1);
    vector<int> nsr(n,n);

    stack<int> st;
    st.push(-1);
    for(int i=0; i<n; i++){
        while(st.top()!=-1 && heights[st.top()] >= heights[i]){
            st.pop();
        }

        nsl[i] = st.top();
        st.push(i);
    }

    stack<int> st2;
    st2.push(n);
    for(int i=n-1; i>=0; i--){
        while(st2.top()!=n && heights[st2.top()] >= heights[i]){
            st2.pop();
        }

        nsr[i] = st2.top();

        st2.push(i);
    }

    int maxArea = 0;
    for(int i=0; i<n; i++){
        int h = heights[i];
        int w = nsr[i] - nsl[i] - 1;

        maxArea = max(maxArea, h * w);
    }

    return maxArea;
}


// leetcode 84 ===============================================
int largestRectangleArea(vector<int>& heights) {
    int n = heights.size();
    stack<int> st;
    int maxArea = 0;

    st.push(-1);

    for(int i=0; i<n; i++){
        while(st.top()!= -1 && heights[st.top()] >= heights[i]){
            int idxPopped = st.top(); st.pop();

            int h = heights[idxPopped];
            int nsr = i;
            int nsl = st.top();

            maxArea = max(maxArea, h * (nsr - nsl - 1));
        }

        st.push(i);
    }

    while(st.top()!=-1){
        int idxPopped = st.top(); st.pop();

        int h = heights[idxPopped];
        int nsr = n;
        int nsl = st.top();

        maxArea = max(maxArea, h * (nsr - nsl - 1));
    }

    return maxArea;
}


// merge intervals ================
vector<vector<int>> merge(vector<vector<int>>& intervals) {
    sort(intervals.begin(), intervals.end(), [](vector<int>& a, vector<int>&b){
        if(a[0] == b[0]){
            return a[1] < b[1];
        }

        return a[0] < b[0];
    });   
}






















void main(){

}