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



















int main(){
    return 0;
}