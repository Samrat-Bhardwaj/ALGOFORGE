import java.util.Scanner;
class LoopExample {
    public static void main(String[] args){
        // int a = 1;

        // while(a <= 5){
        //     System.out.println(a);
        //     a++;
        // }

        // for(int i=1; i<6; i+=2){ // i = 1 , i = 3, i = 5
        //     System.out.println("Hello");
        // }
        
        System.out.print("Enter number of testcases ");
        Scanner scn = new Scanner(System.in);
        int testCases = scn.nextInt();

        while(testCases-- > 0){
            System.out.print("Enter a number ");
            int num = scn.nextInt();    

            if(num%2 == 0){
                System.out.println("Number is even");
            } else {
                System.out.println("Number is odd");
            }
        }
    }
}