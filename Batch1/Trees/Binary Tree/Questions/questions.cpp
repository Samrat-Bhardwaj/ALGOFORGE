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

// leetcode 144
vector<int> preorderTraversal(TreeNode* root) {
        
}

// leetcode 145 
vector<int> postorderTraversal(TreeNode* root) {
        
}

// leetcode 94 
vector<int> inorderTraversal(TreeNode* root) {
        
}

int main(){
    return 0;
}