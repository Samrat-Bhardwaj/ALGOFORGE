import java.util.Scanner;
class Pattern10 {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();
        
        int total_number_of_lines = n;
        int current_number_of_line = 1;
        
        int ospaces = n/2;
        int ispaces = -1;

        while(current_number_of_line <= total_number_of_lines){
            // print outer spaces
            for(int i=1; i<=ospaces; i++){
                System.out.print("  ");
            }

            // print one star
            System.out.print("* ");

            // print innerspaces
            for(int i=1; i<=ispaces; i++){
                System.out.print("  ");
            }

            // print one star (not in first or last line)
            if(current_number_of_line != 1 && current_number_of_line != n){
                System.out.print("* ");
            }

            // prepare for next line
            if(current_number_of_line <= n/2){
                ospaces--;
                ispaces += 2;
            } else {
                ospaces++;
                ispaces -= 2;
            }
            System.out.println();
            current_number_of_line++;
        }
    }
}