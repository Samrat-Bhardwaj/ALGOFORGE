import java.util.*;
class LeapYear {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        int year = scn.nextInt();

        // if(year % 4 == 0){ // maybe a leap year
        //     if(year % 100 == 0){
        //         if(year % 400 == 0){
        //             System.out.println("It's a leap year");
        //         } else {
        //             System.out.println("It's not a leap year");
        //         }
        //     } else { // 2004, 2016, 2020
        //         System.out.println("It's a leap year");
        //     }
        // } else {
        //     System.out.println("It's not a leap year");
        // }

        if(year % 400 == 0){ // (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0)
            System.out.println("It's a leap year");
        } else if(year % 4 == 0 && year % 100 != 0){
            System.out.println("It's a leap year");
        } else {
            System.out.println("It's not a leap year");
        }

        // if((year % 400 == 0) || (year % 4 == 0 && year % 100 != 0)){ // 
        //     System.out.println("It's a leap year");
        // }  else {
        //     System.out.println("It's not a leap year");
        // }
    }
}