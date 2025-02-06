import java.util.*;
class Fever {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        double temp = scn.nextDouble();

        if(temp > 100.0){
            System.out.println("You have a fever, rest up!!");
        } else {
            System.out.println("You don't have a fever, solve problems!!");
        }
    
    }
}