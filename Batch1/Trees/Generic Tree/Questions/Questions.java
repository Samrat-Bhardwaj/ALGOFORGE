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

    // mirror a generic Tree
    public static Node mirrorTree(Node root){
        int size = root.children.size();

        int i = 0;
        int j = size-1;

        while(i<=j){
            Node childAtI = root.children.get(i);
            Node childAtJ = root.children.get(j);

            childAtI = mirrorTree(childAtI);

            if(i < j) // don't mirror again if i == j
                childAtJ = mirrorTree(childAtJ);

            root.children.set(i, childAtJ);
            root.children.set(j, childAtI);

            i++;
            j--;
        }

        return root;
    }

    // remove leaf nodes
    public static Node removeLeafNodes(Node root){
        int size = root.children.size();
        for(int i=size-1; i>=0; i--){
            Node child = root.children.get(i);

            if(child.children.size() == 0){
                root.children.remove(i);
            }
        }

        for(Node child: root.children){
            removeLeafNodes(child);
        }
        return root;
    }



























    // leetcode 430 (flatten a double linkedlist)========================
    // similar to flatten a generic tree
    public Node flatten(Node head) {
        return null;
    }

    





















    public static void displayTree(Node root){
        System.out.print(root.data + " -> ");

        for(Node child: root.children){
            System.out.print(child.data+" ,");
        }

        System.out.println(".");

        // asking the recursive function to print subTrees
        for(Node child: root.children){
            displayTree(child);
        }
    }

    public static void main(String[] args){
        // int[] treeData = {10,20,80,-1,-1,30,50,-1,60,-1,-1,40,90,-1,100,120,-1,130,-1,-1,110,-1,-1,-1};
        // int[] treeData = {10,20,50,-1,60,-1,-1,30,-1,40,80,-1,90,-1,100,-1,-1,-1};
        int[] treeData = {10,20,50,140,-1,-1,-1,30,60,-1,70,110,-1,120,-1,-1,80,-1,-1,40,90,-1,100,130,-1,-1,-1,-1};

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
        // eulerTraversal(root);
        displayTree(root);
        Node rootMirror = removeLeafNodes(root);

        System.out.println("Printing Modified Tree ===================");
        displayTree(rootMirror);
    }
}

