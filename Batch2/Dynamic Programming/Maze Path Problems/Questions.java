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

    public static void main(String[] args){
        int n = 2;
        int m = 3;

        System.out.println(totalMazePaths(n,m));
    }
}