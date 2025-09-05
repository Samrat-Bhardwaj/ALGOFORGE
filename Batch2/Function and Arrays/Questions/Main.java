import java.util.Scanner;
class Main {
    public static int factorial(int n){
        int res = 1;

        for(int mul=1; mul<=n; mul++){
            res = res*mul;
        }

        return res;
    }

    public static int findNCR(int n, int r){
        int nFact = factorial(n);
        int rFact = factorial(r);
        int nMrFact = factorial(n-r);

        int ncr = nFact/(rFact * nMrFact);

        return ncr;
    }

    public static int binaryToDecimal(int num){
        int currBase = 1;
        int decimalNum = 0;

        while(num > 0){
            int lastDigit = num%10;

            decimalNum += lastDigit*currBase;
            currBase *= 2;
            num /= 10;
        }

        return decimalNum;
    }

    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        // int n = scn.nextInt();
        // int r = scn.nextInt();

        // int ncr = findNCR(n,r);
        // System.out.println(ncr);

        int num = scn.nextInt();
        System.out.println(binaryToDecimal(num));
    }
}