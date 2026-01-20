import java.util.ArrayList;
import java.util.Stack;

class TreeNode {
    int data;
    ArrayList<TreeNode> children;

    public TreeNode(int data){
        this.data = data;
        children = new ArrayList<>();
    }
}

class Main {

    public static TreeNode constructTree(int[] dataArray){
        Stack<TreeNode> st = new Stack<>();
        TreeNode root = null;

        for(int i=0; i<dataArray.length; i++){
            if(dataArray[i] == -1){
                st.pop();
            } else {

                TreeNode newNode = new TreeNode(dataArray[i]);
                if(st.size() == 0){
                    root = newNode;
                } else {
                    st.peek().children.add(newNode);
                }

                st.push(newNode);
            }
        }

        return root;
    }

    // Traversing tree recursively ====================
    public static void traverse(TreeNode root){
        System.out.println("Preorder -> " + root.data);

        for(TreeNode child: root.children){
            System.out.println("Traversing on edge from " + root.data + " -> " + child.data);

            traverse(child);

            System.out.println("Traversing on edge from " + child.data + " -> " + root.data);
        }

        System.out.println("Postorder -> " + root.data);
    }

    // Level order traversal 
    public static void levelOrderTraversal(TreeNode root){

    }

    public static void main(String[] args){
        // int[] dataArray1 = {10,20,50,-1,60,-1,-1,30,-1,40,80,-1,90,-1,100,-1,-1,-1};
        int[] dataArray2 = {10,20,50,-1,60,-1,-1,30,70,-1,-1,40,80,-1,90,110,-1,120,-1,-1,100,-1,-1,-1};


        // TreeNode root = constructTree(dataArray1);
        TreeNode root2 = constructTree(dataArray2);

        levelOrderTraversal(root2);
    }
}