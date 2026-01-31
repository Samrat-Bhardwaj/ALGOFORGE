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

class Main {
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

    public static void display(TreeNode root){
        if(root == null) return;

        String nodeStructure = (root.left == null ? "." : root.left.data) + " <- " + root.data + " -> " + (root.right == null ? "." :  root.right.data);

        System.out.println(nodeStructure);
        display(root.left);
        display(root.right);
    }

    public static void main(String[] args){
        Integer[] arr = {10,20,40,null,60,null,null,50,70,null,null,null,30,80,90,null,null,100,null,null,null};

        TreeNode root = buildTree(arr);
        display(root);
    }
}