import java.util.*;
class RotateNumber {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        int num = scn.nextInt();
        int k = scn.nextInt();

        int len = 0;
        int temp = num;

        while(temp != 0){
            temp = temp/10;
            len++;
        }

        k = k % len;
        if(k < 0){
            k = k + len;
        }
        // k lies between 0 and len-1
        int divisor = (int)Math.pow(10,k);
        int mul = (int)Math.pow(10,len-k);

        int rem = num % divisor;
        int quo = num / divisor;

        int rotatedNumber = rem * mul + quo;

        System.out.println("Rotated number is " + rotatedNumber);
    }
}