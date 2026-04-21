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

    // Find k closest elements (Leetcode 658)
    class Solution {
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

        public List<Integer> findClosestElements(int[] arr, int k, int x) {
            int n = arr.length;

            List<Integer> res = new ArrayList<>();
            for(int e: arr) res.add(e);

            if(x < arr[0]){
                return res.subList(0,k);
            } else if(x > arr[n-1]){
                return res.subList(n-k,n);
            }

            int insertionIdx = searchInsert(arr,x);

            int si = Math.max(0,insertionIdx-k);
            int ei = Math.min(n-1, insertionIdx+k);

            while((ei - si + 1) > k){
                if((x - arr[si]) <= (arr[ei] - x)){
                    ei--;
                } else {
                    si++;
                }
            }

            return res.subList(si, ei+1);
        }
    }
}