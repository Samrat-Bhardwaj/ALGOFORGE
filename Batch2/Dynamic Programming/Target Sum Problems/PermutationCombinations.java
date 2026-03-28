import java.util.Arrays;
class PermutationCombinations {
    // coin change permutations ====================================
    public static int totalPermuations_rec2(int[] coins, int tar, int idx){
        if(tar == 0){
            return 1;
        }

        int totalWays = 0;
        // yes call
        if(tar - coins[idx] >= 0){
            totalWays += totalPermuations_rec2(coins, tar-coins[idx], 0); // can start picking from start again
        }

        // no call
        if(idx + 1 < coins.length){
            totalWays += totalPermuations_rec2(coins, tar, idx+1);
        }
        
        return totalWays;
    }

    public static int totalPermuations_rec(int[] coins, int tar){
        if(tar == 0){
            return 1;
        }

        int totalWays = 0;
        for(int coin: coins){
            if(tar - coin >= 0){
                totalWays += totalPermuations_rec(coins, tar-coin);
            }
        }

        return totalWays;
    }

    public static int totalPermuations_memo(int[] coins, int tar, int[] dp){
        if(tar == 0){
            return dp[tar] = 1;
        }

        if(dp[tar] != -1) return dp[tar];

        int totalWays = 0;
        for(int coin: coins){
            if(tar - coin >= 0){
                totalWays += totalPermuations_rec(coins, tar-coin);
            }
        }

        return dp[tar] = totalWays;
    }

    public static int totalPermuations_tab(int[] coins, int Target, int[] dp){
        int n = coins.length;

        for(int tar=0; tar <= Target; tar++){
            if(tar == 0){
                dp[tar] = 1;
                continue;
            }

            int totalWays = 0;
            for(int coin: coins){
                if(tar - coin >= 0){
                    totalWays += dp[tar-coin]; //totalPermuations_rec(coins, tar-coin);
                }
            }

            dp[tar] = totalWays;
        }

        return dp[Target];
    }

    public static int totalPermuations_tabPretty(int[] coins, int Target){
        int n = coins.length;
        int[] dp = new int[Target + 1];

        for(int idx=0; idx<=Target; idx++){
            if(idx == 0){
                dp[idx] = 1;
            } else {
                for(int coin: coins){
                    if(idx - coin >= 0){
                        dp[idx] += dp[idx-coin];
                    }   
                }
            }
        }

        return dp[Target];
    }

    public static int findTotalPermutations(int[] coins, int tar){
        // return totalPermuations_rec(coins, tar);

        int[] dp = new int[tar+1];
        Arrays.fill(dp, -1);

        return totalPermuations_tabPretty(coins,tar);
    }

    // coin change combinations =====================================
    public static int totalCombinations_rec2(int[] coins, int tar, int idx){
        if(tar == 0){
            return 1;
        }

        int totalWays = 0;
        // yes call
        if(tar - coins[idx] >= 0){
            totalWays += totalCombinations_rec2(coins, tar-coins[idx], idx);
        }

        // no call
        if(idx + 1 < coins.length){
            totalWays += totalCombinations_rec2(coins, tar, idx+1);
        }
        
        return totalWays;
    }

    public static int totalCombinations_rec(int[] coins, int tar, int idx){
        if(tar == 0){
            return 1;
        }

        int totalWays = 0;
        for(; idx<coins.length; idx++){
            if(tar - coins[idx] >= 0){
                totalWays += totalCombinations_rec(coins, tar - coins[idx], idx);
            }
        }

        return totalWays;
    }

    public static int totalCombinations_memo(int[] coins, int tar, int idx, int[][] dp){
        if(tar == 0){
            return dp[idx][tar] = 1;
        }

        if(dp[idx][tar] != -1) return dp[idx][tar];

        int totalWays = 0;
        for(int j = idx; j<coins.length; j++){
            if(tar - coins[j] >= 0){
                totalWays += totalCombinations_memo(coins, tar - coins[idx], j, dp);
            }
        }

        return dp[idx][tar] = totalWays;
    }

    // combination in 1D tab
    public static int totalCombinations_tab(int[] coins, int target){
        int[] dp = new int[target+1];
        dp[0] = 1;

        for(int coin: coins){
            for(int idx=coin; idx<=target; idx++){
                dp[idx] += dp[idx-coin];
            }
        }

        return dp[target];
    }


    public static int findTotalCombinations(int[] coins, int tar){
        // return totalCombinations_rec2(coins,tar,0);

        int[][] dp = new int[coins.length][tar+1];
        for(int[] d: dp){
            Arrays.fill(d, -1);
        }

        return totalCombinations_tab(coins, tar);
    }

    // Leetcode 377 (Permutations)
    public int combinationSum4(int[] coins, int Target) {
        int n = coins.length;
        int[] dp = new int[Target + 1];

        for(int idx=0; idx<=Target; idx++){
            if(idx == 0){
                dp[idx] = 1;
            } else {
                for(int coin: coins){
                    if(idx - coin >= 0){
                        dp[idx] += dp[idx-coin];
                    }   
                }
            }
        }

        return dp[Target];
    }

    // Min coins required (Leetcode 322)
    public int minCoinsRequired_rec(int[] coins, int amount){
        if(amount == 0){
            return 0;
        }

        int minCoins = (int)(1e8);

        for(int coin: coins){
            if(amount - coin >= 0){
                minCoins = Math.min(minCoins, minCoinsRequired_rec(coins, amount-coin) + 1);
            }
        }

        return minCoins;
    }

    public int minCoinsRequired_memo(int[] coins, int amount, int[] dp){
        if(amount == 0){
            return dp[amount] = 0;
        }

        if(dp[amount] != 0) return dp[amount];

        int minCoins = (int)(1e8);

        for(int coin: coins){
            if(amount - coin >= 0){
                minCoins = Math.min(minCoins, minCoinsRequired_memo(coins, amount-coin, dp) + 1);
            }
        }

        return dp[amount] = minCoins;
    }

    public int minCoinsRequired_tab(int[] coins, int Amount){
        int[] dp = new int[Amount+1];

        for(int idx=0; idx <= Amount; idx++){
            if(idx == 0){
                dp[idx] = 0;
                continue;
            }

            int minCoins = (int)(1e8);

            for(int coin: coins){
                if(idx - coin >= 0){
                    minCoins = Math.min(minCoins, dp[idx-coin] + 1); //minCoinsRequired_memo(coins, amount-coin, dp) + 1);
                }
            }

            dp[idx] = minCoins;
        }

        return dp[Amount] >= (1e8) ? -1 : dp[Amount];
    }

    public int coinChange(int[] coins, int amount) {
        // int ans = minCoinsRequired_rec(coins, amount);
        // int[] dp = new int[amount+1];
        // int ans = minCoinsRequired_memo(coins,amount,dp);

        // return ans >= (int)(1e8) ? -1 : ans;

        return minCoinsRequired_tab(coins, amount);
    }























    public static void main(String[] args){
        int[] coins = {2,3,5};
        int target = 7;

        System.out.println(findTotalCombinations(coins, target));
    }
}