class Introduction {
    public static int binarySearch(int[] arr, int target){
        int n = arr.length;

        int si = 0;
        int ei = n-1;

        while(si <= ei){
            int mid = (si+ei)/2;

            if(arr[mid] == target){
                return mid;
            } else if(arr[mid] < target){
                si = mid + 1;
            } else {
                ei = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args){
        int[] arr = {3,5,6,11,14,19,28};

        int target = 36;

        int targetIdx = binarySearch(arr, target);
        System.out.println(targetIdx);
    }
}