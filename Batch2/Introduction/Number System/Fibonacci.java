import java.util.*;
class Fibonacci {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();
        int ans = 0;

        if(n <= 1){
            ans = n;
        } else {
            
            int secondLastTerm = 0;
            int lastTerm = 1;

            for(int i=2; i<=n; i++){
                int currentTerm = secondLastTerm + lastTerm;

                secondLastTerm = lastTerm;
                lastTerm = currentTerm;
            }

            ans = lastTerm;
        }

        System.out.println(n + "th fibonacci term is: " + ans);
    }
}