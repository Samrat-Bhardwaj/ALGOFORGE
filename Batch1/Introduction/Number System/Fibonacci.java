import java.util.*;
class Fibonacci {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt(); // 5

        if (n <= 1){ // n== 0, ans = 0, n == 1, ans = 1
            System.out.println(n);
        } else {

            int a = 0;
            int b = 1;

            for(int i = 2; i<=n; i++){ // first term that we are gonna calculate is 2nd
                int c = a + b; // ith term

                // preparing to calculate next terms
                a = b;
                b = c;
            }

            System.out.println(b);
        }
    }
}