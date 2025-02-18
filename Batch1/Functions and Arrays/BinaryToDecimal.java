import java.util.*;
class BinaryToDecimal {
    public static int convertBinaryToDecimal(int num){
        int ans = 0;
        int base = 1; // 2^0;

        while(num > 0){
            int lastDigit = num % 10;

            ans = ans + lastDigit * base;

            num = num/10;
            base = base*2;
        }
        return ans;
    }

    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        int num = scn.nextInt();
        
        int decNum = convertBinaryToDecimal(num);

        System.out.println(decNum);
    }
}