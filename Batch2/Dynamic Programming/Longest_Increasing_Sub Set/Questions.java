class Questions {
    // Longest Increasing subsequence (Leetcode 300) 

    // dp[idx] = Length of LIS ending with nums[idx]
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;

        int[] dp = new int[n];
        int maxLen = 0;

        for(int i=0; i<n; i++){
            dp[i] = 1;

            for(int j=i-1; j>=0; j--){
                if(nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }

    // Longest Decreasing subsequence

    // dp[idx] = Length of LDS ending with nums[idx]
    public int lengthOfLDS(int[] nums) {
        int n = nums.length;

        int[] dp = new int[n];
        int maxLen = 0;

        for(int i=0; i<n; i++){
            dp[i] = 1;

            for(int j=i-1; j>=0; j--){
                if(nums[j] > nums[i]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }

    // Longest Decreasing subsequence

    // dp[idx] = Length of LDS starting with nums[idx]
    public int lengthOfLDS_2(int[] nums) {
        int n = nums.length;

        int[] dp = new int[n];
        int maxLen = 0;

        for(int i=n-1; i>=0; i--){
            dp[i] = 1;

            for(int j=i+1; j<n; j++){
                if(nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }

    // Minimum number of deletion to make array sorted (https://www.geeksforgeeks.org/problems/minimum-number-of-deletions-to-make-a-sorted-sequence3248/1)
    class Solution {
        // returns dp[idx] = Length of LDS starting with nums[idx]
        public int lengthOfLIS(int[] nums) {
            int n = nums.length;

            int[] dp = new int[n];
            int maxLen = 0;

            for(int i=0; i<n; i++){
                dp[i] = 1;

                for(int j=i-1; j>=0; j--){
                    if(nums[j] < nums[i]){
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }

                maxLen = Math.max(maxLen, dp[i]);
            }

            return maxLen;
        }
        
        public int minDeletions(int arr[], int n) {
            return n - lengthOfLIS(arr);
        }
    }

    // Longest Bitonic subsequence (https://www.geeksforgeeks.org/problems/longest-bitonic-subsequence0824/1)
    class Solution {
        // dp[idx] = Length of LIS ending with nums[idx]
        public static int[] makeLIS(int[] nums) {
            int n = nums.length;

            int[] dp = new int[n];

            for(int i=0; i<n; i++){
                dp[i] = 1;

                for(int j=i-1; j>=0; j--){
                    if(nums[j] < nums[i]){
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
            }

            return dp;
        }

        public static int[] makeLDS(int[] nums) {
            int n = nums.length;

            int[] dp = new int[n];

            for(int i=n-1; i>=0; i--){
                dp[i] = 1;

                for(int j=i+1; j<n; j++){
                    if(nums[j] < nums[i]){
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
            }

            return dp;
        }

        public static int longestBitonicSequence(int n, int[] nums) {
            int[] lis_DP = makeLIS(nums);
            int[] lds_DP = makeLDS(nums);

            int maxLen = 0;

            for(int i=1; i<n-1; i++){
                if(lis_DP[i] == 1 || lds_DP[i] == 1) continue;

                maxLen = Math.max(maxLen, lis_DP[i] + lds_DP[i] - 1);
            }

            return maxLen;
        }
    }

    // Maximum sum increasing sequence (https://www.geeksforgeeks.org/problems/maximum-sum-increasing-subsequence4749/1)
    public int maxSumIS(int nums[]) {
        // code here
        int n = nums.length;

        int[] dp = new int[n];
        int maxSum = 0;

        for(int i=0; i<n; i++){
            dp[i] = nums[i];

            for(int j=i-1; j>=0; j--){
                if(nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i], dp[j] + nums[i]);
                }
            }

            maxSum = Math.max(maxSum, dp[i]);
        }

        return maxSum;
    }
    
}