#include<vector>

using namespace std;

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};


// Diameter of a binary Tree (Leetcode 543)===================

// Method 3, for first 2 methods see Java

int heightOfBinaryTree(TreeNode* root, int& maxDia){
    if(root == NULL){
        return -1;
    }

    int leftHeight = heightOfBinaryTree(root->left, maxDia);
    int rightHeight = heightOfBinaryTree(root->right, maxDia);

    int currentNodeDiameter = leftHeight + rightHeight + 2;
    maxDia = max(maxDia, currentNodeDiameter);

    return max(leftHeight, rightHeight) + 1;
}

int diameterOfBinaryTree(TreeNode* root) {
    int maxDia = -1;
    heightOfBinaryTree(root, maxDia);
    return maxDia;
}


// Leetcode 124 (Node to Node max path sum) ==
int maxNodeToNode(TreeNode* root, int& ans){
    if(root == nullptr){
        return -(int)(1e8);
    }

    int maxLeftPathSum = maxNodeToNode(root->left, ans);
    int maxRightPathSum = maxNodeToNode(root->right, ans);


    int maxPathSum = max(max(maxLeftPathSum + root->val, maxRightPathSum + root->val), root->val);
    // 3 Scenarios -> left Path + current node, right path + current node, current node

    int maxNodeToNode = max(maxPathSum, maxLeftPathSum + maxRightPathSum + root->val);
    ans = max(ans, maxNodeToNode);
    // all the above 3 scenariors, leftPathSum + rightPathSum + root.val

    return maxPathSum;
}

int maxPathSum(TreeNode* root) {
    int ans = -(int)(1e8);
    maxNodeToNode(root,ans);
    return ans;
}


















int main(){
    return 0;
}