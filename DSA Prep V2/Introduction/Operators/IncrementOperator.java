class IncrementOperator {
    public static void main(String[] args){
        int y = 7;

        if(y++ == 7){ // first evaluation ( y == 7 => true), then increment y++
            System.out.println("Value of y is " + y);
        } else {
            System.out.println("We are in else and value of y is " + y);
        }

        int x = 7;
        if(++x == 7){ // first increment (x = 8), then evaluation ( 8 == 7)
            System.out.println("Value of y is " + x);
        } else {
            System.out.println("We are in else and value of x is " + x);
        }
    }
}