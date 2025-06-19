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

class Main {
    // leetcode 144, PreOrder Traversal =================================
    public void preorderTraversal(TreeNode root, List<Integer> preorderList){
        if(root == null){
            return;
        }

        preorderList.add(root.val);

        preorderTraversal(root.left, preorderList);
        preorderTraversal(root.right, preorderList);
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> preorderList = new ArrayList<>();

        preorderTraversal(root,preorderList);
        return preorderList;
    }

    // leetcode 145, PostOrder Traversal =================================
    public void postorderTraversal(TreeNode root, List<Integer> postorderList){
        if(root == null){
            return;
        }

        postorderTraversal(root.left, postorderList);
        postorderTraversal(root.right, postorderList);

        postorderList.add(root.val);
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> postorderList = new ArrayList<>();

        postorderTraversal(root,postorderList);
        return postorderList;
    }

    // leetcode 94, Inorder Traversal =============================
    public void inorderTraversal(TreeNode root, List<Integer> inorderList){
        if(root == null){
            return;
        }

        inorderTraversal(root.left, inorderList);

        inorderList.add(root.val);

        inorderTraversal(root.right, inorderList);
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> inorderList = new ArrayList<>();

        inorderTraversal(root,inorderList);
        return inorderList;
    }

    // Iterative traversals =================================================
    class Pair {
        TreeNode node;
        int state;

        public Pair(TreeNode node, int state){
            this.node = node;
            this.state = state;
        }
    }

    public static void allTraversals(TreeNode root){
        List<Integer> preorderList = new ArrayList<>();
        List<Integer> postorderList = new ArrayList<>();
        List<Integer> inorderList = new ArrayList<>();

        Stack<Pair> st = new Stack<>();
        if(root != null){
            st.push(new Pair(root, 1));
        }
        

        while(st.size() > 0){
            TreeNode topNode = st.peek().node;

            if(st.peek().state == 1){
                preorderList.add(topNode.val);
                st.peek().state++;

                if(topNode.left != null){
                    st.push(new Pair(topNode.left, 1)); 
                }
            } else if(st.peek().state == 2){
                inorderList.add(topNode.val);
                st.peek().state++;

                if(topNode.right != null){
                    st.push(new Pair(topNode.right, 1));
                }
            } else {
                postorderList.add(topNode.val);
                st.pop();
            }
        }

        System.out.println(preorderList);
        System.out.println(postorderList);
        System.out.println(inorderList);
    }

    // inorder iterative Traversal ====================================
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> inorderList = new ArrayList<>();

        Stack<Pair> st = new Stack<>();
        if(root != null){
            st.push(new Pair(root, 1));
        }
        

        while(st.size() > 0){
            TreeNode topNode = st.peek().node;

            if(st.peek().state == 1){
                st.peek().state++;

                if(topNode.left != null){
                    st.push(new Pair(topNode.left, 1)); 
                }
            } else if(st.peek().state == 2){
                inorderList.add(topNode.val);
                st.peek().state++;

                if(topNode.right != null){
                    st.push(new Pair(topNode.right, 1));
                }
            } else {
                st.pop();
            }
        }

        return inorderList;
    }























    

    public static void main(String[] args){
        
    }
}