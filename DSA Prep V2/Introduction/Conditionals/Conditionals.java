class Conditionals {
    public static void main(String[] args){
        int a = 18;

        if(a < 5){
            System.out.println("a is less than 5");
        } else if(a < 10) {
            System.out.println("a is less than 10");
        } else if (a < 15){
            System.out.println("a is less than 15");
        } else {
            System.out.println("a is more than 15");
        }

        System.out.println("Out of if else block");
    }
}