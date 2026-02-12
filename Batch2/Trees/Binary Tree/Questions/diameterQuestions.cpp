    // Maximum path sum of node to node (Leetcode 124) =================================
    class Solution {
        public:
            int findMaxNodeToCurrentPathSum(TreeNode* root, int& maxNodeToNode){
                if(root == nullptr){
                    return (int)(-1e8);
                }

                int leftMaxPathSum = findMaxNodeToCurrentPathSum(root->left, maxNodeToNode);
                int rightMaxPathSum = findMaxNodeToCurrentPathSum(root->right, maxNodeToNode);

                int maxPathSumIncludingRoot = max(leftMaxPathSum + root->val, rightMaxPathSum + root->val);
                int maxPathSum = max(maxPathSumIncludingRoot, root->val);

                maxNodeToNode = max(maxNodeToNode, max(maxPathSum, leftMaxPathSum + root->val + rightMaxPathSum));

                return maxPathSum;
            }

            int maxPathSum(TreeNode* root) {
                int maxNodeToNode = -(int)(1e8);
                findMaxNodeToCurrentPathSum(root,maxNodeToNode);
                return maxNodeToNode;
            }
    };
