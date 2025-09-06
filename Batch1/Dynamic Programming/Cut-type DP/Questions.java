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

    // burst balloons (leetcode 312) ========================================================
    public int burstBalloonsRec(int[] nums, int si, int ei, int n, int[][] dp){
        if(dp[si][ei] != -1) return dp[si][ei];
        int leftVal = si == 0 ? 1 : nums[si-1];
        int rightVal = ei == n - 1 ? 1 : nums[ei+1];

        // deciding which balloon will burst at the end in si to ei
        int res = 0;
        for(int cut=si; cut<=ei; cut++){
            int left = cut == si ? 0 : burstBalloonsRec(nums, si, cut-1, n, dp);
            int right = cut == ei ? 0 : burstBalloonsRec(nums, cut+1, ei, n, dp); 

            int curr_ans = left + right + leftVal*nums[cut]*rightVal; 

            res = Math.max(res, curr_ans);
        }

        return dp[si][ei] = res;
    }

    public int burstBalloonsTab(int[] nums, int n){
        int[][] dp = new int[n][n];

        for(int gap=0; gap<n; gap++){
            for(int si=0, ei=gap; ei<n; si++, ei++){
                int leftVal = si == 0 ? 1 : nums[si-1];
                int rightVal = ei == n - 1 ? 1 : nums[ei+1];

                // deciding which balloon will burst at the end in si to ei
                int res = 0;
                for(int cut=si; cut<=ei; cut++){
                    int left = cut == si ? 0 : dp[si][cut-1]; //burstBalloonsRec(nums, si, cut-1, n, dp);
                    int right = cut == ei ? 0 : dp[cut+1][ei]; //burstBalloonsRec(nums, cut+1, ei, n, dp); 

                    int curr_ans = left + right + leftVal*nums[cut]*rightVal; 

                    res = Math.max(res, curr_ans);
                }

                dp[si][ei] = res;
            }
        }

        return dp[0][n-1];
    }

    public int maxCoins(int[] nums) {
        int n = nums.length;
        // int[][] dp = new int[n][n];
        // for(int[] d: dp) Arrays.fill(d, -1);

        // return burstBalloonsRec(nums,0,n-1,n,dp);
        return burstBalloonsTab(nums,n);
    }

    // Minimum cost to cut a stick (leetcode 1547) ===========================
    public static int cutStick(int len, int[] cuts, int si, int ei,int[][] dp){
        if(dp[si][ei] != 0) return dp[si][ei];

        // all the cuts before coming to this state is done
        int leftEnd = si == 0 ? 0 : cuts[si-1];
        int rightEnd = ei == cuts.length-1 ? len : cuts[ei+1];

        int costToCut = rightEnd - leftEnd;

        int res = Integer.MAX_VALUE;
        for(int cut = si; cut<=ei; cut++){
            int left = cut == si ? 0 : cutStick(len,cuts,si,cut-1,dp);
            int right = cut == ei ? 0 : cutStick(len, cuts, cut+1, ei,dp);

            int curr_ans = left + right + costToCut;

            res = Math.min(res, curr_ans);
        }

        return dp[si][ei] = res;
    }

    public static int cutStick_tab(int len, int[] cuts, int[][] dp){
        int cutLen = cuts.length;
        for(int gap=0; gap<cutLen; gap++){
            for(int si=0, ei=gap; ei<cutLen; si++, ei++){
                int leftEnd = si == 0 ? 0 : cuts[si-1];
                int rightEnd = ei == cutLen-1 ? len : cuts[ei+1];

                int costToCut = rightEnd - leftEnd; // currStick length

                int res = Integer.MAX_VALUE;
                for(int cut = si; cut<=ei; cut++){
                    int left = cut == si ? 0 : dp[si][cut-1]; //cutStick(len,cuts,si,cut-1,dp);
                    int right = cut == ei ? 0 : dp[cut+1][ei]; //cutStick(len, cuts, cut+1, ei,dp);

                    int curr_ans = left + right + costToCut;

                    res = Math.min(res, curr_ans);
                }

                dp[si][ei] = res;
            }
        }

        return dp[0][cutLen-1];
    }

    public int minCost(int n, int[] cuts) {
        Arrays.sort(cuts);
        int len = n;
        int cutLength = cuts.length;
        int[][] dp = new int[cutLength][cutLength];
        // return cutStick(len,cuts,0,cuts.length-1,dp);
        return cutStick_tab(len,cuts,dp);
    }

























    public static void main(String[] args){
        int[] arr = {1,2,5,1,4};

        System.out.println(matrixChainOrder(arr));
    }
}