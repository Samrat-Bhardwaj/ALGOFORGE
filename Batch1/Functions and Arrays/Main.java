class Main {
    public static void swap(int a, int b){
        int temp = a;
        
        a = b;
        b = temp;
    }

    public static void main(String[] args){
        int a = 2;
        int b = 3;

        System.out.println(a + ", " + b);

        // int temp = a;
        
        // a = b;
        // b = temp;
        swap(a,b);

        System.out.println(a + ", " + b);
    }
}