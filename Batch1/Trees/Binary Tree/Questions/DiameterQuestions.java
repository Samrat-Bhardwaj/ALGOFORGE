class DiameterQuestions {
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

    // Diameter of a binary Tree (Leetcode 543)===================
    public int heightOfBinaryTree(TreeNode root){
        if(root == null){
            return -1;
        }

        int leftHeight = heightOfBinaryTree(root.left);
        int rightHeight = heightOfBinaryTree(root.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    // O(N^2)
    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null) return -1;

        int leftHeight = heightOfBinaryTree(root.left);
        int rightHeight = heightOfBinaryTree(root.right);

        int currentNodeDiameter = leftHeight + rightHeight + 2;

        int leftMaxDiameter = diameterOfBinaryTree(root.left);
        int righttMaxDiameter = diameterOfBinaryTree(root.right);

        return Math.max(Math.max(leftMaxDiameter, righttMaxDiameter), currentNodeDiameter);
    }

    // Method 2 , {height, diameter}
    public int[] heightDiameterOfBinaryTree(TreeNode root){
        if(root == null){
            return new int[]{-1,-1};
        }

        int[] leftHeightDia = heightDiameterOfBinaryTree(root.left);
        int[] rightHeightDia = heightDiameterOfBinaryTree(root.right);

        int leftHeight = leftHeightDia[0];
        int rightHeight = rightHeightDia[0];

        int leftMaxDiameter = leftHeightDia[1];
        int rightMaxDiameter = rightHeightDia[1];

        int currentNodeHeight = Math.max(leftHeight, rightHeight) + 1;
        int currentNodeDiameter = leftHeight + rightHeight + 2;

        int maxDiameter = Math.max(Math.max(leftMaxDiameter, rightMaxDiameter), currentNodeDiameter);

        return new int[]{currentNodeHeight, maxDiameter};
    }

    public int diameterOfBinaryTree(TreeNode root) {
        return heightDiameterOfBinaryTree(root)[1];
    }

    // Method 3 ==================================================
    // int maxDia;
    public int heightOfBinaryTree(TreeNode root, int[] maxDia){
        if(root == null){
            return -1;
        }

        int leftHeight = heightOfBinaryTree(root.left, maxDia);
        int rightHeight = heightOfBinaryTree(root.right, maxDia);

        int currentNodeDiameter = leftHeight + rightHeight + 2;
        maxDia[0] = Math.max(maxDia[0], currentNodeDiameter);
        // maxDia = Math.max(maxDia, currentNodeDiameter);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public int diameterOfBinaryTree(TreeNode root) {
        // maxDia = -1;
        int[] maxDia = new int[0];
        heightOfBinaryTree(root, maxDia);

        return maxDia[0];
    }

    // Max path sum from leaf to leaf (https://www.geeksforgeeks.org/problems/maximum-path-sum/1) ===========================
    class Solution {
        int maxLeafToNode(Node root, int[] maxLeafToLeaf){
            if(root == null){
                return -(int)(1e8);
            }

            if(root.left == null && root.right == null){
                return root.data;
            }

            int maxLeftLTN = maxLeafToNode(root.left, maxLeafToLeaf);
            int maxRightLTN = maxLeafToNode(root.right, maxLeafToLeaf);

            if(root.left != null && root.right != null){
                maxLeafToLeaf[0] = Math.max(maxLeafToLeaf[0], maxLeftLTN + maxRightLTN + root.data);
            }

            return Math.max(maxLeftLTN, maxRightLTN) + root.data;
        }


        int maxPathSum(Node root) {
            int[] maxLeafToLeaf = new int[]{-(int)(1e8)};

            int leafToRoot = maxLeafToNode(root, maxLeafToLeaf);
            if(maxLeafToLeaf[0] == -(int)(1e8)){ // to handle one TC of Tree with only right/left nodes
                return leafToRoot;
            }

            return maxLeafToLeaf[0];
        }
    }























    public static void main(String[] args){

    }
}