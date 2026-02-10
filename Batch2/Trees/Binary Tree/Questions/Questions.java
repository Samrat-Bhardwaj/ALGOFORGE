class Questions {
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

    // Make tree from preorder and inorder (Leetcode 105) =========
    public TreeNode buildTree(int[] preorder, int preSi, int preEi, int[] inorder, int inSi, int inEi){
        if(preSi > preEi){
            return null;
        }

        if(preSi == preEi){
            return new TreeNode(preorder[preSi]);
        }

        TreeNode root = new TreeNode(preorder[preSi]);

        int rootIdx = inSi;
        int leftTreeElements = 0;

        while(rootIdx <= inEi && inorder[rootIdx] != root.val){
            rootIdx++;
            leftTreeElements++;
        }

        root.left = buildTree(preorder, preSi+1, preSi+leftTreeElements, inorder, inSi, rootIdx-1);
        root.right = buildTree(preorder, preSi+leftTreeElements+1, preEi, inorder, rootIdx+1, inEi);

        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int size = preorder.length;

        return buildTree(preorder,0,size-1,inorder,0,size-1);
    }

    // Make Tree from postOrder and inorder (Leetcode 106) ================
    public TreeNode buildTree(int[] postorder, int poSi, int poEi, int[] inorder, int inSi, int inEi){
        if(inSi > inEi){
            return null;
        }

        if(poSi == poEi){
            return new TreeNode(postOrder[poEi]);
        }

        TreeNode root = new TreeNode(postorder[poEi]);

        int rootIdx = inSi;
        int leftTreeElements = 0;

        while(rootIdx <= inEi && inorder[rootIdx] != root.val){ // first condition is optional if second base case is written
            rootIdx++;
            leftTreeElements++;
        }

        root.left = buildTree(postorder, poSi, poSi + leftTreeElements - 1, inorder, inSi, rootIdx-1);
        root.right = buildTree(postorder, poSi + leftTreeElements, poEi - 1, inorder, rootIdx+1, inEi);

        return root;
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int size = inorder.length;

        return buildTree(postorder, 0, size-1, inorder, 0, size-1);
    }


    // Make Tree frm preorder and postorder (Leetcode 889) ================
    public TreeNode buildTree(int[] preorder, int preSi, int preEi, int[] postorder, int poSi, int poEi){
        if(preSi > preEi){
            return null;
        }

        if(preSi == preEi){
            return new TreeNode(preorder[preSi]);
        }

        TreeNode root = new TreeNode(preorder[preSi]);

        int idx = poSi;

        while(idx <= poEi && postorder[idx] != preorder[preSi + 1]){ // finding root of left subtree in post order
            idx++;
        }

        int leftTreeElements = idx - poSi + 1;

        root.left = buildTree(preorder, preSi + 1, preSi + leftTreeElements, postorder, poSi, idx);
        root.right = buildTree(preorder, preSi + leftTreeElements + 1, preEi, postorder, idx+1, poEi - 1);
        
        return root;
    }

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        int size = preorder.length;

        return buildTree(preorder, 0, size-1, postorder, 0, size-1);
    }

    // ============================================================== Binary Tree View Problems ==========================================
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

        traverse(root, 0, minMax);

        int width = minMax[1] - minMax[0] + 1;

        return width;
    }

    // Vertical level order (https://www.geeksforgeeks.org/problems/print-a-binary-tree-in-vertical-order/1)
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
            if(root == null) return;

            minMax[0] = Math.min(minMax[0], verticalLevel);
            minMax[1] = Math.max(minMax[1], verticalLevel);

            traverse(root.left, verticalLevel - 1, minMax);
            traverse(root.right, verticalLevel + 1, minMax);
        }

        public ArrayList<ArrayList<Integer>> verticalOrder(Node root) {
            int[] minMax = new int[2];

            traverse(root, 0, minMax);

            int shift = -minMax[0];

            int width = minMax[1] - minMax[0] + 1;
            
            ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

            for(int i=0; i<width; i++){
                ans.add(new ArrayList<Integer>());
            }

            LinkedList<Pair> que = new LinkedList<>();
            que.addLast(new Pair(root,shift));

            while(que.size() > 0){
                int size = que.size();

                while(size-- > 0){
                    Pair front = que.removeFirst();

                    Node node = front.node;
                    int currentLevel = front.verticalLevel;

                    ans.get(currentLevel).add(node.data);

                    if(node.left != null){
                        que.addLast(new Pair(node.left, currentLevel - 1));
                    }

                    if(node.right != null){
                        que.addLast(new Pair(node.right, currentLevel + 1));
                    }
                }
            }

            return ans;
        }
    }

    // Vertical order sum (https://www.geeksforgeeks.org/problems/vertical-sum/1) ==============
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
            if(root == null) return;

            minMax[0] = Math.min(minMax[0], verticalLevel);
            minMax[1] = Math.max(minMax[1], verticalLevel);

            traverse(root.left, verticalLevel - 1, minMax);
            traverse(root.right, verticalLevel + 1, minMax);
        }

        public ArrayList<Integer> verticalSum(Node root) {
            int[] minMax = new int[2];
            traverse(root,0,minMax);
            
            int shift = -minMax[0]; 

            int width = minMax[1] - minMax[0] + 1;

            ArrayList<Integer> ans = new ArrayList<>();
            for(int i=0; i<width; i++){
                ans.add(0); // initialising sum of every vertical level with 0
            }

            LinkedList<Pair> que = new LinkedList<>();
            que.add(new Pair(root,shift));

            while(que.size() > 0){
                int size = que.size();
                
                while(size-- > 0){
                    Pair front = que.removeFirst();
                    Node node = front.node;
                    int currentLevel = front.verticalLevel;

                    ans.set(currentLevel, ans.get(currentLevel) + node.data);

                    if(node.left != null){
                        que.add(new Pair(node.left, currentLevel - 1));
                    }

                    if(node.right != null){
                        que.add(new Pair(node.right, currentLevel + 1));
                    }
                }
            }

            return ans;
        }
    }

    // Bottom view of Binary Tree(https://www.geeksforgeeks.org/problems/bottom-view-of-binary-tree/1)
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
            if(root == null) return;

            minMax[0] = Math.min(minMax[0], verticalLevel);
            minMax[1] = Math.max(minMax[1], verticalLevel);

            traverse(root.left, verticalLevel - 1, minMax);
            traverse(root.right, verticalLevel + 1, minMax);
        }

        public ArrayList<Integer> bottomView(Node root) {
            int[] minMax = new int[2];
            traverse(root,0,minMax);
            
            int shift = -minMax[0]; 

            int width = minMax[1] - minMax[0] + 1;

            ArrayList<Integer> ans = new ArrayList<>();
            for(int i=0; i<width; i++){
                ans.add(0); // initialising sum of every vertical level with 0
            }

            LinkedList<Pair> que = new LinkedList<>();
            que.add(new Pair(root,shift));

            while(que.size() > 0){
                int size = que.size();
                
                while(size-- > 0){
                    Pair front = que.removeFirst();
                    Node node = front.node;
                    int currentLevel = front.verticalLevel;

                    ans.set(currentLevel, node.data);

                    if(node.left != null){
                        que.add(new Pair(node.left, currentLevel - 1));
                    }

                    if(node.right != null){
                        que.add(new Pair(node.right, currentLevel + 1));
                    }
                }
            }

            return ans;
        }
    }
    
    // Top view of Binary Tree(https://www.geeksforgeeks.org/problems/top-view-of-binary-tree/1)
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
            if(root == null) return;

            minMax[0] = Math.min(minMax[0], verticalLevel);
            minMax[1] = Math.max(minMax[1], verticalLevel);

            traverse(root.left, verticalLevel - 1, minMax);
            traverse(root.right, verticalLevel + 1, minMax);
        }

        public ArrayList<Integer> topView(Node root) {
            int[] minMax = new int[2];
            traverse(root,0,minMax);
            
            int shift = -minMax[0]; 

            int width = minMax[1] - minMax[0] + 1;

            ArrayList<Integer> ans = new ArrayList<>();
            for(int i=0; i<width; i++){
                ans.add(null); // initialising sum of every vertical level with 0
            }

            LinkedList<Pair> que = new LinkedList<>();
            que.add(new Pair(root,shift));

            while(que.size() > 0){
                int size = que.size();
                
                while(size-- > 0){
                    Pair front = que.removeFirst();
                    Node node = front.node;
                    int currentLevel = front.verticalLevel;
                    
                    if(ans.get(currentLevel) == null){
                        ans.set(currentLevel, node.data);
                    }
                    
                    if(node.left != null){
                        que.add(new Pair(node.left, currentLevel - 1));
                    }

                    if(node.right != null){
                        que.add(new Pair(node.right, currentLevel + 1));
                    }
                }
            }

            return ans;
        }
    }

    // Diagonal Tree Traveral (https://www.geeksforgeeks.org/problems/diagonal-traversal-of-binary-tree/1)
    class Tree {
        public void traverse(Node root, int diagonalLevel, int[] minMax){
            if(root == null) return;

            minMax[0] = Math.min(minMax[0], diagonalLevel);

            traverse(root.left, diagonalLevel - 1, minMax);
            traverse(root.right, diagonalLevel + 0, minMax);
        }

        public int findDiagonalWidth(Node root){
            int[] minMax = new int[2]; // we only need to find min, max will always be 0

            traverse(root, 0, minMax);

            int width = -minMax[0] + 1; // minMax[1] - minMax[0] + 1
            return width;
        }

        public void traverseDiagonally(Node root, int diagonalLevel, ArrayList<ArrayList<Integer>> diagonalOrder){
            if(root == null) return;

            diagonalOrder.get(diagonalLevel).add(root.data);

            // question want to get left subtree answer first
            traverseDiagonally(root.left, diagonalLevel - 1, diagonalOrder);
            traverseDiagonally(root.right, diagonalLevel + 0, diagonalOrder);
        }

        public ArrayList<Integer> diagonal(Node root) {
            int[] minMax = new int[2]; // we only need to find min, max will always be 0

            traverse(root, 0, minMax);

            int width = -minMax[0] + 1; // minMax[1] - minMax[0] + 1
            int shift = -minMax[0];
            
            ArrayList<ArrayList<Integer>> diagonalOrder = new ArrayList<>();

            for(int i=0; i<width; i++){
                diagonalOrder.add(new ArrayList<>());
            }

            traverseDiagonally(root,shift,diagonalOrder);

            //Convert 2d arraylist to 1d
            ArrayList<Integer> ans = new ArrayList<>();
            for(int i=diagonalOrder.size()-1; i>=0; i--){
                ans.addAll(diagonalOrder.get(i));
            }

            return ans;
        }
    }



   

























    public static void main(String[] args){

    }
}


