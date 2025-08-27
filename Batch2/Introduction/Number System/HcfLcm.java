import java.util.*;
class HcfLcm {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        int num1 = scn.nextInt();
        int num2 = scn.nextInt();

        int a = num1;
        int b = num2;

        while(a != 0){
            int rem = b % a;

            b = a;
            a = rem;
        }

        int hcf = b;
        int lcm = (num1*num2)/hcf;

        System.out.println("HCF and LCM of given numbers: " + hcf + ", " + lcm);
    }
}