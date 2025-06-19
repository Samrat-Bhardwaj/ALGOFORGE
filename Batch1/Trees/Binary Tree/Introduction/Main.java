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
    public static void main(String[] args){
        Integer[] arr = {10,20,11,null,15,null,null,12,14,null,null,null,30,null,13,16,null,null,17,null,null};

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

            st.push(new Pair(newNode, 1));
        }
    }
}