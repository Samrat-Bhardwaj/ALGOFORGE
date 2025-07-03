class MorrisTraversal {
    public Node findRightMost(Node node, Node curr){
        Node temp = node;

        while(temp.right != null && temp.right != curr){
            temp = temp.right;
        }

        return temp;
    }

    public void MorrisTraversal(Node root){
        Node curr = root;

        while(curr != null){

            if(curr.left == null){
                curr = curr.right; // thread travel, edge travel
            } else {
                Node rightMost = findRightMost(curr.left, curr);

                if(rightMost.right == null){ // no thread yet, create one
                    rightMost.right = curr; // thread creation
                    curr = curr.left;
                } else {
                    rightMost.right = null; // thread creation
                    curr = curr.right; 
                }
            }
        }
    }

    // inorder morris traversal
    public TreeNode findRightMost(TreeNode node, TreeNode curr){
        TreeNode temp = node;

        while(temp.right != null && temp.right != curr){
            temp = temp.right;
        }

        return temp;
    }

    public void MorrisInTraversal(TreeNode root, List<Integer> inorder){
        TreeNode curr = root;

        while(curr != null){

            if(curr.left == null){
                curr = curr.right; // thread travel, edge travel
            } else {
                TreeNode rightMost = findRightMost(curr.left, curr);

                if(rightMost.right == null){ // no thread yet, create one
                    rightMost.right = curr; // thread creation
                    curr = curr.left;
                } else {
                    rightMost.right = null; // thread creation
                    curr = curr.right; 
                }
            }
        }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> inorder = new ArrayList<>();
        MorrisInTraversal(root, inorder);

        return inorder;
    }
























    public static void main(String[] agrs){

    }
}