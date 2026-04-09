class Questions {
    // House robber (Leetcode 198) ==================================
    public int rob(int[] nums) {
        int n = nums.length;
        
        int[] inc = new int[n]; // inc[i] = optimum ans with ith house included
        int[] exc = new int[n]; // exc[i] = optimum ans with ith house excluded

        inc[0] = nums[0];
        exc[0] = 0;

        for(int i=1; i<n; i++){
            inc[i] = nums[i] + exc[i-1];
            exc[i] = Math.max(inc[i-1], exc[i-1]);
        }

        return Math.max(inc[n-1], exc[n-1]);
    }

    // House robber in O(1) Space (Leetcode 198) ==================================
    public int rob(int[] nums) {
        int n = nums.length;
        
        int inc = nums[0]; // inc[i] = optimum ans with ith house included
        int exc = 0; // exc[i] = optimum ans with ith house excluded

        for(int i=1; i<n; i++){
            int inc_prev = inc;

            inc = nums[i] + exc;
            exc = Math.max(inc_prev, exc);
        }

        return Math.max(inc, exc);
    }

    // House robber 2, array is cyclic (Leetcode 213) =============================
    public int maxRobHouse(int[] nums, int si, int ei){
        if(si >= nums.length) return 0;

        int inc = nums[si]; 
        int exc = 0; 

        for(int i=si+1; i <= ei; i++){
            int inc_prev = inc;

            inc = nums[i] + exc;
            exc = Math.max(inc_prev, exc);
        }

        return Math.max(inc, exc);
    }

    public int rob(int[] nums) {
        int n = nums.length;

        int including0 = maxRobHouse(nums, 0, n-2);
        int excluding0 = maxRobHouse(nums, 1, n-1);

        return Math.max(including0, excluding0);
    }

    // Count square submatrices (leetcode 1277) =====================
    public int countSquares(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        
        int count = 0;

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(i >= 1 && j >= 1 && matrix[i][j] == 1){
                    int squareLength = Math.min(matrix[i-1][j], Math.min(matrix[i][j-1], matrix[i-1][j-1]));

                    matrix[i][j] = squareLength + 1;
                } 

                count += matrix[i][j];
            }
        }

        return count;
    }

    // Maximum area of square submatrix (Leetcode 221) =====================
    public int maximalSquare(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        int[][] dp = new int[n][m];
        
        int maxArea = 0;

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                dp[i][j] = matrix[i][j] - '0';
                if(i >= 1 && j >= 1 && dp[i][j] == 1){
                    int squareLength = Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1]));

                    dp[i][j] = squareLength + 1;
                } 

                maxArea = Math.max(maxArea, dp[i][j]*dp[i][j]);
            }
        }

        return maxArea;
    }

    // Count submatrices with all Ones (Leetcode 1504) ==================
    public int numSubmat(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        int[] height = new int[m];

        int count = 0;

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(mat[i][j] == 0){
                    height[j] = 0;
                    continue;
                }

                height[j]++;

                int minHeight = n; // max Height can be n
                for(int k=j; k>=0; k--){
                    minHeight = Math.min(height[k], minHeight);

                    count += minHeight;
                }
            }
        }

        return count;
    }

    // Cherry Pickup 2 (Leetcode 1463) ==================================
    public int cherryPickup_memo(int row, int c1, int c2, int[][] grid, int n, int m, int[][][] dp){
        if(dp[row][c1][c2] != -1) return dp[row][c1][c2];

        int currCherries = 0;

        if(c1 == c2){
            currCherries = grid[row][c1];
        } else {
            currCherries = grid[row][c1] + grid[row][c2];
        }

        int nextCherries = 0;
        for(int nextC1 = c1-1; nextC1 <= c1 + 1; nextC1++){
            for(int nextC2 = c2-1; nextC2 <= c2 + 1; nextC2++){
                if(row + 1 < n && nextC1>=0 && nextC2>=0 && nextC1<m && nextC2<m){
                    nextCherries = Math.max(nextCherries, cherryPickup_memo(row+1,nextC1,nextC2,grid,n,m,dp));
                }
            }
        }

        return dp[row][c1][c2] = currCherries + nextCherries;
    }

    // dont let robot 1 and robot 2 cross each other to optimise more
    public int cherryPickup_memo(int row, int c1, int c2, int[][] grid, int n, int m, int[][][] dp){
        if(dp[row][c1][c2] != -1) return dp[row][c1][c2];

        int currCherries = 0;

        if(c1 == c2){
            currCherries = grid[row][c1];
        } else {
            currCherries = grid[row][c1] + grid[row][c2];
        }

        int nextCherries = 0;
        for(int nextC1 = c1-1; nextC1 <= c1 + 1; nextC1++){
            for(int nextC2 = c2-1; nextC2 <= c2 + 1; nextC2++){
                if(row + 1 < n && nextC1>=0 && nextC2>=0 && nextC1<m && nextC2<m && nextC1 < nextC2){
                    nextCherries = Math.max(nextCherries, cherryPickup_memo(row+1,nextC1,nextC2,grid,n,m,dp));
                }
            }
        }

        return dp[row][c1][c2] = currCherries + nextCherries;
    }

    public int cherryPickup_Tab(int[][] grid, int n, int m){
        int[][][] dp = new int[n][m][m];

        for(int row=n-1; row>=0; row--){
            for(int c1=0; c1<m; c1++){
                for(int c2=c1+1; c2<m; c2++){ // robot2 will always be atleast one column ahead robot1
                    int currCherries = 0;

                    if(c1 == c2){
                        currCherries = grid[row][c1];
                    } else {
                        currCherries = grid[row][c1] + grid[row][c2];
                    }

                    int nextCherries = 0;
                    for(int nextC1 = c1-1; nextC1 <= c1 + 1; nextC1++){
                        for(int nextC2 = c2-1; nextC2 <= c2 + 1; nextC2++){
                            if(row + 1 < n && nextC1>=0 && nextC2>=0 && nextC1<m && nextC2<m && nextC1 < nextC2){
                                nextCherries = Math.max(nextCherries, dp[row+1][nextC1][nextC2]); //cherryPickup_memo(row+1,nextC1,nextC2,grid,n,m,dp));
                            }
                        }
                    }

                    dp[row][c1][c2] = currCherries + nextCherries;
                }
            }
        }

        return dp[0][0][m-1]; // starting point of both robots is 0th row, 0th col and m-1 col
    }

    // Homework => Solve in 2*m*m space => 2D DP

    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        // int[][][] dp = new int[n][m][m];

        // for(int[][] a: dp){
        //     for(int[] b: a){
        //         Arrays.fill(b, -1);
        //     }
        // }

        // return cherryPickup_memo(0,0,m-1,grid,n,m,dp);
        return cherryPickup_Tab(grid,n,m);
    }
}