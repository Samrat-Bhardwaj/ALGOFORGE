class Questions {
    // Buy and Sell stock 1 (Leetcode 121) ========================
    public int maxProfit(int[] prices) {
        int n = prices.length;

        int[] dp0 = new int[n]; // maximum profit after 1 transaction holding 0 stock => trying to sell
        int[] dp1 = new int[n]; // maximum profit after 1 transaction holding 1 stock => trying to buy

        dp0[0] = 0;
        dp1[0] = -prices[0];

        for(int i=1; i<n; i++){
            dp0[i] = Math.max(dp0[i-1], dp1[i-1] + prices[i]);
            dp1[i] = Math.max(-prices[i], dp1[i-1]);
        }

        return dp0[n-1];
    }

    // Buy and Sell stock 1 in O(1) space (Leetcode 121) ========================
    public int maxProfit(int[] prices) {
        int n = prices.length;

        int sell = 0; // maximum profit after 1 transaction holding 0 stock => trying to sell
        int buy = -prices[0]; // maximum profit after 1 transaction holding 1 stock => trying to buy

        for(int i=1; i<n; i++){
            // previous buy + prices[i] or previous sell
            sell = Math.max(buy + prices[i], sell);

            // -prices[i] or previous buy
            buy = Math.max(-prices[i], buy);
        }

        return sell;
    }

    // Buy and Sell stock 2 (Leetcode 122) ========================
    public int maxProfit(int[] prices) {
        int n = prices.length;

        int[] dp0 = new int[n];
        int[] dp1 = new int[n];

        dp0[0] = 0; // not possible to sell on day 0;
        dp1[0] = -prices[0];

        for(int i=1; i<n; i++){
            dp0[i] = Math.max(dp0[i-1], dp1[i-1] + prices[i]);
            dp1[i] = Math.max(dp1[i-1], dp0[i-1] - prices[i]);
        }

        return dp0[n-1];
    }

    // Buy and Sell stock 2 in O(1) space (Leetcode 122) ========================
    public int maxProfit(int[] prices) {
        int n = prices.length;

        int sell = 0; // 0 profit on day 1
        int buy = -prices[0]; 

        for(int i=1; i<n; i++){
            int prev_sell = sell;
            int prev_buy = buy;

            sell = Math.max(prev_sell, prev_buy + prices[i]);
            buy = Math.max(prev_buy, prev_sell - prices[i]);
        }

        return sell;
    }

    // Buy and Sell stocks with transaction fee(Leetcode 714) ===========
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;

        int sell = 0; // 0 profit on day 1
        int buy = -(prices[0] + fee); 

        for(int i=1; i<n; i++){
            int prev_sell = sell;
            int prev_buy = buy;

            sell = Math.max(prev_sell, prev_buy + prices[i]);
            buy = Math.max(prev_buy, prev_sell - (prices[i] + fee));
        }

        return sell;
    }

    // Buy and Sell stocks with cooldown period(Leetcode 309) =============
    public int maxProfit(int[] prices) {
        int n = prices.length;

        int[] dp0 = new int[n];
        int[] dp1 = new int[n];

        dp0[0] = 0; // not possible to sell on day 0;
        dp1[0] = -prices[0];

        for(int i=1; i<n; i++){
            dp0[i] = Math.max(dp0[i-1], dp1[i-1] + prices[i]); // can sell anytime
            if(i <= 1){
                dp1[i] = Math.max(dp1[i-1], - prices[i]);
            } else {
                dp1[i] = Math.max(dp1[i-1], dp0[i-2] - prices[i]);
            }
            
        }

        return dp0[n-1];
    }

    // Buy and sell stocks 3 (At max 2 transaction allowed) (leetcode 123)
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int K = 2;

        int[][][] dp = new int[n][K+1][2];

        for(int i=0; i<n; i++){
            for(int k=0; k<=K; k++){
                for(int x=0; x<=1; x++){
                    if(k==0){
                        if(x==0){
                            dp[i][k][x] = 0;
                        } else {
                            dp[i][k][x] = Integer.MIN_VALUE;
                        }
                    } else {
                        if(i==0){
                            if(x==0){
                                // dp[i][k][x] = Math.max(dp[i-1][k][1] + prices[i], dp[i-1][k][x]);
                                // dp[i][k][x] = Math.max(dp[-1][k][1] + prices[i], dp[-1][k][1]);
                                // dp[i][k][x] = Math.max(-inf + prices[i], 0);
                                dp[i][k][x] = 0;
                            } else {
                                // dp[i][k][1] = Math.max(dp[i-1][k-1][0] - prices[i], dp[i-1][k][1]);
                                // dp[i][k][1] = Math.max(dp[-1][k-1][0] - prices[i], dp[-1][k][1]);
                                // dp[i][k][1] = Math.max(0 - prices[i], -inf);
                                dp[i][k][1] = -prices[i];
                            }
                        } else {
                            if(x==0){
                                dp[i][k][x] = Math.max(dp[i-1][k][1] + prices[i], dp[i-1][k][x]);
                            } else {
                                dp[i][k][1] = Math.max(dp[i-1][k-1][0] - prices[i], dp[i-1][k][1]);
                            }
                        }
                    }
                }
            }
        }

        return dp[n-1][K][0];
    }

    // Buy and sell stocks 3 in O(1) Space (Leetcode 123)
    public int maxProfit(int[] prices){
        int n = prices.length;

        int dp10 = 0; // selling on day 0 is not possible (sell once)
        int dp11 = -prices[0]; // max profit with day 0 buying (buy once)
        int dp20 = 0; // 0 profit after selling twice  (buy twice, sell twice)
        int dp21 = -prices[0]; // after 2 transaction, 1 stock in holding (buy twice, sell once)

        for(int i=1; i<n; i++){
            dp20 = Math.max(dp20, dp21 + prices[i]);
            dp21 = Math.max(dp21, dp10 - prices[i]);
            dp10 = Math.max(dp10, dp11 + prices[i]);
            dp11 = Math.max(dp11, -prices[i]);
        }

        return dp20;
    }

    // Buy and Sell stocks 4 (Leetcode 188) ================================
    // Homework => Improve Space complexity 
    public int maxProfit(int K, int[] prices) {
        int n = prices.length;

        int[][][] dp = new int[n][K+1][2];

        for(int i=0; i<n; i++){
            for(int k=0; k<=K; k++){
                for(int x=0; x<=1; x++){
                    if(k==0){
                        if(x==0){
                            dp[i][k][x] = 0;
                        } else {
                            dp[i][k][x] = Integer.MIN_VALUE;
                        }
                    } else {
                        if(i==0){
                            if(x==0){
                                dp[i][k][x] = 0;
                            } else {
                                dp[i][k][1] = -prices[i];
                            }
                        } else {
                            if(x==0){
                                dp[i][k][x] = Math.max(dp[i-1][k][1] + prices[i], dp[i-1][k][x]);
                            } else {
                                dp[i][k][1] = Math.max(dp[i-1][k-1][0] - prices[i], dp[i-1][k][1]);
                            }
                        }
                    }
                }
            }
        }

        return dp[n-1][K][0];
    }

}
