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

    // flatten a generic Tree
    public static Node flattenGenericTree(Node root){
        for(Node child : root.children){
            flattenGenericTree(child);
        }

        while(root.children.size() > 1){
            Node lastChild = root.children.remove(root.children.size()-1);
            Node secondLastChild = root.children.get(root.children.size()-1);

            Node tail = getTail(secondLastChild);

            tail.children.add(lastChild);
        }

        return root;
    }

    public static Node getTail(Node root){ // SubTree is already flattened
        Node temp = root;
        while(temp.children.size() > 0){
            temp = temp.children.get(0);
        }

        return temp;
    }

    // Optimized approach of flatten a generic Tree
    public static Node flattenGenericTree2(Node root){ // returning tail of flattened node
        if(root.children.size() == 0){
            return root;
        }

        Node lastChildTail = flattenGenericTree2(root.children.get(root.children.size()-1));

        while(root.children.size() > 1){
            Node lastChild = root.children.remove(root.children.size() - 1);
            Node secondLastChild = root.children.get(root.children.size() - 1);

            Node secondLastChildTail = flattenGenericTree2(secondLastChild);

            secondLastChildTail.children.add(lastChild);
        }

        return lastChildTail;
    }

    // Find in generic tree
    public static boolean find(Node root, int target){
        if(root.data == target){
            return true;
        }
        
        for(Node child: root.children){
            boolean dataFoundInSubtree = find(child, target);

            if(dataFoundInSubtree == true){
                return true;
            }
        }

        return false;
    }

    // Node to root path 
    public static ArrayList<Integer> nodeToRootPath(Node root, int target){
        if(root.data == target){
            ArrayList<Integer> bans = new ArrayList<>();
            bans.add(root.data);

            return bans;
        } 

        for(Node child: root.children){
            ArrayList<Integer> subPath = nodeToRootPath(child, target);

            if(subPath.size() > 0){
                subPath.add(root.data);
                return subPath;
            }
        }

        return new ArrayList<>();
    }

    // LCA of generic Tree
    public static int findLCA(Node root, int tar1, int tar2){
        ArrayList<Integer> ntr1 = nodeToRootPath(root, tar1);
        ArrayList<Integer> ntr2 = nodeToRootPath(root, tar2);

        if(ntr1.size() == 0 || ntr2.size() == 0){
            System.out.println("No LCA");
            return -1;
        }

        int j = ntr2.size() - 1;
        int i = ntr1.size() - 1;
        while(j >= 0 && i>=0 && ntr1.get(i) == ntr2.get(j)){
            i--;
            j--;
        }

        j++;
        return ntr2.get(j);
    }


    // is Mirror
    public static boolean isMirror(Node n1, Node n2){
        if(n1.children.size() != n2.children.size() || n1.data != n2.data){
            return false;
        }
        int size = n1.children.size();
        for(int i=0; i<n1.children.size(); i++){
            Node child1 = n1.children.get(i);
            Node child2 = n2.children.get(size - i - 1);

            if(isMirror(child1, child2) == false){
                return false;
            }
        }

        return true;
    }

    // isSymmetric 
    public static boolean isSymmetric(Node root){
        
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
        // int[] treeData = {10,20,50,-1,-1,30,60,-1,70,110,-1,120,-1,-1,80,-1,-1,40,90,-1,100};
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
        // displayTree(root);
        // Node ModifiedRootTail = flattenGenericTree2(root);


        // System.out.println("Printing Modified Tree ===================");
        // displayTree(root);

        // System.out.println(nodeToRootPath(root, 120));
        System.out.println(findLCA(root,30,115));
    }
}

