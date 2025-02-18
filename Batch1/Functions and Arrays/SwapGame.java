class SwapGame {
    public static void swap(int a, int b){
        int temp = a;
        
        a = b;
        b = temp;
    }
    public static void swapArrayValues(int[] arr){
        int temp = arr[0];

        arr[0] = arr[1];
        arr[1] = temp;
    }

    public static void main(String[] args){
        int a = 2;
        int b = 3;

        System.out.println("Before swapping " + a + ", " + b);
        swap(a,b);
        System.out.println("After swapping " + a + ", " + b);

        int[] arr = new int[2];
        arr[0] = 2;
        arr[1] = 3;

        System.out.println("Before swapping for array " + arr[0] + ", " + arr[1]);
        
        swapArrayValues(arr);
        System.out.println("After swapping for array " + arr[0] + ", " + arr[1]);
    }
}