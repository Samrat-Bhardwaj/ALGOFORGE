import java.util.*;
class Average {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        // write your code here
        int a = scn.nextInt();
        int b = scn.nextInt();
        int c = scn.nextInt();

        double avg = (a+b+c)/3.0;

        System.out.println(avg);
    }
}