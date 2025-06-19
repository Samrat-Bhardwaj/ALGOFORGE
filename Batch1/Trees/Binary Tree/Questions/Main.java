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
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        
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