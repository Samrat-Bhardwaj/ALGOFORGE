import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

class TreeNode {
    int data;
    ArrayList<TreeNode> children;

    public TreeNode(int data){
        this.data = data;
        children = new ArrayList<>();
    }
}

class Main {

    public static TreeNode constructTree(int[] dataArray){
        Stack<TreeNode> st = new Stack<>();
        TreeNode root = null;

        for(int i=0; i<dataArray.length; i++){
            if(dataArray[i] == -1){
                st.pop();
            } else {

                TreeNode newNode = new TreeNode(dataArray[i]);
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

    // Traversing tree recursively ====================
    public static void traverse(TreeNode root){
        System.out.println("Preorder -> " + root.data);

        for(TreeNode child: root.children){
            System.out.println("Traversing on edge from " + root.data + " -> " + child.data);

            traverse(child);

            System.out.println("Traversing on edge from " + child.data + " -> " + root.data);
        }

        System.out.println("Postorder -> " + root.data);
    }

    // Level order traversal 
    public static void levelOrderTraversal(TreeNode root){
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);

        while(que.size() > 0){
            // remove node
            TreeNode frontNode = que.removeFirst();

            // print node data
            System.out.print(frontNode.data + ", ");

            // add node.children
            for(TreeNode child: frontNode.children){
                que.addLast(child);
            }
        }
    }

    // Level order linewise traversal ====================================
    public static void levelOrderLineWise(TreeNode root){
        LinkedList<TreeNode> mainQ = new LinkedList<>();
        LinkedList<TreeNode> childQ = new LinkedList<>();
        int level = 1;

        mainQ.addLast(root);

        while(mainQ.size() > 0){
            // remove everything from mainQ 
            TreeNode parent = mainQ.removeFirst();

            //then print
            System.out.print(parent.data + ",");

            //add children in childQ
            for(TreeNode child: parent.children){
                childQ.add(child);
            }

            
            if(mainQ.size() == 0){
                level++;
                System.out.println();
                mainQ = childQ; // every parent is removed, we start with childQ again
                childQ = new LinkedList<>(); // emptying childQ
            }
        }
    }

    // Using marker =====================
    public static void levelOrderLineWise2(TreeNode root){
        LinkedList<TreeNode> que = new LinkedList<>();
        que.add(root);
        que.add(null); // marker marking end of level

        int level = 1;

        while(que.size() > 0){
            TreeNode frontNode = que.removeFirst();

            if(frontNode != null){
                System.out.print(frontNode.data + ", ");

                for(TreeNode child: frontNode.children){
                    que.addLast(child);
                }

            } else {
                level++;
                System.out.println();

                if(que.size() > 0){
                    que.addLast(null); // marker for next end of level
                }
            }
        }
    }

    // Method 3 
    public static void levelOrderLineWise3(TreeNode root){
        LinkedList<TreeNode> que = new LinkedList<>();
        int level = 1;
        que.add(root);

        while(que.size() > 0){
            int currentLevelNodes = que.size();

            System.out.print("Level " + level + " => ");
            while(currentLevelNodes > 0){
                TreeNode frontNode = que.removeFirst();

                System.out.print(frontNode.data + ", ");

                for(TreeNode child: frontNode.children){
                    que.addLast(child);
                }

                currentLevelNodes--;
            }

            level++;
            System.out.println();
        }
    }

    // Zig zag traversal 
    public static void zigZagTraversal(TreeNode root){
        Stack<TreeNode> mainSt = new Stack<>();
        Stack<TreeNode> childSt = new Stack<>();
        int level = 1;
        mainSt.add(root);

        while(mainSt.size() > 0){
            TreeNode topNode = mainSt.pop();

            System.out.print(topNode.data + ", ");

            if(level%2 == 1){ // odd levels, insert from left to right
                
                for(int i=0; i<topNode.children.size(); i++){
                    TreeNode child = topNode.children.get(i);
                    childSt.add(child);
                }

            } else { // insert children from right to left
                
                for(int i=topNode.children.size()-1; i>=0; i--){
                    TreeNode child = topNode.children.get(i);
                    childSt.add(child);
                }
            }

            if(mainSt.size() == 0){
                level++;
                System.out.println();
                mainSt = childSt;
                childSt = new Stack<>();
            }
        }
    }




























    public static void main(String[] args){
        // int[] dataArray1 = {10,20,50,-1,60,-1,-1,30,-1,40,80,-1,90,-1,100,-1,-1,-1};
        int[] dataArray2 = {10,20,50,-1,60,-1,-1,30,70,-1,-1,40,80,-1,90,110,-1,120,-1,-1,100,-1,-1,-1};


        // TreeNode root = constructTree(dataArray1);
        TreeNode root2 = constructTree(dataArray2);

        // traverse(root);
        // levelOrderLineWise3(root2); 
        zigZagTraversal(root2);
    }
}