class Questions {
    // MCM (https://www.geeksforgeeks.org/problems/matrix-chain-multiplication0303/1)
    public static int mcm_rec(int[] arr, int si, int ei, int[][] dp){
        if(si + 1  == ei){
            return 0;
        }

        if(dp[si][ei] != 0) return dp[si][ei];

        int minCost = Integer.MAX_VALUE;

        for(int cut = si + 1; cut < ei; cut++){
            int leftAns = mcm_rec(arr, si, cut, dp);
            int rightAns = mcm_rec(arr, cut, ei, dp);

            int currCost = leftAns + (arr[si]*arr[cut]*arr[ei]) + rightAns;

            minCost = Math.min(minCost, currCost);
        }

        return dp[si][ei] = minCost;
    }

    public static int mcm_tab(int[] arr){
        int n = arr.length;
        int[][] dp = new int[n][n];

        for(int gap=1; gap<n; gap++){
            for(int si=0, ei=gap; ei <n; si++, ei++){
                if(si + 1  == ei){
                    dp[si][ei] = 0;
                    continue;
                }

                int minCost = Integer.MAX_VALUE;

                for(int cut = si + 1; cut < ei; cut++){
                    int leftAns = dp[si][cut]; //mcm_rec(arr, si, cut, dp);
                    int rightAns = dp[cut][ei]; //mcm_rec(arr, cut, ei, dp);

                    int currCost = leftAns + (arr[si]*arr[cut]*arr[ei]) + rightAns;

                    minCost = Math.min(minCost, currCost);
                }

                dp[si][ei] = minCost;
            }
        }

        return dp[0][n-1];
    }

    static int matrixMultiplication(int arr[]) {
        int n = arr.length;

        int[][] dp = new int[n][n];

        return mcm_tab(arr);    
    }

    // Brackets for MCM (https://www.geeksforgeeks.org/problems/brackets-in-matrix-chain-multiplication1024/1)
    public String mcm_tab(int[] arr){
        int n = arr.length;
        int[][] dp = new int[n][n];
        String[][] sdp = new String[n][n];

        for(int gap=1; gap<n; gap++){
            for(int si=0, ei=gap; ei <n; si++, ei++){
                if(si + 1  == ei){
                    dp[si][ei] = 0;
                    sdp[si][ei] = (char)('A' + si) + "";
                    continue;
                }

                int minCost = Integer.MAX_VALUE;
                String minCostString = "";

                for(int cut = si + 1; cut < ei; cut++){
                    int leftAns = dp[si][cut]; //mcm_rec(arr, si, cut, dp);
                    int rightAns = dp[cut][ei]; //mcm_rec(arr, cut, ei, dp);

                    int currCost = leftAns + (arr[si]*arr[cut]*arr[ei]) + rightAns;

                    if(minCost > currCost){
                        int leftString = sdp[si][cut];
                        int rightString = sdp[cut][ei];

                        minCostString = "(" + leftString + rightString + ")";
                        minCost = currCost;
                    }
                }

                dp[si][ei] = minCost;
                sdp[si][ei] = minCostString;
            }
        }

        return sdp[0][n-1];
    }

    public String matrixChainOrder(int arr[]) {
        return mcm_tab(arr);
    }

    // Leetcode 312, Burst Balloons =========================================================
    public int burstBalloons_rec(int si, int ei, int n, int[] nums, int[][] dp){
        if(dp[si][ei] != -1) return dp[si][ei];

        int leftValue = si == 0 ? 1 : nums[si-1];
        int rightValue = ei == n-1 ? 1 : nums[ei+1];

        int maxCoins = 0;
        // What balloon should be burst at the end to get maximum coins
        for(int cut=si; cut<=ei; cut++){
            int left = cut == si ? 0 : burstBalloons_rec(si, cut-1, n, nums, dp);
            int right = cut == ei ? 0 : burstBalloons_rec(cut+1, ei, n, nums, dp);

            int currAns = left + right + leftValue*nums[cut]*rightValue;;

            maxCoins = Math.max(maxCoins, currAns);
        }

        return dp[si][ei] = maxCoins;
    }

    public int burstBalloons_tab(int n, int[] nums){
        int[][] dp = new int[n][n];

        for(int gap = 0; gap < n; gap++){
            for(int si=0, ei=gap; ei<n; si++, ei++){
                
                int leftValue = si == 0 ? 1 : nums[si-1];
                int rightValue = ei == n-1 ? 1 : nums[ei+1];

                int maxCoins = 0;
                // What balloon should be burst at the end to get maximum coins
                for(int cut=si; cut<=ei; cut++){
                    int left = cut == si ? 0 : dp[si][cut-1]; //burstBalloons_rec(si, cut-1, n, nums, dp);
                    int right = cut == ei ? 0 : dp[cut+1][ei]; //burstBalloons_rec(cut+1, ei, n, nums, dp);

                    int currAns = left + right + leftValue*nums[cut]*rightValue;;

                    maxCoins = Math.max(maxCoins, currAns);
                }

                dp[si][ei] = maxCoins;
            }
        }

        return dp[0][n-1];
    }

    public int maxCoins(int[] nums) {
        int n = nums.length;

        // int[][] dp = new int[n][n];
        // for(int []d : dp) Arrays.fill(d, -1);

        // return burstBalloons_rec(0,n-1,n,nums,dp);
        return burstBalloons_tab(n, nums);
    }
}