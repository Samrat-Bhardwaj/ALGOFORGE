import java.util.ArrayList;
import java.util.Stack;
class Node {
    int data;
    ArrayList<Node> children;

    public Node(){}

    public Node(int data){
        this.data = data;
        this.children = new ArrayList<>();
    }
}
class Questions {

    // size of Tree
    public static int size(Node root){
        int subTreesSize = 0;

        for(Node child: root.children){
            subTreesSize += size(child);
        }

        return subTreesSize + 1;
    }

    // maximum of Tree
    public static int maxValue(Node root){
        int maxOfSubtree = Integer.MIN_VALUE;

        for(Node child : root.children){
            int maxChildValue = maxValue(child);
            maxOfSubtree = Math.max(maxOfSubtree, maxChildValue);
        }

        return Math.max(maxOfSubtree, root.data);
    }

    // height of Tree
    public static int height(Node root){
        if(root == null){
            return -1;
        }

        int height = -1;
        
        for(Node child: root.children){
            int childSubTreeHeight = height(child);

            height = Math.max(height, childSubTreeHeight);
        }

        return height + 1;
    }

    // Euler traversal of tree
    public static void eulerTraversal(Node root){
        System.out.println("Preorder -> " + root.data);

        for(Node child: root.children){
            System.out.println("Taking edge from " + root.data + " -> " + child.data);

            eulerTraversal(child);

            System.out.println("Taking edge from " + child.data + " -> " + root.data);
        }


        System.out.println("Postorder -> " + root.data);
    }



























    // leetcode 430 (flatten a double linkedlist)========================
    // similar to flatten a generic tree
    public Node flatten(Node head) {
        return null;
    }

    























    public static void main(String[] args){
        // int[] treeData = {10,20,80,-1,-1,30,50,-1,60,-1,-1,40,90,-1,100,120,-1,130,-1,-1,110,-1,-1,-1};
        int[] treeData = {10,20,50,-1,60,-1,-1,30,-1,40,80,-1,90,-1,100,-1,-1,-1};

        Stack<Node> st = new Stack<>();
        Node root = null;
        for(int i=0; i<treeData.length; i++){
            int val = treeData[i];

            if(val == -1){
                st.pop();
            } else {
                Node nn = new Node(val);
                if(st.size() == 0){
                    root = nn;
                } else {
                    st.peek().children.add(nn);
                }
                st.push(nn);
            }
        }

        // System.out.println(height(root));
        eulerTraversal(root);
    }
}

