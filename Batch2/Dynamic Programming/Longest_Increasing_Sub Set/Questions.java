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

    // Leetcode 673 (Count of Longest increasing subsequences)
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;

        int[] dp = new int[n];
        int[] count = new int[n];

        for(int i=0; i<n; i++){
            dp[i] = 1;
            count[i] = 1;

            for(int j=i-1; j>=0; j--){
                if(nums[j] < nums[i]){
                    if(dp[i] < dp[j] + 1){ // new Maximum
                        dp[i] = dp[j] + 1;
                        count[i] = count[j]; // maximum len sub ending with nums[i] = count of maximum len sub ending with nums[j]
                    } else if(dp[i] == dp[j] + 1){ // of lis length
                        count[i] += count[j];
                    }
                }
            }
        }

        int lis_len_subs = 0;
        int lis_len = 0;

        for(int i=0; i<n; i++){
            if(dp[i] > lis_len){
                lis_len = dp[i];
                lis_len_subs = count[i];
            } else if(dp[i] == lis_len){
                lis_len_subs += count[i];
            }
        }

        return lis_len_subs;
    }
    
    // LIS better in NlogN ======================================
    public int findInsertionPosition(int x, ArrayList<Integer> lis){
        int left = 0;
        int right = lis.size() - 1;

        while(left <= right){
            int mid = (left + right)/2;

            if(lis.get(mid) < x){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        ArrayList<Integer> lis = new ArrayList<>();

        for(int i=0; i<n; i++){
            int insertPos = findInsertionPosition(nums[i], lis);

            if(lis.size() == insertPos){
                lis.add(nums[i]);
            } else {
                lis.set(insertPos, nums[i]);
            }
        }

        return lis.size();
    }

    // Russian doll envelopes (Leetcode 354) in O(N^2) 
    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;

        Arrays.sort(envelopes, (int[] a, int[] b) ->{
            if(a[1] == b[1]){
                return a[0] - b[0];
            }

            return a[1] - b[1];
        });

        int[] dp = new int[n];
        int maxLength = 0;

        for(int i=0; i<n; i++){
            dp[i] = 1;
            for(int j=i-1; j>=0; j--){
                if(envelopes[j][0] < envelopes[i][0] && envelopes[j][1] < envelopes[i][1]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            maxLength = Math.max(maxLength, dp[i]);
        }

        return maxLength;
    }

    // Russian doll better (NlogN)
    public int findInsertionPosition(ArrayList<int[]> lis, int[] x){
        int left = 0, right = lis.size() - 1;

        while(left <= right){
            int mid = (left + right)/2;

            if(lis.get(mid)[0] < x[0] && lis.get(mid)[1] < x[1]){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;

        Arrays.sort(envelopes, (int[] a, int[] b) ->{
            if(a[0] == b[0]){
                return b[1] - a[1]; // process bigger elements before so that we have better chances of adding it in the end
            }

            return a[0] - b[0];
        });

        ArrayList<int[]> lis = new ArrayList<>();

        for(int i=0; i<n; i++){
            int insertPos = findInsertionPosition(lis, envelopes[i]);

            if(lis.size() == insertPos){
                lis.add(envelopes[i]);
            } else {
                lis.set(insertPos, envelopes[i]);
            }
        }

        return lis.size();
    }

}