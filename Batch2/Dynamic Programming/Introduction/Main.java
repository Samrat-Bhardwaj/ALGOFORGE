class Main {
    public static int fib_rec(int n){
        if(n==0 || n==1){
            return n;
        }

        int lastTerm = fib_rec(n-1);
        int secondLastTerm = fib_rec(n-2);

        return lastTerm + secondLastTerm;
    }

    public static int fib_memo(int n, int[] memo){
        if(n==0 || n==1){
            return memo[n] = n;
        }

        if(memo[n] != 0) return memo[n];

        int lastTerm = fib_memo(n-1, memo);
        int secondLastTerm = fib_memo(n-2, memo);

        return memo[n] = lastTerm + secondLastTerm;
    }

    public static int fib_tab_copyMem(int N){
        int[] memo = new int[N+1];

        for(int n=0; n<=N; n++){
            if(n==0 || n==1){
                memo[n] = n;
                continue;
            }

            int lastTerm = memo[n-1]; //fib_memo(n-1, memo);
            int secondLastTerm = memo[n-2]; //fib_memo(n-2, memo);

            memo[n] = lastTerm + secondLastTerm;
        }

        return memo[N];
    }

    public static int fib_tab(int n){
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;

        for(int idx=2; idx<=n; idx++){
            int lastTerm = dp[idx-1];
            int secondLastTerm = dp[idx-2];

            dp[idx] = lastTerm + secondLastTerm;
        }

        return dp[n];
    }

    public static int fib_MostOptimized(int n){
        int a = 0;
        int b = 1;

        for(int idx=2; idx<=n; idx++){
            int c = a + b;

            a = b;
            b = c;
        }

        return b;
    }

    public static int fibonacci(int n){
        int[] memo = new int[n+1];

        // return fib_memo(n, memo);
        // return fib_tab(n);
        return fib_tab_copyMem(n);
    }

    public static void main(String[] args){
        int n = 6;

        int fib_n = fibonacci(n); //fib_rec(n);

        System.out.println(fib_n);
    }
}