import java.util.Scanner;
class SumOfDigits {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        int num = scn.nextInt();
        
        int sum = 0;

        while(num > 0){
            int lastDigit = num % 10;

            sum += lastDigit;

            // we don't need last digit
            num = num/10; 
        }

        System.out.println("Sum of digits is: " + sum);
    }
}