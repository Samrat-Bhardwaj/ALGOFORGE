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

    // Find floor and ceil
    public static void findFloorAndCeil(int[] arr, int tar){
        int floor = Integer.MIN_VALUE;
        int ceil = Integer.MAX_VALUE;

        int left = 0;
        int right = arr.length - 1;

        while(left <= right){
            int mid = (left + right)/2;

            if(arr[mid] == tar){
                floor = arr[mid];
                ceil = arr[mid];
                break;
            } else if(arr[mid] < tar){
                floor = arr[mid];
                left = mid + 1;
            } else {
                ceil = arr[mid];
                right = mid - 1;
            }
        }

        System.out.println("Floor of target is: " + floor);
        System.out.println("Ceil of target is: " + ceil);
    }

    // Leetcode 34 (First and last occurrence)
    public int findFirstOccurrence(int[] arr, int target){
        int left = 0;
        int right = arr.length -1;

        int firstOcc = -1;

        while(left <= right){
            int mid = (left + right)/2;

            if(arr[mid] == target){
                firstOcc = mid;
                right = mid - 1;
            } else if(arr[mid] < target){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return firstOcc;
    }

    public int findLastOccurrence(int[] arr, int target){
        int left = 0;
        int right = arr.length -1;

        int lastOcc = -1;

        while(left <= right){
            int mid = (left + right)/2;

            if(arr[mid] == target){
                lastOcc = mid;
                left = mid + 1;
            } else if(arr[mid] < target){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return lastOcc;
    }

    public int[] searchRange(int[] nums, int target) {
        int firstOcc = findFirstOccurrence(nums, target);
        int lastOcc = findLastOccurrence(nums, target);

        return new int[]{firstOcc, lastOcc};
    }



























    // https://www.geeksforgeeks.org/problems/floor-in-a-sorted-array-1587115620/1
    static int findFloor(int[] arr, int tar) {
        int floorIdx = -1;

        int left = 0;
        int right = arr.length - 1;

        while(left <= right){
            int mid = (left + right)/2;

            if(arr[mid] <= tar){
                floorIdx = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return floorIdx;
    }

    public static void main(String[] args){
        int[] arr = {3,5,8,9,10,11,14};

        int tar = 10;
        int idx = findTarget(arr, tar);

        System.out.println("Target exists at index: " + idx);
    }
}