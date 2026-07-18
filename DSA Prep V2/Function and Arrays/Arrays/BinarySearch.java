class BinarySearch {
    public static int findTarget(int[] arr, int tar){
        int n = arr.length;
        int left = 0;
        int right = n-1;

        while(left <= right){
            int mid = (left + right)/2;

            if(arr[mid] == tar){
                return mid;
            } else if(arr[mid] < tar){
                left = mid + 1;
            } else {
                right = mid - 1; // move to left region
            }
        }

        return -1;
    }

    public static void main(String[] args){
        int[] arr = {3,5,8,9,10,11,14};

        int tar = 10;
        int idx = findTarget(arr, tar);

        System.out.println("Target exists at index: " + idx);
    }
}