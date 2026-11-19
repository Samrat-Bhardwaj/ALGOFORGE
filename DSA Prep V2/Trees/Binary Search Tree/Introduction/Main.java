class Node {
    int data;
    Node left;
    Node right;

    public Node(int data){
        this.data = data;
    }
}

class Main {
    public static Node constructTree(int[] data, int si, int ei){
        if(si > ei){
            return null;
        }

        int mid = (si + ei)/2;

        Node root = new Node(data[mid]);

        root.left = constructTree(data, si, mid - 1);
        root.right = constructTree(data, mid + 1, ei);

        return root;
    }

    public static void display(Node root){
        if(root == null){
            return;
        }

        String current = (root.left == null ? "." : root.left.data) + " <- " + root.data + " -> " 
                            + (root.right == null ? "." : root.right.data);

        System.out.println(current);

        display(root.left);
        display(root.right);
    }

    public static void main(String[] args){
        int[] data = {1,7,11,15,16,25,31,39,45,50,59};

        Node root = constructTree(data,0,data.length-1);

        display(root);
    }
}