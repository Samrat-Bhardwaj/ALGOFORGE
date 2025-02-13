import java.util.*;
class Pattern5 {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();

        int total_number_of_lines = 2*n + 1;
        int current_number_of_line = 1;

        int stars = 1;
        int spaces = n;
        

        while(current_number_of_line <= total_number_of_lines){
            // print spaces
            for(int i=1; i<=spaces; i++){
                System.out.print("  ");
            }

            // print stars
            for(int i=1; i<=stars; i++){
                System.out.print("* ");
            }
            
            // prepare for next line
            System.out.println();
            if(current_number_of_line <= n){
                spaces--;
                stars = stars+2;
            } else {
                spaces++;
                stars -= 2;
            }
            current_number_of_line++;
        }
    }
}