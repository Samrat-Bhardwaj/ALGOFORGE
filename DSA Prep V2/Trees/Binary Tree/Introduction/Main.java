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

        String current = "";

        current += root.left == null ? "." : root.left.data;
        current += " <- ";
        current += root.data;
        current += " -> ";
        current += root.right == null ? "." : root.right.data;

        System.out.println(current);
        
        display(root.left);
        display(root.right);
    }

    public static void main(String[] args){
        Integer[] arr = {10,11,13,null,16,null,null,14,17,null,null,null,12,15,null,null,16,null,null};

        Node root = buildTree(arr);
        display(root);
    }
}