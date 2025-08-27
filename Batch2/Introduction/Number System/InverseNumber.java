import java.util.*;
class InverseNumber {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        int num = scn.nextInt();

        int ans = 0;
        int pos = 1;

        while(num != 0){
            int lastDigit = num % 10;

            ans = ans + pos*((int)(Math.pow(10, lastDigit - 1)));

            num = num/10;
            pos++;
        }        

        System.out.println("Inversed number is: " + ans);
    }
}