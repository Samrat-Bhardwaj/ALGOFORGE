class PandC {
    // coin change permutation single use
    public static void printPermutations(int[] coins, int tar){
        int n  = coins.length;

        boolean[] vis = new boolean[n];

        coinChangePermutations(coins,vis,tar,"");

    }

    public static void coinChangePermutations(int[] coins,boolean[] vis, int tar, String asf){
        if(tar == 0){
            System.out.println(asf);
            return;
        }

        if(tar < 0){
            return;
        }

        for(int i=0; i<coins.length; i++){
            if(vis[i] == false){
                vis[i] = true;
                coinChangePermutations(coins,vis,tar-coins[i],asf+coins[i]+",");
                vis[i] = false;
            }
        }
    }

    // coin change permutations multiple use
    public static void coinChangePermutationsMultiple(int[] coins, int tar, String asf){
        if(tar == 0){
            System.out.println(asf);
            return;
        }

        if(tar<0){
            return;
        }

        for(int i=0; i<coins.length; i++){
            coinChangePermutationsMultiple(coins,tar-coins[i],asf+coins[i]+",");
        }
    }

    // coin change combination single use
    public static void coinChangeCombination(int[] coins, int idx, int tar, String asf){
        if(tar < 0){
            return;
        }

        if(tar==0){
            System.out.println(asf);
            return;
        }

        for(int j=idx; j<coins.length; j++){
            coinChangeCombination(coins,j+1,tar-coins[j],asf+coins[j]+",");
        }
    }

    // coin change combinations multiple use
    public static void coinChangeCombinationMultiple(int[] coins, int idx, int tar, String asf){
        if(tar < 0){
            return;
        }

        if(tar == 0){
            System.out.println(asf);
            return;
        }

        for(int j = idx; j<coins.length; j++){
            coinChangeCombinationMultiple(coins, j, tar-coins[j], asf+coins[j]+",");
        }
        // coinChangeCombinationMultiple(coins, idx, tar-coins[idx], asf+coins[idx]+",");
        // coinChangeCombinationMultiple(coins, idx+1, tar, asf);
    }

    






















    public static void main(String[] args){
        int[] coins = {2,4,6,5,3};
        int tar = 10;

        // printPermutations(coins,tar);
        coinChangePermutationsMultiple(coins,tar,"");
        // coinChangeCombinationMultiple(coins,0,tar,"");
    }
}