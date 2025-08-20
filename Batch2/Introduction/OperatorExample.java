class OperatorExample {
    public static void main(String[] args){
        // int a = 10;

        // if(a++ == 10){ // first comparison, then increment
        //     System.out.println("value of a is " + a); // a=11
        // } else {
        //     System.out.println("else part but value of a is " + a);
        // }

        // if(--a == 10){ // first decrement, then comparison
        //     System.out.println("value of a is " + a);
        // } else {
        //     System.out.println("else part but value of a is " + a);
        // }

        int x = 2, y =5;

        int exp1 = (x*y/x);

        int exp2 = (x*(y/x));

        System.out.println(exp1);
        System.out.println(exp2);

    }
}