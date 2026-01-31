#include<bits/stdc++.h>

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

void traversal(TreeNode* root){
    stack<pair<TreeNode*,int>> st;
    vector<int> preorder;
    vector<int> inorder;
    vector<int> postorder;

    if(root){
        st.push({root,0});
    }

    while(st.size() > 0){
        TreeNode topNode = st.top().first;

        if(st.top().second == 0){
            preorder.push_back(topNode->val);
            
            st.top().second++;

            if(topNode->left){
                st.push({topNode->left, 0});
            }
        } else if(st.top().second == 1){
            inorder.push_back(topNode->val);

            st.top().second++;

            if(topNode->right){
                st.push({topNode->right,0});
            } 
        } else {
            postorder.push_back(topNode->val);

            st.pop();
        }
    }
}