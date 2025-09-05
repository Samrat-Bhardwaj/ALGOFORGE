class Swap {
    public static void swapIntegers(int[] arr){
        int temp = arr[0];
        arr[0] = arr[1]; // a = 5;
        arr[1] = temp; // b = temp = previous value of a = 3;
    }

    public static void main(String[] args){
        int[] arr = new int[2];
        arr[0] = 3;
        arr[1] = 5;

        System.out.println("Integers before Swapping ->"  +  " arr[0]: " + arr[0] + " and arr[1]: " + arr[1]);
        swapIntegers(arr);
        System.out.println("Integers before Swapping ->"  +  " arr[0]: " + arr[0] + " and arr[1]: " + arr[1]);
    }
}