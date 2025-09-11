class Questions {
    // Buy and sell Stock 1 =========================================
    // Leetcode 121 
    public int maxProfit(int[] prices) {
        int n = prices.length;

        int[] dp0 = new int[n]; // max profit with 0 stocks in hand
        int[] dp1 = new int[n]; // max profit with 1 stock in hand

        dp0[0] = 0; // can't sell on first day
        dp1[0]= -prices[0]; // buy on first day

        for(int i=1; i<n; i++){
            dp0[i] = Math.max(dp0[i-1], dp1[i-1] + prices[i]);
            dp1[i] = Math.max(dp1[i-1], -prices[i]);
        }

        return dp0[n-1];
    }

    // O(1) space
    public int maxProfit(int[] prices) {
        int n = prices.length;

        int dp0 = 0; // max profit with 0 stocks in hand
        int dp1 = -prices[0]; // max profit with 1 stock in hand

        for(int i=1; i<n; i++){
            dp0 = Math.max(dp0, dp1 + prices[i]); // rest or sell today
            dp1 = Math.max(dp1, -prices[i]); // rest or buy today
        }

        return dp0;
    }

    // Buy and sell Stock 2 =========================================
    // Leetcode 122
    public int maxProfit(int[] prices) {
        int n = prices.length;

        int[] dp0 = new int[n]; // max profit with 0 stocks in hand
        int[] dp1 = new int[n]; // max profit with 1 stock in hand

        dp0[0] = 0; // can't sell on first day
        dp1[0]= -prices[0]; // buy on first day

        for(int i=1; i<n; i++){
            dp0[i] = Math.max(dp0[i-1], dp1[i-1] + prices[i]); // rest or bought any prev day, sold today
            dp1[i] = Math.max(dp1[i-1], dp0[i-1] - prices[i]);
        }

        return dp0[n-1];
    }

    // in O(1) space
    public int maxProfit(int[] prices) {
        int n = prices.length;

        int dp0 = 0; // can't sell on first day
        int dp1= -prices[0]; // buy on first day

        for(int i=1; i<n; i++){
            int dp0_prev = dp0;

            dp0 = Math.max(dp0, dp1 + prices[i]); 
            dp1 = Math.max(dp1, dp0_prev - prices[i]);
        }

        return dp0;
    }

    // Buy and sell stock with Transaction fees ==========================================
    // Leetcode 714
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;

        int dp0 = 0; // can't sell on first day
        int dp1= -(prices[0] + fee); // buy on first day

        for(int i=1; i<n; i++){
            int dp0_prev = dp0;

            dp0 = Math.max(dp0, dp1 + prices[i]); 
            dp1 = Math.max(dp1, dp0_prev - (prices[i] + fee));
        }

        return dp0;
    }

    // Buy and sell stock with Cooldown  =================================================
    // Leetcode 309
    public int maxProfit(int[] prices) {
        int n = prices.length;

        int[] dp0 = new int[n]; // max profit with 0 stocks in hand
        int[] dp1 = new int[n]; // max profit with 1 stock in hand

        dp0[0] = 0; // can't sell on first day
        dp1[0]= -prices[0]; // buy on first day

        for(int i=1; i<n; i++){
            dp0[i] = Math.max(dp0[i-1], dp1[i-1] + prices[i]); // rest or bought any prev day, sold today
            if(i==1){
                dp1[i] = Math.max(dp1[i-1],-prices[i]);
            } else{
                dp1[i] = Math.max(dp1[i-1], dp0[i-2] - prices[i]);
            }
        }

        return dp0[n-1];
    }

    // Buy and sell Stock 3 ============================================================
    // Leetcode 123 
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int K = 2;

        int[][][] dp = new int[n][K+1][2];

        for(int i=0; i<n; i++){
            for(int k=0; k<=K; k++){
                for(int x=0; x<2; x++){
                    if(k==0){
                        if(x==0){
                            dp[i][k][x] = 0;
                        } else {
                            dp[i][k][x] = -(int)(1e8);
                        }
                    } else {
                        if(i==0){
                            if(x==0){
                                // dp[i][k][0] = Math.max(dp[-1][k][0],dp[-1][k][1] + prices[i]);
                                // dp[i][k][0] = Math.max(0,(-inf) + prices[i]); = 0
                                dp[i][k][x] = 0;
                            } else {
                                // dp[i][k][1] = Math.max(dp[-1][k][1], dp[-1][k-1][0] - prices[i]);
                                // dp[i][k][1] = Math.max((-inf), 0 - prices[i]);
                                dp[i][k][x] = -prices[i];
                            }
                        } else {
                            if(x==0){
                                dp[i][k][x] = Math.max(dp[i-1][k][x],dp[i-1][k][1] + prices[i]);
                            } else {
                                dp[i][k][x] = Math.max(dp[i-1][k][x], dp[i-1][k-1][0] - prices[i]);
                            }
                        }
                    }
                }
            }
        }

        return dp[n-1][K][0];
    }

    // Buy and sell Stock 3, O(1) space ============================================================
    // Leetcode 123 
    public int maxProfit(int[] prices) {
        int n = prices.length;
        
        int dp10 = 0; // 0 stocks with 1 transactions on 0th day
        int dp11 = -prices[0]; // buy today, 1 stock in hand
        int dp20 = 0; // 0 stocks with 2 transactions on 0th day
        int dp21 = -prices[0]; // buy today, 1 stock in hand

        for(int i=1; i<n; i++){
            dp20 = Math.max(dp20, dp21 + prices[i]);
            dp21 = Math.max(dp21, dp10 - prices[i]);
            dp10 = Math.max(dp10, dp11 + prices[i]);
            dp11 = Math.max(dp11, -prices[i]);
        }

        return dp20;
    }
    // Buy and sell Stock 3 ============================================================
    // Leetcode 188
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        int K = k;

        int[][][] dp = new int[n][K+1][2];

        for(int i=0; i<n; i++){
            for(k=0; k<=K; k++){
                for(int x=0; x<2; x++){
                    if(k==0){
                        if(x==0){
                            dp[i][k][x] = 0;
                        } else {
                            dp[i][k][x] = -(int)(1e8);
                        }
                    } else {
                        if(i==0){
                            if(x==0){
                                dp[i][k][x] = 0;
                            } else {
                                dp[i][k][x] = -prices[i];
                            }
                        } else {
                            if(x==0){
                                dp[i][k][x] = Math.max(dp[i-1][k][x],dp[i-1][k][1] + prices[i]);
                            } else {
                                dp[i][k][x] = Math.max(dp[i-1][k][x], dp[i-1][k-1][0] - prices[i]);
                            }
                        }
                    }
                }
            }
        }

        return dp[n-1][K][0];
    }
}