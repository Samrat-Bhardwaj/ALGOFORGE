import java.util.*;
class Pattern13 {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();

        int total_number_of_lines = 2*n + 1;
        int current_number_of_line = 1;

        int stars = 1;
        int spaces = n;
        int startNum = 1;
        

        while(current_number_of_line <= total_number_of_lines){
            // print spaces
            for(int i=1; i<=spaces; i++){
                System.out.print("  ");
            }

            // print stars
            int num = startNum;
            for(int i=1; i<=stars; i++){
                System.out.print(num + " ");
                if(i <= stars/2){
                    num++;
                } else {
                    num--;
                }
            }
            
            // prepare for next line
            if(current_number_of_line <= n){
                spaces--;
                stars+=2;
                startNum++;
            } else {
                spaces++;
                stars-=2;
                startNum--;
            }
            System.out.println();
            current_number_of_line++;
        }
    }
}