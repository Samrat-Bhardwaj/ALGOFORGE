class TreeNode {
    int data;

    TreeNode left;
    TreeNode right;

    public TreeNode(int data){
        this.data = data;
    }
}

class Main {
    public static TreeNode buildBST(int[] arr, int si, int ei){
        if(si > ei){
            return null;
        }

        int mid = (si+ei)/2;

        TreeNode root = new TreeNode(arr[mid]);

        root.left = buildBST(arr, si, mid-1);
        root.right = buildBST(arr, mid+1, ei);

        return root;
    }

    // Minimum of BST (https://www.geeksforgeeks.org/problems/minimum-element-in-bst/1)
    public int minValue(Node root) {
        if(root == null) return Integer.MAX_VALUE;

        if(root.left == null) return root.data;

        return minValue(root.left);
    }

    // Minimum of BST Iterative (https://www.geeksforgeeks.org/problems/minimum-element-in-bst/1)
    public int minValue(Node root) {
        if(root == null) return Integer.MAX_VALUE;
        
        while(root.left != null){
            root = root.left;
        }

        return root.data;
    }

    // Maximum of BST ()
    public int maxValue(Node root) {
        while(root.right != null){
            root = root.right;
        }

        return root.data;
    }

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

    // https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/





























    public static void main(String[] args){
        int[] arr = {1,2,3,5,8,10,12,13,15,18};

        TreeNode root = buildBST(arr,0,arr.length-1);
    }
}