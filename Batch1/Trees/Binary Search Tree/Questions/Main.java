class Main {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // leetcode 700 ===============================================
    public TreeNode searchBST(TreeNode root, int target) { // Avg O(logN), worst O(N)
        if(root == null){
            return null;
        }

        if(root.val == target){
            return root;
        } else if(root.val < target){
            return searchBST(root.right, target);
        } else {
            return searchBST(root.left, target);
        }
    }

    // LCA of BST (Leetcode 235) ========================================
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return null;

        if(root.val < p.val && root.val < q.val){ // both nodes are in right subtree
            return lowestCommonAncestor(root.right, p, q);
        } else if(root.val > p.val && root.val > q.val){ // both nodes are in left subtree
            return lowestCommonAncestor(root.left, p, q);
        } else { // if I need to divert in 2 directions, I am the LCA
            return root;
        }
    }

    // Add node in BST (Leetcode 701) ====================================
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null){
            return new TreeNode(val);
        }

        if(root.val < val){
            root.right = insertIntoBST(root.right, val);
        } else {
            root.left = insertIntoBST(root.left, val);
        }

        return root;
    }

    // Delete node from BST (Leetcode 450) ==============================
    public int findLeftMost(TreeNode root){
        TreeNode temp = root;

        while(temp.left != null){
            temp = temp.left;
        }

        return temp.val;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return null;
        
        if(root.val > key){
            root.left = deleteNode(root.left, key);
        } else if(root.val < key){
            root.right = deleteNode(root.right, key);
        } else {
            if(root.left == null && root.right == null){ // leaf node
                return null;
            } else if(root.left == null){ // one child
                return root.right;
            } else if(root.right == null){ // one child
                return root.left;
            } else { // both child
                // find just greater
                int justGreaterVal = findLeftMost(root.right);
                root.val = justGreaterVal;

                root.right = deleteNode(root.right, justGreaterVal);
            }
        }
        
        return root;
    }

    // more cleaner, less readable
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return null;
        
        if(root.val > key){
            root.left = deleteNode(root.left, key);
        } else if(root.val < key){
            root.right = deleteNode(root.right, key);
        } else {
            if(root.left == null || root.right == null){ // zero or one child
                return root.left == null ? root.right : root.left;
            } else {
                // find just greater
                int justGreaterVal = findLeftMost(root.right);
                root.val = justGreaterVal;

                root.right = deleteNode(root.right, justGreaterVal);
            }
        }
        
        return root;
    }
}