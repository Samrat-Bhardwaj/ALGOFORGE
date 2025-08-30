class Questions {
    // Leetcode 300, Longest increasing subsequence ======================================
    public int lis_rec(int[] nums, int idx, int[] dp){
        if(dp[idx] != 0) return dp[idx];

        int ans = 1;
        for(int j=idx-1; j>=0; j--){
            if(nums[j] < nums[idx]){
                ans = Math.max(ans, lis_rec(nums,j,dp) + 1);
            }
        }

        return dp[idx] = ans;
    }

    public int lis_tab(int[] nums, int[] dp){
        int res = 0;
        for(int idx=0; idx<nums.length; idx++){

            int ans = 1;
            for(int j=idx-1; j>=0; j--){
                if(nums[j] < nums[idx]){
                    ans = Math.max(ans, dp[j] + 1);
                }
            }

            dp[idx] = ans;
            res = Math.max(res, dp[idx]);
        }

        return res;
    }

    public int lis_tab(int[] nums, int[] dp){
        int res = 0;
        for(int idx=0; idx<nums.length; idx++){
            dp[idx] = 1;
            for(int j=idx-1; j>=0; j--){
                if(nums[j] < nums[i]){
                    dp[idx] = Math.max(dp[idx], dp[j] + 1);
                }
            }

            res = Math.max(res, dp[idx]);
        }

        return res;
    }

    public int lengthOfLIS(int[] nums) {
        int n = nums.length;

        int[] dp = new int[n];

        // int res = 0;
        // for(int i=n-1; i>=0; i--){
        //     res = Math.max(res,lis_rec(nums,i,dp));
        // }

        return lis_tab(nums,dp);
    }

    // Longest Decresing subsequence
    // dp[i] = LDS ending with nums[i]
    public int lds_end(int[] nums, int[] dp){
        int res = 0;
        for(int idx=0; idx<nums.length; idx++){
            dp[idx] = 1;
            for(int j=idx-1; j>=0; j--){
                if(nums[j] > nums[i]){
                    dp[idx] = Math.max(dp[idx], dp[j] + 1);
                }
            }

            res = Math.max(res, dp[idx]);
        }

        return res;
    }

    // dp[i] = LDS starting with nums[i]
    public int lds_start(int[] nums, int[] dp){
        int n = nums.length;
        int res = 0;
        for(int idx=n-1; idx >=0; idx--){
            dp[idx] = 1;
            for(int j=idx+1; j<n; j++){
                if(nums[j] < nums[i]){
                    dp[idx] = Math.max(dp[idx], dp[j] + 1);
                }
            }

            res = Math.max(res, dp[idx]);
        }

        return res;
    }

    // Longest bitonic subsequence (https://www.geeksforgeeks.org/problems/longest-bitonic-subsequence0824/1)
    public static int LongestBitonicSequence(int n, int[] nums) {
        int[] lis_dp = new int[n];
        int[] lds_dp = new int[n];

        for(int i=0; i<n; i++){
            lis_dp[i] = 1;

            for(int j=i-1; j>=0; j--){
                if(nums[j] < nums[i]){
                    lis_dp[i] = Math.max(lis_dp[i], lis_dp[j] + 1);
                }
            }
        }

        for(int i=n-1; i>=0; i--){
            lds_dp[i] = 1;

            for(int j=i+1; j<n; j++){
                if(nums[j] < nums[i]){
                    lds_dp[i] = Math.max(lds_dp[i], lds_dp[j] + 1);
                }
            }
        }

        int res = 0;
        for(int i=1; i<n-1; i++){
            if(lis_dp[i] == 1 || lds_dp[i] == 1) continue;
            
            res = Math.max(res, lis_dp[i] + lds_dp[i] - 1);
        }

        return res;
    }
















    public static void main(String[] args){

    }
}