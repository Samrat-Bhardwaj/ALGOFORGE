import java.util.*;
class Contest1 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int N = scn.nextInt();

        int temp = N;
        int length = 0;

        while(temp > 0){
            temp = temp/10;
            length++;
        }

        int pos = length;
        int sum = 0;
        
        while(N > 0){
            int lastDigit = N%10;
            N = N/10;

            if(pos % 2 == 0){
                sum = sum + lastDigit;
            }

            pos--;
        }

        System.out.println(sum);
    }

    // public static void main(String[] args) {
    //     Scanner scn = new Scanner(System.in);
    //     int N = scn.nextInt();

    //     int count = 0;
    //     // for(int i=1; i*i<=N; i++){
    //     //     count++;
    //     // }

    //     for(int i=1; i<=N; i++){
    //         if(i*i <= N){
    //             count++;
    //         }
    //     }

    //     System.out.println(count);
        
    // }

    // public static void main(String[] args) {
    //     Scanner scn = new Scanner(System.in);
    //     int N = scn.nextInt();
        
    //     int total_number_of_lines = N;
    //     int current_number_of_line = 1;

    //     int stars = 1;
    //     int spaces = N-2;

    //     while(current_number_of_line <= total_number_of_lines){
    //         // print stars
    //         for(int i=1; i<=stars; i++){
    //             System.out.print("* ");
    //         }

    //         // print spaces
    //         for(int i=1; i<=spaces; i++){
    //             System.out.print("  ");
    //         }

    //         // print stars
    //         for(int i=1; i<=stars; i++){
    //             if(current_number_of_line == N/2 + 1 && i==stars){ // printing last star in middle line
    //                 continue;
    //             }
    //             System.out.print("* ");
    //         }

    //         // prepare for next line
    //         if(current_number_of_line <= N/2){
    //             stars++;
    //             spaces -= 2;
    //         } else {
    //             stars--;
    //             spaces += 2;
    //         }

    //         System.out.println();
    //         current_number_of_line++;
    //     }
    // }
}