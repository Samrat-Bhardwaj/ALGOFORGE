import java.util.*;
class EvenOdd {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);
        int testCases = 5;

        while(testCases > 0){
            // work 
            int num = scn.nextInt();

            if(num % 2 == 0){
                System.out.println("EVEN");
            } else {
                System.out.println("ODD");
            }

            // updation
            testCases--;
        }

        // for(int testCases=5; testCases>0; testCases--){
        //     int num = scn.nextInt();

        //     if(num % 2 == 0){
        //         System.out.println("EVEN");
        //     } else {
        //         System.out.println("ODD");
        //     }
        // }
    }
}