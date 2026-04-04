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

    // Leetcode 115 (Distinct Subsequences) ===================
    public int numDistinct_rec(String s, String t, int n, int m){
        if(m == 0){
            return 1;
        }
        if(n == 0){
            return 0;
        }
        if(n < m){
            return 0;
        }

        int ans = 0;

        if(s.charAt(n-1) == t.charAt(m-1)){
            ans =  numDistinct_rec(s,t,n-1,m-1) + numDistinct_rec(s,t,n-1,m);
        } else {
            ans = numDistinct_rec(s,t,n-1,m);
        }

        return ans;
    }

    public int numDistinct_memo(String s, String t, int n, int m, int[][] dp){
        if(m == 0){
            return dp[n][m] = 1;
        }
        if(n == 0){
            return dp[n][m] = 0;
        }
        if(n < m){
            return dp[n][m] = 0;
        }

        if(dp[n][m] != -1) return dp[n][m];

        int ans = 0;

        if(s.charAt(n-1) == t.charAt(m-1)){
            ans =  numDistinct_memo(s,t,n-1,m-1,dp) + numDistinct_memo(s,t,n-1,m,dp);
        } else {
            ans = numDistinct_memo(s,t,n-1,m,dp);
        }

        return dp[n][m] = ans;
    }

    public int numDistinct_tab(String s, String t, int N, int M){
        int[][] dp = new int[N+1][M+1];

        for(int n=0; n<=N; n++){
            for(int m=0; m<=M; m++){
                if(m == 0){
                    dp[n][m] = 1;
                    continue;
                }
                if(n == 0){
                    dp[n][m] = 0;
                    continue;
                }
                if(n < m){
                    dp[n][m] = 0;
                    continue;
                }

                int ans = 0;

                if(s.charAt(n-1) == t.charAt(m-1)){
                    ans =  dp[n-1][m-1] + dp[n-1][m]; //numDistinct_memo(s,t,n-1,m-1,dp) + numDistinct_memo(s,t,n-1,m,dp);
                } else {
                    ans = dp[n-1][m]; //numDistinct_memo(s,t,n-1,m,dp);
                }

                dp[n][m] = ans;
            }
        }

        return dp[N][M];
    }

    public int numDistinct_tab(String s, String t, int N, int M){
        int[][] dp = new int[N+1][M+1];

        for(int i=0; i<=N; i++){
            for(int j=0; j<=M; j++){
                if(j == 0){
                    dp[i][j] = 1;
                } else if(i == 0){
                    dp[i][j] = 0;
                } else if(i < j){
                    dp[i][j] = 0;
                } else {
                    if(s.charAt(i-1) == t.charAt(j-1)){
                        dp[i][j] =  dp[i-1][j-1] + dp[i-1][j];
                    } else {
                        dp[i][j] = dp[i-1][j]; 
                    }
                }
            }
        }

        return dp[N][M];
    }

    public int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();

        // int[][] dp = new int[n+1][m+1];
        // for(int[] d: dp){
        //     Arrays.fill(d, -1);
        // }

        // return numDistinct_memo(s,t,n,m);
        return numDistinct_tab(s,t,n,m);
    }

    // Longest common subsequence (Leetcode 1143) =================
    public int longestCommonSubsequence_memo(String a, String b, int n, int m, int[][] dp){
        if(n == 0 || m == 0){
            return dp[n][m] = 0;
        }

        if(dp[n][m] != -1) return dp[n][m];

        int ans = 0;
        if(a.charAt(n-1) == b.charAt(m-1)){
            ans = longestCommonSubsequence_memo(a,b,n-1,m-1,dp) + 1;
        } else {
            ans = Math.max(longestCommonSubsequence_memo(a,b,n-1,m,dp), longestCommonSubsequence_memo(a,b,n,m-1,dp));
        }

        return dp[n][m] = ans;
    }

    public int longestCommonSubsequence_tab(String a, String b, int n, int m){
        int[][] dp = new int[n+1][m+1];

        for(int i=0; i<=n; i++){
            for(int j=0; j<=m; j++){
                if(i == 0 || j == 0){
                    dp[i][j] = 0;
                    continue;
                }

                if(a.charAt(i-1) == b.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1; //longestCommonSubsequence_memo(a,b,n-1,m-1,dp) + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]); //longestCommonSubsequence_memo(a,b,n-1,m,dp), longestCommonSubsequence_memo(a,b,n,m-1,dp));
                }
            }
        }

        return dp[n][m];
    }

    public int longestCommonSubsequence_tab(String a, String b, int n, int m){
        int[][] dp = new int[n+1][m+1];
        String[][] sdp = new String[n+1][m+1];

        for(int i=0; i<=n; i++){
            for(int j=0; j<=m; j++){
                if(i == 0 || j == 0){
                    dp[i][j] = 0;
                    sdp[i][j] = "";
                    continue;
                }

                if(a.charAt(i-1) == b.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1; //longestCommonSubsequence_memo(a,b,n-1,m-1,dp) + 1;
                    sdp[i][j] = sdp[i-1][j-1] + a.charAt(i-1);
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                    if(dp[i-1][j] > dp[i][j-1]){
                        sdp[i][j] = sdp[i-1][j];
                    } else {
                        sdp[i][j] = sdp[i][j-1];
                    }
                }
            }
        }

        System.out.println(sdp[n][m]);
        return dp[n][m];
    }

    public int longestCommonSubsequence(String a, String b) {
        int n = a.length();
        int m = b.length();

        // int[][] dp = new int[n+1][m+1];
        // for(int[] d: dp){
        //     Arrays.fill(d, -1);
        // }

        return longestCommonSubsequence_tab(a,b,n,m);
    }

    // Leetcode 583 (Delete operations)
    public int minDistance(String a, String b) {
        int n = a.length();
        int m = b.length();

        int lcs_len = longestCommonSubsequence_tab(a,b,n,m);

        return (n - lcs_len) + (m - lcs_len); // delete everything except lcs
    }

    // Leetcode 72 (Edit distance) ============================
    public int minDistance_rec(String a, String b, int n, int m, int[][] dp){
        if(n == 0 || m == 0){
            return dp[n][m] = n == 0 ? m : n;
        }

        if(dp[n][m] != -1) return dp[n][m];

        int ans = 0;
        if(a.charAt(n-1) == b.charAt(m-1)){
            ans = minDistance_rec(a,b,n-1,m-1,dp);
        } else {
            int insert = minDistance_rec(a,b,n,m-1,dp);
            int delete = minDistance_rec(a,b,n-1,m,dp);
            int replace = minDistance_rec(a,b,n-1,m-1,dp);

            ans = Math.min(insert, Math.min(delete, replace)) + 1;
        }

        return dp[n][m] = ans;
    }

    public int minDistance_tab(String a, String b, int n, int m){
        int[][] dp = new int[n+1][m+1];

        // dp[i][j] = minimum number of operations to convert first i letters of string a into first j letters of string b
        
        for(int i=0; i<=n; i++){
            for(int j=0; j<=m; j++){
                if(i==0){
                    dp[i][j] = j;
                } else if(j==0){
                    dp[i][j] = i;
                } else if(a.charAt(i-1) == b.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    int insert = dp[i][j-1];
                    int delete = dp[i-1][j];
                    int replace = dp[i-1][j-1];

                    dp[i][j] = Math.min(insert, Math.min(delete, replace)) + 1;
                }
            }
        }

        return dp[n][m];
    }

    public int minDistance(String a, String b) {
        int n = a.length();
        int m = b.length();

        // int[][] dp = new int[n+1][m+1];
        // for(int[] d: dp){
        //     Arrays.fill(d, -1);
        // }

        // return minDistance_rec(a,b,n,m,dp);
        return minDistance_tab(a,b,n,m);
    }

    // Leetcode 44 (Wildcard matching) ==========================
    public boolean wildcardMatching_rec(String s, String p, int n, int m, Boolean[][] dp){
        if(n==0 && m==0){
            return dp[n][m] = true;
        }
        if(n==0 && m==1 && m.charAt(m-1) == '*'){
            return true;
        }
        if(n==0 || m==0){
            return dp[n][m] = false;
        }

        if(dp[n][m] != null) return dp[n][m];

        boolean ans = false;

        if(p.charAt(m-1) == '?' || s.charAt(n-1) == p.charAt(m-1)){
            ans = wildcardMatching_rec(s,p,n-1,m-1,dp);
        } else if(p.charAt(m-1) == '*'){
            ans = wildcardMatching_rec(s,p,n-1,m,dp) || wildcardMatching_rec(s,p,n,m-1,dp);
        }

        return dp[n][m] = ans;
    }

    public String combineStars(String p){
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<p.length(); i++){
            if(p.charAt(i) == '*'){
                int j = i;
                while(j<p.length() && p.charAt(j) == '*') j++; 

                i = j-1;

                sb.append('*');
            } else {
                sb.append(p.charAt(i));
            }
        }

        return sb.toString();
    }


    public boolean isMatch(String s, String p) {
        String fixedP = combineStars(p);

        int n = s.length();
        int m = fixedP.length();

        Boolean[][] dp = new Boolean[n+1][m+1]; 

        return wildcardMatching_rec(s,fixedP,n,m,dp);
    }

    // Distinct Subsequences 2 (No duplicates, Leetcode 940) ==============================
    // Homework => Try in O(26) ~= O(1) space
    public int distinctSubseqII(String s) {
        int n = s.length();
        int mod = (int)(1e9+7);

        long[] dp = new long[n+1];
        dp[0] = 1L;

        int[] loc = new int[26];

        for(int i=1; i<=n; i++){
            dp[i] = 2*dp[i-1];

            char ch = s.charAt(i-1);
            int lastOcc = loc[ch-'a'];

            if(lastOcc != 0){
                dp[i] = (dp[i] - dp[lastOcc - 1] + mod) % mod;
            }

            loc[ch-'a'] = i;

            dp[i] = dp[i] % mod;
        }


        return (int)(dp[n] - 1 + mod)%mod;
    }

    // Subs ending with a^i b^j c^k (https://www.geeksforgeeks.org/problems/count-subsequences-of-type-ai-bj-ck4425/1)
    public int fun(String s) {
        long s_a = 0;
        long s_ab = 0;
        long s_abc = 0;
        int mod = (int)(1e9 + 7);

        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == 'a'){
                s_a = (2*s_a + 1) % mod;
            } else if(s.charAt(i) == 'b'){
                s_ab = (2*s_ab + s_a) % mod;
            } else {
                s_abc = (2*s_abc + s_ab) % mod;
            }
        }
        
        return (int)s_abc % mod;
    }

























    public static void main(String[] args){
        String str = "abbccb";

        System.out.println(pallindromicSubstringDP(str));
    }
}