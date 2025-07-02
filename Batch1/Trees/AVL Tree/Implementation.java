class Node {
    int data;
    Node left;
    Node right;

    int height;
    int balanceFactor;

    public Node(){}
    public Node(int data){
        this.data = data;
    }
}

class Implementation {
    // AVL Utils =====================================
    // update height and BF
    public static void updateHeightAndBF(Node root){
        int leftHeight = -1;
        int rightHeight = -1;

        if(root.left != null){
            leftHeight = root.left.height;
        }

        if(root.right != null){
            rightHeight = root.right.height;
        }

        root.height = Math.max(leftHeight, rightHeight) + 1;
        root.balanceFactor = leftHeight - rightHeight;
    }

    public static Node rightRotate(Node A){
        Node B = A.left;
        Node BKaRight = B.right;

        B.right = A;
        A.left = BKaRight;

        updateHeightAndBF(A);
        updateHeightAndBF(B);

        return B; // updated root
    }

    public static Node leftRotate(Node A){
        Node B = A.right;
        Node BKaLeft = B.left;

        B.left = A;
        A.right = BKaLeft;

        updateHeightAndBF(A);
        updateHeightAndBF(B);

        return B; // updated root
    }

    // rotate if not balanced
    public static Node balanceBST(Node root){
        updateHeightAndBF(root);
        // rotate if skewed
        if(root.balanceFactor == 2){ // left-left, left-right
            if(root.left.balanceFactor == 1){ // left-left
                return rightRotate(root); // right rotate
            } else { // left-right
                root.left = leftRotate(root.left); // left rotate (root.left)
                return rightRotate(root); // // right rotate root
            }
        } else if(root.balanceFactor == -2){ // right-right, right-left
            if(root.right.balanceFactor == -1){ // right-right
                return leftRotate(root); // left rotate
            } else { // right-left
                root.right = rightRotate(root.right); // // right rotate (root.right)
                return leftRotate(root); // // left rotate root
            }
        }

        return root;
    }

    // BST functions ==================================================
    public static Node addData(Node root, int val){
        if(root == null){
            return new Node(val);
        }

        if(root.data < val){
            root.right = addData(root.right, val);
        } else {
            root.left = addData(root.left, val);
        }

        return balanceBST(root);
    }

    public static int findLeftMost(Node root){
        Node temp = root;

        while(temp.left != null){
            temp = temp.left;
        }

        return temp.data;
    }

    public static Node deleteNode(Node root, int key){
        if(root == null) return null;
        
        if(root.data > key){
            root.left = deleteNode(root.left, key);
        } else if(root.data < key){
            root.right = deleteNode(root.right, key);
        } else {
            if(root.left == null && root.right == null){ // leaf node
                return null;
            } else if(root.left == null){ // one child
                return root.right;
            } else if(root.right == null){ // one child
                return root.left;
            } else { // both child
                // find just greater
                int justGreaterVal = findLeftMost(root.right);
                root.data = justGreaterVal;

                root.right = deleteNode(root.right, justGreaterVal);
            }
        }

        return balanceBST(root);
    }


    public static void display(Node root){
        if(root == null){
            return;
        }

        String currNodeStucture = (root.left == null ? "." : root.left.data) + " <- " + (root.data + "," + root.balanceFactor ) + " -> " + (root.right == null ? "." : root.right.data);
        System.out.println(currNodeStucture);

        display(root.left);
        display(root.right);
    }

    public static void main(String[] args){
        Node root = null;
        for(int i=0; i<10; i++){
           root = addData(root, i*10 + 3);
           display(root);

           System.out.println("===================================");
        }
    }
}