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

    // Iterative traversals
    class Pair {
        TreeNode node;
        int state;

        public Pair(TreeNode node, int state){
            this.node = node;
            this.state = state;
        }
    }

    public List<Integer> getTraversals(TreeNode root){
        Stack<Pair> st = new Stack<>();
        List<Integer> preorder = new ArrayList<>();
        List<Integer> inorder = new ArrayList<>();
        List<Integer> postorder = new ArrayList<>();

        if(root != null){
            st.push(new Pair(root,0));
        }

        while(st.size() > 0){
            Pair top = st.peek();

            if(top.state == 0){ // we are seeing this node for the first time, add left child
                preorder.add(top.node.val);

                top.state++;

                if(top.node.left != null){ 
                    st.push(new Pair(top.node.left, 0));
                }
            } else if(top.state == 1){ // left child visited, visit right child now
                inorder.add(top.node.val);
                top.state++;

                if(top.node.right != null){
                    st.push(new Pair(top.node.right, 0));
                }
            } else {
                postorder.add(top.node.val);

                st.pop(); // both left child and right child visited, remove
            }
        }

        return inorder;
    }

    // Get K level down ============================

    public static TreeNode findTargetNode(TreeNode root, int tar){
        if(root == null){
            return null;
        }

        if(root.val == tar){
            return root;
        }

        TreeNode leftAns = findTargetNode(root.left, tar);
        if(leftAns != null) return leftAns;

        TreeNode rightAns = findTargetNode(root.right, tar);
        if(rightAns != null) return rightAns;
        
        return null;
    }

    public static void getKLevelDown(TreeNode node, int K, ArrayList<TreeNode> ans){
        if(node == null){
            return;
        }

        if(K==0){
            ans.add(node);
            return;
        }

        getKLevelDown(node.left, K-1, ans);
        getKLevelDown(node.right, K-1, ans);
    }

    public static ArrayList<TreeNode> getKLevelDown(TreeNode root, int tar, int K){
        TreeNode targetNode = findTargetNode(root, tar);

        ArrayList<TreeNode> ans = new ArrayList<>();
        getKLevelDown(targetNode,K,ans);
        return ans;
    }

    // Get K distance away (Leetcode 863) =======================================
    public ArrayList<TreeNode> getNodeToRootPath(TreeNode root, TreeNode target){
        if(root == null){
            return new ArrayList<>();
        }

        if(root.equals(target)){
            ArrayList<TreeNode> bans = new ArrayList<>();
            bans.add(root);
            return bans;
        }

        ArrayList<TreeNode> leftAns = getNodeToRootPath(root.left, target);
        if(leftAns.size() > 0){
            leftAns.add(root);
            return leftAns;
        }

        ArrayList<TreeNode> rightAns = getNodeToRootPath(root.right, target);
        if(rightAns.size() > 0){
            rightAns.add(root);
            return rightAns;
        }

        return new ArrayList<>();
    }

    public void getKLevelDown(TreeNode node, TreeNode blocker, int K, List<Integer> ans){
        if(node == null || node.equals(blocker) || K < 0){
            return;
        }

        if(K == 0){
            ans.add(node.val);
            return;
        }

        getKLevelDown(node.left, blocker, K-1, ans);
        getKLevelDown(node.right, blocker, K-1, ans);
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        ArrayList<TreeNode> nodeToRootPath = getNodeToRootPath(root, target);

        List<Integer> ans = new ArrayList<>();

        TreeNode blocker = null;
        for(int i=0; i<nodeToRootPath.size(); i++){
            TreeNode node = nodeToRootPath.get(i);

            getKLevelDown(node,blocker,k-i,ans);

            blocker = node;
        }

        return ans;
    }

    // LCA Of binary tree (Leetcode 236) ============================================
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        ArrayList<TreeNode> ntr1 = getNodeToRootPath(root, p);
        ArrayList<TreeNode> ntr2 = getNodeToRootPath(root, q);

        int i = ntr1.size() - 1;
        int j = ntr2.size() - 1;

        while(i>=0 && j>=0 && ntr1.get(i).equals(ntr2.get(j))){
            i--;
            j--;
        }

        return ntr2.get(j+1);
    }

    // LCA Of binary tree without extra space (Leetcode 236) ============================================
    
    // return whether p or q was found 
    public boolean LCA_better(TreeNode root, TreeNode p, TreeNode q, TreeNode[] LCA){
        if(root == null){
            return false;
        }

        boolean self = false;
        if(root.equals(p) || root.equals(q)){
            self = true;
        }

        boolean left = LCA_better(root.left, p, q, LCA);
        boolean right = LCA_better(root.right, p, q, LCA);

        if((left && right) || (self && left) || (self && right)){
            LCA[0] = root;
        } 

        return self || left || right;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode[] LCA = new TreeNode[1];

        LCA_better(root, p, q, LCA);

        return LCA[0];
    }

    // Remove leaf Nodes with given value (Leetcode 1325) =======================
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if(root == null){
            return null;
        }   

        root.left = removeLeafNodes(root.left, target);
        root.right = removeLeafNodes(root.right, target);

        if(root.left == null && root.right == null && root.val == target){
            return null; // this null will be attached to parent
        }

        return root;
    }

    //======================================== Binary Tree Construction Questions ====================================

    // Construct tree from preorder and inorder (Leetcode 105)
    public TreeNode buildTree(int[] preorder, int prsi, int prei, int[] inorder, int insi, int inei){
        if(prsi > prei) return null;
        if(prsi == prei) return new TreeNode(preorder[prsi]); // not compulsory to write

        int rootEle = preorder[prsi]; // starting index of preorder will be root element

        int rootIdx = insi;
        while(inorder[rootIdx] != rootEle){
            rootIdx++;
        }

        int countOfLeftElements = rootIdx - insi;

        TreeNode root = new TreeNode(rootEle);

        root.left = buildTree(preorder, prsi+1, prsi+countOfLeftElements, inorder, insi, rootIdx-1);
        root.right = buildTree(preorder, prsi+countOfLeftElements+1, prei, inorder, rootIdx+1, inei);

        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;

        return buildTree(preorder,0,n-1,inorder,0,n-1);
    }


































    public static void main(String[] args){
        
    }
}