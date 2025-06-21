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

class Node
{
    int data;
    Node left, right;

    Node(int item)
    {
        data = item;
        left = right = null;
    }
}

class Main {
    // node to root path in Binary Tree ===============================
    public ArrayList<Integer> nodeToRootPath(Node root, int target){
        if(root == null){
            return new ArrayList<>();
        }

        if(root.data == target){
            ArrayList<Integer> bans = new ArrayList<>();
            bans.add(root.data);
            return bans;
        }

        ArrayList<Integer> leftAns = nodeToRootPath(root.left, target);
        if(leftAns.size() > 0){
            leftAns.add(root.data);
            return leftAns;
        }

        ArrayList<Integer> rightAns = nodeToRootPath(root.right, target);
        if(rightAns.size() > 0){
            rightAns.add(root.data);
            return rightAns;
        }

        return new ArrayList<>();
    }

    // Get all nodes which is K level down =====================
    public static void getKLevelDown(Node node, int K, ArrayList<Integer> ans){
        if(node == null){
            return;
        }
        if(K == 0){
            ans.add(node.data);
            return;
        }

        getKLevelDown(node.left, K - 1, ans);
        getKLevelDown(node.right, K - 1, ans);
    }

    public static ArrayList<Integer> getKLevelDown(Node root,  Node target, int K){
        ArrayList<Integer> ans = new ArrayList<>();

        getKLevelDown(target,K, ans);

        return ans;
    }

    // leetcode 863, Get Nodes at K distance 
    public static ArrayList<TreeNode> nodeToRootPath(TreeNode root, TreeNode target){
        if(root == null){
            return new ArrayList<>();
        }

        if(root.equals(target)){
            ArrayList<TreeNode> bans = new ArrayList<>();
            bans.add(target);
            return bans;
        }

        ArrayList<TreeNode> left = nodeToRootPath(root.left, target);
        if(left.size() > 0){
            left.add(root);
            return left;
        }

        ArrayList<TreeNode> right = nodeToRootPath(root.right, target);
        if(right.size() > 0){
            right.add(root);
            return right;
        }

        return new ArrayList<>();
    }

    public static void getKLevelDown(TreeNode node, TreeNode blocker, int k, List<Integer> res){
        if(node == null || k<0){
            return;
        }

        if(node.equals(blocker)){
            return;
        }

        if(k == 0){
            res.add(node.data);
            return;
        }

        getKLevelDown(node.left, blocker, k-1, res);
        getKLevelDown(node.right, blocker, k-1, res);
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        ArrayList<TreeNode> ntr = nodeToRootPath(root, target);

        List<Integer> res = new ArrayList<>();

        for(int i=0; i<ntr.size(); i++){
            TreeNode currentNode = ntr.get(i);
            TreeNode blocker = i == 0 ? null : ntr.get(i-1);
            getKLevelDown(currentNode, blocker, k - i, res);
        }

        return res;
    }

// leetcode 236, LCA ===============================================
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) { // O(N), O(N) space
        ArrayList<TreeNode> ntrp = nodeToRootPath(root, p);
        ArrayList<TreeNode> ntrq = nodeToRootPath(root, q);

        int i = ntrp.size() - 1;
        int j = ntrq.size() - 1;

        while(i>=0 && j>=0 && ntrp.get(i) == ntrq.get(j)){
            i--;
            j--;
        }
        j++;

        return ntrq.get(j);
    }

    // LCA better
    public boolean LCA_better(TreeNode root, TreeNode p, TreeNode q, TreeNode[] LCA){
        if(root == null){
            return false;
        }
        if(LCA[0] != null){
            return false; // don't go anywhere else once we have found LCA
        }
        
        boolean self = false;
        if(root == p || root == q){
            self = true;
        }

        boolean left = LCA_better(root.left, p, q, LCA);
        boolean right = LCA_better(root.rightt, p, q, LCA);

        if((self && left) || (self && right) || (left && right)){
            LCA[0] = root;
        }

        return self || left || right;
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) { // O(N), O(rec) space
        TreeNode[] LCA = new TreeNode[1];
        LCA_better(root,p,q,LCA);
        return LCA[0];
    }

    // leetcode 1325, REMOVE leaves equal to given target
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if(root == null){
            return null;
        }

        root.left = removeLeafNodes(root.left, target);
        root.right = removeLeafNodes(root.right, target);

        if(root.left == null && root.right == null && root.val == target){
            return null;
        }

        return root;
    }


    
































    // https://www.geeksforgeeks.org/problems/sum-of-binary-tree/1
    static int sumBT(Node root) {
        if(root == null) return 0;

        int leftSum =  sumBT(root.left);
        int rightSum = sumBT(root.right);
        return leftSum + rightSum + root.data;
    }

    // https://www.geeksforgeeks.org/problems/size-of-binary-tree/1
    public static int getSize(Node node) {
        // return node == null ? 0 : getSize(node.left) + getSize(node.right) + 1;
        if(node == null){
            return 0;
        }

        int leftSize = getSize(node.left);
        int rightSize = getSize(node.right);

        return leftSize + rightSize + 1;
    }

    // leetcode 104 ==================================
    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }

        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        return Math.max(leftDepth, rightDepth) + 1;
    }

    public static void main(String[] args){
        
    }
}