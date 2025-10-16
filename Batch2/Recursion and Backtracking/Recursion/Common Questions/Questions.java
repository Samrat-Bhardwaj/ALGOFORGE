class Questions {
    public static void printPermutations(String str, String asf){
        if(str.length() == 0){
            System.out.println(asf);
            return;
        }

        for(int i=0; i<str.length(); i++){
            char currChar = str.charAt(i);

            String leftRemaining = str.substring(0,i);
            String rightRemaining = str.substring(i+1);

            String remString = leftRemaining + rightRemaining;

            printPermutations(remString, asf + currChar);
        }
    }

    public static int findMaxPathSum(int[][] mat, int row, int col){
        if(col >= mat[0].length || row >= mat.length){
            return Integer.MIN_VALUE;
        }

        if(row == mat.length - 1 && col == mat[0].length - 1){
            return mat[row][col];
        }
        
        int rightPathSum = findMaxPathSum(mat, row, col+1);
        int downPathSum = findMaxPathSum(mat, row+1, col);

        int maxPathSum = mat[row][col] + Math.max(rightPathSum, downPathSum);

        return maxPathSum;
    }

    public static void main(String[] args){
        // printPermutations("abc","");
        int[][] mat = {{10,-11,4},{-13,19,6}};
        System.out.println(findMaxPathSum(mat,0,0));
    }
}