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

    public static void main(String[] args){
        int[] coins = {2,5,3};
        int tar = 7;

        System.out.println(coinChangePermutations(coins,tar));
    }
}