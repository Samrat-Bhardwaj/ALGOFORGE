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
                    rightMost.right = null; // thread deletion
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
                inorder.add(curr.val);
                curr = curr.right; // thread travel, edge travel
            } else {
                TreeNode rightMost = findRightMost(curr.left, curr);

                if(rightMost.right == null){ // no thread yet, create one
                    rightMost.right = curr; // thread creation
                    curr = curr.left;
                } else {
                    rightMost.right = null; // thread deletion

                    inorder.add(curr.val);
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

    // Preorder morris traversal
    public TreeNode findRightMost(TreeNode node, TreeNode curr){
        TreeNode temp = node;

        while(temp.right != null && temp.right != curr){
            temp = temp.right;
        }

        return temp;
    }

    public void MorrisPreTraversal(TreeNode root, List<Integer> preorder){
        TreeNode curr = root;

        while(curr != null){

            if(curr.left == null){

                preorder.add(curr.val);
                curr = curr.right; // thread travel, edge travel
            } else {
                TreeNode rightMost = findRightMost(curr.left, curr);

                if(rightMost.right == null){ // no thread yet, create one
                    rightMost.right = curr; // thread creation

                    preorder.add(curr.val);
                    curr = curr.left;
                } else {
                    rightMost.right = null; // thread deletion

                    curr = curr.right; 
                }
            }
        }
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> preorder = new ArrayList<>();
        MorrisPreTraversal(root, preorder);

        return preorder;
    }

    // postorder homework 























    public static void main(String[] agrs){

    }
}