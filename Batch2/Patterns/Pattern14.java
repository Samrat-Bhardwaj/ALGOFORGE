import java.util.Scanner;
class Pattern14 {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();
        
        int total_number_of_lines = n;
        int current_number_of_line = 1;

        int spaces = 2*n-3 ;
        int stars = 1;
        
        while(current_number_of_line <= total_number_of_lines){
            // print stars
            int num = 1;
            for(int i=1; i<=stars; i++){
                System.out.print(num + " ");
                num++;
            }
            num--; // we are ahead one step, taking this step back

            // print spaces
            for(int i=1; i<=spaces; i++){
                System.out.print("  ");
            }

            // print stars
            if(current_number_of_line == total_number_of_lines){ // in last line, print one less star
                stars--;
                num--;
            }
            for(int i=1; i<=stars; i++){
                System.out.print(num + " ");
                num--;
            }

            // prepare for next line
            System.out.println();
            current_number_of_line++;
            stars++;
            spaces-=2;
        }
    }
}