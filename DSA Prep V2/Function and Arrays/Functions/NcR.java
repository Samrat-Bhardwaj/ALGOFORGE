import java.util.Scanner;
class NcR {
    public static int findFactorial(int num){
        int res = 1;

        for(int mul = 1; mul <= num; mul++){
            res = res * mul;
        }

        return res;
    }

    public static int findNcR(int n, int r){
        int nFac = findFactorial(n);
        int rFac = findFactorial(r);
        int NmRFac = findFactorial(n-r);

        int ncrRes = nFac / (rFac*NmRFac);

        return ncrRes;
    }

    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();
        int r = scn.nextInt();

        int res = findNcR(n,r);

        System.out.println("Result is: " + res);
    }
}