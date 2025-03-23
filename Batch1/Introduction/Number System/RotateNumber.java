import java.util.*;
class RotateNumber {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        int num = scn.nextInt(); 
        int k = scn.nextInt();

        int len = 0;
        int temp = num;

        while(temp > 0){
            temp = temp/10;
            len++;
        }

        k = k % len; // 3452 = 2

        if(k < 0){
            k = k + len;
        }

        int divisor = (int)Math.pow(10,k);

        int ld = num % divisor;
        int sd = num / divisor;

        int ans = ld * (int)Math.pow(10, len-k ) + sd;

        System.out.println(ans);
    }
}