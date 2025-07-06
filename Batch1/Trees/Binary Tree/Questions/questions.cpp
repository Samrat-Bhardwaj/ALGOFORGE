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

// leetcode 112
bool hasPathSum(TreeNode* root, int targetSum) {
    if(root == nullptr) return false;

    if(root->left == NULL && root->right == NULL){ // leaf
        return root->val == targetSum;
    }

    return hasPathSum(root->left, targetSum - root->val) || hasPathSum(root->right, targetSum - root->val);
}

// leetcode 113 
void traverse(TreeNode* root, int targetSum,vector<int>& currentPath,vector<vector<int>>& allPaths){
    if(!root) return;

    if(!root->left && !root->right){
        if(root->val == targetSum){
            currentPath.push_back(root->val);
            allPaths.push_back(currentPath);
            currentPath.pop_back();
        }
        return;
    }

    currentPath.push_back(root->val);

    traverse(root->left, targetSum - root->val, currentPath, allPaths);
    traverse(root->right, targetSum - root->val, currentPath, allPaths);

    currentPath.pop_back();
}

vector<vector<int>> pathSum(TreeNode* root, int targetSum) {
    vector<vector<int>> allPaths;
    vector<int> currentPath;

    traverse(root,targetSum,currentPath,allPaths);

    return allPaths;
}

// leetcode 297 (Serialize and deserialize BT) ===================
class Codec {
public:

    // Encodes a tree to a single string.
    string serialize(TreeNode* root) {
        if(!root){
            return "#";
        }

        return to_string(root->val) + "," + serialize(root->left) + "," + serialize(root->right);
    }

    // Decodes your encoded data to tree.
    TreeNode* makeTreeFromString(string& data, int& idx){
        if(data[idx] == '#'){
            idx += 2;
            return nullptr;
        }

        int num = 0;
        bool isNegative = false;
        while(data[idx] != ','){
            if(data[idx] == '-'){
                isNegative = true;
                idx++;
                continue;
            }
            num = num*10 + (data[idx] - '0');
            idx++;
        }
        idx++;

        if(isNegative) num *= -1;

        TreeNode* root = new TreeNode(num);

        root->left = makeTreeFromString(data,idx); 
        root->right = makeTreeFromString(data,idx); 

        return root;
    }
    TreeNode* deserialize(string data) {
        int idx = 0;

        return makeTreeFromString(data, idx);   
    }
};



















int main(){
    return 0;
}