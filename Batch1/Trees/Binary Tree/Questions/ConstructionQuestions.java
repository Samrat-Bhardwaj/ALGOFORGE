class ConstructionQuestions {
    // leetcode 105, build from preorder and inorder
    
    public TreeNode buildTree(int[] preorder, int psi, int pei, int[] inorder, int isi, int iei){
        if(psi > pei){
            return null;
        }

        TreeNode root = new TreeNode(preorder[psi]);

        int leftElements = 0;
        int rootIdx = isi;
        while(rootIdx <= iei && inorder[rootIdx] != root.val){
            rootIdx++;
            leftElements++;
        }

        root.left = buildTree(preorder, psi + 1, psi + leftElements, inorder, isi, rootIdx-1);
        root.right = buildTree(preorder, psi + leftElements + 1, pei, inorder, rootIdx + 1, iei);

        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        return buildTree(preorder, 0, n-1, inorder, 0, n-1);
    }

    // leetcode 106 =====================================================================
    public TreeNode buildTreeFromInPost(int[] inorder, int isi, int iei, int[] postorder, int psi, int pei){
        if(isi > iei){
            return null;
        }

        TreeNode root = new TreeNode(postorder[pei]);
        int rootIdx = isi;

        while(inorder[rootIdx] != root.val){
            rootIdx++;
        }

        int leftTreeElements = rootIdx - isi;

        root.left = buildTreeFromInPost(inorder, isi, rootIdx-1, postorder, psi, psi + leftTreeElements - 1);
        root.right = buildTreeFromInPost(inorder,rootIdx+1, iei, postorder, psi+leftTreeElements, pei-1);

        return root;
    }
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int n = inorder.length;

        return buildTreeFromInPost(inorder, 0, n-1, postorder, 0, n-1);
    }


    // leetcode 889 =======================================================
    public TreeNode buildTreeFromPrePost(int[] preorder, int prsi, int prei, int[] postorder, int posi, int poei){
        if(prsi > prei){
            return null;
        }

        if(prsi == prei){
            return new TreeNode(preorder[prsi]);
        }

        TreeNode root = new TreeNode(preorder[prsi]);
        int idx = posi;

        while(postorder[idx] != preorder[prsi + 1]){ // looking for root of left tree in postOrder
            idx++;
        }

        int subTreeElements = idx - posi + 1;

        root.left = buildTreeFromPrePost(preorder, prsi + 1, prsi + subTreeElements, postorder, posi, posi + subTreeElements - 1);
        root.right = buildTreeFromPrePost(preorder, prsi+subTreeElements+1, prei, postorder, posi + subTreeElements, poei - 1);

        return root;
    }

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        int n = preorder.length;

        return buildTreeFromPrePost(preorder,0,n-1, postorder,0,n-1);
    }


























    public static void main(String[] args){

    }
}