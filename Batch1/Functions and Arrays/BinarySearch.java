class BinarySearch {
    // leetcode 34
    public int[] searchRange(int[] nums, int target) {
        int n = nums.length;

        int lastIndex = -1;
        int firstIndex = -1;

        int si = 0;
        int ei = n-1;

        while(si <= ei){ // to find fi
            int mid = (si+ei)/2;

            if(nums[mid] == target){
                firstIndex = mid;
                ei = mid - 1;
            } else if(nums[mid] < target){
                si = mid + 1;
            } else {
                ei = mid - 1;
            }
        }

        si = 0;
        ei = n-1;

        while(si <= ei){ // to find fi
            int mid = (si+ei)/2;

            if(nums[mid] == target){
                lastIndex = mid;
                si = mid + 1;
            } else if(nums[mid] < target){
                si = mid + 1;
            } else {
                ei = mid - 1;
            }
        }

        int[] ans = new int[2];
        ans[0] = firstIndex;
        ans[1] = lastIndex;

        // return new int[]{firstIndex, lastIndex};
        return ans;
    }
    public static void floorAndCeil(int[] arr, int tar){
        int n = arr.length;

        int floor = Integer.MIN_VALUE;
        int ceil = Integer.MAX_VALUE;

        int si = 0;
        int ei = n-1;

        while(si <= ei){
            int mid = (si+ei)/2;

            if(arr[mid] == tar){
                floor = arr[mid];
                ceil = arr[mid];
                break; 
            } else if(arr[mid] < tar){
                floor = arr[mid];
                si = mid + 1;
            } else {
                ceil = arr[mid];
                ei = mid - 1;
            }
        }

        System.out.println("Floor is " + floor);
        System.out.println("Ceil is " + ceil);
    }

    public static int binarySearch(int[] arr, int tar){
        int n = arr.length;

        int si = 0;
        int ei = n-1;

        while(si <= ei){
            int mid = (si+ei)/2;

            if(arr[mid] == tar){
                return mid;
            } else if(arr[mid] < tar){ // discarding left, moving to right
                si = mid + 1;
            } else { // discarding right, moving to left
                ei = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args){
        int[] arr = {2,5,8,9,11,14,16,20,49};
        // int tar = 38;

        // int idx = binarySearch(arr, tar);
        // System.out.println(idx);
        floorAndCeil(arr,78);
    }
}