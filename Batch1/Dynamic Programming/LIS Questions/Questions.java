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

    // Maximum sum increasing subs (https://www.geeksforgeeks.org/problems/maximum-sum-increasing-subsequence4749/1)
    public int maxSumIS(int arr[]) {
        int n = arr.length;

        int[] dp = new int[n];
        int res = 0;

        for(int i=0; i<n; i++){
            dp[i] = arr[i];

            for(int j=i-1; j>=0; j--){
                if(arr[j] < arr[i]){
                    dp[i] = Math.max(dp[i], dp[j] + arr[i]);
                }
            }

            res = Math.max(res, dp[i]);
        }

        return res;
    }

    // https://www.geeksforgeeks.org/problems/minimum-number-of-deletions-to-make-a-sorted-sequence3248/1
    // Minimum deletions to make array sorted
    public int minDeletions(int nums[], int n) {
        int lis_length = 0;
        int[] dp = new int[n];

        for(int idx=0; idx<nums.length; idx++){
            dp[idx] = 1;
            for(int j=idx-1; j>=0; j--){
                if(nums[j] < nums[idx]){
                    dp[idx] = Math.max(dp[idx], dp[j] + 1);
                }
            }

            lis_length = Math.max(lis_length, dp[idx]);
        }

        return n - lis_length;
    }

    // count of lis ===================
    // leetcode 673 =========================================
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;

        int[] dp = new int[n];
        int[] count = new int[n];

        for(int i=0; i<n; i++){
            dp[i] = 1; count[i] = 1;
            for(int j=i-1; j>=0; j--){
                if(nums[j] < nums[i]){
                    if(dp[i] < dp[j] + 1){
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    } else if(dp[i] == dp[j] + 1){
                        count[i] += count[j];
                    }
                }
            }
        }

        int lis_length = 0;
        int lis_count = 0;
        for(int i=0; i<n; i++){
            if(dp[i] > lis_length){
                lis_length = dp[i];
                lis_count = count[i];
            } else if(dp[i] == lis_length){
                lis_count += count[i];
            }
        }

        return lis_count;
    }

    // LIS in NLOGN ===============================================
    public int findInsertionIndex(ArrayList<Integer> dp, int ele){
        int left = 0, right = dp.size() - 1;

        while(left <= right){
            int mid = (left + right)/2;

            if(dp.get(mid) < ele){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    public int lengthOfLIS(int[] nums) {
        ArrayList<Integer> dp = new ArrayList<>();

        for(int i=0; i<nums.length; i++){
            int ele = nums[i];

            int idx = findInsertionIndex(dp, ele);

            if(idx == dp.size()){
                dp.add(ele);
            } else {
                dp.set(idx, ele);
            }
        }

        // System.out.println(dp);
        return dp.size();
    }


    // Russian doll envelopes =============================================================
    // Leetcode 354 (TLE)
    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        int[] dp = new int[n];

        Arrays.sort(envelopes, (int[] a, int[] b)->{
            if(a[0] == b[0]){
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });

        int res = 1;
        for(int i=0; i<n; i++){
            dp[i] = 1;
            for(int j=i-1; j>=0; j--){
                if(envelopes[j][0] < envelopes[i][0] && envelopes[j][1] < envelopes[i][1]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            res = Math.max(dp[i], res);
        }

        return res;
    }
    
    // Russian doll but in NlogN
    public int findInsertionIndex(ArrayList<int[]> dp, int[] ele){
        int left = 0, right = dp.size()-1;

        while(left <= right){
            int mid = (left + right)/2;

            if(dp.get(mid)[0] < ele[0] && dp.get(mid)[1] < ele[1]){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;

        Arrays.sort(envelopes, (int[] a, int[] b)->{
            if(a[0] == b[0]){
                return b[1] - a[1]; // in case of duplicate values, bigger element will have a better chance to get 
                                    // attached at the end
            }
            return a[0] - b[0];
        });

        ArrayList<int[]> dp = new ArrayList<>();

        int res = 1;
        for(int i=0; i<n; i++){
            int[] ele = envelopes[i];

            int idx = findInsertionIndex(dp, ele);

            if(dp.size() == idx){
                dp.add(ele);
            } else {
                dp.set(idx, ele);
            }
        }

        return dp.size();
    }




























    public static void main(String[] args){

    }
}