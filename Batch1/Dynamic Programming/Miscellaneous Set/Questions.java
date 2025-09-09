class Questions {
    // House robber (Leetcode 198) =======================
    public int rob(int[] nums) {
        int n = nums.length;
        int[] inc = new int[n];
        int[] exc = new int[n];

        inc[0] = nums[0];
        exc[0] = 0;

        for(int i=1; i<n; i++){
            inc[i] = nums[i] + exc[i-1];
            exc[i] = Math.max(inc[i-1], exc[i-1]);
        }

        return Math.max(inc[n-1], exc[n-1]);
    }

    // House robber but in O(1) space
    public int rob(int[] nums) {
        int n = nums.length;
    
        int inc = nums[0];
        int exc = 0;

        for(int i=1; i<n; i++){
            int new_inc = nums[i] + exc; 
            int new_exc = Math.max(inc, exc);

            inc = new_inc;
            exc = new_exc;
        }

        return Math.max(inc, exc);
    }

    // House robber 2 (Leetcode 213) ====================================================
    public int robIndices(int[] nums, int si, int ei) {
        int n = nums.length;
    
        int inc = nums[si];
        int exc = 0;

        for(int i=si+1; i<=ei; i++){
            int new_inc = nums[i] + exc; 
            int new_exc = Math.max(inc, exc);

            inc = new_inc;
            exc = new_exc;
        }

        return Math.max(inc, exc);
    }

    public int rob(int[] nums) {
        if(nums.length == 1) return nums[0];
        int zeroIncluded = robIndices(nums,0,nums.length-2);
        int zeroExcluded = robIndices(nums,1,nums.length-1);

        return Math.max(zeroIncluded, zeroExcluded);
    }

    // Count square submatrices (Leetcode 1277) ========================================
    public int countSquares(int[][] matrix) {
        int n = arr.length;
        int m = arr[0].length;

        int[][] dp = new int[n][m];
        int count = 0;

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(i==0 || j==0){
                    dp[i][j] = matrix[i][j];
                } else if(matrix[i][j] == 1){
                    dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i-1][j-1], dp[i][j-1])) + 1;
                }

                count += dp[i][j];
            }
        }

        return count;
    }

    // Maximal square (leetcode 221) ==========================================
    public int maximalSquare(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        int[][] dp = new int[n][m];
        int maxSide = 0;

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(i==0 || j==0){
                    dp[i][j] = matrix[i][j] - '0';
                } else if(matrix[i][j] == '1'){
                    dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i-1][j-1], dp[i][j-1])) + 1;
                }

                maxSide = Math.max(dp[i][j], maxSide);
            }
        }

        return maxSide*maxSide;
    }

    // Count submatrices with All ones (Leetcode 1504) ===============================
    public int numSubmat(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        int[] heightSoFar = new int[m];
        int count = 0;

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(mat[i][j] == 0){
                    heightSoFar[j] = 0;
                    continue;
                } else {
                    heightSoFar[j]++;
                }

                int minHeight = n;
                for(int k=j; k>=0; k--){ // len = 1 when k=j, len = 2 when k =j-1 and so on
                    minHeight = Math.min(minHeight, heightSoFar[k]);

                    count += minHeight;
                }
            }
        }

        return count;
    }




















    public static void main(String[] args){

    }
}