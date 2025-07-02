class Questions {
    // leetcode 110 =======================================
    class Pair {
        int height;
        int balancingFactor; // redundant
        boolean isBalanced;

        public Pair(){}

        public Pair(int height, int balancingFactor,boolean isBalanced){
            this.height = height;
            this.balancingFactor = balancingFactor;
            this.isBalanced = isBalanced;
        }
    }

    public Pair calculateHeightAndBF(TreeNode root){
        if(root == null){
            return new Pair(-1,0,true);
        }

        Pair lpair = calculateHeightAndBF(root.left);
        Pair rpair = calculateHeightAndBF(root.right);

        Pair myPair = new Pair();

        myPair.height = Math.max(lpair.height, rpair.height) + 1;
        myPair.balancingFactor = Math.abs(lpair.height - rpair.height);

        myPair.isBalanced = myPair.balancingFactor < 2 && lpair.isBalanced && rpair.isBalanced;

        return myPair;
    }

    public boolean isBalanced(TreeNode root) {
       return calculateHeightAndBF(root).isBalanced;
    }
}