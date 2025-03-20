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

    public static int factorial(int n){ // fac(x) = x * fac(x-1);
        if(n==0){
            return 1;
        }

        int smallerAns = factorial(n-1);

        int ans = n * smallerAns;

        return ans;
    }

    public static int pow(int x, int y){
        if(y == 0){
            return 1;
        }

        return x * pow(x, y-1);

        // int ans = x * smallerAns;

        // return ans;
    }
    
    public static int pow_log(int x, int y){
        if(y==0){
            return 1;
        }
    
        int smallerAns = pow_log(x, y/2);
        int ans = smallerAns * smallerAns;

        if(y % 2 != 0){
            ans = ans*x;
        }

        return ans;
    }

    public static void printZigZag(int n){
        if(n==0){
            return;
        }

        System.out.print(n);

        printZigZag(n-1);

        System.out.print(n);

        printZigZag(n-1);

        System.out.print(n);
    }

    public static void TOH(int n, int A, int C, int B){ // move n discs from A -> C using B
        if(n == 0){
            return;
        }


        TOH(n-1, A, B, C);

        System.out.println("Moving disc " + n + " from " + A +" -> " + C);
        
        TOH(n-1, B, C, A);
    }

    public static void main(String[] args){
        int n = 3;
        TOH(n,10,30,20);
    }
}