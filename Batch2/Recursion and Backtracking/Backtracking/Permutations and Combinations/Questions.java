class Questions {
    // Coin change permutations, one coin can be used one time 
    public static void coinChangePermutations(int[] coins, int tar, boolean[] vis, String asf){
        if(tar < 0) return;

        if(tar == 0){
            System.out.println(asf);
            return;
        }

        for(int idx=0; idx < coins.length; idx++){
            if(vis[idx] == false){
                vis[idx] = true;

                coinChangePermutations(coins, tar-coins[idx], vis, asf + coins[idx] + ",");
                
                vis[idx] = false;
            }
        }
    }

    // Coin change permutations, one coin can be used multiple times 
    public static void coinChangePermutationsMultiple(int[] coins, int tar, String asf){
        if(tar < 0) return;

        if(tar == 0){
            System.out.println(asf);
            return;
        }

        for(int idx=0; idx < coins.length; idx++){
            coinChangePermutationsMultiple(coins, tar-coins[idx], asf + coins[idx] + ",");    
        }
    }

    // Coin change combinations, one coin can be used one time
    public static void printTargetSumSubsets(int[] arr, int idx, int tar, String sub){
        if(idx == arr.length){
            if(tar == 0){
                System.out.println(sub);
            }
            return;
        }

        printTargetSumSubsets(arr, idx+1, tar-arr[idx], sub + arr[idx] + ","); // yes call
        printTargetSumSubsets(arr, idx+1, tar, sub); // no call
    }

    // Coin change combinations, one coin can be used one time
    public static void coinChangeCombinations(int[] coins, int idx, int tar, String asf){
        if(tar < 0) return;

        if(tar == 0){
            System.out.println(asf);
            return;
        }

        for(int j=idx; j<coins.length; j++){
            coinChangeCombinations(coins,j+1,tar-coins[j],asf+coins[j]+",");
        }
    }

    // coin change combination, one coin can be used multiple time
    public static void coinChangeCombinationsMultiple(int[] coins, int idx, int tar, String asf){
        if(tar < 0) return;
        if(tar == 0){
            System.out.println(asf);
            return;
        }

        if(idx == coins.length) return;

        // coins[idx] said yes and we can use it again
        coinChangeCombinationsMultiple(coins, idx, tar-coins[idx], asf + coins[idx] + ",");

        // coins[idx] said no and all the times coins[idx] could've said yes is done
        coinChangeCombinationsMultiple(coins, idx + 1, tar, asf);
    }

    // coin change combination, one coin can be used multiple time using for loop
    public static void coinChangeCombinationsMultiple2(int[] coins, int idx, int tar, String asf){
        if(tar < 0) return;
        if(tar == 0){
            System.out.println(asf);
            return;
        }

        for(int j = idx; j<coins.length; j++){
            coinChangeCombinationsMultiple2(coins, j, tar-coins[j], asf+coins[j]+","); // you can use jth coin again
        }
    }

    





























    public static void main(String[] args){
        int[] coins = {2,3,4,6,5};
        // boolean[] vis = new boolean[coins.length];

        // coinChangePermutations(coins,10,vis,"");
        // coinChangePermutationsMultiple(coins,10,"");

        // printTargetSumSubsets(coins,0,10,"");
        coinChangeCombinationsMultiple2(coins,0,10,"");
    }
}