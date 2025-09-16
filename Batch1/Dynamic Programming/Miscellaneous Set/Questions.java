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

    // Cherry pickup 2 =================================================
    // Leetcode 1463 ==============================================
    public int cherryPickup_rec(int row, int col1, int col2, int[][] grid, int[][][] dp, int n, int m){
        int cherries = 0;
        if(col1 == col2){
            cherries = grid[row][col1];
        } else {
            cherries = grid[row][col1] + grid[row][col2];
        }

        if(row == n-1){
            return cherries;
        }

        if(dp[row][col1][col2] != -1) return dp[row][col1][col2];

        int maxAns = 0;
        for(int nextR1Pos = col1-1; nextR1Pos <= col1+1; nextR1Pos++){
            for(int nextR2Pos = col2-1; nextR2Pos <= col2+1; nextR2Pos++){
                if(nextR1Pos >=0 && nextR1Pos < m && nextR2Pos >=0 && nextR2Pos < m){
                    maxAns = Math.max(maxAns,cherryPickup_rec(row+1, nextR1Pos, nextR2Pos, grid,dp,n,m));
                }
            }
        }

        return dp[row][col1][col2] = cherries + maxAns;
    }

    // dont let robot1 and robot2 cross each other =========================================
    public int cherryPickup_rec2(int row, int col1, int col2, int[][] grid, int[][][] dp, int n, int m){
        int cherries = grid[row][col1] + grid[row][col2];
        if(row == n-1){
            return cherries;
        }

        if(dp[row][col1][col2] != -1) return dp[row][col1][col2];

        int maxAns = 0;
        for(int nextR1Pos = col1-1; nextR1Pos <= col1+1; nextR1Pos++){
            for(int nextR2Pos = col2-1; nextR2Pos <= col2+1; nextR2Pos++){
                if(nextR1Pos >=0 && nextR1Pos < m && nextR2Pos >=0 && nextR2Pos < m && nextR1Pos < nextR2Pos){
                    maxAns = Math.max(maxAns,cherryPickup_rec2(row+1, nextR1Pos, nextR2Pos, grid,dp,n,m));
                }
            }
        }

        return dp[row][col1][col2] = cherries + maxAns;
    }

    public int cherryPickup_tab(int[][] grid, int[][][] dp, int n, int m){
        for(int row=n-1; row>=0; row--){
            for(int col1=0; col1 < m; col1++){
                for(int col2=col1+1; col2<m; col2++){ // not letting robot1 and robot2 cross each other

                    int cherries = grid[row][col1] + grid[row][col2];
                    if(row == n-1){
                        dp[row][col1][col2] = cherries;
                        continue;
                    }

                    int maxAns = 0;
                    for(int nextR1Pos = col1-1; nextR1Pos <= col1+1; nextR1Pos++){
                        for(int nextR2Pos = col2-1; nextR2Pos <= col2+1; nextR2Pos++){
                            if(nextR1Pos >=0 && nextR1Pos < m && nextR2Pos >=0 && nextR2Pos < m && nextR1Pos < nextR2Pos){
                                maxAns = Math.max(maxAns,dp[row+1][nextR1Pos][nextR2Pos]); //cherryPickup_rec2(row+1, nextR1Pos, nextR2Pos, grid,dp,n,m));
                            }
                        }
                    }

                    dp[row][col1][col2] = cherries + maxAns;
                }
            }
        }

        return dp[0][0][m-1];
    }

    // Also solve in 2*m*m space because we just need to look at next row


    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int[][][] dp = new int[n][m][m];
        // for(int[][] d: dp){
        //     for(int[] a: d){
        //         Arrays.fill(a, -1);
        //     }
        // }

        return cherryPickup_tab(grid,dp,n,m);
    }

    // cherry pickup ==================================
    // leetcode 741 ===============================================================
    public int rec(int r1, int c1, int r2, int c2, int[][] grid, int[][][][] dp, int n){
        if(r1 == n-1 && c1 == n-1){
            return dp[r1][c1][r2][c2] = grid[r1][c1];
        }

        int cherries = 0;
        if(r1 == r2 && c1 == c2){
            cherries = grid[r1][c1];
        } else {
            cherries = grid[r1][c1] + grid[r2][c2];
        }

        if(dp[r1][c1][r2][c2] != -1) return dp[r1][c1][r2][c2];

        int[][] dirs = {{0,1,0,1},{0,1,1,0},{1,0,0,1},{1,0,1,0}};
        int maxAns = Integer.MIN_VALUE;
        for(int[] dir: dirs){
            int newR1 = r1 + dir[0];
            int newC1 = c1 + dir[1];
            int newR2 = r2 + dir[2];
            int newC2 = c2 + dir[3];

            if(newR1 >= 0 && newC1 >=0 && newR2 >=0 && newC2 >=0 && newR1 < n && newC1 < n && 
                newR2 < n && newC2 < n && grid[newR1][newC1]!=-1 && grid[newR2][newC2] !=-1){
                    maxAns = Math.max(maxAns, rec(newR1, newC1, newR2, newC2, grid, dp, n));
            }
        }

        return dp[r1][c1][r2][c2] = maxAns + cherries;
    }

    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int[][][][] dp = new int[n][n][n][n];

        for(int[][][] d: dp){
            for(int[][] a: d){
                for(int[] b: a)
                    Arrays.fill(b, -1);
            }
        }

        return Math.max(rec(0,0,0,0,grid,dp,n),0);
    }

    // O(N^3) =================================================================
    public int rec(int r1, int c1, int r2, int[][] grid, int[][][] dp, int n){
        int c2 = r1 + c1 - r2;
        if(r1 == n-1 && c1 == n-1){
            return dp[r1][c1][r2] = grid[r1][c1];
        }

        int cherries = 0;
        if(r1 == r2 && c1 == c2){
            cherries = grid[r1][c1];
        } else {
            cherries = grid[r1][c1] + grid[r2][c2];
        }

        if(dp[r1][c1][r2] != -1) return dp[r1][c1][r2];

        int[][] dirs = {{0,1,0,1},{0,1,1,0},{1,0,0,1},{1,0,1,0}};
        int maxAns = Integer.MIN_VALUE;
        for(int[] dir: dirs){
            int newR1 = r1 + dir[0];
            int newC1 = c1 + dir[1];
            int newR2 = r2 + dir[2];
            int newC2 = c2 + dir[3];

            if(newR1 >= 0 && newC1 >=0 && newR2 >=0 && newC2 >=0 && newR1 < n && newC1 < n && 
                newR2 < n && newC2 < n && grid[newR1][newC1]!=-1 && grid[newR2][newC2] !=-1){
                    maxAns = Math.max(maxAns, rec(newR1, newC1, newR2, grid, dp, n));
            }
        }

        return dp[r1][c1][r2] = maxAns + cherries;
    }

    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int[][][] dp = new int[n][n][n];

        for(int[][] d: dp){
            for(int[] a: d){
                Arrays.fill(a, -1);
            }
        }

        return Math.max(rec(0,0,0,grid,dp,n),0);
    }




















    public static void main(String[] args){

    }
}