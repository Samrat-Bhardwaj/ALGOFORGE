class TreeNode {
    int data;

    TreeNode left;
    TreeNode right;

    public TreeNode(int data){
        this.data = data;
    }
}

class Main {
    public static TreeNode buildBST(int[] arr, int si, int ei){
        if(si > ei){
            return null;
        }
        
        int mid = (si+ei)/2;

        TreeNode root = new TreeNode(arr[mid]);

        root.left = buildBST(arr, si, mid-1);
        root.right = buildBST(arr, mid+1, ei);

        return root;
    }

    public static void display(TreeNode root){
        if(root == null) return;

        String nodeStructure = (root.left == null ? "." : root.left.data) + " <- " + root.data + " -> " + (root.right == null ? "." :  root.right.data);

        System.out.println(nodeStructure);
        display(root.left);
        display(root.right);
    }


    public static void main(String[] args){
        int[] arr = {1,2,3,5,8,10,12,13,15,18};

        TreeNode root = buildBST(arr,0,arr.length-1);
        display(root);
    }
}