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

    public static void main(String[] args){
        int n = 2;
        int m = 3;

        int[][] dp = new int[n][m];

        System.out.println(totalMazePathsWithJumps_rec(0,0,n-1,m-1,dp));
        System.out.println(totalMazePathsWithJumps_tab(n-1,m-1,dp));
    }
}