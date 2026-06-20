class LogicalOperators {
    public static void main(String[] args){

        if((year % 4 == 0) && (year % 400 == 0)){
            System.out.println("This is a leap year");
        } else if((year%4 == 0) && (year % 100 != 0)){
            System.out.println("This is a leap year");
        } else {
            System.out.println("Not a leap year");
        }

        if((year % 4 == 0) && (year % 400 == 0 ||  year % 100 != 0)){
            System.out.println("This is a leap year");
        } else {
            System.out.println("Not a leap year");
        }
        
    }
}