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

    //Homework =  Generate longest pallindromic subsequence string =====================

    public int numDistinct_rec(int n, int m,String s, String t, int[][] dp){
        if(m == 0){
            return dp[n][m] = 1;
        }

        if(n < m){
            return dp[n][m] = 0;
        }

        if(dp[n][m] != -1) return dp[n][m];

        int count = 0;
        if(s.charAt(n-1) == t.charAt(m-1)){ // you can include or not include curr char
            count = numDistinct_rec(n-1,m-1,s,t,dp) + numDistinct_rec(n-1,m,s,t,dp);
        } else { // can't include curr char
            count = numDistinct_rec(n-1,m,s,t,dp);
        }

        return dp[n][m] = count;
    }

    public int numDistinct_tab(int N, int M,String s, String t, int[][] dp){
        for(int m=0; m<=M; m++){
            for(int n=0; n<=N; n++){
                if(m == 0){
                    dp[n][m] = 1;
                } else if(n < m){
                    dp[n][m] = 0;
                } else {
                    if(s.charAt(n-1) == t.charAt(m-1)){
                        dp[n][m] = dp[n-1][m-1] + dp[n-1][m];
                    } else {
                        dp[n][m] = dp[n-1][m];
                    }
                }
            }
        }

        return dp[N][M];
    }

    public int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length(); 

        int[][] dp = new int[n+1][m+1];
        // for(int[] d: dp) Arrays.fill(d, -1);

        // return numDistinct_rec(n,m,s,t,dp);
        return numDistinct_tab(n,m,s,t,dp);
    }

    // Longest common subsequence (LCS) (Leetcode 1143) =======================================
    public int lcs_rec(int n, int m, String s1, String s2, int[][] dp){
        if(n == 0 || m == 0){
            return dp[n][m] = 0;
        }

        if(dp[n][m] != -1) return dp[n][m];

        int ans = 0;
        if(s1.charAt(n-1) == s2.charAt(m-1)){
            ans = lcs_rec(n-1,m-1,s1,s2,dp) + 1;
        } else {
            ans = Math.max(lcs_rec(n-1,m,s1,s2,dp), lcs_rec(n,m-1,s1,s2,dp));
        }

        return dp[n][m] = ans;
    }

    public int lcs_tab(int n, int m, String s1, String s2, int[][] dp){
        
        for(int i=0; i<=n; i++){
            for(int j=0; j<=m; j++){
                if(i==0 || j==0){
                    dp[i][j] = 0;
                } else {
                    if(s1.charAt(i-1) == s2.charAt(j-1)){
                        dp[i][j] = dp[i-1][j-1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                    }
                }
            }
        }

        return dp[n][m];
    }

    public int lcs_tabWithString(int n, int m, String s1, String s2, int[][] dp){
        String[][] sdp = new int[n+1][m+1];

        for(int i=0; i<=n; i++){
            for(int j=0; j<=m; j++){
                if(i==0 || j==0){
                    dp[i][j] = 0;
                    sdp[i][j] = ""; 
                } else {
                    if(s1.charAt(i-1) == s2.charAt(j-1)){
                        dp[i][j] = dp[i-1][j-1] + 1;
                        sdp[i][j] = sdp[i-1][j-1] + s1.charAt(i-1);
                    } else if(dp[i-1][j] < dp[i][j-1]){
                        dp[i][j] = dp[i][j-1];
                        sdp[i][j] = sdp[i][j-1];
                    } else {
                        dp[i][j] = dp[i-1][j];
                        sdp[i][j] = sdp[i-1][j];
                    }
                }
            }
        }

        System.out.println(sdp[n][m]);
        return dp[n][m];
    }

    public int longestCommonSubsequence(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        int[][] dp = new int[n+1][m+1];
        // for(int[] d: dp) Arrays.fill(d, -1);

        // return lcs_rec(n,m,s1,s2,dp);
        return lcs_tabWithString(n,m,s1,s2,dp);
    }

    // leetcode 1035 (Max uncrossed lines) ================================
    public int lcs_tab(int n, int m, int[] nums1, int[] nums2){
        int[][] dp = new int[n+1][m+1];

        for(int i=0; i<=n; i++){
            for(int j=0; j<=m; j++){
                if(i==0 || j==0){
                    dp[i][j] = 0;
                } else {
                    if(nums1[i-1] == nums2[j-1]){
                        dp[i][j] = dp[i-1][j-1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                    }
                }
            }
        }

        return dp[n][m];
    }

    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;

        return lcs_tab(n,m,nums1,nums2);
    }

    // Edit distance (leetcode 72) ======================================
    public int minDistance_rec(String s1, String s2, int n, int m, int[][] dp){
        if(n == 0 || m == 0){
            return dp[n][m] = n == 0 ? m : n;
        }

        if(dp[n][m] != -1) return dp[n][m];

        if(s1.charAt(n-1) == s2.charAt(m-1)){
            return dp[n][m] = minDistance_rec(s1,s2,n-1,m-1,dp);
        }    

        int insert = minDistance_rec(s1,s2,n,m-1,dp);
        int delete = minDistance_rec(s1,s2,n-1,m,dp);
        int replace = minDistance_rec(s1,s2,n-1,m-1,dp);

        return dp[n][m] = Math.min(insert, Math.min(delete, replace)) + 1;
    }

    public int minDistance_tab(String s1, String s2, int N, int M, int[][] dp){
        for(int n=0; n<=N; n++){
            for(int m=0; m<=M; m++){
                if(n==0){
                    dp[n][m] = m;
                } else if(m==0){
                    dp[n][m] = n;
                } else if(s1.charAt(n-1) == s2.charAt(m-1)){
                    dp[n][m] = dp[n-1][m-1];
                } else {
                    dp[n][m] = 1 + Math.min(dp[n][m-1], Math.min(dp[n-1][m], dp[n-1][m-1]));
                }
            }
        }

        return dp[N][M];
    }

    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int[][] dp = new int[n+1][m+1];
        // for(int[] d: dp) Arrays.fill(d, -1);

        // return minDistance_rec(word1,word2,n,m,dp);
        return minDistance_tab(word1,word2,n,m,dp);
    }

    public boolean isMatch_rec(String s, String p, int n, int m,Boolean[][]dp){
        if(n == 0 || m == 0){
            if(n == 0 && m == 0) return dp[n][m] = true;
            else if(m == 1 && p.charAt(m-1) == '*') return dp[n][m] = true;

            return dp[n][m] = false;
        }

        if(dp[n][m] != null) return dp[n][m];

        boolean res = false;
        if(s.charAt(n-1) == p.charAt(m-1) || p.charAt(m-1) == '?'){
            res = isMatch_rec(s,p,n-1,m-1,dp);
        } else if(p.charAt(m-1) == '*'){
            res = isMatch_rec(s,p,n-1,m) || isMatch_rec(s,p,n,m-1);
        }

        return dp[n][m] = res;
    }

    public boolean isMatch_tab(String s, String p, int N, int M, boolean[][]dp){
        for(int i=0; i<=N; i++){
            for(int j=0; j<=M; j++){
                if(i==0 && j==0){
                    dp[i][j] = true;
                } else if(i==0 && j==1 && p.charAt(j-1) == '*'){
                    dp[i][j] = true;
                } else if(i == 0 || j == 0){
                    dp[i][j] = false;
                } else if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?'){
                    dp[i][j] = dp[i-1][j-1];
                } else if(p.charAt(j-1) == '*'){
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                } 
            }
        }

        return dp[N][M];
    }

    public String removeConsecutiveStars(String str){
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<str.length(); i++){
            char ch = str.charAt(i);

            while(i<str.length() && str.charAt(i) == '*') i++;

            sb.append(ch);
            if(ch == '*') i--;
        }

        return sb.toString();
    }

    public boolean isMatch(String s, String p) {
        p = removeConsecutiveStars(p);
        int n = s.length();
        int m = p.length();

        // Boolean[][] dp = new Boolean[n+1][m+1]; // by default = null

        // return isMatch_rec(s,p,n,m,dp);

        boolean[][] dp = new boolean[n+1][m+1]; // by default = null

        return isMatch_tab(s,p,n,m,dp);
    }

    // distinct Subsequences 2 (leetcode 940) ==========================================================
    public int distinctSubseqII(String s) {
        int n = s.length();
        long mod = (long)(1e9+7);

        long[] dp = new long[n + 1];

        int[] loc = new int[26];
        Arrays.fill(loc, -1);

        for(int i=1; i<=n; i++){
            char ch = s.charAt(i-1);

            long res = (2*dp[i-1] + 1)%mod; 
            int lastOccurence = loc[ch-'a'];

            if(lastOccurence != -1){
                res = (res - dp[lastOccurence-1] - 1)+mod; // removing duplicate subs, +mod in case negative res
            }

            loc[ch-'a'] = i;
            dp[i] = res % mod;
        }

        return (int)(dp[n]); 
    } 

    // https://www.geeksforgeeks.org/problems/count-subsequences-of-type-ai-bj-ck4425/1 
    // Subsequence of type aibjck
    public int fun(String s) {
        long sa = 0;
        long sab = 0;
        long sabc = 0;

        long mod = (long)(1e9+7);

        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == 'a'){
                sa = 2*sa + 1;
            } else if(s.charAt(i) == 'b'){
                sab = 2*sab + sa; // twice of subs ending with a^i b^j + subs ending at a
            } else if(s.charAt(i) == 'c'){
                sabc = 2*sabc + sab;
            }

            sa = sa % mod;
            sab = sab % mod;
            sabc = sabc % mod;
        }

        return (int)(sabc);
    }




























    public static void main(String[] args){

    }
}