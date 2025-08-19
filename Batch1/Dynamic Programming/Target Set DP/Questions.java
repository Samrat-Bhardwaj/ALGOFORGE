import java.util.Arrays;
class Questions {
    public static int coinChangePermutations_rec2(int[] coins, int tar, int idx){
        if(tar == 0){
            return 1;
        }

        int totalWays = 0;
        if(tar - coins[idx] >= 0){ // current idx said yes to be included in permutation
            totalWays += coinChangePermutations_rec2(coins, tar-coins[idx], 0);
        }

        if(idx + 1 < coins.length){
            totalWays += coinChangePermutations_rec2(coins,tar,idx+1);
        }
        
        return totalWays;
    }

    public static int coinChangePermutations_rec(int[] coins, int tar){
        if(tar == 0){
            return 1;
        }

        int totalWays = 0;
        for(int coin: coins){
            if(tar - coin >= 0){
                totalWays += coinChangePermutations_rec(coins,tar-coin);
            }
        }

        return totalWays;
    }

    public static int coinChangePermutations_memo(int[] coins, int tar, int[] dp){
        if(tar == 0){
            return dp[tar] = 1;
        }

        if(dp[tar] != -1) return dp[tar];

        int totalWays = 0;
        for(int coin: coins){
            if(tar - coin >= 0){
                totalWays += coinChangePermutations_memo(coins,tar-coin,dp);
            }
        }

        return dp[tar] = totalWays;
    }

    public static int coinChangePermutations_tab(int[] coins, int Tar, int[] dp){
        for(int tar=0; tar<=Tar; tar++){
            if(tar == 0){
                dp[tar] = 1;
                continue;
            }

            int totalWays = 0;
            for(int coin: coins){
                if(tar - coin >= 0){
                    totalWays += dp[tar-coin]; //coinChangePermutations_memo(coins,tar-coin,dp);
                }
            }

            dp[tar] = totalWays;
        }

        return dp[Tar];
    }

    public static int coinChangePermutations_tab_pretty(int[] coins, int target){
        int[] dp = new int[target+1];

        for(int idx=0; idx<=target; idx++){
            if(idx == 0){
                dp[idx] = 1;
                continue;
            }

            int totalWays = 0;
            for(int coin: coins){
                if(idx - coin >= 0){
                    totalWays += dp[idx-coin];
                }
            }

            dp[idx] = totalWays;
        }

        return dp[target];
    }



    public static int coinChangePermutations(int[] coins, int tar){
        // return coinChangePermutations_rec2(coins,tar,0,"");

        int[] dp = new int[tar+1];
        Arrays.fill(dp,-1);
        // return coinChangePermutations_rec(coins,tar);
        // return coinChangePermutations_memo(coins,tar,dp);
        return coinChangePermutations_tab(coins,tar,dp);
    }


    // Coin change combination ==================================================
    public int coinChangeCombination_rec(int[] coins, int tar, int idx,int[][] dp){
        if(tar == 0){
            return dp[tar][idx] = 1;
        }

        if(dp[tar][idx] != -1) return dp[tar][idx];

        int totalWays = 0;
        for(; idx<coins.length; idx++){
            if(tar - coins[idx] >= 0){
                totalWays += coinChangeCombination_rec(coins, tar-coins[idx], idx, dp);
            }
        }

        return dp[tar][idx] = totalWays;
    }

    public static int coinChangeCombination_tab(int[] coins, int Tar){
        int[] dp = new int[Tar + 1];
        dp[0] = 1;

        for(int coin: coins){
            for(int idx=coin; idx<=Tar; idx++){
                dp[idx] += dp[idx-coin];
            }
        }
        
        return dp[Tar];
    }

    public int change(int amount, int[] coins) {
        // int[][] dp = new int[amount+1][coins.length+1];
        // for(int[] d: dp){
        //     Arrays.fill(d,-1);
        // }
        // return coinChangeCombination_rec(coins,amount,0,dp);
        return coinChangeCombination_tab(coins,amount);
    }

