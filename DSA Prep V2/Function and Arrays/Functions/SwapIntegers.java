class SwapIntegers {
    public static void swap(int a, int b){
        int temp = a; // temp = a = 5; 

        a = b; // a= 8;
        b = temp; // b = 5;
    }

    public static int sum(int f, int s){
        int sum = f + s;

        return sum;
    }

    public static void main(String[] args){
        int a = 5;
        int b = 8;

        System.out.println(a + "," + b);
        
        swap(a,b);

        System.out.println(a + "," + b);
    }
}