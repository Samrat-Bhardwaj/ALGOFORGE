import java.util.Scanner;
class DigitsCount {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        int num = scn.nextInt();
        
        int count = 0;

        while(num > 0){
            num = num / 10;
            count++;
        }

        System.out.println("Number of digits is: " + count);
    }
}