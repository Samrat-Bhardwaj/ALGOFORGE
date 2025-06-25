class Node {
    int data;
    Node left;
    Node right;

    public Node(int data){
        this.data = data;
    }
}

class Main {
    public static int sum(Node root){
        if(root == null) return 0;

        return sum(root.left) + sum(root.right) + root.data;
    }

    public static int minOfBST(Node root){
        if(root == null) return Integer.MAX_VALUE;
        if(root.left == null) return root.data;
        
        return minOfBST(root.left);
    }

    public static int minOfBST_iterative(Node root){
        if(root == null) return -1;

        Node temp = root;
        while(temp.left != null){
            temp = temp.left;
        }

        return temp.data;
    }

    public static int maxOfBST(Node root){
        if(root == null) return Integer.MIN_VALUE;
        if(root.right == null) return root.data;

        return maxOfBST(root.right);
    }

    public static int maxOfBST_iterative(Node root){
        if(root == null) return -1;
        Node temp = root;

        while(temp.right != null){
            temp = temp.right;
        }

        return temp.data;
    }

    public TreeNode searchBST(TreeNode root, int val) {
        
    }























    public static Node buildBST(int[] arr, int si, int ei){
        if(si > ei){
            return null;
        }

        int mid = (si + ei)/2;

        Node root = new Node(arr[mid]);

        root.left = buildBST(arr, si, mid - 1);
        root.right = buildBST(arr, mid + 1, ei);

        return root;
    }

    public static void display(Node root){
        if(root == null){
            return;
        }

        String currNodeStucture = (root.left == null ? "." : root.left.data) + " <- " + root.data + " -> " + (root.right == null ? "." : root.right.data);
        System.out.println(currNodeStucture);

        display(root.left);
        display(root.right);
    }

    public static void main(String[] args){
        int[] arr = {2,5,9,11,34,45,55,61,69,71,85};

        Node root = buildBST(arr, 0, arr.length-1);

        // display(root);
        System.out.println(minOfBST(root));
        System.out.println(minOfBST_iterative(root));
        System.out.println(maxOfBST(root));
        System.out.println(maxOfBST_iterative(root));
    }
}