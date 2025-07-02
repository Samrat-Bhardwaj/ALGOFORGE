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

// leetcode 110 ==================================

int heightOfBinaryTree(TreeNode* root, bool& isTreeBalanced){
    if(!root) return -1;

    int leftHeight = heightOfBinaryTree(root->left, isTreeBalanced);
    int rightHeight = heightOfBinaryTree(root->right, isTreeBalanced);

    int balanceFactor = abs(leftHeight - rightHeight);

    if(balanceFactor >= 2){
        isTreeBalanced = false;
    }

    return max(leftHeight, rightHeight) + 1;
}


bool isBalanced(TreeNode* root) {
    bool isTreeBalanced = true;
    heightOfBinaryTree(root, isTreeBalanced);
    
    return isTreeBalanced;
}

int main(){
    return 0;
}