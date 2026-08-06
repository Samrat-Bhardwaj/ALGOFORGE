class Main {
    public static void printArray(int[] arr, int idx){
        if(idx == arr.length){
            return;
        }

        System.out.println(arr[idx]);
        printArray(arr, idx+1);
    }

    public static void printArrayReverse(int[] arr, int idx){
        if(idx == arr.length){
            return;
        }

        printArrayReverse(arr, idx+1);
        System.out.println(arr[idx]);
    }

    public static int maxOfArray(int[] arr, int idx){
        if(idx == arr.length){
            return Integer.MIN_VALUE;
        }

        int smallerAns = maxOfArray(arr, idx+1);

        int ans = Math.max(smallerAns, arr[idx]);

        return ans;
    }

    public static void main(String[] args){
        int[] arr = {2,5,7,1,9};
        // printArray(arr,0);
        // printArrayReverse(arr,0);
        System.out.println(maxOfArray(arr,0));
    }
}