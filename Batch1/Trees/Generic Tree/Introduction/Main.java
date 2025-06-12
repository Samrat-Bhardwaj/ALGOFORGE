import java.util.ArrayList;
class Node {
    int data;
    ArrayList<Node> children;

    public Node(){}

    public Node(int data){
        this.data = data;
        this.children = new ArrayList<>();
    }
}

class Main {
    public static void displayTree(Node root){

    }
    
    public static void main(String[] args){
        int[] treeData = {10,20,80,-1,-1,30,50,-1,60,-1,-1,40,90,-1,100,120,-1,130,-1,-1,110,-1,-1,-1};

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

        displayTree(root);
    }
}