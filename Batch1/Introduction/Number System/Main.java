import java.util.*;
class Main {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt(); // 5

        // int count = 0;

        // while(n > 0){
        //     n = n/10; // reducing digits by 1
        //     count++;
        // }

        // System.out.println(count);


        // ================== SUM OF DIGITS =================

        // int sum = 0;
        // int temp = n;

        // while(temp > 0){
        //     int lastDigit = temp % 10; // last

        //     sum = sum + lastDigit;

        //     temp = temp/10;
        // }

        // System.out.println(sum);


        // ================== REVERSE A NUMBER ======================

        // int reverse = 0;
        // int num = n;
        
        // while(num > 0){
        //     int rem = num % 10;

        //     reverse = reverse*10 + rem;

        //     num = num/10;
        // }

        // System.out.println(reverse);

        int originalPosition = 1;
        int inv = 0;
        int num = n;

        while(num > 0){
            int originalDigit = num % 10;

            int inversePosition = originalDigit;
            int inverseDigit = originalPosition;

            inv = inv + inverseDigit * (int)(Math.pow(10,inversePosition-1));

            num = num/10;
            originalPosition++;
        }

        System.out.println(inv);
    }
}