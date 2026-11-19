class Main {
    // find minimum in BST (https://www.geeksforgeeks.org/problems/minimum-element-in-bst/1)
    public int minValue(Node root) {
        if(root == null){
            return -1;
        }

        Node temp = root;

        while(temp.left != null){
            temp = temp.left;
        }

        return temp.data;
    }

    // Leetcode 700. Search in BST
    public TreeNode searchBST(TreeNode root, int target) {
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

    // Search in BST iteratively
    public TreeNode searchBST(TreeNode root, int target) {
        TreeNode temp = root;

        while(temp != null){
            if(temp.val == target){
                return temp;
            } else if(temp.val < target){
                temp = temp.right;
            } else {
                temp = temp.left;
            }
        }

        return temp;
    }

    // LCA of BST (Leetcode 235)
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode temp = root;

        while(temp != null){
            if(temp.val < p.val && temp.val < q.val){ // both the values are greater than root
                temp = temp.right;
            } else if(temp.val > p.val && temp.val > q.val){ // both the values are smaller than root
                temp = temp.left;
            } else {
                return temp;
            }
        }

        return temp;
    }





































    public static void main(String[] args){

    }
}