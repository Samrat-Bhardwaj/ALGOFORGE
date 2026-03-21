class Questions {
    // Friends Pairing
    public long friendsPairing_rec(int n){
        if(n==0 || n==1){
            return 1;
        }

        long single = friendsPairing_rec(n-1);
        long pairUp = (n-1)*friendsPairing_rec(n-2);

        return single + pairUp;
    }

    public long friendsPairing_memo(int n, long[] dp){
        if(n==0 || n==1){
            return dp[n] = 1;
        }

        if(dp[n] != 0) return dp[n];

        long single = friendsPairing_memo(n-1, dp);
        long pairUp = (n-1)*friendsPairing_memo(n-2, dp);

        return dp[n] = single + pairUp;
    }

    public long friendsPairing_tab(int N, long[] dp){

        for(int n=0; n<=N; n++){
            if(n==0 || n==1){
                dp[n] = 1;
                continue;
            }


            long single = dp[n-1]; //friendsPairing_memo(n-1, dp);
            long pairUp = (n-1)*dp[n-2]; //(n-1)*friendsPairing_memo(n-2, dp);

            dp[n] = single + pairUp;
        }

        return dp[N];
    }

    public long countFriendsPairings(int n) {
        // return friendsPairing_rec(n);

        long[] dp = new long[n+1];
        // return friendsPairing_memo(n, dp);
        return friendsPairing_tab(n, dp);
    }
}