import java.util.*;
class ReverseNumber {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        int num = scn.nextInt();

        int reversedNumber = 0;

        while(num != 0){
            int lastDigit = num % 10;
            
            reversedNumber = reversedNumber * 10 + lastDigit;

            num = num/10;
        }

        System.out.println("Reversed number is: " + reversedNumber);
    }
}