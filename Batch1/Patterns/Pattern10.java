import java.util.*;
class Pattern10 {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();

        int star = 1; // System.out.print("* ");
        int outerSpaces = n/2;
        int innerSpaces = -1;

        for(int i=0; i<n; i++){
            // print outer spaces
            for(int j=0; j<outerSpaces; j++){
                System.out.print("  ");
            }

            // print one star
            System.out.print("* ");

            // print inner spaces
            for(int j=0; j<innerSpaces; j++){
                System.out.print("  ");
            }

            // print one star (not at every line)
            if(i > 0 &&  i < n-1){
                System.out.print("* ");
            }

            // prepare for next line
            System.out.println();
            if(i< n/2){
                outerSpaces--;
                innerSpaces += 2;
            } else {
                outerSpaces++;
                innerSpaces -= 2;
            }
        }
        
    }
}