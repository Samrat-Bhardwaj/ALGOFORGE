import java.util.ArrayList;
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

    public static void display(TreeNode root){
        System.out.print(root.data + " -> ");

        for(TreeNode child: root.children){
            System.out.print(child.data + ", ");
        }

        System.out.println();

        // Recursively printing child subTrees
        for(TreeNode child: root.children){
            display(child);
        }
    }

    public static int getSize(TreeNode root){
        int totalSize = 0;
        
        for(TreeNode child: root.children){
            totalSize += getSize(child);
        }

        return totalSize + 1;
    }

    public static int getMaximum(TreeNode root){
        int treeMax = root.data;

        for(TreeNode child: root.children){
            int childMax = getMaximum(child);

            treeMax = Math.max(treeMax, childMax);
        }

        return treeMax;
    }

    public static int getHeight(TreeNode root){
        if(root == null){
            return -1;
        }

        int treeHeight = -1;

        for(TreeNode child: root.children){
            int childHeight = getHeight(child);

            treeHeight = Math.max(treeHeight, childHeight);
        }

        return treeHeight + 1;
    }

    // Mirror a generic tree
    public static TreeNode makeMirror(TreeNode root){
        int childrenSize = root.children.size();

        int left = 0;
        int right = childrenSize - 1;

        while(left <= right){
            // left node should be mirrored
            TreeNode leftMirror = makeMirror(root.children.get(left));

            // right node should be mirrored
            TreeNode rightNode = root.children.get(right);
            TreeNode rightMirror = left < right ? makeMirror(rightNode) : rightNode;

            // swap nodes at position left and right
            root.children.set(left, rightMirror);
            root.children.set(right, leftMirror);

            left++;
            right--;
        }

        return root;
    }

    // Remove leaf Nodes (Only Preorder will work) ===========================
    public static void removeLeafNodes(TreeNode root){

        for(int i=root.children.size()-1; i>=0; i--){
            TreeNode child = root.children.get(i);

            if(child.children.size() == 0){
                root.children.remove(i);
            }
        }

        for(TreeNode child: root.children){
            removeLeafNodes(child);
        }
    }

    // Linearize a GT ========================================
    public static TreeNode findTail(TreeNode node){ // node is already linearised
        TreeNode temp = node;

        while(temp.children.size() > 0){
            temp = temp.children.get(0);
        }

        return temp;
    }

    public static TreeNode lineariseGT(TreeNode root){
        for(TreeNode child: root.children){
            lineariseGT(child);
        }

        while(root.children.size() > 1){
            int childrenSize = root.children.size();

            TreeNode lastChild = root.children.get(childrenSize - 1);
            TreeNode secondLastChild = root.children.get(childrenSize - 2);

            TreeNode tail = findTail(secondLastChild);

            root.children.remove(childrenSize-1); // remove last child

            tail.children.add(lastChild); // add last child to tail of second last child
        }

        return root;
    }

    // Linearize a GT ========================================
    public static TreeNode lineariseGT_better(TreeNode root){
        if(root.children.size() == 0){
            return root;
        }

        TreeNode lastChildTail = lineariseGT_better(root.children.get(root.children.size() - 1));

        while(root.children.size() > 1){
            int childrenSize = root.children.size();

            TreeNode lastChild = root.children.get(childrenSize - 1);
            TreeNode secondLastChild = root.children.get(childrenSize - 2);

            TreeNode secondLastChildTail = lineariseGT_better(secondLastChild);

            root.children.remove(childrenSize-1); // remove last child
            secondLastChildTail.children.add(lastChild); // add last child to tail of second last child
        }

        return lastChildTail;
    }

    public static void lineariseTree(TreeNode root){

        //after this loop there will only be one child

        for(int i=root.children.size()-2; i>=0; i--){

            TreeNode lastnode = root.children.remove(i+1);

            root.children.get(root.children.size()-1).children.add(lastnode);

        }

        

        //if child is there

        if(root.children.size()>0)
            lineariseTree(root.children.get(0));

    }

    public static boolean find(TreeNode root, int target){
        
    }




























    public static void main(String[] args){
        // int[] dataArray = {10,20,50,-1,60,-1,-1,30,70,-1,-1,40,80,-1,90,110,-1,120,-1,-1,100,-1,-1,-1};
        int[] dataArray = {10,20,50,-1,60,-1,-1,30,70,-1,80,100,-1,110,-1,-1,90,-1,-1,40,120,-1,130,-1,-1,-1};

        TreeNode root = constructTree(dataArray);

        display(root);
        // System.out.println(getHeight(root));
        System.out.println(" ================== After Question ======================= ");

        lineariseTree(root);

        display(root);
    }
}