class Questions {
    // count of Pallindromic substring 
    public static int pallindromicSubstringDP(String str){
        int n = str.length();

        boolean[][] dp = new boolean[n][n];
        int count = 0 ;

        for(int gap=0; gap < n; gap++){ // len in consideration = gap + 1
            for(int i=0, j=gap; j<n; i++, j++){
                if(gap == 0){
                    dp[i][j] = true;
                } else if(gap == 1){
                    dp[i][j] = str.charAt(i) == str.charAt(j);
                } else {
                    if(str.charAt(i) == str.charAt(j)){
                        dp[i][j] = dp[i+1][j-1];
                    }
                }

                if(dp[i][j]){
                    System.out.println(str.substring(i,j+1));
                    count++;
                }
            }
        }

        return count;
    }

    // Leetcode 647 =======
    public int countSubstrings(String str) {
        int n = str.length();
        boolean[][] dp = new boolean[n][n];
        int count = 0 ;

        for(int gap=0; gap < n; gap++){ // len in consideration = gap + 1
            for(int i=0, j=gap; j<n; i++, j++){
                if(gap == 0){
                    dp[i][j] = true;
                } else if(gap == 1){
                    dp[i][j] = str.charAt(i) == str.charAt(j);
                } else {
                    if(str.charAt(i) == str.charAt(j)){
                        dp[i][j] = dp[i+1][j-1];
                    }
                }

                if(dp[i][j]){
                    // System.out.println(str.substring(i,j+1));
                    count++;
                }
            }
        }

        return count;
    }

    // Leetcode 5 ==================
    public String longestPalindrome(String str) {
        int n = str.length();

        boolean[][] dp = new boolean[n][n];
        int maxLength = 0;
        String ans = "";

        for(int gap=0; gap < n; gap++){ // len in consideration = gap + 1
            for(int i=0, j=gap; j<n; i++, j++){
                if(gap == 0){
                    dp[i][j] = true;
                } else if(gap == 1){
                    dp[i][j] = str.charAt(i) == str.charAt(j);
                } else {
                    if(str.charAt(i) == str.charAt(j)){
                        dp[i][j] = dp[i+1][j-1];
                    }
                }

                if(dp[i][j] && maxLength < (j-i + 1)){
                    maxLength = j - i + 1;
                    ans = str.substring(i,j+1);
                }
            }
        }

        return ans;
    }

    // Leetcode 516 (Longest Pallindromic Subsequence) ===================
    public int longestPalindromeSubseq_rec(String s, int si, int ei){
        if(si > ei){
            return 0;
        }

        if(si == ei){
            return 1;
        }

        int maxLength = 0;
        if(s.charAt(si) == s.charAt(ei)){
            maxLength = longestPalindromeSubseq_rec(s, si+1, ei-1) + 2;
        } else {
            maxLength = Math.max(longestPalindromeSubseq_rec(s,si,ei-1), longestPalindromeSubseq_rec(s,si+1,ei));
        }

        return maxLength;
    }

    public int longestPalindromeSubseq_memo(String s, int si, int ei, int[][] dp){
        if(si > ei){
            return 0;
        }

        if(si == ei){
            return dp[si][ei] = 1;
        }

        if(dp[si][ei] != 0) return dp[si][ei];

        int maxLength = 0;
        if(s.charAt(si) == s.charAt(ei)){
            maxLength = longestPalindromeSubseq_memo(s, si+1, ei-1, dp) + 2;
        } else {
            maxLength = Math.max(longestPalindromeSubseq_memo(s,si,ei-1,dp), longestPalindromeSubseq_memo(s,si+1,ei,dp));
        }

        return dp[si][ei] = maxLength;
    }

    public int longestPalindromeSubseq_tab(String s){
        int n = s.length();
        int[][] dp = new int[n][n];

        for(int gap = 0; gap<n; gap++){
            for(int si=0, ei=gap; ei < n; si++,ei++){
                if(si == ei){
                    dp[si][ei] = 1;
                    continue;
                }

                int maxLength = 0;
                if(s.charAt(si) == s.charAt(ei)){
                    maxLength = dp[si+1][ei-1] + 2; //longestPalindromeSubseq_memo(s, si+1, ei-1, dp) + 2;
                } else {
                    maxLength = Math.max(dp[si][ei-1], dp[si+1][ei]); //longestPalindromeSubseq_memo(s,si,ei-1,dp), longestPalindromeSubseq_memo(s,si+1,ei,dp));
                }

                dp[si][ei] = maxLength;
            }
        }

        return dp[0][n-1];
    }

    public int longestPalindromeSubseq(String s) {
        // int n = s.length();
        // int[][] dp = new int[n][n];

        // return longestPalindromeSubseq_memo(s,0,n-1,dp);
        return longestPalindromeSubseq_tab(s);
    }
















    public static void main(String[] args){
        String str = "abbccb";

        System.out.println(pallindromicSubstringDP(str));
    }
}