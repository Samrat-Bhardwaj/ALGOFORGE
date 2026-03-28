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

    // coin change combinations ===================================
    public static int totalCombinations_rec(int[] coins, int tar, int idx){
        
    }

    public static int findTotalCombinations(int[] coins, int tar){
        return totalCombinations_rec(coins,tar,0);
    }























    public static void main(String[] args){
        int[] coins = {2,3,5};
        int target = 7;

        System.out.println(findTotalCombinations(coins, target));
    }
}