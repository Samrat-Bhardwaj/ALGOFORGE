import java.util.ArrayList;
import java.util.Stack;

class Node {
    int data;
    ArrayList<Node> children;

    public Node(int data){
        this.data = data;
        children = new ArrayList<>();
    }
}

class Main {

    public static Node constructTree(int[] dataArray){
        Stack<Node> st = new Stack<>();
        Node root = null;

        for(int i=0; i<dataArray.length; i++){
            if(dataArray[i] == -1){
                st.pop();
            } else {

                Node newNode = new Node(dataArray[i]);
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

    public static void display(Node root){
        System.out.print(root.data + " -> ");

        for(Node child: root.children){
            System.out.print(child.data + ", ");
        }

        System.out.println();

        // Recursively printing child subTrees
        for(Node child: root.children){
            display(child);
        }
    }

    public static int getSize(Node root){
        int totalSize = 0;
        
        for(Node child: root.children){
            totalSize += getSize(child);
        }

        return totalSize + 1;
    }

    public static int getMaximum(Node root){
        int treeMax = root.data;

        for(Node child: root.children){
            int childMax = getMaximum(child);

            treeMax = Math.max(treeMax, childMax);
        }

        return treeMax;
    }

    public static int getHeight(Node root){
        if(root == null){
            return -1;
        }

        int treeHeight = -1;

        for(Node child: root.children){
            int childHeight = getHeight(child);

            treeHeight = Math.max(treeHeight, childHeight);
        }

        return treeHeight + 1;
    }

    // Mirror a generic tree
    public static Node makeMirror(Node root){
        int childrenSize = root.children.size();

        int left = 0;
        int right = childrenSize - 1;

        while(left <= right){
            // left node should be mirrored
            Node leftMirror = makeMirror(root.children.get(left));

            // right node should be mirrored
            Node rightNode = root.children.get(right);
            Node rightMirror = left < right ? makeMirror(rightNode) : rightNode;

            // swap nodes at position left and right
            root.children.set(left, rightMirror);
            root.children.set(right, leftMirror);

            left++;
            right--;
        }

        return root;
    }

    // Remove leaf Nodes (Only Preorder will work) ===========================
    public static void removeLeafNodes(Node root){

        for(int i=root.children.size()-1; i>=0; i--){
            Node child = root.children.get(i);

            if(child.children.size() == 0){
                root.children.remove(i);
            }
        }

        for(Node child: root.children){
            removeLeafNodes(child);
        }
    }

    // Linearize a GT ========================================
    public static Node findTail(Node node){ // node is already linearised
        Node temp = node;

        while(temp.children.size() > 0){
            temp = temp.children.get(0);
        }

        return temp;
    }

    public static Node lineariseGT(Node root){
        for(Node child: root.children){
            lineariseGT(child);
        }

        while(root.children.size() > 1){
            int childrenSize = root.children.size();

            Node lastChild = root.children.get(childrenSize - 1);
            Node secondLastChild = root.children.get(childrenSize - 2);

            Node tail = findTail(secondLastChild);

            root.children.remove(childrenSize-1); // remove last child

            tail.children.add(lastChild); // add last child to tail of second last child
        }

        return root;
    }

    // Linearize a GT ========================================
    public static Node lineariseGT_better(Node root){
        if(root.children.size() == 0){
            return root;
        }

        Node lastChildTail = lineariseGT_better(root.children.get(root.children.size() - 1));

        while(root.children.size() > 1){
            int childrenSize = root.children.size();

            Node lastChild = root.children.get(childrenSize - 1);
            Node secondLastChild = root.children.get(childrenSize - 2);

            Node secondLastChildTail = lineariseGT_better(secondLastChild);

            root.children.remove(childrenSize-1); // remove last child
            secondLastChildTail.children.add(lastChild); // add last child to tail of second last child
        }

        return lastChildTail;
    }

    public static void lineariseTree(Node root){

        //after this loop there will only be one child

        for(int i=root.children.size()-2; i>=0; i--){

            Node lastnode = root.children.remove(i+1);

            root.children.get(root.children.size()-1).children.add(lastnode);

        }

        

        //if child is there

        if(root.children.size()>0)
            lineariseTree(root.children.get(0));

    }

    // Find in generic tree
    public static boolean find(Node root, int target){
        if(root.data == target){
            return true;
        }

        for(Node child: root.children){
            boolean targetExists = find(child, target);

            if(targetExists){ // no need to ask next child
                return true;
            }
        }

        return false;
    }

    // Find node to root path
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



























    public static void main(String[] args){
        // int[] dataArray = {10,20,50,-1,60,-1,-1,30,70,-1,-1,40,80,-1,90,110,-1,120,-1,-1,100,-1,-1,-1};
        int[] dataArray = {10,20,50,-1,60,-1,-1,30,70,-1,80,100,-1,110,-1,-1,90,-1,-1,40,120,-1,130,-1,-1,-1};

        Node root = constructTree(dataArray);

        // displayTree(root);
        System.out.println(nodeToRootPath(root,12));
    }
}