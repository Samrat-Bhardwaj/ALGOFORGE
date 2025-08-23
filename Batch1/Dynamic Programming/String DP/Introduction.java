class Introduction {
    // boolean dp where dp[i][j] = string from i to j is pallindrome or not
    public boolean[][] makeIsPallindromeDP(String s){
        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        for(int gap=0; gap < n; gap++){
            for(int i=0, j=gap; j<n; i++,j++){
                if(gap == 0){ // len = 1
                    dp[i][j] = true;
                } else if(gap == 1){ // len = 2
                    dp[i][j] = (s.charAt(i) == s.charAt(j));
                } else {
                    if(s.charAt(i) == s.charAt(j)){
                        dp[i][j] = dp[i+1][j-1];
                    }
                }
            }
        }

        return dp;
    }

    // leetcode 5 (longest pallindromic substring) ====================================
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        int si = 0;
        int maxLen = 0;

        for(int gap=0; gap < n; gap++){
            for(int i=0, j=gap; j<n; i++,j++){
                if(gap == 0){ // len = 1
                    dp[i][j] = true;
                } else if(gap == 1){ // len = 2
                    dp[i][j] = (s.charAt(i) == s.charAt(j));
                } else {
                    if(s.charAt(i) == s.charAt(j)){
                        dp[i][j] = dp[i+1][j-1];
                    }
                }

                if(dp[i][j] == true && maxLen < j-i+1){
                    si = i;
                    maxLen = j-i+1;
                }
            }
        }

        return s.substring(si, si+maxLen);
    }

    // Leetcode 516 (Longest pallindromic subsequence) =======================================
    public int lps_rec(String s, int si, int ei, int[][] dp){
        if(si == ei){
            return dp[si][ei] = 1;
        }
        if(si > ei){ // bb
            return dp[si][ei] = 0;
        }

        if(dp[si][ei] != 0) return dp[si][ei];
        

        if(s.charAt(si) == s.charAt(ei)){
            return dp[si][ei] = lps_rec(s, si+1, ei-1, dp) + 2;
        } else {
            return dp[si][ei] = Math.max(lps_rec(s, si, ei-1, dp), lps_rec(s,si+1, ei, dp));
        }
    }

    public int lps_rec(String s, int si, int ei, int[][] dp){
        if(si == ei){
            return dp[si][ei] = 1;
        }
        if(si > ei){ // bb
            return dp[si][ei] = 0;
        }

        if(dp[si][ei] != 0) return dp[si][ei];
        

        if(s.charAt(si) == s.charAt(ei)){
            return dp[si][ei] = lps_rec(s, si+1, ei-1, dp) + 2;
        } else {
            return dp[si][ei] = Math.max(lps_rec(s, si, ei-1, dp), lps_rec(s,si+1, ei, dp));
        }
    }

    public int lps_tab1(String s){
        int n = s.length();
        int[][] dp = new int[n][n];

        for(int gap=0; gap<n; gap++){
            for(int si=0, ei = gap; ei<n; si++, ei++){
                if(si == ei){
                    dp[si][ei] = 1;
                    continue;
                }
                

                if(s.charAt(si) == s.charAt(ei)){
                    dp[si][ei] = dp[si+1][ei-1] + 2; //lps_rec(s, si+1, ei-1, dp) + 2;
                } else {
                    dp[si][ei] = Math.max(dp[si][ei-1], dp[si+1][ei]); //Math.max(lps_rec(s, si, ei-1, dp), lps_rec(s,si+1, ei, dp));
                }
            }
        }

        return dp[0][n-1];
    }

    public int lps_tab_pretty(String s){
        int n = s.length();
        int[][] dp = new int[n][n];

        for(int gap=0; gap<n; gap++){
            for(int i=0, j = gap; j<n; i++, j++){
                if(gap == 0){ // len = 1
                    dp[i][j] = 1;
                } 
                // not neccessory 
                // else if(gap == 1){ // len 2, if both chars equal, pall length = 2, else 1
                //     dp[i][j] = (s.charAt(i) == s.charAt(j)) ? 2 : 1; // aa, bc
                // } 
                else {
                    if(s.charAt(i) == s.charAt(j)){
                        dp[i][j] = dp[i+1][j-1] + 2;
                    } else {
                        dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                    }
                }
            }
        }

        return dp[0][n-1];
    }

    public int longestPalindromeSubseq(String s) {
        int n = s.length();

        int[][] dp = new int[n][n];
        // return lps_rec(s,0,n-1,dp);
        return lps_tab_pretty(s);
    }
















    public static void main(String[] args){

    }
}