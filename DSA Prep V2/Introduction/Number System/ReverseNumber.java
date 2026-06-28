import java.util.Scanner;
class ReverseNumber {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        int num = scn.nextInt();
        
        int reversedNumber = 0;

        while(num > 0){
            int lastDigit = num % 10; // fetch last digit

            reversedNumber = reversedNumber * 10 + lastDigit; // attach last digit at end

            num = num/10; // remove last digit
        }

        System.out.println("Reverse of given num is: " + reversedNumber);
    }
}