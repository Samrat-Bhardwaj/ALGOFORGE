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

class MorrisTraversal {
    // Morris Traversal Base code 
    public static TreeNode findRightMost(TreeNode temp, TreeNode curr){

        while(temp.right != null && temp.right != curr){
            temp = temp.right;
        }

        return temp;
    }

    public static void morrisTraverse(TreeNode root){
        TreeNode curr = root;

        while(curr != null){
            if(curr.left == null){
                curr = curr.right;
            } else {
                TreeNode rightMost = findRightMost(curr.left, curr);

                if(rightMost.right == null){ // no thread yet, create one
                    rightMost.right = curr;

                    curr = curr.left;
                } else { // thread is present, remove thread
                    rightMost.right = null;

                    curr = curr.right;
                }
            }
        }
    }

    // Morris inorder Traversal
    class Solution {
        public TreeNode findRightMost(TreeNode temp, TreeNode curr){

            while(temp.right != null && temp.right != curr){
                temp = temp.right;
            }

            return temp;
        }

        public List<Integer> morrisInorderTraversal(TreeNode root){
            List<Integer> inorder = new ArrayList<>();
            TreeNode curr = root;

            while(curr != null){
                if(curr.left == null){
                    inorder.add(curr.val);

                    curr = curr.right;
                } else {
                    TreeNode rightMost = findRightMost(curr.left, curr);

                    if(rightMost.right == null){ // no thread yet, create one
                        rightMost.right = curr;

                        curr = curr.left;
                    } else { // thread is present, remove thread
                        rightMost.right = null;

                        inorder.add(curr.val);

                        curr = curr.right;
                    }
                }
            }

            return inorder;
        }

        public List<Integer> inorderTraversal(TreeNode root) {
            return morrisInorderTraversal(root);
        }
    }

    // Morris preorder traversal ====================================
    class Solution {
        public TreeNode findRightMost(TreeNode temp, TreeNode curr){

            while(temp.right != null && temp.right != curr){
                temp = temp.right;
            }

            return temp;
        }

        public List<Integer> morrisPreorderTraversal(TreeNode root){
            List<Integer> preorder = new ArrayList<>();
            TreeNode curr = root;

            while(curr != null){
                if(curr.left == null){
                    preorder.add(curr.val);

                    curr = curr.right;
                } else {
                    TreeNode rightMost = findRightMost(curr.left, curr);

                    if(rightMost.right == null){ // no thread yet, create one
                        rightMost.right = curr;

                        preorder.add(curr.val);

                        curr = curr.left;
                    } else { // thread is present, remove thread
                        rightMost.right = null;

                        curr = curr.right;
                    }
                }
            }

            return preorder;
        }

        public List<Integer> preorderTraversal(TreeNode root) {
            return morrisPreorderTraversal(root);
        }
    }

    // Morris postorder traversal ===================================
    class Solution {
        public TreeNode findLeftMost(TreeNode temp, TreeNode curr){

            while(temp.left != null && temp.left != curr){
                temp = temp.left;
            }

            return temp;
        }

        // moving to right subtree first 
        public List<Integer> morrisPostOrderTraversal(TreeNode root){
            List<Integer> preorder = new ArrayList<>();
            TreeNode curr = root;

            while(curr != null){
                if(curr.right == null){
                    preorder.add(curr.val);

                    curr = curr.left;
                } else {
                    TreeNode leftMost = findLeftMost(curr.right, curr);

                    if(leftMost.left == null){ // no thread yet, create one
                        leftMost.left = curr;

                        preorder.add(curr.val);

                        curr = curr.right;
                    } else { // thread is present, remove thread
                        leftMost.left = null;

                        curr = curr.left;
                    }
                }
            }

            Collections.reverse(preorder); // reversing preorder of (root, right, left)
            return preorder;
        }

        public List<Integer> postorderTraversal(TreeNode root) {
            return morrisPostOrderTraversal(root);
        }
    }


























    

    public static void main(String[] args){

    }
}