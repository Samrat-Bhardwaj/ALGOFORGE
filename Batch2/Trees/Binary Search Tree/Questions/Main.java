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

    // Is BST (https://www.geeksforgeeks.org/problems/check-for-bst/1) ======================================
    class BstPair {
        int max;
        int min;
        boolean isBst;

        public BstPair(){}

        public BstPair(int max, int min, boolean isBst){
            this.max = max;
            this.min = min;
            this.isBst = isBst;
        }
    }
    
    public BstPair checkIfBst(Node root){
        if(root == null){
            return new BstPair(Integer.MIN_VALUE, Integer.MAX_VALUE, true); // -inf, inf, true
        }

        BstPair leftPair = checkIfBst(root.left);
        BstPair rightPair = checkIfBst(root.right);

        BstPair ansPair = new BstPair();

        ansPair.isBst = leftPair.isBst && rightPair.isBst && leftPair.max < root.data && root.data < rightPair.min;

        ansPair.min = Math.min(leftPair.min, root.data); // leftPair.min should always be minimum except when root.left = null
        ansPair.max = Math.max(rightPair.max, root.data); // rightPair.max should always be max except when root.right = null
        
        return ansPair;
    }

    public boolean isBST(Node root) {
        return checkIfBst(root).isBst;
    }

    // Leetcode 1373 (Maximum sum of a BST subtree) ==========================================
    class Solution {

        class BstPair {
            int max;
            int min;
            boolean isBst;
            int sum; // sum of current tree
            int maxSum; // maximum sum of any BST subtree

            public BstPair(){}

            public BstPair(int max, int min, boolean isBst, int sum, int maxSum){
                this.max = max;
                this.min = min;
                this.isBst = isBst;
                this.sum = sum;
                this.maxSum = maxSum;
            }
        }

        public BstPair findMaxBSTSum(TreeNode root){
            if(root == null){
                return new BstPair(Integer.MIN_VALUE,Integer.MAX_VALUE,true,0,0);
            }

            BstPair leftPair = findMaxBSTSum(root.left);
            BstPair rightPair = findMaxBSTSum(root.right);

        
            int maxSumSoFar = Math.max(leftPair.maxSum, rightPair.maxSum);

            // checking if tree is bst or not
            if(!leftPair.isBst || !rightPair.isBst || leftPair.max >= root.val || rightPair.min <= root.val){
                return new BstPair(12,13,false,45,maxSumSoFar); // only concerned with maxSum
            }
            
            int minOfTree = Math.min(leftPair.min, root.val);
            int maxOfTree = Math.max(rightPair.max, root.val);
            int sumOfTree = leftPair.sum + rightPair.sum + root.val;

            int maxSum = Math.max(maxSumSoFar, sumOfTree);

            return new BstPair(maxOfTree,minOfTree,true,sumOfTree,maxSum);
        }


        public int maxSumBST(TreeNode root) {
            return findMaxBSTSum(root).maxSum;
        }
    }

    // Leetcode 98 (Validate BST) ========================================
    class Solution {
        // inorder travel with a previous node to check everything is increasing or not
        TreeNode prev;
        public boolean isIncreasing(TreeNode root){
            if(root == null) return true;

            if(isIncreasing(root.left) == false) return false; 

            if(prev == null){
                prev = root;
            } else if(prev.val >= root.val){
                return false;
            }

            prev = root;

            if(isIncreasing(root.right) == false) return false; 

            return true;
        }

        public boolean isValidBST(TreeNode root) {
            prev = null;

            return isIncreasing(root);
        }
    }

    // Leetcode 99 (Validate BST) =====================================
    class Solution {
        public void getInorder(TreeNode root, ArrayList<TreeNode> inorder){
            if(root == null){
                return;
            }
            
            getInorder(root.left, inorder);

            inorder.add(root);

            getInorder(root.right, inorder);
        }

        public void recoverTree(TreeNode root) {
            ArrayList<TreeNode> inorder = new ArrayList<>();
            getInorder(root, inorder);

            TreeNode a = null, b = null;
            TreeNode prev = inorder.get(0);

            for(int i=1; i<inorder.size(); i++){
                TreeNode curr = inorder.get(i);

                if(prev.val >= curr.val){
                    b = curr;
                    if(a == null){
                        a = prev;
                    } else {
                        break;
                    }
                }

                prev = curr;
            }

            int temp = a.val;
            a.val = b.val;
            b.val = temp;
        }
    }

    // Leetcode 99 better O(1) extra space ====================================
    class Solution {
        TreeNode prev;
        TreeNode a;
        TreeNode b;

        public boolean inorderTraverse(TreeNode root){
            if(root == null) return false;

            if(inorderTraverse(root.left)) return true;

            if(prev!= null && prev.val >= root.val){
                b = root;
                if(a == null){
                    a = prev;
                } else {
                    return true; // found a,b -> no need to traverse more
                }
            }

            prev = root;

            if(inorderTraverse(root.right)) return true;

            return false;
        }

        public void recoverTree(TreeNode root) {
            prev = null;
            a = null;
            b = null;

            inorderTraverse(root);

            int temp = a.val;
            a.val = b.val;
            b.val = temp;
        }
    }

    // BST Iterator (Leetcode 173) =========================================
    class BSTIterator {
        Stack<TreeNode> st;

        public void addTillLeftMost(TreeNode node){
            while(node != null){
                st.push(node);
                node = node.left;
            }
        }

        public BSTIterator(TreeNode root) {
            st = new Stack<>();
            addTillLeftMost(root);
        }
        
        public int next() {
            TreeNode topNode = st.pop();

            if(topNode.right != null){ // get nextGreater on top 
                addTillLeftMost(topNode.right);
            }

            return topNode.val;
        }
        
        public boolean hasNext() {
            return st.size() > 0;
        }
    }

    // Leetcode 230, Kth Smallest element =================================================================
    // Method 1 = Recursive inorder travel  (O(rec space))
    // Method 2 = Morris traversal but only till kth inorder (Input Tree ruin, O(1) space)
    // Method 3 = using stack (O(h) space)
    public void addTillLeftMost(Stack<TreeNode> st, TreeNode node){
        while(node != null){
            st.push(node);
            node = node.left;
        }
    }

    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> st = new Stack<>();

        addTillLeftMost(st,root);
        // removing k-1 elements so that kth element is on top after this loop
        while(k-- > 1){
            TreeNode topNode = st.pop();

            if(topNode.right != null){ // get nextGreater on top 
                addTillLeftMost(st,topNode.right);
            }
        }

        return st.peek().val;
    }

    // Find predecessor and successor -> O(K) avg time -> (https://www.geeksforgeeks.org/problems/predecessor-and-successor/1)
    class Solution {
        Node prev;
        Node pred;
        Node succ;

        public boolean inorderTraverse(Node root, int key){
            if(root == null) return false;

            if(inorderTraverse(root.left, key)) return true;

            if(root.data < key){
                pred = root;
            } else if(succ == null && root.data > key){
                succ = root;
                return true;
            }

            prev = root;

            if(inorderTraverse(root.right, key)) return true;

            return false;
        }

        public ArrayList<Node> findPreSuc(Node root, int key) {
            prev = null;
            pred = null;
            succ = null;

            inorderTraverse(root, key);

            ArrayList<Node> ans = new ArrayList<>();
            ans.add(pred);
            ans.add(succ);

            return ans;
        }
    }

    // Find pred and succ O(logN - Average) ========================================
    public Node findRightMost(Node temp){
        while(temp.right != null){
            temp = temp.right;
        }

        return temp;
    }

    public Node findLeftMost(Node temp){
        while(temp.left != null){
            temp = temp.left;
        }

        return temp;
    }

    public ArrayList<Node> findPreSuc(Node root, int key) {
        Node pred = null;
        Node succ = null;

        Node curr = root;

        while(curr != null){
            if(curr.data < key){
                pred = curr; // parent is just smaller if there is no leftSubtree of curr node when curr.data == key
                curr = curr.right;
            } else if(curr.data > key){
                succ = curr; // parent is just greater if there is no rightSubtree of curr node when curr.data == key
                curr = curr.left;
            } else {
                // find just smaller -> rightMost of left subtree
                if(curr.left != null){
                    pred = findRightMost(curr.left);
                }

                // find just greater -> leftMost of right subtree
                if(curr.right != null){
                    succ = findLeftMost(curr.right);
                }
                break;
            }
        }

        ArrayList<Node> ans = new ArrayList<>();
        ans.add(pred);
        ans.add(succ);

        return ans;
    }





























    public static void main(String[] args){
        int[] arr = {1,2,3,5,8,10,12,13,15,18};

        TreeNode root = buildBST(arr,0,arr.length-1);
    }
}