class Main {
    public static void printDecreasing(int n){
        if(n == 0){
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

    public static void printDecInc(int n){
        if(n == 1){
            System.out.println(n);
            return;
        }

        System.out.println(n);

        printDecInc(n-1);

        System.out.println(n);
    }

    // Factorial of a number 
    public static int fact(int n){
        if(n <= 1){
            return 1;
        }

        int smallerAns = fact(n-1);
        
        int ans = n * smallerAns;

        return ans;
    }

    // O(N);
    public static int pow(int x, int y){
        if(y == 0) return 1;

        int smallerAns = pow(x, y-1);

        int ans = x * smallerAns;

        return ans;
    }

    // O(log(Y))
    public static int pow_log(int x, int y){
        if(y == 0) return 1;

        int smallerAns = pow_log(x, y/2);

        int ans = smallerAns * smallerAns;

        if(y%2 != 0){
            ans = ans*x;
        }

        return ans;
    }

    // print zig-zag
    public static void printZigZag(int n){
        if(n==0) return;

        System.out.println("pre-area " + n);

        printZigZag(n-1);

        System.out.println("in-area " +n);

        printZigZag(n-1);

        System.out.println("post-area " +n);
    }

    // tower of hanoi
    public static void TOH(int n, int A, int C, int B){
        if(n==0){
            return;
        }

        TOH(n-1, A, B, C);

        System.out.println("Moving disc " + n + " from " + A + " -> " + C);

        TOH(n-1, B, C, A); 
    }

    public static void main(String[] args){
        TOH(3,10,30,20);
        // int x = 2;
        // int y = 7;
        // printDecInc(n);

        // System.out.println(pow_log(x,y));
        // printZigZag(3);
    }
}