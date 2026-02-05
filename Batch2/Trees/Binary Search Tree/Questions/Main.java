class TreeNode {
    int data;

    TreeNode left;
    TreeNode right;

    public TreeNode(int data){
        this.data = data;
    }
}

class Main {
    // Search in BST (Leetcode 700) =============================================
    public TreeNode searchBST(TreeNode root, int target) { // worst case scenario O(N)
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

    // Search in BST (Leetcode 700) =============================================
    public TreeNode searchBST(TreeNode root, int target) { // worst case scenario O(N)
        while(root != null){
            if(root.val == target){
                return root;
            } else if(root.val < target){
                root = root.right;
            } else {
                root = root.left;
            }
        }

        return null;
    }

    // LCA of BST (Homework - leetcode 235) https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/

    // Insert into BST (Leetcode 701) https://leetcode.com/problems/insert-into-a-binary-search-tree/ ===========
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null){
            return new TreeNode(val);
        }

        if(root.val < val){
            root.right = insertIntoBST(root.right,val);
        } else {
            root.left = insertIntoBST(root.left, val);
        }

        return root;
    }
    

    // remove node in BST (Leetcode 450) ==================================================
    
    public TreeNode findLeftMost(TreeNode root){
        while(root.left != null){
            root = root.left;
        }

        return root;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null){
            return null;
        }

        if(root.val < key){
            root.right = deleteNode(root.right, key);
        } else if(root.val > key){
            root.left = deleteNode(root.left, key);
        } else {
            // root is a leaf node
            if(root.left == null && root.right == null){
                return null;
            }

            // only one child
            if(root.left != null && root.right == null){
                return root.left;
            } else if(root.left == null && root.right != null){
                return root.right;
            }

            // both children (replace with justGreater val and then delete just greater)
            TreeNode justGreater = findLeftMost(root.right);

            root.val = justGreater.val;
            root.right = deleteNode(root.right, justGreater.val);
        }

        return root;
    }
    

    // Trim a BST (leetcode 669) ==============================================
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if(root == null){
            return root;
        }

        if(root.val < low){
            return trimBST(root.right, low, high); // no need to look at left subtree
        } else if(root.val > high){
            return trimBST(root.left, low, high); // no need to look at right subtree
        } else {
            root.left = trimBST(root.left, low, high);
            root.right = trimBST(root.right, low, high);
            
            return root;
        }
    }

    // Convert BST to greater Tree (Leetcode 538) https://leetcode.com/problems/convert-bst-to-greater-tree/description/
    public void convertBST(TreeNode root, int[] sum){ // reverse inorder (decreasing)
        if(root == null) return;

        convertBST(root.right, sum);

        sum[0] += root.val;
        root.val = sum[0];

        convertBST(root.left, sum);
    }

    public TreeNode convertBST(TreeNode root) {
        int[] sum = new int[1];

        convertBST(root, sum);

        return root;
    }

    // Make BST from Preorder (Leetcode 1008) ================================
    public TreeNode makeBSTFromPreorder(int[] preorder, int[] idx, int lowerBound, int upperBound){
        if(idx[0] >= preorder.length || preorder[idx[0]] <= lowerBound || preorder[idx[0]] >= upperBound){
            return null;
        }

        TreeNode root = new TreeNode(preorder[idx[0]]);
        idx[0]++;

        root.left = makeBSTFromPreorder(preorder, idx, lowerBound, root.val);
        root.right = makeBSTFromPreorder(preorder, idx, root.val, upperBound);

        return root;
    }

    public TreeNode bstFromPreorder(int[] preorder) {
        int[] idx = new int[1];
        return makeBSTFromPreorder(preorder, idx, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    // Make BST from Preorder (Leetcode 1008) ================================
    // we can remove lowerBound as we are going in the left direction first 
    public TreeNode makeBSTFromPreorder(int[] preorder, int[] idx, int upperBound){
        if(idx[0] >= preorder.length || preorder[idx[0]] >= upperBound){
            return null;
        }

        TreeNode root = new TreeNode(preorder[idx[0]]);
        idx[0]++;

        root.left = makeBSTFromPreorder(preorder, idx, root.val);
        root.right = makeBSTFromPreorder(preorder, idx, upperBound);

        return root;
    }

    public TreeNode bstFromPreorder(int[] preorder) {
        int[] idx = new int[1];
        return makeBSTFromPreorder(preorder, idx, Integer.MAX_VALUE);
    }

    // Make BST from postorder https://www.geeksforgeeks.org/problems/construct-bst-from-post-order/1


























    public static void main(String[] args){
        int[] arr = {1,2,3,5,8,10,12,13,15,18};

        TreeNode root = buildBST(arr,0,arr.length-1);
    }
}