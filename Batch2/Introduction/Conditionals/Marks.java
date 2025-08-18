import java.util.*;
class Marks {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        // int num = scn.nextInt();

        // if(num > 0){
        //     System.out.println("Number is positive");
        // } else if(num < 0){
        //     System.out.println("Number is negative");
        // } else {
        //     System.out.println("Number is neither positive nor negative");
        // }

        int marks = scn.nextInt();

        if(marks > 90){
            System.out.println("Excellent");
        } else if(marks >= 80){
            System.out.println("Good");
        } else if(marks >= 60){
            System.out.println("Can do better");
        } else if(marks >= 45){
            System.out.println("Average");
        } else {
            System.out.println("Poor");
        }

    }
}