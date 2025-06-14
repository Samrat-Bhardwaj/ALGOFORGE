import java.util.ArrayList;
import java.util.Stack;
import java.util.LinkedList;
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
    // Euler traversal of tree
    public static void eulerTraversal(Node root){
        System.out.println("Preorder -> " + root.data);

        for(Node child: root.children){
            System.out.println("Taking edge from " + root.data + " -> " + child.data);

            eulerTraversal(child);

            System.out.println("Taking edge from " + child.data + " -> " + root.data);
        }


        System.out.println("Postorder -> " + root.data);
    }


    // level order traversal =====================================================
    public static void levelOrderTraversal(Node root){
        LinkedList<Node> que = new LinkedList<>();

        que.addLast(root);

        while(que.size() > 0){
            Node front = que.removeFirst();
            System.out.print(front.data + ",");

            for(Node child: front.children){
                que.addLast(child);
            }
        }
    }


    // mainQ, childQ
    public static void levelOrderLineWiseTraversal(Node root){
        LinkedList<Node> mainQ = new LinkedList<>();
        LinkedList<Node> childQ = new LinkedList<>();

        mainQ.addLast(root);
        int level = 1;

        while(mainQ.size()>0){
            Node mainQueNode = mainQ.removeFirst();

            System.out.print(mainQueNode.data + ",");

            for(Node child: mainQueNode.children){
                childQ.add(child);
            }

            if(mainQ.size() == 0 && childQ.size() > 0){
                System.out.println("level " + level + " end.");
                level++;
                mainQ = childQ;
                childQ = new LinkedList<>();
            }
        }
    }

    // using marker
    public static void levelOrderLineWiseTraversal2(Node root){
        LinkedList<Node> que = new LinkedList<>();

        que.addLast(root);
        que.addLast(null); //marker
        int level = 1;

        while(que.size() > 1){ // marker + atleast one element
            Node front = que.removeFirst();

            if(front == null){
                System.out.println();
                level++;
                que.addLast(null);
            } else {
                System.out.print(front.data + ",");

                for(Node child: front.children){
                    que.addLast(child);
                }
            }
        }
    }

    // using count 
    public static void levelOrderLineWiseTraversal3(Node root){
        LinkedList<Node> que = new LinkedList<>();

        que.addLast(root);
        int level = 1;

        while(que.size() > 0){ // marker + atleast one element
            int countOfCurrLevel = que.size();

            while(countOfCurrLevel-- > 0){
                Node front = que.removeFirst();
                System.out.print(front.data + ",");

                for(Node child: front.children){
                    que.addLast(child);
                }
            }

            System.out.println();
            level++;
        }
    }

    // zig-zag traversal 
    public static void zigZagTraversal(Node root){
        Stack<Node> mainSt = new Stack<>();
        Stack<Node> childSt = new Stack<>();

        int level = 1;
        mainSt.push(root);

        while(mainSt.size() > 0){
            Node top = mainSt.pop();

            System.out.print(top.data + ",");

            if(level%2 == 1){
                for(int i=0; i<top.children.size(); i++){
                    Node child = top.children.get(i);
                    childSt.push(child);
                }
            } else {
                for(int i=top.children.size()-1; i>=0; i--){
                    Node child = top.children.get(i);
                    childSt.push(child);
                }
            }

            if(mainSt.size() == 0 && childSt.size() > 0){
                System.out.println();
                level++;
                mainSt = childSt;
                childSt = new Stack<>();
            }
        }
    }





    public static void main(String[] args){
        // int[] treeData = {10,20,80,-1,-1,30,50,-1,60,-1,-1,40,90,-1,100,120,-1,130,-1,-1,110,-1,-1,-1};
        // int[] treeData = {10,20,50,-1,-1,30,60,-1,70,110,-1,120,-1,-1,80,-1,-1,40,90,-1,100,-1,-1,-1};
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

        zigZagTraversal(root);
    }
}