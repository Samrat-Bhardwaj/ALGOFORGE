import java.util.*;
class Prime {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        int num = scn.nextInt();
        boolean isPrime = true;

        // for(int i = 2; i < num; i++){
        //     if(num % i == 0){
        //         isPrime = false;
        //         break;
        //     }
        // }

        for(int i = 2; i*i <= num; i++){
            if(num % i == 0){
                isPrime = false;
                break;
            }
        }

        if(isPrime == true){
            System.out.println("This is a prime number.");
        } else {
            System.out.println("This is not a prime number.");
        }
    }
}