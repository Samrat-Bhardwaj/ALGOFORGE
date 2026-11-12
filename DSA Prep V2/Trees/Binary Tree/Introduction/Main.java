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
    public static Node buildTree(Integer[] arr){
        Stack<Pair> st = new Stack<>();

        Node root = new Node(arr[0]);
        st.push(new Pair(root,0));

        for(int i=1; i<arr.length; i++){
            Integer ele = arr[i];

            Pair topPair = st.peek();

            if(ele == null){
                if(topPair.state == 0){
                    topPair.state++;
                } else {
                    st.pop();
                }
            } else {
                Node newNode = new Node(ele);

                if(topPair.state == 0){
                    topPair.node.left = newNode;
                    topPair.state++;
                } else {
                    topPair.node.right = newNode;
                    st.pop(); // left and right both attached, pop
                }

                st.push(new Pair(newNode,0));
            }
        }

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

    public static int size(Node root){
        if(root == null){
            return 0;
        }
        
        int totalSize = 0;

        totalSize += size(root.left);
        totalSize += size(root.right);

        return totalSize + 1;
    }

    public static void main(String[] args){
        Integer[] arr = {10,11,13,null,16,null,null,14,17,null,null,null,12,15,null,null,16,null,null};

        Node root = buildTree(arr);
        display(root);
    }
}