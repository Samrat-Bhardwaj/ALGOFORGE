import java.util.*;
class NcR {
    public static int factorial(int n){
        int ans = 1;

        for(int mul=1; mul<=n; mul++){
            ans = ans * mul;
        }

        return ans;
    }

    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();
        int r = scn.nextInt();

        int nfactorial = factorial(n);
        int rFactorial = factorial(r);
        int nmrFactorial = factorial(n-r);

        int ans = nfactorial / (rFactorial * nmrFactorial);

        System.out.println(ans);
    }
}