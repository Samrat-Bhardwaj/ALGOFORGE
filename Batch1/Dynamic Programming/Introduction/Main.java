class Main {
    public static int fib_rec(int n){
        if(n <= 1) return n;

        int nm1 = fib_rec(n-1);
        int nm2 = fib_rec(n-2);

        int nTerm = nm1 + nm2;

        return nTerm;
    }

    public static int fib_mem(int n, int[] memo){
        if(n <= 1) return memo[n] = n;

        if(memo[n] != 0){
            return memo[n];
        }

        int nm1 = fib_mem(n-1,memo);
        int nm2 = fib_mem(n-2,memo);

        int nTerm = nm1 + nm2;

        return memo[n] = nTerm;
    }

    // Replace return with continue
    // replace recursive calls with dp states
    // remove memoization step
    public static int fib_tab(int N, int[] memo){
        for(int n=0; n<=N; n++){
            if(n <= 1){
                memo[n] = n;
                continue;
            } 

            int nm1 = memo[n-1]; //fib_mem(n-1,memo);
            int nm2 = memo[n-2]; //fib_mem(n-2,memo);

            int nTerm = nm1 + nm2;

            memo[n] = nTerm;
        }

        return memo[N];
    }

    public static int fib_tabulation(int n){
        int[] dp = new int[n+1];

        //answers not dependent on other
        dp[0] = 0;
        dp[1] = 1;

        for(int i=2; i<=n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }

    // Most optimized
    public static int fib_O1(int n){
        int a = 0;
        int b = 1;

        for(int i=2; i<=n; i++){
            int c = b + a;

            a = b;
            b = c;
        }

        return b;
    }

    public static void main(String[] args){
        int n = 45;
        // System.out.println(fib_rec(n));

        // int[] memo = new int[n+1];
        // System.out.println(fib_mem(n,memo));
        System.out.println(fib_O1(n));
    }
}