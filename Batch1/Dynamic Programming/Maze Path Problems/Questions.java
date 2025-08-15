class Questions {
    // Leetcode 62 ===============================================================================
    public static int totalMazePaths_rec(int row, int col, int n, int m,int[][] dp){
        if(row == n-1 && col == m-1){
            return dp[row][col] = 1;
        }

        if(dp[row][col] != 0) return dp[row][col];

        int totalWays = 0;

        if(row + 1 < n){ // proactive calls
            totalWays += totalMazePaths_rec(row+1,col,n,m,dp);
        }

        if(col + 1 < m){
            totalWays += totalMazePaths_rec(row,col+1,n,m,dp);
        }
        
        if(row+1 < n && col+1 < m){
            totalWays += totalMazePaths_rec(row+1,col+1,n,m,dp);
        }
        
        return dp[row][col] = totalWays;
    }

    public static int totalMazePaths_tab(int n, int m, int[][] dp){
        for(int row = n-1; row>=0; row--){
            for(int col = m-1; col>=0; col--){
                if(row == n-1 && col == m-1){
                    dp[row][col] = 1;
                    continue;
                }

                int totalWays = 0;

                if(row + 1 < n){ // proactive calls
                    totalWays += dp[row+1][col]; //totalMazePaths_rec(row+1,col,n,m,dp);
                }

                if(col + 1 < m){
                    totalWays += dp[row][col+1]; //totalMazePaths_rec(row,col+1,n,m,dp);
                }
                
                if(row+1 < n && col+1 < m){
                    totalWays += dp[row+1][col+1]; //totalMazePaths_rec(row+1,col+1,n,m,dp);
                }
                
                dp[row][col] = totalWays;
            }
        }

        return dp[0][0];
    }

    public static int totalMazePathsWithJumps_rec(int row, int col, int dr, int dc, int[][] dp){
        if(row == dr && col == dc){
            return dp[row][col] = 1;
        }

        if(dp[row][col] != 0) return dp[row][col];

        int totalWays = 0;
        // vertical jumps
        for(int jump=1; jump <= dr-row; jump++){
            totalWays += totalMazePathsWithJumps_rec(row+jump, col, dr, dc, dp);
        }

        // horizontal jumps
        for(int jump=1; jump <= dc-col; jump++){
            totalWays += totalMazePathsWithJumps_rec(row, col+jump, dr, dc, dp);
        }

        // diagonal jumps
        for(int jump=1; jump <= Math.min(dr-row, dc-col); jump++){
            totalWays += totalMazePathsWithJumps_rec(row+jump, col+jump, dr, dc, dp);
        }

        return dp[row][col] = totalWays;
    }

    public static int totalMazePathsWithJumps_tab(int dr, int dc, int[][] dp){
        for(int row=dr; row>=0; row--){
            for(int col=dc; col>=0; col--){
                if(row == dr && col == dc){
                    dp[row][col] = 1;
                    continue;
                }

                int totalWays = 0;
                // vertical jumps
                for(int jump=1; jump <= dr-row; jump++){
                    totalWays += dp[row+jump][col]; //totalMazePathsWithJumps_rec(row+jump, col, dr, dc, dp);
                }

                // horizontal jumps
                for(int jump=1; jump <= dc-col; jump++){
                    totalWays += dp[row][col+jump]; //totalMazePathsWithJumps_rec(row, col+jump, dr, dc, dp);
                }

                // diagonal jumps
                for(int jump=1; jump <= Math.min(dr-row, dc-col); jump++){
                    totalWays += dp[row+jump][col+jump]; //totalMazePathsWithJumps_rec(row+jump, col+jump, dr, dc, dp);
                }

                dp[row][col] = totalWays;
            }
        }

        return dp[0][0];
    }

    // leetcode 64 
    public int minMazePathSum_rec(int row, int col, int n, int m, int[][] grid, int[][] dp){
        if(row == n-1 && col == m-1){
            return dp[row][col] = grid[row][col];
        }

        if(dp[row][col] != -1) return dp[row][col];

        int minCost = Integer.MAX_VALUE;

        if(row+1 < n){
            minCost = Math.min(minCost, minMazePathSum_rec(row+1,col,n,m,grid,dp));
        }

        if(col+1 < m){
            minCost = Math.min(minCost, minMazePathSum_rec(row, col+1,n,m,grid,dp));
        }

        return dp[row][col] = minCost + grid[row][col];
    }

    public int minMazePathSum_tab(int n, int m, int[][] grid, int[][] dp){
        for(int row=n-1; row>=0; row--){
            for(int col=m-1; col>=0; col--){
                if(row == n-1 && col == m-1){
                    dp[row][col] = grid[row][col];
                    continue;
                }

                int minCost = Integer.MAX_VALUE;

                if(row+1 < n){
                    minCost = Math.min(minCost, dp[row+1][col]); //Math.min(minCost, minMazePathSum_rec(row+1,col,n,m,grid,dp));
                }

                if(col+1 < m){
                    minCost = Math.min(minCost, dp[row][col+1]); //Math.min(minCost, minMazePathSum_rec(row, col+1,n,m,grid,dp));
                }

                dp[row][col] = minCost + grid[row][col];
            }
        }

        return dp[0][0];
    }

    public int minMazePathSum_tab2(int n, int m, int[][] grid, int[][] dp,String[][] sdp){
        for(int row=n-1; row>=0; row--){
            for(int col=m-1; col>=0; col--){
                if(row == n-1 && col == m-1){
                    dp[row][col] = grid[row][col];
                    sdp[row][col] = grid[row][col] + "";
                } else if(row == n-1){
                    dp[row][col] = dp[row][col+1] + grid[row][col];
                    sdp[row][col] = grid[row][col] + "," + sdp[row][col+1];
                } else if(col ==  m-1){
                    dp[row][col] = dp[row+1][col] + grid[row][col];
                    sdp[row][col] =grid[row][col] + "," + sdp[row+1][col];
                } else {
                    if(dp[row+1][col] < dp[row][col+1]){
                        dp[row][col] = dp[row+1][col] + grid[row][col];
                        sdp[row][col] = grid[row][col] + "," + sdp[row+1][col];
                    } else {
                        dp[row][col] = dp[row][col+1] + grid[row][col];
                        sdp[row][col] = grid[row][col] + "," + sdp[row][col+1];
                    }
                }
            }
        }   

        System.out.println(sdp[0][0]);
        return dp[0][0];
    }

    public int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int[][] dp = new int[n][m];
        String[][] sdp = new String[n][m];
        // 0 is always a possible answer, filling dp array with -1
        for(int[] d: dp){
            Arrays.fill(d, -1);
        }

        return minMazePathSum_tab2(n,m,grid,dp,sdp);
    }

    // Goldmine (https://www.geeksforgeeks.org/problems/gold-mine-problem2608/1)
    public int getMaxGold_rec(int row, int col, int n, int m, int[][] mat,int[][] dp){
        if(col == m-1){
            return dp[row][col] = mat[row][col];
        }

        if(dp[row][col] != -1) return dp[row][col];

        int maxGold = 0;
        
        if(row-1 >= 0){ // right up
            maxGold = Math.max(maxGold, getMaxGold_rec(row-1,col+1,n,m,mat,dp));
        }
        // right right
        maxGold = Math.max(maxGold, getMaxGold_rec(row,col+1,n,m,mat,dp));
        
        if(row+1 < n){// right down
            maxGold = Math.max(maxGold, getMaxGold_rec(row+1,col+1,n,m,mat,dp));
        }

        return dp[row][col] = maxGold + mat[row][col];
    }

    public int getMaxGold_tab(int n, int m, int[][] mat, int[][] dp){
        for(int col=m-1; col>=0; col--){
            for(int row=0; row<n; row++){
                if(col == m-1){
                    dp[row][col] = mat[row][col];
                    continue;
                }

                int maxGold = 0;
                
                if(row-1 >= 0){ // right up
                    maxGold = Math.max(maxGold, dp[row-1][col+1]); //Math.max(maxGold, getMaxGold_rec(row-1,col+1,n,m,mat,dp));
                }
                // right right
                maxGold = Math.max(maxGold, dp[row][col+1]); //Math.max(maxGold, getMaxGold_rec(row,col+1,n,m,mat,dp));
                
                if(row+1 < n){// right down
                    maxGold = Math.max(maxGold, dp[row+1][col+1]); //Math.max(maxGold, getMaxGold_rec(row+1,col+1,n,m,mat,dp));
                }

                dp[row][col] = maxGold + mat[row][col];
            }
        }

        int maxGold = 0;
        for(int row=0; row<n; row++){
            maxGold = Math.max(maxGold, dp[row][0]);
        }

        return maxGold;
    }

    public int maxGold(int[][] mat) {
        int ans = 0;

        int n = mat.length;
        int m = mat[0].length;

        int[][] dp = new int[n][m];
        for(int[] d:dp){
            Arrays.fill(d,-1);
        }

        // for(int row=0; row<n; row++){
        //     int goldFromThisRow = getMaxGold_rec(row,0,n,m,mat,dp);
        //     ans = Math.max(goldFromThisRow, ans);
        // }

        return getMaxGold_tab(n,m,mat,dp);
    }

    // Leetcode 746 ===============================================
    public int minCostClimbingStairs_rec(int n, int[] cost, int[] dp){
        if(n <= 1){
            return dp[n] = cost[n];
        }

        if(dp[n] != -1) return dp[n];

        int minCost = Math.min(minCostClimbingStairs_rec(n-1,cost,dp), minCostClimbingStairs_rec(n-2,cost,dp));

        return dp[n] = minCost + (n < cost.length ? cost[n] : 0);
    }

    public int minCostClimbingStairs_tab(int N, int[] cost, int[] dp){
        for(int n=0; n<=N; n++){
            if(n <= 1){
                dp[n] = cost[n];
                continue;
            }

            int minCost = Math.min(dp[n-1], dp[n-2]); //Math.min(minCostClimbingStairs_rec(n-1,cost,dp), minCostClimbingStairs_rec(n-2,cost,dp));

            dp[n] = minCost + (n < cost.length ? cost[n] : 0);
        }   

        return dp[N]; 
    }

    // Solve in O(1) space
    public int minCostClimbingStairs_tabO1(int N, int[] cost){
        for(int n=0; n<=N; n++){
            
        }
    }

    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;

        int[] dp = new int[n+1];
        Arrays.fill(dp,-1);

        return minCostClimbingStairs_tab(n,cost,dp);
    }

    // leetcode 91 =================================================
    public int numDecodings_rec(int idx, String s, int[] dp){
        if(idx == s.length()){
            return dp[idx] = 1;    
        }
        if(s.charAt(idx) == '0'){
            return dp[idx] = 0;
        }

        if(dp[idx] != -1) return dp[idx];

        int singleCharWays = numDecodings_rec(idx+1,s,dp);
        int twoCharsWays = 0;


        if(idx + 1 < s.length()){
            int numUsing2Chars = 10*(s.charAt(idx)-'0') + (s.charAt(idx+1) - '0');

            if(numUsing2Chars <= 26){
                twoCharsWays = numDecodings_rec(idx+2, s, dp);
            }
        }
        
        return dp[idx] = singleCharWays + twoCharsWays;
    }

    public int numDecodings_tab(String s, int[] dp){
        for(int idx=s.length(); idx>=0; idx--){
            if(idx == s.length()){
                dp[idx] = 1; 
                continue;   
            }
            if(s.charAt(idx) == '0'){
                dp[idx] = 0;
                continue;
            }

            int singleCharWays = dp[idx+1]; //numDecodings_rec(idx+1,s,dp);
            int twoCharsWays = 0;


            if(idx + 1 < s.length()){
                int numUsing2Chars = 10*(s.charAt(idx)-'0') + (s.charAt(idx+1) - '0');

                if(numUsing2Chars <= 26){
                    twoCharsWays = dp[idx+2]; //numDecodings_rec(idx+2, s, dp);
                }
            }
            
            dp[idx] = singleCharWays + twoCharsWays;
        }
        return dp[0];
    }

    public int numDecodings(String s) {
        int n = s.length();

        int[] dp = new int[n+1];
        // Arrays.fill(dp, -1);

        return numDecodings_tab(s,dp);
    }

    // leetcode 639 =============================================================
    public long numDecodings_rec(int idx, String s, int n, long[] dp){
        if(idx == n){
            return dp[idx] = 1;
        } 
        if(s.charAt(idx) == '0'){
            return dp[idx] = 0;
        }

        if(dp[idx] != -1) return dp[idx];

        long oneCharWays = numDecodings_rec(idx+1,s,n,dp);
        char ch1 = s.charAt(idx);

        long totalWays = ch1 == '*' ? 9*oneCharWays : oneCharWays;

        if(idx + 1 < n){
            char ch2 = s.charAt(idx+1);
            long twoCharsWays = numDecodings_rec(idx+2,s,n,dp);

            if(ch1 =='*' && ch2 =='*'){
                totalWays += 15*twoCharsWays;
            } else if(ch1 =='*'){
                totalWays += (ch2 <= '6' ? 2*twoCharsWays : twoCharsWays);
            } else if(ch1=='1' && ch2 =='*'){
                totalWays += 9*twoCharsWays;
            } else if(ch1 =='2' && ch2 == '*'){
                totalWays += 6*twoCharsWays;
            } else if(ch2 == '*'){

            } else {
                int num = (ch1 -'0')*10 + (ch2 - '0');
                if(num <= 26){
                    totalWays += twoCharsWays;
                }
            }
        }
        
        return dp[idx] = totalWays % mod;
    }

    public long numDecodings_tab(int idx, String s, int n, long[] dp){
        for(idx = n; idx>=0; idx--){
            if(idx == n){
                dp[idx] = 1;
                continue;
            } 
            if(s.charAt(idx) == '0'){
                dp[idx] = 0;
                continue;
            }

            long oneCharWays = dp[idx+1]; //numDecodings_rec(idx+1,s,n,dp);
            char ch1 = s.charAt(idx);

            long totalWays = ch1 == '*' ? 9*oneCharWays : oneCharWays;

            if(idx + 1 < n){
                char ch2 = s.charAt(idx+1);
                long twoCharsWays = dp[idx+2]; //numDecodings_rec(idx+2,s,n,dp);

                if(ch1 =='*' && ch2 =='*'){
                    totalWays += 15*twoCharsWays;
                } else if(ch1 =='*'){
                    totalWays += (ch2 <= '6' ? 2*twoCharsWays : twoCharsWays);
                } else if(ch1=='1' && ch2 =='*'){
                    totalWays += 9*twoCharsWays;
                } else if(ch1 =='2' && ch2 == '*'){
                    totalWays += 6*twoCharsWays;
                } else if(ch2 == '*'){

                } else {
                    int num = (ch1 -'0')*10 + (ch2 - '0');
                    if(num <= 26){
                        totalWays += twoCharsWays;
                    }
                }
            }
            
            dp[idx] = totalWays % mod;
        }

        return dp[0];
    }

    public int numDecodings(String s) {
        int n = s.length();

        long[] dp = new long[n+1];
        Arrays.fill(dp, -1);

        return numDecodings_tab(0,s,n,dp);
    }






















    public static void main(String[] args){
        int n = 2;
        int m = 3;

        int[][] dp = new int[n][m];

        System.out.println(totalMazePathsWithJumps_rec(0,0,n-1,m-1,dp));
        System.out.println(totalMazePathsWithJumps_tab(n-1,m-1,dp));
    }
}