import java.util.*;
class Factorial {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        long n = scn.nextInt();
        long ans = 1;

        for(int mul = 1; mul <= n; mul++){
            ans = ans * mul;
        }

        System.out.println("Factorial of given number is: " + ans);
    }
}