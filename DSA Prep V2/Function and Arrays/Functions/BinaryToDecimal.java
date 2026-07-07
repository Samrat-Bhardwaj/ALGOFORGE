import java.util.Scanner;

class BinaryToDecimal {
    public static int convertDecimal(int binaryNum){
        int res = 0;
        int pow2 = 1;

        while(binaryNum > 0){
            // find last digit
            int lastDigit = binaryNum % 10;

            // convert to binary
            int binary = lastDigit * pow2;
            res += binary;

            // remove last digit
            binaryNum /= 10;
            pow2 = pow2*2;
        }

        return res;
    }

    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        int binaryNum = scn.nextInt();

        int decimalNum = convertDecimal(binaryNum);

        System.out.println("Decimal number is " + decimalNum);
    }
    
}