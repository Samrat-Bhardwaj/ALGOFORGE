import java.util.Scanner;
class InverseNumber {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        int num = scn.nextInt();
        
        int inversedNum = 0;
        int pos = 1;

        while(num > 0){
            int lastDigit = num % 10;

            int multiplier = (int)Math.pow(10, lastDigit - 1);

            inversedNum = inversedNum + (pos * multiplier);

            num /= 10;
            pos++;
        }

        System.out.println("Inverse of the given number is: " + inversedNum);
    }
}