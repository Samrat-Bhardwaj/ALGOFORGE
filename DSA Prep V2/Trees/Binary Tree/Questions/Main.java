class Main {
    
    // Leetcode 104 (Depth of tree) =====================
    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }

        int depth = 0;

        depth = Math.max(depth, maxDepth(root.left));
        depth = Math.max(depth, maxDepth(root.right));

        return depth + 1;
    }

    // All traversals
    public void traverse(Node root){
        if(root == null){
            return;
        }

        preorder.add(root.data);

        traverse(root.left);

        inorder.add(root.data);

        traverse(root.right);

        postorder.add(root.data);
    }

    // Leetcode 144 (Preorder Traversal for leetcode)
    class Solution {
        public void traverse(TreeNode root, List<Integer> ans){
            if(root == null){
                return;
            }

            ans.add(root.val);

            traverse(root.left, ans);

            traverse(root.right, ans);
        }

        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> ans = new ArrayList<>();

            traverse(root,ans);

            return ans;
        }
    }


































    public static void main(String[] args){
        
    }
}