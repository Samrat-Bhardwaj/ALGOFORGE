import java.util.*;
class Operators {
    public static void main(String[] args){
    //    int a = 10;

    //    if(a++ == 11){ // first check, then increment -> a == 11 , then a = a+1
    //     System.out.println("It works!!!"); 
    //    } else {
    //     System.out.println("It won't work!!");
    //    }

    //    System.out.println(a);

        // int b = 1;

        // if(b-- > 0){ // first decrement (b = 0),  then check  (0 > 0)
        //     System.out.println("Beeeeeeeeeeee");
        // }
        // System.out.println(b);


        // int x = 9, y = 12;
        // int a = 2, b = 4, c = 6;
        // int exp = 4/3 * (x + 34) + 9 * (a + b * c) + (3 + y * (2 + a)) / (a + b*y);			

        // System.out.println(exp); 

        int a;

        int b = 56;
        // if(b < 45){
        //     a = 100;
        // } else {
        //     a = -100;
        // }

        a = (b < 45) ? 100 : -100; 

        int c = (a < 0) ? 456 : 123;

        System.out.println(a);
        System.out.println(c);
    }
}