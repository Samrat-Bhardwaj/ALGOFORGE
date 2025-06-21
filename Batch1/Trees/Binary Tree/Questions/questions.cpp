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
    // cpp is boring, solve in java
}

// leetcode 145 
vector<int> postorderTraversal(TreeNode* root) {
    // cpp is boring, solve in java
}

// leetcode 94 
vector<int> inorderTraversal(TreeNode* root) {
    // cpp is boring, solve in java
}

// leetcode 236 , LCA ==================================
vector<TreeNode*> nodeToRootPath(TreeNode* root, TreeNode* target){
    if(root == NULL){
        return {};
    }

    if(root == target){
        return {target};
    }

    vector<TreeNode*> leftAns = nodeToRootPath(root->left, target);
    if(leftAns.size() > 0){
        leftAns.push_back(root);
        return leftAns;
    }

    vector<TreeNode*> rightAns = nodeToRootPath(root->right, target);
    if(rightAns.size() > 0){
        rightAns.push_back(root);
        return rightAns;
    }

    return {};
}

TreeNode* lowestCommonAncestor(TreeNode* root, TreeNode* p, TreeNode* q) {
    vector<TreeNode*> ntrp = nodeToRootPath(root, p);
    vector<TreeNode*> ntrq = nodeToRootPath(root, q);

    int i = ntrp.size() - 1;
    int j = ntrq.size() - 1;

    while(i>=0 && j>=0 && ntrp[i] == ntrq[j]){
        i--;
        j--;
    }

    return ntrq[j+1];
}

// LCA better
bool LCA_better(TreeNode* root, TreeNode* p, TreeNode* q, TreeNode*& LCA){
    if(root == NULL || LCA != NULL){
        return false;
    }

    bool self = false;
    if(root == p || root == q){
        self = true;
    }

    bool left = LCA_better(root->left, p , q, LCA);
    bool right = LCA_better(root->right, p , q, LCA);

    if((left && right) || (self && left) || (self && right)){
        LCA = root;
    }

    return self || left || right;
}

TreeNode* lowestCommonAncestor(TreeNode* root, TreeNode* p, TreeNode* q) {
    TreeNode* LCA = nullptr;
    LCA_better(root, p, q, LCA);
    return LCA;
}

int main(){
    return 0;
}