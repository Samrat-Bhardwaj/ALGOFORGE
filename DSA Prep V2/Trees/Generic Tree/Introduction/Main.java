import java.util.Stack;
import java.util.ArrayList;
import java.util.LinkedList;
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

    public static int height(Node root){
        int h = -1;

        for(Node child: root.children){
            h = Math.max(height(child), h);
        }

        return h + 1;
    }

    public static void traverse(Node root){
        System.out.println("Pre-order " + root.data); 

        for(int i=0; i<root.children.size(); i++){
            Node child = root.children.get(i);
            traverse(child);

            if(i < root.children.size() - 1)
                System.out.println("In-order " + root.data);
        }
         
        System.out.println("Post-order " + root.data); 
    }

    public static void levelOrder(Node root){
        LinkedList<Node> que = new LinkedList<>();
        que.addLast(root);

        while(que.size() > 0){
            Node front = que.removeFirst();

            System.out.print(front.data + ",");

            for(Node child : front.children){
                que.addLast(child);
            }
        }
    }

    public static void levelOrderLineWise(Node root){
        LinkedList<Node> que = new LinkedList<>();
        que.addLast(root);
        que.addLast(null); // marker

        while(que.size() > 0){
            Node front = que.removeFirst();

            if(front == null){
                System.out.println(".");

                if(que.size() > 0){
                    que.addLast(null);
                }

            } else {
                System.out.print(front.data + ",");

                for(Node child: front.children){
                    que.addLast(child);
                }
            }
        }
    }

    public static void levelOrderLineWise2(Node root){
        LinkedList<Node> que = new LinkedList<>();
        que.addLast(root);
        int level = 1;

        while(que.size() > 0){
            int size = que.size();
            System.out.print("Level: " + level + " -> ");

            while(size-- > 0){
                Node front = que.removeFirst();

                System.out.print(front.data + ",");

                for(Node child : front.children){
                    que.addLast(child);
                }
            }

            System.out.println();
            level++;
        }
    }
    

    public static void main(String[] args){
        int[] dataArray = {10,20,50,-1,60,-1,-1,30,70,110,-1,120,-1,-1,-1,40,80,-1,90,-1,100,-1,-1,-1};

        Node root = constructTree(dataArray);

        // displayTree(root);
        // System.out.println(height(root));
        levelOrderLineWise2(root);
    }
}