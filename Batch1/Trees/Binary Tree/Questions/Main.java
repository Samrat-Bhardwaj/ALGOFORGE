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


    // leetcode 112
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) return false;

        if(root.left == null && root.right == null){ // leaf
            return root.val == targetSum;
        }

        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    // leetcode 113 
    public void getAllPaths(TreeNode root,int targetSum,List<Integer> currentPath, List<List<Integer>> allPaths){
        if(root == null) return;

        if(root.left == null && root.right == null){
            if(root.val == targetSum){
                currentPath.add(root.val);

                allPaths.add(new ArrayList<>(currentPath)); // O(N)

                currentPath.remove(currentPath.size()-1);
            }
            return;
        }

        currentPath.add(root.val);

        getAllPaths(root.left, targetSum - root.val, currentPath, allPaths);
        getAllPaths(root.right, targetSum - root.val, currentPath, allPaths);

        currentPath.remove(currentPath.size()-1);
    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> allPaths = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();

        getAllPaths(root,targetSum, currentPath,allPaths);
        return allPaths;
    }

// Binary tree to doubly linkedList (https://www.geeksforgeeks.org/problems/binary-tree-to-dll/1)

class Solution {
    // Function to convert binary tree to doubly linked list and return it.
    Node head;
    Node prev;
    void convertBT_DLL(Node root){
        if(root == null) return;

        convertBT_DLL(root.left);

        if(head == null){
            head = root;
        } else {
            prev.right = root; // already visited prev, can destroy
            root.left = prev; // already visited left, can destroy
        }

        prev = root;
        convertBT_DLL(root.right);
    }

    Node bToDLL(Node root) {
        head = null;
        prev = null;

        convertBT_DLL(root);

        return head;
    }
}

// leetcode 114 (Flatten tree same as of generic tree)
public TreeNode flattenTree(TreeNode root){
    if(root == null) return null;

    if(root.left == null && root.right == null){
        return root;
    }

    TreeNode leftKaTail = flattenTree(root.left);
    TreeNode rightKaTail = flattenTree(root.right);
    
    TreeNode rightSubTree = root.right;
    TreeNode leftSubTree = root.left;

    root.left = null;
    if(leftSubTree != null){
        root.right = leftSubTree;
        leftKaTail.right = rightSubTree;
    }

    return rightKaTail != null ? rightKaTail : leftKaTail;
}

public void flatten(TreeNode root) {
    flattenTree(root);
}


// leetcode 114 (Flatten tree same as of generic tree)
TreeNode prev;
public void flattenTree(TreeNode root){
    if(root == null) return;
    
    flattenTree(root.right); // want our previous to be on root.right
    flattenTree(root.left);

    // root will be at rightmost of left subtree
    root.right = prev;
    root.left = null;

    prev = root;
}

public void flatten(TreeNode root) {
    prev = null;
    flattenTree(root);
}

// Leetcode 116 ===========================
public Node connect(Node root) {
    if(root == null){
        return null;
    }

    Node curr = root;
    Node first = root.left; // first node of next level

    while(first != null){
        curr.left.next = curr.right; // setting next of left child = right

        if(curr.next == null){ // if current is at last node of current level
            curr = first; // moving curr pointer to first node of next level
            first = first.left; 
        } else {
            Node next = curr.next;
            curr.right.next = next.left; // setting next of right child

            curr = next;
        }
    }

    return root;
}

// Leetcode 117 =================================
public Node connect(Node root) {
    Node curr = root; // for travelling on current level
    Node head = null; // should point to first node of next level
    Node prev = null; // prev node to travel on next level

    while(curr != null){
        // travel on the next level
        while(curr != null){
            if(curr.left != null){
                if(prev == null){ // first node that is not null in this level
                    head = curr.left;
                } else {
                    prev.next = curr.left;
                }
                prev = curr.left;
            }

            if(curr.right != null){
                if(prev == null){ // first node that is not null in this level
                    head = curr.right;
                } else {
                    prev.next = curr.right;
                }
                prev = curr.right;
            }

            curr = curr.next;
        }

        curr = head;
        head = null;
        prev = null;
    }        

    return root;
}

// leetcode 979 (Distribute coins)
public int coinsRequired(TreeNode root, int[] moves){
    if(root == null){
        return 0;
    }

    int leftTreeRequirement = coinsRequired(root.left, moves);
    int rightTreeRequirement = coinsRequired(root.right, moves);

    moves[0] += Math.abs(leftTreeRequirement) + Math.abs(rightTreeRequirement);

    return leftTreeRequirement + rightTreeRequirement + root.val - 1;
}

public int distributeCoins(TreeNode root) {
    int[] moves = new int[1];

    coinsRequired(root, moves);      
    return moves[0];
}

// leetcode 968 (Binary Tree Cameras) ======================================
class Solution {
    // 1 (i am not covered by any camera, needs coverage)
    // 0 (i am the camera)
    // -1 (i am covered, don't need camera)

    int totalCamera;
    public int cameraRequired(TreeNode root){
        if(root == null){
            return -1; // null doesn't need any camera
        }

        int leftNodeRequirement = cameraRequired(root.left);
        int rightNodeRequirement = cameraRequired(root.right);

        if(leftNodeRequirement == 1 || rightNodeRequirement == 1){ // left or right needs coverage
            totalCamera++;
            return 0;
        }

        if(leftNodeRequirement == 0 || rightNodeRequirement == 0){ // left or right is giving me coverage
            return -1;
        }

        return 1; // left and right is covered but i am not
    }

    public int minCameraCover(TreeNode root) {
        totalCamera = 0;

        int cameraRequired = cameraRequired(root);
        if(cameraRequired == 1){ // root may also need camera
            totalCamera++;
        }

        return totalCamera;
    }
}
































    public static void main(String[] args){
        
    }
}