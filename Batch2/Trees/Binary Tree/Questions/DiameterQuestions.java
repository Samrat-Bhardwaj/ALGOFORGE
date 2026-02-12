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
    public static int findHeight3(TreeNode root, int[] maximumDiamter){
        if(root == null) return -1;

        int leftHeight = findHeight3(root.left, maximumDiamter);
        int rightHeight = findHeight3(root.right, maximumDiamter);

        int currNodeDiameter = leftHeight + rightHeight + 2;
        maximumDiamter[0] = Math.max(maximumDiamter[0], currNodeDiameter);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static int findDiameter3(TreeNode root){
        int[] maximumDiamter = new int[1];
        findHeight3(root, maximumDiamter);

        return maximumDiamter[0];
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