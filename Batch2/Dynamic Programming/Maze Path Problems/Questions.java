class Questions {
    public static int totalMazePaths_rec(int row, int col, int n, int m){
        if(row == n-1 && col == m-1){
            return 1;
        }

        int totalWays = 0;

        if(row + 1 < n){
            totalWays += totalMazePaths_rec(row+1, col, n, m);
        }

        if(col + 1 < m){
            totalWays += totalMazePaths_rec(row, col+1, n, m);
        }
        
        if(row + 1 < n && col + 1 < m){
            totalWays += totalMazePaths_rec(row+1, col+1, n, m);
        }
        
        return totalWays;
    }

    public static int totalMazePaths_memo(int row, int col, int n, int m, int[][] dp){
        if(row == n-1 && col == m-1){
            return dp[row][col] = 1;
        }

        if(dp[row][col] != 0) return dp[row][col];

        int totalWays = 0;
        if(col + 1 < m){
            totalWays += totalMazePaths_memo(row, col+1, n, m, dp);
        }

        if(row + 1 < n){
            totalWays += totalMazePaths_memo(row+1, col, n, m, dp);
        }

        if(row + 1 < n && col + 1 < m){
            totalWays += totalMazePaths_memo(row+1, col+1, n, m, dp);
        }
        
        return dp[row][col] = totalWays;
    }

    public static int totalMazePaths_tab(int n, int m, int[][] dp){
        for(int col=m-1; col>=0; col--){
            for(int row=n-1; row>=0; row--){
                if(row == n-1 && col == m-1){
                    dp[row][col] = 1;
                    continue;
                }

                int totalWays = 0;
                if(col + 1 < m){
                    totalWays += dp[row][col+1]; //totalMazePaths_memo(row, col+1, n, m, dp);
                }

                if(row + 1 < n){
                    totalWays += dp[row+1][col]; //totalMazePaths_memo(row+1, col, n, m, dp);
                }

                if(row + 1 < n && col + 1 < m){
                    totalWays += dp[row+1][col+1]; //totalMazePaths_memo(row+1, col+1, n, m, dp);
                }
                
                dp[row][col] = totalWays;
            }
        }

        return dp[0][0];
    }

    public static int totalMazePaths_Pretty(int n, int m, int[][] dp){
        for(int col=m-1; col>=0; col--){
            for(int row=n-1; row>=0; row--){
                if(row == n-1 || col == m-1){
                    dp[row][col] = 1;
                } else {
                    dp[row][col] = dp[row+1][col] + dp[row][col+1] + dp[row+1][col+1];
                }
            }
        }

        return dp[0][0];
    }

    public static int totalMazePaths(int n, int m){
        // return totalMazePaths_rec(0,0,n,m);

        int[][] dp = new int[n][m];

        // return totalMazePaths_memo(0,0,n,m,dp);
        return totalMazePaths_Pretty(n,m,dp);
    }

    // Total Maze paths with Jumps Allowed =============================
    public static int totalMazePathsWithJumps_rec(int row, int col, int dr, int dc){
        if(row == dr && col == dc){
            return 1;
        }

        int totalWays = 0;
        // horizontal jumps
        for(int jump=1; jump <= dc-col; jump++){
            totalWays += totalMazePathsWithJumps_rec(row, col+jump, dr, dc);
        }

        // vertical jumps
        for(int jump=1; jump <= dr-row; jump++){
            totalWays += totalMazePathsWithJumps_rec(row+jump, col, dr, dc);
        }

        // diagonal jumps
        for(int jump=1; jump <= Math.min(dc-col, dr-row); jump++){
            totalWays += totalMazePathsWithJumps_rec(row + jump, col + jump, dr, dc);
        }

        return totalWays;
    }

    public static int totalMazePathsWithJumps_memo(int row, int col, int dr, int dc, int[][] dp){
        if(row == dr && col == dc){
            return dp[row][col] = 1;
        }

        if(dp[row][col] != 0) return dp[row][col];

        int totalWays = 0;
        // horizontal jumps
        for(int jump=1; jump <= dc-col; jump++){
            totalWays += totalMazePathsWithJumps_memo(row, col+jump, dr, dc, dp);
        }

        // vertical jumps
        for(int jump=1; jump <= dr-row; jump++){
            totalWays += totalMazePathsWithJumps_memo(row+jump, col, dr, dc, dp);
        }

        // diagonal jumps
        for(int jump=1; jump <= Math.min(dc-col, dr-row); jump++){
            totalWays += totalMazePathsWithJumps_memo(row + jump, col + jump, dr, dc, dp);
        }

        return dp[row][col] = totalWays;
    }

    public static int totalMazePathsWithJumps_tab(int n, int m, int dr, int dc, int[][] dp){
        for(int row=n-1; row>=0; row--){
            for(int col=m-1; col>=0; col--){
                if(row == dr && col == dc){
                    dp[row][col] = 1;
                    continue;
                }

                int totalWays = 0;
                // horizontal jumps
                for(int jump=1; jump <= dc-col; jump++){
                    totalWays += dp[row][col+jump]; ///totalMazePathsWithJumps_memo(row, col+jump, dr, dc, dp);
                }

                // vertical jumps
                for(int jump=1; jump <= dr-row; jump++){
                    totalWays += dp[row+jump][col]; //totalMazePathsWithJumps_memo(row+jump, col, dr, dc, dp);
                }

                // diagonal jumps
                for(int jump=1; jump <= Math.min(dc-col, dr-row); jump++){
                    totalWays += dp[row+jump][col+jump]; //totalMazePathsWithJumps_memo(row + jump, col + jump, dr, dc, dp);
                }

                dp[row][col] = totalWays;
            }
        }

        return dp[0][0];
    }


    public static int totalMazePathsWithJumps(int n, int m){
        int dr = n-1;
        int dc = m-1;

        int[][] dp = new int[n][m];

        // return totalMazePathsWithJumps_rec(0,0,dr,dc);
        // return totalMazePathsWithJumps_memo(0,0,dr,dc,dp);
        return totalMazePathsWithJumps_tab(n,m,dr,dc,dp);
    }

    // Leetcode 64(Minimum path sum) ==============================
    public int minPathSum_rec(int row, int col, int n, int m, int[][] grid){
        if(row == n-1 && col == m-1){
            return grid[row][col];
        }

        int minSum = Integer.MAX_VALUE;

        if(row + 1 < n){
            minSum = Math.min(minSum,minPathSum_rec(row+1, col, n, m, grid));
        }

        if(col + 1 < m){
            minSum = Math.min(minSum,minPathSum_rec(row, col+1, n, m, grid));
        }

        return minSum + grid[row][col];
    }

    public int minPathSum_memo(int row, int col, int n, int m, int[][] grid, int[][] dp){
        if(row == n-1 && col == m-1){
            return dp[row][col] = grid[row][col];
        }

        if(dp[row][col] != -1) return dp[row][col]; // if(dp[row][col] != Integer.MAX_VALUE)

        int minSum = Integer.MAX_VALUE;

        if(row + 1 < n){
            minSum = Math.min(minSum,minPathSum_memo(row+1, col, n, m, grid,dp));
        }

        if(col + 1 < m){
            minSum = Math.min(minSum,minPathSum_memo(row, col+1, n, m, grid,dp));
        }

        return dp[row][col] = minSum + grid[row][col];
    }

    public int minPathSum_tab(int n, int m, int[][] grid){
        int[][] dp = new int[n][m];

        for(int row=n-1; row>=0; row--){
            for(int col=m-1; col>=0; col--){
                if(row == n-1 && col == m-1){
                    dp[row][col] = grid[row][col];
                    continue;
                }

                int minSum = Integer.MAX_VALUE;

                if(row + 1 < n){
                    minSum = Math.min(minSum, dp[row+1][col]); //Math.min(minSum,minPathSum_memo(row+1, col, n, m, grid,dp));
                }

                if(col + 1 < m){
                    minSum = Math.min(minSum, dp[row][col+1]); //Math.min(minSum,minPathSum_memo(row, col+1, n, m, grid,dp));
                }

                dp[row][col] = minSum + grid[row][col];
            }
        }

        return dp[0][0];
    }

    public int minPathSum_pretty(int n, int m, int[][] grid){
        int[][] dp = new int[n][m];

        for(int row=n-1; row>=0; row--){
            for(int col=m-1; col>=0; col--){
                if(row == n-1 && col == m-1){
                    dp[row][col] = grid[row][col];
                } else if(row == n-1){
                    dp[row][col] = grid[row][col] + dp[row][col+1];
                } else if(col == m-1){
                    dp[row][col] = grid[row][col] + dp[row+1][col];
                } else {
                    dp[row][col] = grid[row][col] + Math.min(dp[row+1][col], dp[row][col+1]);
                }
            }
        }

        return dp[0][0];
    }

    public int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        // int[][] dp = new int[n][m];
        // for(int[] d: dp){
        //     Arrays.fill(d, -1); // 0 can be a possible answer
        //     //Arrays.fill(d, Integer.MAX_VALUE)
        // }

        return minPathSum_pretty(n,m,grid);
    }

    // Goldmine (https://www.geeksforgeeks.org/problems/gold-mine-problem2608/1)
    public int maxGold_rec(int row, int col, int n, int m, int[][] mat){
        if(col == m-1){
            return mat[row][col];
        }

        int maxSum = 0;

        // diagonally up
        if(row - 1 >= 0){
            maxSum = Math.max(maxSum, maxGold_rec(row-1,col+1,n,m,mat));
        }

        // right
        maxSum = Math.max(maxSum, maxGold_rec(row,col+1,n,m,mat));

        // diagonally down
        if(row + 1 < n){
            maxSum = Math.max(maxSum, maxGold_rec(row+1,col+1,n,m,mat));
        }
        
        return maxSum + mat[row][col];
    }

    public int maxGold_memo(int row, int col, int n, int m, int[][] mat, int[][] dp){
        if(col == m-1){
            return dp[row][col] = mat[row][col];
        }

        if(dp[row][col] != -1) return dp[row][col];

        int maxSum = 0;

        // diagonally up
        if(row - 1 >= 0){
            maxSum = Math.max(maxSum, maxGold_memo(row-1,col+1,n,m,mat,dp));
        }

        // right
        maxSum = Math.max(maxSum, maxGold_memo(row,col+1,n,m,mat,dp));

        // diagonally down
        if(row + 1 < n){
            maxSum = Math.max(maxSum, maxGold_memo(row+1,col+1,n,m,mat,dp));
        }
        
        return dp[row][col] = maxSum + mat[row][col];
    }

    public int maxGold_tab(int n, int m, int[][] mat){
        int[][] dp = new int[n][m];

        for(int col = m-1; col>=0; col--){
            for(int row=0; row<n; row++){ // or move from n-1 to 0
                if(col == m-1){
                    dp[row][col] = mat[row][col];
                    continue;
                } 

                int maxSum = 0;

                // diagonally up
                if(row - 1 >= 0){
                    maxSum = Math.max(maxSum, dp[row-1][col+1]); //maxGold_memo(row-1,col+1,n,m,mat,dp));
                }

                // right
                maxSum = Math.max(maxSum, dp[row][col+1]); //maxGold_memo(row,col+1,n,m,mat,dp));

                // diagonally down
                if(row + 1 < n){
                    maxSum = Math.max(maxSum, dp[row+1][col+1]); //maxGold_memo(row+1,col+1,n,m,mat,dp));
                }
                
                dp[row][col] = maxSum + mat[row][col];
            }
        }

        int maxPossibleGold = 0;
        for(int row=0; row<n; row++){
            maxPossibleGold = Math.max(maxPossibleGold, dp[row][0]);
        }
        
        return maxPossibleGold;
    }

    public int maxGold(int[][] mat) {
        // int maxAns = 0;

        int n = mat.length;
        int m = mat[0].length;

        // int[][] dp = new int[n][m];
        // for(int[] d: dp) Arrays.fill(d, -1);

        // for(int row=0; row<n; row++){
        //     maxAns = Math.max(maxAns, maxGold_memo(row,0,n,m,mat,dp));
        // }

        return maxGold_tab(n,m,mat);
    }

    // Min cost climbing stairs (Leetcode 746) ==========
    public int minCost_memo(int idx, int[] cost, int[] dp){
        if(idx <= 1){
            return dp[idx] = cost[idx];
        }

        if(dp[idx] != -1) return dp[idx];

        int minCostWithOneStep = minCost_memo(idx-1, cost, dp);
        int minCostWithTwoStep = minCost_memo(idx-2, cost, dp);

        return dp[idx] = Math.min(minCostWithOneStep, minCostWithTwoStep) + (idx < cost.length ? cost[idx] : 0);
    }

    public int minCost_tab(int[] cost){
        int n = cost.length;
        int[] dp = new int[n+1];

        for(int idx=0; idx<=n; idx++){
            if(idx <= 1){
                dp[idx] = cost[idx];
                continue;
            }

            int minCostWithOneStep = dp[idx-1]; //minCost_memo(idx-1, cost, dp);
            int minCostWithTwoStep = dp[idx-2]; //minCost_memo(idx-2, cost, dp);

            dp[idx] = Math.min(minCostWithOneStep, minCostWithTwoStep) + (idx < cost.length ? cost[idx] : 0);
        }

        return dp[n];
    }

    public int minCost_tabPretty(int[] cost){
        int n = cost.length;
        int[] dp = new int[n+1];

        for(int idx=0; idx<=n; idx++){
            if(idx <= 1){
                dp[idx] = cost[idx];
            } else if(idx == n){
                dp[idx] = Math.min(dp[idx-1], dp[idx-2]);
            } else {
                dp[idx] = cost[idx] + Math.min(dp[idx-1], dp[idx-2]);
            }
        }

        return dp[n];
    }

    public int minCost_SpaceOptimized(int[] cost){
        int n = cost.length;
        int a = cost[0];
        int b = cost[1];

        for(int idx=2; idx<=n; idx++){
            int c = Math.min(a,b);

            if(idx < n)
                c += cost[idx];

            a = b;
            b = c;
        }

        return b;
    }

    public int minCost_PrintPath(int[] cost){
        int n = cost.length;
        int[] dp = new int[n+1];
        String[] sdp = new String[n+1];

        for(int idx=0; idx<=n; idx++){
            if(idx <= 1){
                dp[idx] = cost[idx];
                sdp[idx] = idx + "";
            } else {
                int oneStep = dp[idx-1];
                int twoStep = dp[idx-2];

                if(oneStep < twoStep){
                    dp[idx] = oneStep;
                    sdp[idx] = sdp[idx-1] + " + 1  -> " + idx;
                } else {
                    dp[idx] = twoStep;
                    sdp[idx] = sdp[idx-2] + " + 2 -> " + idx;
                }

                if(idx < n){
                    dp[idx] += cost[idx];
                }
            }
        }

        System.out.println(sdp[n]);
        return dp[n];
    }

    public int minCostClimbingStairs(int[] cost) {
        // int n = cost.length;
        // int[] dp = new int[n+1];
        // Arrays.fill(dp, -1);

        return minCost_PrintPath(cost);
    }

    // Decode ways (Leetcode 91) =================
    public int numDecodings_rec(int idx, String s, int[] dp){
        if(idx == s.length()){
            return dp[idx] = 1;
        }

        if(s.charAt(idx) == '0'){ // no possible ans
            return dp[idx] = 0;
        }

        if(dp[idx] != -1) return dp[idx];

        int oneChar = numDecodings_rec(idx+1, s, dp);
        int twoChar = 0;

        if(idx + 1 < s.length()){
            int num = 10*(s.charAt(idx) - '0') + s.charAt(idx+1) - '0';

            if(num <= 26){
                twoChar += numDecodings_rec(idx+2, s, dp);
            }
        }

        return dp[idx] = oneChar + twoChar;
    }

    public int numDecodings_tab(String s){
        int n = s.length();
        int[] dp = new int[n+1];
        
        for(int idx=n; idx>=0; idx--){
            if(idx == s.length()){
                dp[idx] = 1;
                continue;
            }

            if(s.charAt(idx) == '0'){ // no possible ans
                dp[idx] = 0;
                continue;
            }

            int oneChar = dp[idx+1]; //numDecodings_rec(idx+1, s, dp);
            int twoChar = 0;

            if(idx + 1 < s.length()){
                int num = 10*(s.charAt(idx) - '0') + s.charAt(idx+1) - '0';

                if(num <= 26){
                    twoChar += dp[idx+2]; //numDecodings_rec(idx+2, s, dp);
                }
            }

            dp[idx] = oneChar + twoChar;
        }

        return dp[0];
    }

    // HOMEWORK => Solve in O(1) space 

    public int numDecodings(String s) {
        // int n = s.length();

        // int[] dp = new int[n+1];
        // Arrays.fill(dp, -1);

        // return numDecodings_rec(0,s,dp);
        return numDecodings_tab(s);
    }

    // Num decodings 2 (Leetcode 639) ============================
    int mod = (int)(1e9 + 7);
    public long numDecodings_rec(int idx, String s, long[] dp){
        if(idx == s.length()){
            return dp[idx] = 1;
        }

        if(s.charAt(idx) == '0'){
            return dp[idx] = 0;
        }

        if(dp[idx] != -1) return dp[idx];

        long oneChar = numDecodings_rec(idx+1, s, dp);
        if(s.charAt(idx) == '*') oneChar *= 9;

        long twoChar = 0;
        if(idx + 1 < s.length()){
            twoChar = numDecodings_rec(idx+2, s, dp);
            char ch1 = s.charAt(idx), ch2 = s.charAt(idx+1);

            if(ch1 == '*' && ch2 == '*'){
                twoChar *= 15;
            } else if(ch1 == '*'){
                twoChar = (ch2 - '0' <= 6) ? twoChar*2 : twoChar;
            } else if(ch1 == '1' && ch2 == '*'){
                twoChar *= 9;
            } else if(ch1 == '2' && ch2 == '*'){
                twoChar *= 6;
            } else if(ch2 == '*'){
                twoChar = 0;
            } else {
                int num = 10*(ch1-'0') + (ch2-'0');
                if(num > 26){
                    twoChar = 0;
                }
            }
        }

        return dp[idx] = (oneChar + twoChar)%mod;
    }

    public long numDecodings_tab(String s){
        int n = s.length();
        long[] dp = new long[n+1];
        for(int idx=n; idx>=0; idx--){
            if(idx == s.length()){
                dp[idx] = 1;
                continue;
            }

            if(s.charAt(idx) == '0'){
                dp[idx] = 0;
                continue;
            }

            long oneChar = dp[idx+1]; //numDecodings_rec(idx+1, s, dp);
            if(s.charAt(idx) == '*') oneChar *= 9;

            long twoChar = 0;
            if(idx + 1 < s.length()){
                twoChar = dp[idx+2]; //numDecodings_rec(idx+2, s, dp);
                char ch1 = s.charAt(idx), ch2 = s.charAt(idx+1);

                if(ch1 == '*' && ch2 == '*'){
                    twoChar *= 15;
                } else if(ch1 == '*'){
                    twoChar = (ch2 - '0' <= 6) ? twoChar*2 : twoChar;
                } else if(ch1 == '1' && ch2 == '*'){
                    twoChar *= 9;
                } else if(ch1 == '2' && ch2 == '*'){
                    twoChar *= 6;
                } else if(ch2 == '*'){
                    twoChar = 0;
                } else {
                    int num = 10*(ch1-'0') + (ch2-'0');
                    if(num > 26){
                        twoChar = 0;
                    }
                }
            }

            dp[idx] = (oneChar + twoChar)%mod;
        }
        
        return dp[0];
    }

    // HOMEWORK => Solve in O(1) space 

    public int numDecodings(String s) {
        // int n = s.length();

        // long[] dp = new long[n+1];
        // Arrays.fill(dp, -1);

        return (int)(numDecodings_tab(s));
    }


































    public static void main(String[] args){
        int n = 2;
        int m = 3;

        System.out.println(totalMazePathsWithJumps(n,m));
    }
}