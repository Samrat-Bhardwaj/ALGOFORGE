import java.util.*;
class DigitProblems {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        int num = scn.nextInt();
        // int sumOfDigits = 0;

        // while(num > 0){
        //     // retrieve last digit
        //     int lastDigit = num%10;

        //     sumOfDigits += lastDigit;
        //     // delete last digit
        //     num = num/10;
        // }

        // System.out.println("Sum of digits is: " + sumOfDigits);

        // Number of digits

        int digitCount = 0;

        while(num > 0){
            // remove last digit
            num = num/10;
            digitCount++;
        }

        System.out.println("Count of digit in this number is: " + digitCount);
    }
}