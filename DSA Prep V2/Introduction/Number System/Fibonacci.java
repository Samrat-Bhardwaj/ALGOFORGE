import java.util.Scanner;
class Fibonacci {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();
        

        int secondLastTerm = 0;
        int lastTerm = 1;

        // we already have first 2 terms, to reach nth term, travel n-2 times
        for(int i=1; i<=n-2; i++){ // for(int i=3; i<=n; i++)
            int currentTerm = secondLastTerm + lastTerm;

            secondLastTerm = lastTerm;
            lastTerm = currentTerm;
        }

        System.out.println("Nth term of fibonacci is: " + lastTerm);
    }
}