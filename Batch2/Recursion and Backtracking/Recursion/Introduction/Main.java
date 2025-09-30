class Main {
    public static void printDecreasing(int n){
        if(n == 0){
            return;
        }

        System.out.println(n);
        printDecreasing(n-1);
    }

    public static void printIncreasing(int n){
        if(n == 0){
            return;
        }

        printIncreasing(n-1);
        System.out.println(n);
    }

    public static void printDecreasingIncreasing(int n){
        if(n == 1){
            System.out.println(n);
            return;
        }

        System.out.println(n);

        printDecreasingIncreasing(n-1);

        System.out.println(n);   
    }

    public static int factorial(int n){
        if(n <= 1){
            return 1;
        }

        int smallerAns = factorial(n-1);

        int ans = n * smallerAns;
        return ans;
    }

    public static int power(int x, int y){
        if(y <= 0){
            return 1;
        }

        int smallerAns = power(x, y-1);

        int ans = x * smallerAns;
        return ans;
    }

    public static int power_log(int x, int y){
        if(y <= 0){
            return 1;
        }

        int smallerAns = power_log(x, y/2);

        int ans = smallerAns * smallerAns;

        if(y%2 == 1){
            ans = ans * x;
        }

        return ans;
    }

    public static void main(String[] args){
        int x = 2, y = 6;
        System.out.println(power_log(x,y));
    }
}