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
}