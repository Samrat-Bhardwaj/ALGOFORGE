import java.util.Stack;

class TreeNode {
    int data;

    TreeNode left;
    TreeNode right;

    public TreeNode(int data){
        this.data = data;
    }
}

class Pair {
    TreeNode node;
    int state;

    public Pair(TreeNode node, int state){
        this.node = node;
        this.state = state;
    }
}

class DiameterQuestions {
    
    // finding diameter through height [ O(n^2) ] =================
    public static int findHeight(TreeNode root){
        if(root == null) return -1;

        int leftHeight = findHeight(root.left);
        int rightHeight = findHeight(root.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static int findDiameter(TreeNode root){
        if(root == null) return -1;

        int leftHeight = findHeight(root.left);
        int rightHeight = findHeight(root.right);

        int currentDiameter = leftHeight + rightHeight + 2;

        int leftMaxDiameter = findDiameter(root.left); // max diameter in left tree
        int rightMaxDiameter = findDiameter(root.right);

        return Math.max(currentDiameter, Math.max(leftMaxDiameter, rightMaxDiameter));
    }

    // Method-2 (Find diameter while calculating height =======
    public static int[] findHeightDiameter(TreeNode root){ // returns {height, diameter}
        if(root == null){
            return new int[]{-1,-1};
        }

        int[] leftHeightDiameter = findHeightDiameter(root.left);
        int[] rightHeightDiameter = findHeightDiameter(root.right);

        int leftHeight = leftHeightDiameter[0];
        int rightHeight = rightHeightDiameter[0];

        int leftMaxDiameter = leftHeightDiameter[1];
        int rightMaxDiameter = rightHeightDiameter[1];

        int currNodeHeight = Math.max(leftHeight, rightHeight) + 1;
        
        int currNodeDiameter = leftHeight + rightHeight + 2;
        int currNodeMaxDiameter = Math.max(currNodeDiameter, Math.max(leftMaxDiameter, rightMaxDiameter));

        return new int[]{currNodeHeight, currNodeMaxDiameter};
    }

    public static int findDiameter2(TreeNode root){
        int[] heightDia =  findHeightDiameter(root);

        return heightDia[1];    
    }

    // Method 3 => Only find height, while finding height, keep calculating diameter ===============================================
    public static int findHeight3(TreeNode root, int[] maximumDiameter){
        if(root == null) return -1;

        int leftHeight = findHeight3(root.left, maximumDiameter);
        int rightHeight = findHeight3(root.right, maximumDiameter);

        int currNodeDiameter = leftHeight + rightHeight + 2;
        maximumDiameter[0] = Math.max(maximumDiameter[0], currNodeDiameter);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static int findDiameter3(TreeNode root){
        int[] maximumDiameter = new int[1];
        findHeight3(root, maximumDiameter);

        return maximumDiameter[0];
    }

    // Find maximum path sum from leaf to leaf (https://www.geeksforgeeks.org/problems/maximum-path-sum/1)
    class Solution {
        int maxLeafToNode(Node root, int[] maxLeafToLeaf){
            if(root == null){
                return (int)(-1e8);
            }

            if(root.left == null && root.right == null){
                return root.data;
            }

            int leftMaxLTN = maxLeafToNode(root.left, maxLeafToLeaf);
            int rightMaxLTN = maxLeafToNode(root.right, maxLeafToLeaf);

            if(root.left != null && root.right != null){
                maxLeafToLeaf[0] = Math.max(maxLeafToLeaf[0], leftMaxLTN + root.data + rightMaxLTN);
            }

            return Math.max(leftMaxLTN, rightMaxLTN) + root.data;
        }
        
        int maxPathSum(Node root) {
            int[] maxLeafToLeaf = new int[1];
            maxLeafToLeaf[0] = (int)(-1e8);

            int maxleafToRoot = maxLeafToNode(root, maxLeafToLeaf);

            if(root.left != null && root.right != null){
                return maxLeafToLeaf[0];
            } else { // if any one of left or right is null, leafToRoot can be a possible ans
                return Math.max(maxleafToRoot,maxLeafToLeaf[0]);
            }
        }
    }

    // Maximum path sum of node to node (Leetcode 124) =================================
    class Solution {
        public int findMaxNodeToCurrentPathSum(TreeNode root, int[] maxNodeToNode){
            if(root == null){
                return (int)(-1e8);
            }

            int leftMaxPathSum = findMaxNodeToCurrentPathSum(root.left, maxNodeToNode);
            int rightMaxPathSum = findMaxNodeToCurrentPathSum(root.right, maxNodeToNode);

            int maxPathSumIncludingRoot = Math.max(leftMaxPathSum + root.val, rightMaxPathSum + root.val);
            int maxPathSum = Math.max(maxPathSumIncludingRoot, root.val);

            maxNodeToNode[0] = Math.max(maxNodeToNode[0], Math.max(maxPathSum, leftMaxPathSum + root.val + rightMaxPathSum));

            return maxPathSum;
        }

        public int maxPathSum(TreeNode root) {
            int[] maxNodeToNode = new int[1];
            maxNodeToNode[0] = (int)(-1e8);

            findMaxNodeToCurrentPathSum(root,maxNodeToNode);

            return maxNodeToNode[0];
        }
    }





















    public static TreeNode buildTree(Integer[] arr){
        Stack<Pair> st = new Stack<>();

        TreeNode root = null;

        for(int i=0; i<arr.length; i++){
            Integer ele = arr[i];

            if(ele == null){
                if(st.size() == 0){ // no tree possible, first node is null itself 
                    return null;
                } else if(st.peek().state == 0){
                    st.peek().state++;
                } else {
                    st.pop();
                }
            } else {
                TreeNode newNode = new TreeNode(ele);

                if(st.size() == 0){
                    root = newNode;
                } else if(st.peek().state == 0){
                    st.peek().node.left = newNode;

                    st.peek().state++;
                } else {
                    st.peek().node.right = newNode;

                    st.pop();
                }

                st.push(new Pair(newNode, 0));
            }
        }

        return root;
    }

    public static void main(String[] args){
        Integer[] arr = {10,20,40,null,60,null,null,50,70,null,null,null,30,80,90,null,null,100,null,null,null};

        TreeNode root = buildTree(arr);
        System.out.println(findDiameter3(root));
    }
}