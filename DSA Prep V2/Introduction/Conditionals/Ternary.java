import java.util.Scanner;
class Ternary {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        int x = scn.nextInt();
        int y = scn.nextInt();

        int max = x > y ? x : y;

        System.out.println("Max value is " + max);
    }
}