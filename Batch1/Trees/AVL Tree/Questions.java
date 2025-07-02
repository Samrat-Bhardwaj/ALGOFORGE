class Questions {
    // leetcode 110 =======================================
    class Pair {
        int height;
        int balancingFactor; // redundant
        boolean isBalanced;

        public Pair(){}

        public Pair(int height, int balancingFactor,boolean isBalanced){
            this.height = height;
            this.balancingFactor = balancingFactor;
            this.isBalanced = isBalanced;
        }
    }

    public Pair calculateHeightAndBF(TreeNode root){
        if(root == null){
            return new Pair(-1,0,true);
        }

        Pair lpair = calculateHeightAndBF(root.left);
        Pair rpair = calculateHeightAndBF(root.right);

        Pair myPair = new Pair();

        myPair.height = Math.max(lpair.height, rpair.height) + 1;
        myPair.balancingFactor = Math.abs(lpair.height - rpair.height);

        myPair.isBalanced = myPair.balancingFactor < 2 && lpair.isBalanced && rpair.isBalanced;

        return myPair;
    }

    public boolean isBalanced(TreeNode root) {
       return calculateHeightAndBF(root).isBalanced;
    }


    // Leetcode 1382 ==================================================
    class Solution {
        int[] height;
        public void updateHeightArray(TreeNode root){
            int leftHeight = -1;
            int rightHeight = -1;

            if(root.left != null){
                leftHeight = height[root.left.val];
            }

            if(root.right != null){
                rightHeight = height[root.right.val];
            }

            height[root.val] = Math.max(leftHeight, rightHeight) + 1;
        }

        public TreeNode rightRotate(TreeNode A){
            TreeNode B = A.left;
            TreeNode BKaRight = B.right;

            B.right = A;
            A.left = BKaRight;

            B.right = balanceBST_(A); // making sure A is also balanced

            return balanceBST_(B); // updated root
        }

        public TreeNode leftRotate(TreeNode A){
            TreeNode B = A.right;
            TreeNode BKaLeft = B.left;

            B.left = A;
            A.right = BKaLeft;

            B.left = balanceBST_(A); // making sure A is also balanced

            return balanceBST_(B); // updated root
        }

        // rotate if not balanced
        public int findBF(TreeNode root){
            int lh = -1;
            int rh = -1;

            if(root.left != null){
                lh = height[root.left.val];
            }

            if(root.right != null){
                rh = height[root.right.val];
            }

            return lh - rh;
        }

        public TreeNode balanceBST_(TreeNode root){
            updateHeightArray(root);
            // rotate if skewed
            if(findBF(root) >= 2){ // left-left, left-right
                if(findBF(root.left) >= 1){ // left-left
                    return rightRotate(root); // right rotate
                } else { // left-right
                    root.left = leftRotate(root.left); // left rotate (root.left)
                    return rightRotate(root); // // right rotate root
                }
            } else if(findBF(root) <= -2){ // right-right, right-left
                if(findBF(root.right) <= -1){ // right-right
                    return leftRotate(root); // left rotate
                } else { // right-left
                    root.right = rightRotate(root.right); // // right rotate (root.right)
                    return leftRotate(root); // // left rotate root
                }
            }

            return root;
        }

        public TreeNode constructTree(TreeNode root){
            if(root == null) return null;

            root.left = constructTree(root.left);
            root.right = constructTree(root.right);

            return balanceBST_(root);
        }

        public TreeNode balanceBST(TreeNode root) {
            height = new int[100001]; // hashmap of node.data vs height
            Arrays.fill(height,0);

            return constructTree(root);
        }
    }
}