    // leetcode 377 ==================================
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target+1];

        for(int idx=0; idx<=target; idx++){
            if(idx == 0){
                dp[idx] = 1;
                continue;
            }

            int totalWays = 0;
            for(int coin: nums){
                if(idx - coin >= 0){
                    totalWays += dp[idx-coin];
                }
            }

            dp[idx] = totalWays;
        }

        return dp[target];
    }

    // leetcode 322 (minimum coins to make sum) ========================================
    public int minCoinsRequired_rec(int[] coins, int target, int[] dp){
        if(target == 0){
            return dp[target] = 0;
        }

        if(dp[target] != -1) return dp[target];

        int minCoins = (int)(1e8);
        for(int coin: coins){
            int currCoins = 1e8;

            if(target - coin >= 0){
                currCoins = minCoinsRequired_rec(coins,target-coin, dp);
            }

            minCoins = Math.min(currCoins+1, minCoins);
        }

        return dp[target] = minCoins;
    }


    public int minCoinsRequired_tab(int[] coins, int Target, int[] dp){
        for(int target=0; target<=Target, target++){
            if(target == 0){
                dp[target] = 0;
                continue;
            }

            int minCoins = (int)(1e8);
            for(int coin: coins){
                int currCoins = (int)1e8;

                if(target - coin >= 0){
                    currCoins = dp[target-coin]; //minCoinsRequired_rec(coins,target-coin, dp);
                }

                minCoins = Math.min(currCoins+1, minCoins);
            }

            dp[target] = minCoins;
        }

        return dp[Target];
    }

    public int minCoinsRequired_tab_pretty(int[] coins, int Target, int[] dp){
        Arrays.fill(dp, int(1e8));

        dp[0] = 0;
        for(int target=1; target<=Target, target++){
            for(int coin: coins){ // target = 5, coin = 2
                if(target - coin >= 0){
                    dp[target] = Math.min(dp[target-coin] + 1, dp[target]);
                }
            }
        }

        return dp[Target];
    }

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];

        int totalCoins =  minCoinsRequired_tab(coins,amount,dp);

        return totalCoins >= (1e8) ? -1 : totalCoins;
    }

    // (Target Sum subset) https://www.geeksforgeeks.org/problems/subset-sum-problem-1611555638/1 ====================
    public static boolean isSubsetSum_rec(int[] arr, int tar, int idx, Boolean[][] dp){
        if(tar == 0 || idx == arr.length){
            return dp[idx][tar] = tar == 0 ? true : false;
        }

        if(dp[idx][tar] != null) return dp[idx][tar];

        boolean isPossible = false;
        if(tar - arr[idx] >= 0){ // yes call
            isPossible = isSubsetSum_rec(arr, tar - arr[idx], idx + 1, dp);
        }

        isPossible = isPossible || isSubsetSum_rec(arr, tar, idx + 1, dp); // no call

        return dp[idx][tar] = isPossible;
    }

    static Boolean isSubsetSum(int arr[], int sum) {
        // by default null is stored at every place
        Boolean[][] dp = new Boolean[arr.length+1][sum+1];

        return isSubsetSum_rec(arr, sum, 0, dp);
    }

    //Count of subsets with sum = target, https://www.geeksforgeeks.org/problems/perfect-sum-problem5633/1
    public static int targetSumSubset_rec(int[] arr, int tar, int idx, int[][] dp){
        if(idx == arr.length){
            return dp[idx][tar] = tar == 0 ? 1 : 0;
        }

        if(dp[idx][tar] != -1) return dp[idx][tar];

        int count = 0;
        if(tar - arr[idx] >= 0){ // yes call
            count = targetSumSubset_rec(arr, tar - arr[idx], idx + 1, dp);
        }

        count += targetSumSubset_rec(arr, tar, idx + 1, dp); // no call

        return dp[idx][tar] = count;
    }

    public static int targetSumSubset_tab(int[] arr, int Tar, int idx, int[][] dp){
        for(idx = arr.length; idx>=0; idx--){
            for(int tar = 0; tar <= Tar; tar++){
                if(idx == arr.length){
                    dp[idx][tar] = tar == 0 ? 1 : 0;
                    continue;
                }

                int count = 0;
                if(tar - arr[idx] >= 0){ // yes call
                    count = dp[idx+1][tar-arr[idx]]; //targetSumSubset_rec(arr, tar - arr[idx], idx + 1, dp);
                }

                count += dp[idx+1][tar]; //targetSumSubset_rec(arr, tar, idx + 1, dp); // no call

                dp[idx][tar] = count;
            }
        }

        return dp[0][Tar];
    }

    public int perfectSum(int[] nums, int target) {
        int n = nums.length;
        int[][] dp = new int[n+1][target+1];

        for(int[] d: dp) Arrays.fill(d,-1);

        return targetSumSubset_tab(nums,target,0,dp);
    }

















    public static void main(String[] args){
        int[] coins = {2,5,3};
        int tar = 7;

        System.out.println(coinChangePermutations(coins,tar));
    }
}