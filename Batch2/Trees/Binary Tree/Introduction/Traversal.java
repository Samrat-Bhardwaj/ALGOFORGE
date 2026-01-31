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

class Traversal {
    class Pair {
        TreeNode node;
        int state;

        public Pair(TreeNode node, int state){
            this.node = node;
            this.state = state;
        }
    }

    public static void traverse(TreeNode root){
        Stack<Pair> st = new Stack<>();

        if(root != null){
            st.push(new Pair(root,0));
        }

        while(st.size() > 0){
            TreeNode topNode = st.peek().node;

            if(st.peek().state == 0){ // seeing node for the first time, push left
                preorder.add(topNode.val);
                st.peek().state++;

                if(topNode.left != null){
                    st.push(new Pair(topNode.left,0));
                }

            } else if(st.peek().state == 1){ // already seen left subtree, push right
                inorder.add(topNode.val);
                st.peek().state++;

                if(topNode.right != null){
                    st.push(new Pair(topNode.right, 0));
                }
            } else { // seen both left and right subtree, pop
                postorder.add(topNode.val);

                st.pop();
            }
        }
    }

    public static void main(String[] args){

    }
}