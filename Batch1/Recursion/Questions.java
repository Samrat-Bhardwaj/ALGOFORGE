class Questions {
    public static void printDecreasing(int n){
        if(n==0){ // base case
            return;
        }

        System.out.println(n);
        
        printDecreasing(n-1);
    }

    public static void printIncreasing(int n){
        if(n==0){
            return;
        }

        printIncreasing(n-1);

        System.out.println(n);
    }

    public static void printDecreasingIncreasing(int n){
        if(n==1){
            System.out.println(n);
            return;
        }


        System.out.println(n);

        printDecreasingIncreasing(n-1);

        System.out.println(n);
    }

    public static int factorial(int n){
        if(n==0){
            return 1;
        }

        int smallerAns = factorial(n-1);

        int ans = n * smallerAns;

        return ans;
    }

    public static void main(String[] args){
        int n = 4;
        // printDecreasingIncreasing(n); 
        System.out.println(factorial(n));
    }
}