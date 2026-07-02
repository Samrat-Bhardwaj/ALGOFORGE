import java.util.Scanner;
class Pattern6 {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();

        int total_number_of_lines = 2*n+1;
        int current_number_of_line = 1;

        int stars = n+1, spaces = 1;

        while(current_number_of_line <= total_number_of_lines){
            // print stars
            for(int i=1; i<=stars; i++){
                System.out.print("* ");
            }

            // print spaces
            for(int i=1; i<=spaces; i++){
                System.out.print("  ");
            }

            // print stars
            for(int i=1; i<=stars; i++){
                System.out.print("* ");
            }

            // prepare for next line
            if(current_number_of_line <= n){
                stars--;
                spaces+=2;
            } else {
                stars++;
                spaces-=2;
            }

            System.out.println();
            current_number_of_line++;
        }
    }
}