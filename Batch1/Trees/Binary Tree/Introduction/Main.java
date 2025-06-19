import java.util.Stack;
class Node {
    int data;
    Node left;
    Node right;

    public Node(int data){
        this.data = data;
    }
}

class Pair {
    Node node;
    int state;

    public Pair(Node node, int state){
        this.node = node;
        this.state = state;
    }
}


class Main {
    public static int size(Node root){
        if(root == null){
            return 0;
        }

        int leftSize = size(root.left);
        int rightSize = size(root.right);

        return leftSize + rightSize + 1;
    }

    public static int sum(Node root){
        if(root == null){
            return 0;
        }

        int leftSum = sum(root.left);
        int rightSum = sum(root.right);

        return leftSum + rightSum + root.data;
    }

    public static int maxOfBinaryTree(Node root){
        if(root == null){
            return Integer.MIN_VALUE;
        }

        int leftMax = maxOfBinaryTree(root.left);
        int rightMax = maxOfBinaryTree(root.right);

        return Math.max(Math.max(leftMax, rightMax), root.data);
    }

    public static int height(Node root){
        if(root == null){
            return -1;
        }

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static void display(Node root){
        if(root == null){
            return;
        }

        String currNodeStucture = (root.left == null ? "." : root.left.data) + " <- " + root.data + " -> " + (root.right == null ? "." : root.right.data);
        // String currNodeStucture = "";
        // if(root.left != null){
        //     currNodeStucture += root.left.data + " <- ";
        // }

        // currNodeStucture += root.data;

        // if(root.right != null){
        //     currNodeStucture += " -> " + root.right.data;
        // }

        System.out.println(currNodeStucture);

        display(root.left);
        display(root.right);
    }

    public static Node constructTree(Integer[] arr){
        Node root = null;
        Stack<Pair> st = new Stack<>();

        for(int i=0; i<arr.length; i++){
            Integer ele = arr[i];

            Node newNode = null;
            if(ele != null){
                newNode = new Node(ele);
            }

            if(st.size() == 0){
                root = newNode;
            } else if(st.peek().state == 1){
                st.peek().node.left = newNode;
                st.peek().state++;
            } else {
                st.peek().node.right = newNode;
                st.pop();
            }
            if(newNode != null){
                st.push(new Pair(newNode, 1));
            }
        }

        return root;
    }

    public static void main(String[] args){
        Integer[] arr = {10,20,11,null,15,null,null,12,14,null,null,null,30,null,13,16,null,null,17,null,null};
        Node root = constructTree(arr);
        display(root);
    }
}