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

    // Construct Binary Tree from preorder and postorder (Leetcode 889)
    public TreeNode buildTree(int[] preorder, int prsi, int prei, int[] postorder, int posi, int poei){
        if(prsi > prei){
            return null;
        }

        if(prsi == prei){
            return new TreeNode(preorder[prsi]);
        }

        int rootEle = preorder[prsi];
        TreeNode root = new TreeNode(rootEle);

        int possibleLeftTreeRootEle = preorder[prsi + 1];
        int leftRootIdx = posi;

        while(postorder[leftRootIdx] != possibleLeftTreeRootEle){
            leftRootIdx++;
        }

        int leftSubtreeEle = leftRootIdx - posi + 1;

        root.left = buildTree(preorder, prsi + 1, prsi + leftSubtreeEle, postorder, posi, leftRootIdx);
        root.right = buildTree(preorder, prsi + leftSubtreeEle + 1, prei, postorder, leftRootIdx + 1, poei - 1);

        return root;
    }

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        int n = preorder.length;

        return buildTree(preorder,0,n-1,postorder,0,n-1);
    }

    // ================================================ BINARY TREE VIEWS PROBLEMS ====================================================

    // width of binary Tree
    public void traverse(TreeNode root, int verticalLevel, int[] minMax){
        if(root == null){
            return;
        }

        minMax[0] = Math.min(minMax[0], verticalLevel);
        minMax[1] = Math.max(minMax[1], verticalLevel);

        traverse(root.left, verticalLevel - 1, minMax);
        traverse(root.right, verticalLevel + 1, minMax);
    }

    public int widthOfBinaryTree(TreeNode root){
        int[] minMax = new int[2];

        traverse(root,0,minMax);

        int width = minMax[1] - minMax[0] + 1;

        return width;
    }

    // Vertical order of a tree ===============================
    class Solution {
        class Pair {
            Node node;
            int verticalLevel;

            public Pair(Node node, int verticalLevel){
                this.node = node;
                this.verticalLevel = verticalLevel;
            }
        }

        public void traverse(Node root, int verticalLevel, int[] minMax){
            if(root == null){
                return;
            }

            minMax[0] = Math.min(minMax[0], verticalLevel);
            minMax[1] = Math.max(minMax[1], verticalLevel);

            traverse(root.left, verticalLevel - 1, minMax);
            traverse(root.right, verticalLevel + 1, minMax);
        }

        public ArrayList<ArrayList<Integer>> verticalOrder(Node root) {
            int[] minMax = new int[2];

            traverse(root,0,minMax);

            int width = minMax[1] - minMax[0] + 1;

            int shift = -minMax[0]; // how left you can go

            ArrayList<ArrayList<Integer>> res = new ArrayList<>();
            for(int i=0; i<width; i++){
                res.add(new ArrayList<>());
            }

            LinkedList<Pair> que = new LinkedList<>();
            que.addLast(new Pair(root,shift));

            while(que.size() > 0){
                int size = que.size();

                while(size-- > 0){
                    Pair front = que.removeFirst();

                    Node node = front.node;
                    int currentLevel = front.verticalLevel;

                    res.get(currentLevel).add(node.data); // getting the list at that particulal vLevel, then adding data

                    if(node.left != null){
                        que.addLast(new Pair(node.left, currentLevel - 1));
                    }

                    if(node.right != null){
                        que.addLast(new Pair(node.right, currentLevel + 1));
                    }
                }
            }

            return res;
        }
    }

    // Vertical sum (https://www.geeksforgeeks.org/problems/vertical-sum/1)
    class Solution {
        class Pair {
            Node node;
            int verticalLevel;

            public Pair(Node node, int verticalLevel){
                this.node = node;
                this.verticalLevel = verticalLevel;
            }
        }

        public void traverse(Node root, int verticalLevel, int[] minMax){
            if(root == null){
                return;
            }

            minMax[0] = Math.min(minMax[0], verticalLevel);
            minMax[1] = Math.max(minMax[1], verticalLevel);

            traverse(root.left, verticalLevel - 1, minMax);
            traverse(root.right, verticalLevel + 1, minMax);
        }

        public ArrayList<Integer> verticalSum(Node root) {
            int[] minMax = new int[2];

            traverse(root,0,minMax);

            int width = minMax[1] - minMax[0] + 1;

            int shift = -minMax[0]; // how left you can go

            ArrayList<Integer> res = new ArrayList<>();
            for(int i=0; i<width; i++){
                res.add(0); // initialising every level sum with 0
            }

            LinkedList<Pair> que = new LinkedList<>();
            que.addLast(new Pair(root,shift));

            while(que.size() > 0){
                int size = que.size();

                while(size-- > 0){
                    Pair front = que.removeFirst();

                    Node node = front.node;
                    int currentLevel = front.verticalLevel;

                    int oldSum = res.get(currentLevel);

                    res.set(currentLevel, oldSum + node.data); // getting the list at that particulal vLevel, then adding data

                    if(node.left != null){
                        que.addLast(new Pair(node.left, currentLevel - 1));
                    }

                    if(node.right != null){
                        que.addLast(new Pair(node.right, currentLevel + 1));
                    }
                }
            }

            return res;
        }
    }

    // bottom view of a tree (https://www.geeksforgeeks.org/problems/bottom-view-of-binary-tree/1)
    class Solution {
        class Pair {
            Node node;
            int verticalLevel;

            public Pair(Node node, int verticalLevel){
                this.node = node;
                this.verticalLevel = verticalLevel;
            }
        }

        public void traverse(Node root, int verticalLevel, int[] minMax){
            if(root == null){
                return;
            }

            minMax[0] = Math.min(minMax[0], verticalLevel);
            minMax[1] = Math.max(minMax[1], verticalLevel);

            traverse(root.left, verticalLevel - 1, minMax);
            traverse(root.right, verticalLevel + 1, minMax);
        }

        public ArrayList<Integer> bottomView(Node root) {
            int[] minMax = new int[2];

            traverse(root,0,minMax);

            int width = minMax[1] - minMax[0] + 1;

            int shift = -minMax[0]; // how left you can go

            ArrayList<Integer> res = new ArrayList<>();
            for(int i=0; i<width; i++){
                res.add(0); // initialising every level bottom element with 0
            }

            LinkedList<Pair> que = new LinkedList<>();
            que.addLast(new Pair(root,shift));

            while(que.size() > 0){
                int size = que.size();

                while(size-- > 0){
                    Pair front = que.removeFirst();

                    Node node = front.node;
                    int currentLevel = front.verticalLevel;

                    res.set(currentLevel, node.data); 

                    if(node.left != null){
                        que.addLast(new Pair(node.left, currentLevel - 1));
                    }

                    if(node.right != null){
                        que.addLast(new Pair(node.right, currentLevel + 1));
                    }
                }
            }

            return res;
        }
    }

    // top view of a tree (https://www.geeksforgeeks.org/problems/top-view-of-binary-tree/1)
    class Solution {
        class Pair {
            Node node;
            int verticalLevel;

            public Pair(Node node, int verticalLevel){
                this.node = node;
                this.verticalLevel = verticalLevel;
            }
        }

        public void traverse(Node root, int verticalLevel, int[] minMax){
            if(root == null){
                return;
            }

            minMax[0] = Math.min(minMax[0], verticalLevel);
            minMax[1] = Math.max(minMax[1], verticalLevel);

            traverse(root.left, verticalLevel - 1, minMax);
            traverse(root.right, verticalLevel + 1, minMax);
        }

        public ArrayList<Integer> topView(Node root) {
            int[] minMax = new int[2];

            traverse(root,0,minMax);

            int width = minMax[1] - minMax[0] + 1;

            int shift = -minMax[0]; // how left you can go

            ArrayList<Integer> res = new ArrayList<>();
            for(int i=0; i<width; i++){
                res.add(null); // initialising every level top element with null
            }

            LinkedList<Pair> que = new LinkedList<>();
            que.addLast(new Pair(root,shift));

            while(que.size() > 0){
                int size = que.size();

                while(size-- > 0){
                    Pair front = que.removeFirst();

                    Node node = front.node;
                    int currentLevel = front.verticalLevel;

                    if(res.get(currentLevel) == null){ // if there is no element at this element then only add element 
                        res.set(currentLevel, node.data); 
                    }
                    
                    if(node.left != null){
                        que.addLast(new Pair(node.left, currentLevel - 1));
                    }

                    if(node.right != null){
                        que.addLast(new Pair(node.right, currentLevel + 1));
                    }
                }
            }

            return res;
        }
    }

    // diagonal traversal of a tree (https://www.geeksforgeeks.org/problems/diagonal-traversal-of-binary-tree/1)
    class Solution {
        public void traverse(Node root, int diagonalLevel, int[] minMax){
            if(root == null){
                return;
            }

            minMax[0] = Math.min(minMax[0], diagonalLevel);
            minMax[1] = Math.max(minMax[1], diagonalLevel);

            traverse(root.left, diagonalLevel - 1, minMax);
            traverse(root.right, diagonalLevel, minMax);
        }
        
        public void fillDiagonalLevels(Node root, int currentLevel, ArrayList<ArrayList<Integer>> res){
            if(root == null){
                return;
            }

            res.get(currentLevel).add(root.data);

            fillDiagonalLevels(root.left, currentLevel - 1, res);
            fillDiagonalLevels(root.right, currentLevel, res);
        }

        public ArrayList<Integer> diagonal(Node root) {
            int[] minMax = new int[2];

            traverse(root,0,minMax);

            int totalDiagonals = - minMax[0] + 1;
            int shift = -minMax[0]; // total left levels

            ArrayList<ArrayList<Integer>> res = new ArrayList<>();
            for(int i=0; i<totalDiagonals; i++){
                res.add(new ArrayList<>());
            }   

            fillDiagonalLevels(root,shift,res);

            // convert 2d to 1d
            ArrayList<Integer> convertedAns = new ArrayList<>();
            for(int i=totalDiagonals-1; i>=0; i--){
                for(int e: res.get(i)){
                    convertedAns.add(e);
                }
            }

            return convertedAns;
        }
    }


    // ================================  QUESTIONS RELATED TO DIAMETER OF TREE ====================================================
    public int getHeight(TreeNode root){
        if(root == null){
            return -1;
        }

        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null){
            return -1;
        }

        int leftMaxDia = diameterOfBinaryTree(root.left);
        int rightMaxDia = diameterOfBinaryTree(root.right);

        int myDia = getHeight(root.left) + getHeight(root.right) + 2;

        int maxDiameter = Math.max(myDia, Math.max(leftMaxDia, rightMaxDia));

        return maxDiameter;
    }

    // Method 2 
    class Solution {
        public int getHeight(TreeNode root, int[] maxDiameter){
            if(root == null){
                return -1;
            }

            int leftHeight = getHeight(root.left, maxDiameter);
            int rightHeight = getHeight(root.right, maxDiameter);

            int myDiameter = leftHeight + rightHeight + 2;
            maxDiameter[0] = Math.max(maxDiameter[0], myDiameter); // max of all diameter

            return Math.max(leftHeight, rightHeight) + 1;
        }

        public int diameterOfBinaryTree(TreeNode root) {
            int[] maxDiameter = new int[1];
            maxDiameter[0] = -1;

            getHeight(root, maxDiameter);

            return maxDiameter[0];
        }
    }

    // Method 3 ============
    class Solution {
        // {height, maxDiameter}
        public int[] getHeighDia(TreeNode root){
            if(root == null){
                return new int[]{-1,-1};
            }

            int[] leftHeightDia = getHeighDia(root.left);
            int[] rightHeightDia = getHeighDia(root.right);

            int leftHeight = leftHeightDia[0];
            int rightHeight = rightHeightDia[0];

            int leftMaxDia = leftHeightDia[1];
            int rightMaxDia = rightHeightDia[1];

            int myHeight = Math.max(leftHeight, rightHeight) + 1;

            int myDia = leftHeight + rightHeight + 2;
            int maxDiameter = Math.max(myDia, Math.max(leftMaxDia, rightMaxDia));

            return new int[]{myHeight, maxDiameter};
        }

        public int diameterOfBinaryTree(TreeNode root) {
            return getHeighDia(root)[1];
        }
    }

    // Max path sum between two leaves 
    class Solution {
        public int leafToNode(Node root, int[] maxLeafToLeaf){
                if(root == null){
                    return -(int)(1e8);
                }

                if(root.left == null && root.right == null){
                    return root.data;
                }

                int leftMaxLeafToNode = leafToNode(root.left, maxLeafToLeaf);
                int rightMaxLeafToNode = leafToNode(root.right, maxLeafToLeaf);

                int myLeafToLeaf = leftMaxLeafToNode + root.data + rightMaxLeafToNode;

                if(root.left != null && root.right != null){ // only if leaf to leaf exists
                    maxLeafToLeaf[0] = Math.max(maxLeafToLeaf[0], myLeafToLeaf);
                }
            
                return Math.max(leftMaxLeafToNode, rightMaxLeafToNode) + root.data;
            }

            public int maxPathSum(Node root) {
                int[] maxLeafToLeaf = new int[1];
                maxLeafToLeaf[0] = (int)(-1e8);

                int leafToRoot = leafToNode(root, maxLeafToLeaf);

                return maxLeafToLeaf[0] == (int)(-1e8) ? -1 : maxLeafToLeaf[0];
            }
    }































    public static void main(String[] args){
        
    }
}