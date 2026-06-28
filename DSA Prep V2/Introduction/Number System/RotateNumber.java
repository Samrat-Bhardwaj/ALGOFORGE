import java.util.Scanner;
class RotateNumber {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        int num = scn.nextInt();
        int k = scn.nextInt();

        int len = 0;
        int temp = num; // so that num is not lost

        while(temp > 0){
            temp /= 10;
            len++;
        }

        k = k % len;
        if(k < 0){
            k = k + len;
        }

        int div = (int)Math.pow(10,k);
        int mul = (int)Math.pow(10, len - k);

        int lastDigits = num % div;
        int frontDigits = num / div;

        int rotatedNumber = lastDigits * mul + frontDigits;

        System.out.println("Rotated Number is: " + rotatedNumber);
    }
}