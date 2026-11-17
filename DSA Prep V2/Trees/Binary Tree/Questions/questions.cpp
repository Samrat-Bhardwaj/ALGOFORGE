#include<bits/stdc++.h>

// iterative traversals
vector<int> getTraversals(TreeNode* root){
    stack<pair<TreeNode*,int>> st;

    vector<int> preorder;
    vector<int> inorder;
    vector<int> postorder;

    if(root){
        st.push({root,0});
    }
    

    while(st.size()){
        pair<TreeNode*,int>& top = st.top();

        if(top.second == 0){
            preorder.push_back(top.first->val);
            top.second++;

            if(top.first->left){
                st.push({top.first->left, 0});
            }
        } else if(top.second == 1){
            inorder.push_back(top.first->val);
            top.second++;

            if(top.first->right){
                st.push({top.first->right, 0});
            }
        } else {
            postorder.push_back(top.first->val);
            st.pop();
        }
    }

    return postorder;
}

void main(){
    
}