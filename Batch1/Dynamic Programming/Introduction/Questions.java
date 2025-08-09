class Questions {
    // https://www.geeksforgeeks.org/problems/friends-pairing-problem5425/1
    public long countFriendsPairings_rec(int n, long[] memo){
        if(n <= 2){
            return memo[n] = n;
        }

        if(memo[n] != 0){
            return memo[n];
        }

        long single = countFriendsPairings_rec(n-1, memo);
        long pairUp = (n-1)*countFriendsPairings_rec(n-2, memo);

        return memo[n] = single + pairUp;
    }

    public long countFriendsPairings_tab(int N, long[] dp){
        for(int n=1; n<=N; n++){
            if(n <= 2){
                dp[n] = n;
                continue;
            }

            long single = dp[n-1]; //countFriendsPairings_rec(n-1, memo);
            long pairUp = (n-1)*dp[n-2]; //(n-1)*countFriendsPairings_rec(n-2, memo);

            dp[n] = single + pairUp;
        }

        return dp[N];
    }

    public long countFriendsPairings(int n) {
        long[] memo = new long[n+1];
        return countFriendsPairings_tab(n, memo);
    }

    // Climbing stairs (Leetcode 70) =======================================
    public int climbStairs_rec(int n, int[] dp){
        if(n <= 1){
            return dp[n] = 1;
        }

        if(dp[n] != 0){
            return dp[n];
        }

        int waysAfterTakingOneStep = climbStairs_rec(n-1, dp);
        int waysAfterTakingTwoStep = climbStairs_rec(n-2, dp);

        return dp[n] = waysAfterTakingOneStep + waysAfterTakingTwoStep;
    }

    public int climbStairs_tab(int N, int[] dp){
        for(int n=0; n<=N; n++){
            if(n <= 1){
                dp[n] = 1;
                continue;
            }

            int waysAfterTakingOneStep = dp[n-1]; //climbStairs_rec(n-1, dp);
            int waysAfterTakingTwoStep = dp[n-2]; //climbStairs_rec(n-2, dp);

            dp[n] = waysAfterTakingOneStep + waysAfterTakingTwoStep;
        }

        return dp[N];
    }

    public int climbStairs(int n) {
        int[] dp = new int[n+1];
        return climbStairs_tab(n, dp);
    }

























    public static void main(String[] args){

    }
}