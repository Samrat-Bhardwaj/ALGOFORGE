class Questions {
    // matrix chain multiplication (MCM) ================================
    // https://www.geeksforgeeks.org/problems/matrix-chain-multiplication0303/1
    static int mcm_rec(int si, int ei, int[] arr, int[][] dp){
        if(si + 1 == ei){
            return dp[si][ei] = 0;
        }

        if(dp[si][ei] != 0) return dp[si][ei];

        int res = Integer.MAX_VALUE;
        for(int cut = si + 1; cut < ei; cut++){
            int left = mcm_rec(si, cut, arr, dp);
            int right = mcm_rec(cut, ei, arr, dp);

            int curr_ans = left + arr[si]*arr[cut]*arr[ei]  + right;

            res = Math.min(res, curr_ans);
        }

        return dp[si][ei] = res;
    }

    static int mcm_tab(int[] arr, int[][] dp){
        int n = arr.length;

        for(int gap=1; gap<arr.length; gap++){
            for(int si=0, ei = gap; ei < n; si++, ei++){
                if(si + 1 == ei){
                    dp[si][ei] = 0;
                    continue;
                }

                int res = Integer.MAX_VALUE;
                for(int cut = si + 1; cut < ei; cut++){
                    int left = dp[si][cut]; //mcm_rec(si, cut, arr, dp);
                    int right = dp[cut][ei]; //mcm_rec(cut, ei, arr, dp);

                    int curr_ans = left + arr[si]*arr[cut]*arr[ei]  + right;

                    res = Math.min(res, curr_ans);
                }

                dp[si][ei] = res;
            }
        }

        return dp[0][n-1];
    }

    static int matrixMultiplication(int arr[]) {
        int n = arr.length;

        int[][] dp = new int[n][n];

        // return mcm_rec(0,n-1,arr,dp);
        return mcm_tab(arr,dp);
    }

    // matrix chain multiplication brackets =========================
    // https://www.geeksforgeeks.org/problems/brackets-in-matrix-chain-multiplication1024/1 
    static String mcm_tab(int[] arr, int[][] dp, String[][] sdp){
        int n = arr.length;

        for(int gap=1; gap<arr.length; gap++){
            for(int si=0, ei = gap; ei < n; si++, ei++){
                if(si + 1 == ei){
                    dp[si][ei] = 0;
                    sdp[si][ei] = "" + (char)('A' + si);
                    continue;
                }

                int res = Integer.MAX_VALUE;
                String resString = "";
                for(int cut = si + 1; cut < ei; cut++){
                    int left = dp[si][cut]; //mcm_rec(si, cut, arr, dp);
                    int right = dp[cut][ei]; //mcm_rec(cut, ei, arr, dp);

                    int curr_ans = left + arr[si]*arr[cut]*arr[ei]  + right;

                    if(res > curr_ans){
                        res = curr_ans;

                        String leftString = sdp[si][cut];
                        String rightString = sdp[cut][ei];
                        resString = "(" + leftString + rightString + ")";
                    }
                }

                dp[si][ei] = res;
                sdp[si][ei] = resString;
            }
        }

        return sdp[0][n-1];
    }

    static String matrixChainOrder(int arr[]) {
        int n = arr.length;

        int[][] dp = new int[n][n];
        String[][] sdp = new String[n][n];

        return mcm_tab(arr,dp,sdp);
    }

    public static void main(String[] args){
        int[] arr = {1,2,5,1,4};

        System.out.println(matrixChainOrder(arr));
    }
}