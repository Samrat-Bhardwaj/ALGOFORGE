import java.util.*;
class GCDlcm {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        int num1 = scn.nextInt(); 
        int num2 = scn.nextInt();

        int ognum1 = num1;
        int ognum2 = num2;

        while(num2 % num1 != 0){
            int rem = num2 % num1;

            num2 = num1;
            num1 = rem;
        }

        int gcd = num1;
        int lcm = (ognum1 * ognum2) / gcd;

        System.out.println(gcd);
        System.out.println(lcm);
    }
}