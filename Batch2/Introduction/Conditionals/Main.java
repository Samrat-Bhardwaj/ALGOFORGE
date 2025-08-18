class Main {
    public static void main(String[] args){
        int a = 356;

        if(a < 5){
            System.out.println("a is smaller than 5");
            a = 2;
        } else {
            System.out.println("a is greater than 5");
            a = 3;
        }

        System.out.println(a);
    }
}