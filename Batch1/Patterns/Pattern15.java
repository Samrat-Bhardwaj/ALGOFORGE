import java.util.*;
class Pattern15 {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();

        int total_number_of_lines = n;
        int current_number_of_line = 1;

        int stars = n;
        int spaces = 0;
        

        while(current_number_of_line <= total_number_of_lines){
            // print spaces
            for(int i=0; i<spaces; i++){
                System.out.print("  ");
            }
            
            // print stars
            for(int i=1; i<=stars; i++){
                if(current_number_of_line == 1 || current_number_of_line > n/2 || i==1 || i==stars){
                    System.out.print("* ");
                } else {
                    System.out.print("  ");
                }
            }
            
            // prepare for next line
            System.out.println();
            if(current_number_of_line <= n/2){
                spaces++;
                stars -= 2;
            } else {
                spaces--;
                stars += 2;
            }
            current_number_of_line++;
        }
    }
}