import java.util.*;
class Pattern11 {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();

        int current_number_of_line = 1;
        
        int total_number_of_lines = n;

        int stars = 1;
        int num = 1;

        while(current_number_of_line <= total_number_of_lines){
            // print stars
            for(int i=1; i<=stars; i++){
                System.out.print(num + "\t");
                num++;
            }

            // prepare for next line
            System.out.println();
            stars++;
            current_number_of_line++;
        }
    }
}