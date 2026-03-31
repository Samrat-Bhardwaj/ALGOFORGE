class KnapsackType {
    // https://www.geeksforgeeks.org/problems/subset-sum-problem-1611555638/1 (Target sum subset)
    public static Boolean isSubsetSum_rec(int idx, int[] arr, int tar){
        if(idx == arr.length){
            return tar == 0;
        }

        Boolean isPossible = false;
        // yes call
        if(tar - arr[idx] >= 0){
            isPossible = isSubsetSum_rec(idx+1, arr, tar - arr[idx]);
        }

        // no call
        isPossible = isPossible || isSubsetSum_rec(idx+1, arr, tar);

        return isPossible;
    }

    public static Boolean isSubsetSum_memo(int idx, int[] arr, int tar, Boolean[][] dp){
        if(idx == arr.length){
            return dp[idx][tar] = tar == 0;
        }
        
        if(dp[idx][tar] != null) return dp[idx][tar];

        Boolean isPossible = false;
        // yes call
        if(tar - arr[idx] >= 0){
            isPossible = isSubsetSum_memo(idx+1, arr, tar - arr[idx], dp);
        }

        // no call
        isPossible =  isPossible || isSubsetSum_memo(idx+1, arr, tar, dp) ;

        return dp[idx][tar] = isPossible;
    }

    // is it possible to get a subset from first "n" elements with sum = tar
    public static Boolean isSubsetSum_memo2(int n, int[] arr, int tar, Boolean[] dp){
        if(n == 0){
            return dp[n][tar] = tar == 0;
        }

        if(dp[n][tar] != null) return dp[n][tar];

        Boolean isPossible = false;
        // yes call
        if(tar - arr[n-1] >= 0){
            isPossible = isSubsetSum_memo2(n-1, arr, tar-arr[n-1], dp);
        }
        // no call
        isPossible = isPossible || isSubsetSum_memo2(n-1, arr, tar, dp);

        return dp[n][tar] = isPossible;
    }

    public static Boolean isSubsetSum_tab(int N, int[] arr, int Target, Boolean[][] dp){
        for(int n=0; n<=N; n++){
            for(int tar=0; tar<=Target; tar++){
                if(n == 0){
                    dp[n][tar] = tar == 0;
                    continue;
                }

                Boolean isPossible = false;
                // yes call
                if(tar - arr[n-1] >= 0){
                    isPossible = dp[n-1][tar-arr[n-1]]; //isSubsetSum_memo2(n-1, arr, tar-arr[n-1], dp);
                }
                // no call
                isPossible = isPossible || dp[n-1][tar]; //isSubsetSum_memo2(n-1, arr, tar, dp);

                dp[n][tar] = isPossible;
            }
        }

        return dp[N][Target];
    }

    static Boolean isSubsetSum(int arr[], int sum) {
        int n = arr.length;

        Boolean[][] dp = new Boolean[n+1][sum+1]; // default value null

        return isSubsetSum_tab(n,arr,sum,dp);
    }

    // Number of subsets with target = sum
    public int targetSumSubset_rec(int n, int[] nums, int tar){
        if(n == 0){
            return tar == 0 ? 1 : 0;
        }

        // yes call
        int totalSubsets = 0;
        if(tar - nums[n-1] >= 0){
            totalSubsets += targetSumSubset_rec(n-1, nums, tar - nums[n-1]);
        }

        // no call
        totalSubsets += targetSumSubset_rec(n-1, nums, tar);

        return totalSubsets;
    }

    public int targetSumSubset_memo(int n, int[] nums, int tar, int[][] dp){
        if(n == 0){
            return dp[n][tar] = tar == 0 ? 1 : 0;
        }

        if(dp[n][tar] != -1) return dp[n][tar];

        // yes call
        int totalSubsets = 0;
        if(tar - nums[n-1] >= 0){
            totalSubsets += targetSumSubset_memo(n-1, nums, tar - nums[n-1], dp);
        }

        // no call
        totalSubsets += targetSumSubset_memo(n-1, nums, tar, dp);

        return dp[n][tar] = totalSubsets;
    }

    public int targetSumSubset_tab(int N, int[] nums, int Target, int[][] dp){
        for(int n=0; n<=N; n++){
            for(int tar=0; tar<=Target; tar++){
                if(n == 0){
                    dp[n][tar] = tar == 0 ? 1 : 0;
                    continue;
                }

                // yes call
                int totalSubsets = 0;
                if(tar - nums[n-1] >= 0){
                    totalSubsets += dp[n-1][tar-nums[n-1]]; //targetSumSubset_memo(n-1, nums, tar - nums[n-1], dp);
                }

                // no call
                totalSubsets += dp[n-1][tar]; //targetSumSubset_memo(n-1, nums, tar, dp);

                dp[n][tar] = totalSubsets;
            }
        }

        return dp[N][Target];
    }

    public int perfectSum(int[] nums, int target) {
        int n = nums.length;

        int[][] dp = new int[n+1][target+1];
        // for(int[] d: dp){
        //     Arrays.fill(d,-1);
        // }

        return targetSumSubset_tab(n, nums, target, dp);
    }

    // Leetcode 416 ====================
    public static Boolean isSubsetSum_tab(int N, int[] arr, int Target, Boolean[][] dp){
        for(int n=0; n<=N; n++){
            for(int tar=0; tar<=Target; tar++){
                if(n == 0){
                    dp[n][tar] = tar == 0;
                    continue;
                }

                Boolean isPossible = false;
                // yes call
                if(tar - arr[n-1] >= 0){
                    isPossible = dp[n-1][tar-arr[n-1]]; //isSubsetSum_memo2(n-1, arr, tar-arr[n-1], dp);
                }
                // no call
                isPossible = isPossible || dp[n-1][tar]; //isSubsetSum_memo2(n-1, arr, tar, dp);

                dp[n][tar] = isPossible;
            }
        }

        return dp[N][Target];
    }
    
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for(int e: nums){
            sum += e;
        }

        if(sum % 2 != 0) return false;

        int tar = sum/2;

        return isSubsetSum_tab(n, nums, tar, new Boolean[n+1][tar+1]);
    }

    // 0-1 Knapsack (https://www.geeksforgeeks.org/problems/0-1-knapsack-problem0945/1)
    public int knapsack_rec(int n, int cap, int[] val, int[] wt){
        if(n == 0){
            return 0;
        }

        int maxProfit = 0;

        // yes call
        if(cap >= wt[n-1]){ // capacity should be greater the weight we are about to pick
            maxProfit = knapsack_rec(n-1, cap - wt[n-1], val, wt) + val[n-1];
        }

        // no call
        maxProfit = Math.max(maxProfit, knapsack_rec(n-1,cap,val,wt));

        return maxProfit;
    }

    public int knapsack_memo(int n, int cap, int[] val, int[] wt, int[][] dp){
        if(n == 0){
            return dp[n][cap] = 0;
        }

        if(dp[n][cap] != -1) return dp[n][cap];

        int maxProfit = 0;

        // yes call
        if(cap >= wt[n-1]){ // capacity should be greater the weight we are about to pick
            maxProfit = knapsack_memo(n-1, cap - wt[n-1], val, wt, dp) + val[n-1];
        }

        // no call
        maxProfit = Math.max(maxProfit, knapsack_memo(n-1,cap,val,wt, dp));

        return dp[n][cap] = maxProfit;
    }

    public int knapsack_tab(int N, int totalCapacity, int[] val, int[] wt, int[][] dp){
        for(int n=0; n<=N; n++){
            for(int cap=0; cap <= totalCapacity; cap++){
                if(n == 0){
                    dp[n][cap] = 0;
                    continue;
                }

                int maxProfit = 0;

                // yes call
                if(cap - wt[n-1] >= 0){ // capacity should be greater the weight we are about to pick
                    maxProfit = dp[n-1][cap-wt[n-1]] + val[n-1];//knapsack_memo(n-1, cap - wt[n-1], val, wt, dp) + val[n-1];
                }

                // no call
                maxProfit = Math.max(maxProfit, dp[n-1][cap]); //knapsack_memo(n-1,cap,val,wt, dp));

                dp[n][cap] = maxProfit;
            }
        }

        return dp[N][totalCapacity];
    }

    public int knapsack(int W, int val[], int wt[]) {
        int n = val.length;

        int[][] dp = new int[n+1][W+1];
        // for(int[] d: dp){
        //     Arrays.fill(d, -1);
        // }

        return knapsack_tab(n,W,val,wt,dp);
    }
    
}
