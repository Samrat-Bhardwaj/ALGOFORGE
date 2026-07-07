import java.util.Scanner;
class Pattern12 {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();
        
        int total_number_of_lines = n;
        int current_number_of_line = 1;

        int stars = 1;
        int secondLastTerm = 1;
        int lastTerm = 1;

        while(current_number_of_line <= total_number_of_lines){
            // print stars
            for(int i=1; i<=stars; i++){
                System.out.print(secondLastTerm + "\t");
                
                int currentTerm = secondLastTerm + lastTerm;
                secondLastTerm = lastTerm;
                lastTerm = currentTerm;
            }

            // prepare for next line
            System.out.println();
            stars++;
            current_number_of_line++;
        }
    }
}