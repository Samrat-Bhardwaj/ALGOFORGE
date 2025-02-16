import java.util.*;
class Pattern14 {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();

        int total_number_of_lines = n;
        int current_number_of_line = 1;

        int stars = 1;
        int spaces = 2*n-3;
        

        while(current_number_of_line <= total_number_of_lines){
            // print stars
            for(int i=1; i<=stars; i++){
                System.out.print(i + " ");
            }

            // print spaces
            for(int i=1; i<=spaces; i++){
                System.out.print("  ");
            }

            // print stars
            if(current_number_of_line == total_number_of_lines) {
                stars--;
            }
            for(int i=stars; i>=1; i--){
                System.out.print(i + " ");
            }

            // prepare for next line
            System.out.println();
            stars++;
            spaces -= 2;
            current_number_of_line++;
        }
    }
}