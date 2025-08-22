import java.util.*;
class Factorial {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();

        int fact = 1;

        for(int mul=1; mul <= n; mul++){
            fact = fact * mul;
        }

        System.out.println("Factorial of " + n + " is: " + fact);
    }
}