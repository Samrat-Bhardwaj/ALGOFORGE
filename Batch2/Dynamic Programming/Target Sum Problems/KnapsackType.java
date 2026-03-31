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

    static Boolean isSubsetSum(int arr[], int sum) {
        int n = arr.length;

        Boolean[][] dp = new Boolean[n+1][sum+1]; // default value null

        return isSubsetSum_memo(0,arr,sum,dp);
    }
}
