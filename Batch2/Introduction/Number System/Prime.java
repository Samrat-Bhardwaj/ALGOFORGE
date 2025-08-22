import java.util.*;
class Prime {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();

        // int factorCount = 0;
        // for(int i=1; i<=n; i++){
        //     if(n % i == 0){
        //         factorCount++;
        //     }
        //     if(factorCount >= 3){
        //         break;
        //     }
        // }

        // if(factorCount == 2){
        //     System.out.println("This is a prime number");
        // } else {
        //     System.out.println("This is not a prime number");
        // }

        // boolean isPrime = true;
        // for(int i=2; i*i <= n; i++){
        //     if(n % i == 0){
        //         isPrime = false;
        //         break;
        //     }
        // }

        // if(isPrime == true && n != 1){ // for n = 1, isPrime will be true
        //     System.out.println("This is a prime number");
        // } else {
        //     System.out.println("This is not prime number");
        // }


        // All prime numbers till n

        for(int num=2; num <= n; num++){
            // check if this 'num' is prime or not
            boolean isPrime = true;
            for(int i=2; i*i <= num; i++){
                if(num % i == 0){
                    isPrime = false;
                    break;
                }
            }

            if(isPrime == true){
                System.out.print(num + ",");
            } 
        }
    }
}