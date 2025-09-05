class Swap {
    public static void swapIntegers(int a, int b){
        int temp = a;
        a = b; // a = 5;
        b = temp; // b = temp = previous value of a = 3;
    }

    public static void main(String[] args){
        int a = 3;
        int b = 5;

        System.out.println("Integers before Swapping ->"  +  " a: " + a + " and b: " + b);
        swapIntegers(a,b);
        System.out.println("Integers after Swapping ->"  +  " a: " + a + " and b: " + b);
    }
}