import java.util.Stack;
import java.util.ArrayList;
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

        Node root = new Node(dataArray[0]);

        st.push(root);
        for(int i=1; i<dataArray.length; i++){
            int data = dataArray[i];

            if(data == -1){
                st.pop();
            } else {
                Node newNode = new Node(data);

                Node topNode = st.peek();
                topNode.children.add(newNode);

                st.push(newNode);
            }
        }

        return root;
    }

    public static void displayTree(Node root){
        System.out.print(root.data + " -> ");

        for(int i=0; i<root.children.size(); i++){
            Node child = root.children.get(i);

            System.out.print(child.data + ",");
        }
        System.out.println(".");

        // display all subTrees
        for(Node child: root.children){
            displayTree(child);
        }
    }

    public static int sizeOfTree(Node root){
        int totalSize = 0;

        for(Node child: root.children){
            totalSize += sizeOfTree(child);
        }

        return totalSize + 1; // adding +1 for root
    }
    

    public static void main(String[] args){
        int[] dataArray = {10,20,50,-1,60,-1,-1,30,70,110,-1,120,-1,-1,-1,40,80,-1,90,-1,100,-1,-1,-1};

        Node root = constructTree(dataArray);

        // displayTree(root);
        System.out.println(sizeOfTree(root));
    }
}