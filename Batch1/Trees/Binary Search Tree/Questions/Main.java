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

    // Find pair sum (Leetcode 653) ==================================
    public boolean findEleInBST(TreeNode root, int ele, TreeNode blocker){
        if(root == null) return false;

        if(root != blocker && root.val == ele) return true;

        if(root.val < ele){
            return findEleInBST(root.right, ele, blocker);
        } else {
            return findEleInBST(root.left, ele, blocker);
        }
    }

    public boolean findPairSum(TreeNode curr, TreeNode root, int k){
        if(curr == null){
            return false;
        }

        int secondEle = k - curr.val;

        if(findEleInBST(root,secondEle,curr)){ // logN
            return true;
        }

        return findPairSum(curr.left, root, k) || findPairSum(curr.right, root, k);
    }

    public boolean findTarget(TreeNode root, int k) {
        return findPairSum(root,root,k); // NlogN
    }

    // Build BST from preorder(Leetcode 1008) =============================
    public TreeNode buildBSTFromPreorder(int[] preorder, int[] idx, int lowerBound, int upperBound){
        if(idx[0] >= preorder.length || preorder[idx[0]] <= lowerBound || preorder[idx[0]] >= upperBound){
            return null;
        }

        TreeNode root = new TreeNode(preorder[idx[0]]);
        idx[0]++;

        root.left = buildBSTFromPreorder(preorder,idx,lowerBound,root.val);
        root.right = buildBSTFromPreorder(preorder,idx,root.val,upperBound);

        return root;
    }

    public TreeNode bstFromPreorder(int[] preorder) {
        int[] idx = new int[1];
        return buildBSTFromPreorder(preorder, idx, Integer.MIN_VALUE, Integer.MAX_VALUE); 
    }

    // Without upperBound, we dont need lowerbound coz we are handling left first and smaller values are already attached in left subtree
    public TreeNode buildBSTFromPreorder_better(int[] preorder, int[] idx, int upperBound){
        if(idx[0] >= preorder.length || preorder[idx[0]] >= upperBound){
            return null;
        }

        TreeNode root = new TreeNode(preorder[idx[0]]);
        idx[0]++;

        root.left = buildBSTFromPreorder_better(preorder,idx,root.val);
        root.right = buildBSTFromPreorder_better(preorder,idx,upperBound);

        return root;
    }

    public TreeNode bstFromPreorder(int[] preorder) {
        int[] idx = new int[1];
        return buildBSTFromPreorder_better(preorder, idx, Integer.MAX_VALUE); 
    }

    // Construct BST from postorder (https://www.geeksforgeeks.org/problems/construct-bst-from-post-order/1)
    public static Node buildBSTFromPostorder(int[] post, int[] idx, int lowerBound, int upperBound){
        if(idx[0] < 0 || post[idx[0]] <= lowerBound || post[idx[0]] >= upperBound){
            return null;
        }

        Node root = new Node(post[idx[0]]);
        idx[0]--;

        root.right = buildBSTFromPostorder(post, idx, root.data, upperBound);
        root.left = buildBSTFromPostorder(post, idx, lowerBound, root.data);
        
        return root;
    }

    public static Node constructTree(int post[], int n) {
        int[] idx = new int[1];
        idx[0] = n-1;

        return buildBSTFromPostorder(post, idx, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    // We don't need upperbound because we are processing elements for right node (larger elements)
    public static Node buildBSTFromPostorder_better(int[] post, int[] idx, int lowerBound){
        if(idx[0] < 0 || post[idx[0]] <= lowerBound){
            return null;
        }

        Node root = new Node(post[idx[0]]);
        idx[0]--;

        root.right = buildBSTFromPostorder_better(post, idx, root.data);
        root.left = buildBSTFromPostorder_better(post, idx, lowerBound);
        
        return root;
    }

    public static Node constructTree(int post[], int n) {
        int[] idx = new int[1];
        idx[0] = n-1;

        return buildBSTFromPostorder_better(post, idx, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    // Validate BST
    class BSTPair {
        long max;
        long min;
        boolean isBST;

        public BSTPair(){}

        public BSTPair(int max, int min, boolean isBST){
            this.max = max;
            this.min = min;
            this.isBST = isBST;
        }
    }

    public BSTPair isTreeBST(TreeNode root){
        if(root == null){
            return new BSTPair(Long.MIN_VALUE, Long.MAX_VALUE, true);
        }

        if(root.left == null && root.right == null){
            return new BSTPair(root.val, root.val, true);
        }

        BSTPair left = isTreeBST(root.left);
        BSTPair right = isTreeBST(root.right);

        if(left.isBST == false || right.isBST == false || left.max >= root.val || right.min <= root.val){
            return new BSTPair(1,2,false); // any garbage value with false
        }

        BSTPair ansPair = new BSTPair();
        ansPair.min = Math.min(left.min, root.val); // handling inf cases
        ansPair.max = Math.max(right.max, root.val); // handling -inf cases

        ansPair.isBST = true;

        return ansPair;
    }

    public boolean isValidBST(TreeNode root) {
        BSTPair res = isTreeBST(root);
        return res.isBST;
    }

    // leetcode 1373 =======================================
    class BSTPair {
        int max;
        int min;
        boolean isBST;
        int sum;
        int maxSum;

        public BSTPair(){}

        public BSTPair(int max, int min, boolean isBST, int sum, int maxSum){
            this.max = max;
            this.min = min;
            this.isBST = isBST;
            this.sum = sum;
            this.maxSum = maxSum;
        }
    }

    public BSTPair validateBST(TreeNode root){
        if(root == null){
            return new BSTPair(Integer.MIN_VALUE, Integer.MAX_VALUE, true, 0, 0);
        }

        if(root.left == null && root.right == null){
            return new BSTPair(root.val, root.val, true, root.val, root.val);
        }

        BSTPair left = validateBST(root.left);
        BSTPair right = validateBST(root.right);

        int currentMaxSum = Math.max(left.maxSum, right.maxSum);
        
        if(!left.isBST || !right.isBST || left.max >= root.val || right.min <= root.val){
            return new BSTPair(1,2,false,0,currentMaxSum); // max min garbage, isBST = false, sum = 0 but maxSum should be maximum
        }
        
        BSTPair ansPair = new BSTPair();
        ansPair.max = Math.max(root.val, right.max);
        ansPair.min = Math.min(root.val, left.min);
        ansPair.isBST = true;
        ansPair.sum = left.sum + right.sum + root.val;
        ansPair.maxSum = Math.max(currentMaxSum, ansPair.sum);

        return ansPair;
    }

    public int maxSumBST(TreeNode root) {
        int maxSum = validateBST(root).maxSum;

        return Math.max(maxSum, 0);
    }

    // Leetcode 98 better  ==============================
    TreeNode prev ;
    public boolean traverse(TreeNode root){
        if(root == null) return true;

        if(traverse(root.left) == false) return false;

        if(prev == null){
            prev = root;
        } else if(prev.val >= root.val){
            return false;
        }

        prev = root;

        if(traverse(root.right) == false) return false;

        return true;
    }

    public boolean isValidBST(TreeNode root) {
        prev = null;
        return traverse(root);
    }

    // leetcode 99, Recover BST ====================================
    class Solution {
        TreeNode a;
        TreeNode b;
        TreeNode prev;
        public boolean inorderTraverse(TreeNode root){
            if(root == null){
                return false;
            }

            if(inorderTraverse(root.left) == true){
                return true;
            }

            if(prev != null && prev.val >= root.val){
                b = root;
                if(a == null){
                    a = prev;
                } else {
                    return true;
                }
            }

            prev = root;
            if(inorderTraverse(root.right) == true){
                return true;
            }

            return false;
        }

        public void recoverTree(TreeNode root) {
            a = null;
            b = null;
            prev = null;

            inorderTraverse(root);

            int temp = a.val;
            a.val = b.val;
            b.val = temp;
        }
    }

    // leetcode 173, BST iterator ======================================================
    class BSTIterator {
        Stack<TreeNode> st;
        public void addAllLeft(TreeNode curr){

            while(curr != null){
                st.add(curr);
                curr = curr.left;
            }
        }

        public BSTIterator(TreeNode root) {
            st = new Stack<>();

            addAllLeft(root);
        }
        
        public int next() {
            TreeNode top = st.pop();

            addAllLeft(top.right); // going to add just greater
            
            return top.val;
        }
        
        public boolean hasNext() {
            return st.size() > 0;
        }
    }
}