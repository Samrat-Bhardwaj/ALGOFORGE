class Questions {
    // Get K level down ============================

    public static TreeNode findTargetNode(TreeNode root, int tar){
        if(root == null){
            return null;
        }

        if(root.val == tar){
            return root;
        }

        TreeNode leftAns = findTargetNode(root.left, tar);
        if(leftAns != null) return leftAns;

        TreeNode rightAns = findTargetNode(root.right, tar);
        if(rightAns != null) return rightAns;
        
        return null;
    }

    public static void getKLevelDown(TreeNode node, int K, ArrayList<TreeNode> ans){
        if(node == null){
            return;
        }

        if(K==0){
            ans.add(node);
            return;
        }

        getKLevelDown(node.left, K-1, ans);
        getKLevelDown(node.right, K-1, ans);
    }

    public static ArrayList<TreeNode> getKLevelDown(TreeNode root, int tar, int K){
        TreeNode targetNode = findTargetNode(root, tar);

        ArrayList<TreeNode> ans = new ArrayList<>();
        getKLevelDown(targetNode,K,ans);
        return ans;
    }

    public static void main(String[] args){

    }
}


