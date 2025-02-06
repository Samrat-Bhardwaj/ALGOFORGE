import java.util.*;
class Main {
    public static void main(String[] args){
        // int a = 10;

        // if(a > 78){ // true
        //     System.out.println("Hello World!!!");
        // } else {
        //     System.out.println("Byee World!!!");
        // }

        Scanner scn = new Scanner(System.in);

        int num = 43;

        if(num == 0){
            System.out.println("Neither positive, nor negative");
        } else if(num > 0){
            System.out.println("It's a positive number");
        } else {
            System.out.println("It's a negative number");
        }

        if(num > 54){
            System.out.println("Num is greater than 54");
        } else if(num < 100) {
            System.out.println("Num is less than 100");
        } else {
            System.out.println("AlgoForge");
        }
    
    }
}