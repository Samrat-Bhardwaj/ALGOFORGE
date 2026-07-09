class SwapIntegersSolution {
    public static void swapIntegers(int[] arr){
        int temp = arr[0];

        arr[0] = arr[1];
        arr[1] = temp;
    }

    public static void main(String[] args){
        int[] arr = new int[2];

        arr[0] = 5;
        arr[1] = 8;

        System.out.println("Before swap: " + arr[0] + ", " + arr[1]);

        swapIntegers(arr);

        System.out.println("Before swap: " + arr[0] + ", " + arr[1]);
    }
}