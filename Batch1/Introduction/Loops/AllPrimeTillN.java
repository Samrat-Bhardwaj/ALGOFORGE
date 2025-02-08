import java.util.*;
class AllPrimeTillN {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();

        for(int num = 2; num <= n; num++){
            
            boolean isPrime = true;
            for(int i=2; i*i <= num; i++){
                if(num % i == 0){
                    isPrime = false;
                    break;
                }
            }

            if(isPrime == true){
                System.out.println(num);
            }
        }
    }
}