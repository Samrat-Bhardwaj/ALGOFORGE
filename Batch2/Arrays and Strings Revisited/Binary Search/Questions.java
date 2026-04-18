class Questions {
    // find insertion index(Leetcode 35)
    public int searchInsert(int[] nums, int target) {
        int n = nums.length;

        int left = 0;
        int right = n - 1;

        while(left <= right){
            int mid = (left + right)/2;

            if(nums[mid] == target){
                return mid;
            } else if(nums[mid] < target){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    // Leetcode 34 (Find first and last index)
    public int findFirstIndex(int[] nums, int target, int n){
        int left = 0;
        int right = n-1;

        int firstIdx = -1;

        while(left <= right){
            int mid = (left + right)/2;

            if(nums[mid] == target){
                firstIdx = mid;
                right = mid - 1;
            } else if(nums[mid] < target){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return firstIdx;
    }

    public int findLastIndex(int[] nums, int target, int n){
        int left = 0;
        int right = n-1;

        int lastIdx = -1;

        while(left <= right){
            int mid = (left + right)/2;

            if(nums[mid] == target){
                lastIdx = mid;
                left = mid + 1;
            } else if(nums[mid] < target){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return lastIdx;
    }

    public int[] searchRange(int[] nums, int target) {
        int n = nums.length;

        int firstIdx = findFirstIndex(nums,target,n);
        int lastIdx = findLastIndex(nums,target,n);

        return new int[]{firstIdx, lastIdx};
    }
}