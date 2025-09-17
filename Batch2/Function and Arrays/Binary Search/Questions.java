class Questions {
    public static int[] FindFloorAndCeil(int[] arr, int target){
        int n = arr.length;

        int si = 0;
        int ei = n-1;
        int floor = Integer.MIN_VALUE;
        int ceil = Integer.MAX_VALUE;

        while(si <= ei){
            int mid = (si + ei)/2;

            if(arr[mid] == target){
                floor = arr[mid];
                ceil = arr[mid];
                break;
            } else if(arr[mid] < target){
                floor = arr[mid];
                si = mid + 1;
            } else {
                ceil = arr[mid];
                ei = mid - 1;
            }
        }

        return new int[]{floor, ceil};
    }

    // find first and last index (Leetcode 34) ===================================
    public static int[] findFirstAndLastIndex(int[] arr, int target){
        int n = arr.length;
        int si = 0;
        int ei = n-1;

        int firstIndex = -1;

        while(si <= ei){
            int mid = (si+ei)/2;

            if(arr[mid] == target){
                firstIndex = mid; // assuming this is firstIndex
                ei = mid - 1; // moving left to find better firstIndex 
            } else if(arr[mid] < target){
                si = mid + 1;
            } else {
                ei = mid - 1;
            }
        }

        int lastIndex = -1;
        si = 0; ei = n-1;

        while(si <= ei){
            int mid = (si+ei)/2;

            if(arr[mid] == target){
                lastIndex = mid; // assuming this is lastIndex
                si = mid + 1; // moving right to find better lastIndex
            } else if(arr[mid] < target){
                si = mid + 1;
            } else {
                ei = mid - 1;
            }
        }

        return new int[]{firstIndex, lastIndex};
    }

    // https://www.geeksforgeeks.org/problems/floor-in-a-sorted-array-1587115620/1
    public int findFloor(int[] arr, int target) {
        int n = arr.length;

        int si = 0;
        int ei = n-1;
        int floor = -1;

        while(si <= ei){
            int mid = (si+ei)/2;

            if(arr[mid] <= target){
                floor = mid;
                si = mid + 1;
            } else {
                ei = mid - 1;
            }
        }

        return floor;
    }

    // https://www.geeksforgeeks.org/problems/ceil-in-a-sorted-array/1 =================
    public int findCeil(int[] arr, int target) {
        int n = arr.length;

        int si = 0;
        int ei = n-1;

        int ceil = -1;

        while(si <= ei){
            int mid = (si+ei)/2;

            if(arr[mid] == target){
                ceil = mid;
                ei = mid - 1;
            } else if(arr[mid] < target){
                si = mid + 1;
            } else {
                ceil = mid;
                ei = mid - 1;
            }

            /*
                if(arr[mid] >= target){
                    ceil = mid;
                    ei = mid - 1;
                } else {
                    si = mid + 1;
                } 
            */
        }

        return ceil;
    }



    public static void main(String[] args){
        // int[] arr = {3,5,6,11,14,19,28};

        // int target = 110;

        // int[] floorCeil = FindFloorAndCeil(arr, target);
        // System.out.println("Floor value is " + floorCeil[0]);
        // System.out.println("Ceil value is " + floorCeil[1]);

        int[] arr = {1,3,3,3,5,5,5,5,8,11,13,13,19,19,19};
        int target = 1;

        int[] firstLast = findFirstAndLastIndex(arr, target);
        System.out.println("First index is " + firstLast[0]);
        System.out.println("Last index is " + firstLast[1]);
    }
}