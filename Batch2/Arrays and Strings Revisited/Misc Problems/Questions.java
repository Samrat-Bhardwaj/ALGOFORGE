class Questions {
    // https://www.geeksforgeeks.org/problems/count-pairs-with-given-sum--150253/1
    int countPairs(int arr[], int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;

        for(int i=0; i<arr.length; i++){
            int counter = target - arr[i];

            count += map.getOrDefault(counter, 0);

            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        return count;
    }

    // 4 sum 2 (Leetcode 454)
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;

        for(int a: nums1){
            for(int b: nums2){
                map.put(a+b, map.getOrDefault(a+b,0) + 1);
            }
        }

        for(int c: nums3){
            for(int d: nums4){
                int counter = 0 - (c + d); // target - current_sum

                count += map.getOrDefault(counter, 0);
            }
        }

        return count;
    }

    // Moore's voting algorithm (Majority Element, leetcode 169)
    public int majorityElement(int[] nums) {
        int candidate = -1;
        int count = 0;

        for(int i=0; i<nums.length; i++){
            if(count == 0){
                candidate = nums[i];
                count = 1;
                continue;
            }

            if(nums[i] == candidate){
                count++;
            } else {
                count--;
            }
        }

        return candidate;
    }

    // Majority Element 2 (Leetcode 229)
    public List<Integer> majorityElement(int[] nums) {
        int n = nums.length;
        int candidate1 = 0;
        int candidate2 = 0;
        int count1 = 0;
        int count2 = 0;

        for(int ele: nums){
            if(count1 != 0 && candidate1 == ele){
                count1++;
            } else if(count2 != 0 && candidate2 == ele){
                count2++;
            } else if(count1 == 0){
                candidate1 = ele;
                count1 = 1;
            } else if(count2 == 0){
                candidate2 = ele;
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }

        // not neccessory that candidate1 and candidate2 is correct
        count1=0;count2=0;

        for(int ele: nums){
            if(ele == candidate1) count1++;
            if(ele == candidate2) count2++;
        }

        List<Integer> ans = new ArrayList<>();
        if(count1 > (n/3)){
            ans.add(candidate1);
        }

        // second case is when whole array is {0,0,0....}
        if(count2 > (n/3) && candidate1 != candidate2){
            ans.add(candidate2);
        }

        return ans;
    }

    // Find missing and positive number 
    ArrayList<Integer> findTwoElement(int arr[]) {
        int n = arr.length;
        int missing = -1;
        int repeating = -1;

        // how to find repeating number
        for(int i=0; i<n; i++){
            int ele = Math.abs(arr[i]);

            if(arr[ele-1] < 0){ // if it is already negative
                repeating = ele;
            } else {
                arr[ele-1] *= -1;
            }
        }

        // how to find missing number
        for(int i=0; i<n; i++){
            if(arr[i] > 0){
                missing = (i+1);
            }
        }
        
        ArrayList<Integer> ans = new ArrayList<>();
        
        ans.add(repeating);
        ans.add(missing);
        
        return ans;
    }

    // Leetcode 152 (Maximum product subarray)
    public int maxProduct(int[] nums) {
        int minProductSoFar = nums[0];
        int maxProductSoFar = nums[0];

        int res = nums[0];

        for(int i=1; i<nums.length; i++){
            if(nums[i] < 0){ // swap min and max
                int temp = minProductSoFar;

                minProductSoFar = maxProductSoFar;
                maxProductSoFar = temp;
            }

            // a new subarray can start from today
            minProductSoFar = Math.min(nums[i], minProductSoFar * nums[i]);
            maxProductSoFar = Math.max(nums[i], maxProductSoFar * nums[i]);

            res = Math.max(res, maxProductSoFar);
        }

        return res;
    }

    // Leetcode 134 (Gas station)
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int total_gas = 0;
        int total_cost = 0;

        int current_gas = 0;
        int start = 0;

        for(int i=0; i<gas.length; i++){
            current_gas += (gas[i] - cost[i]);

            if(current_gas < 0){
                start = i + 1;
                current_gas = 0;
            }

            total_gas += gas[i];
            total_cost += cost[i];
        }

        return (total_gas - total_cost) < 0 ? -1 : start;
    }

    // Leetcode 1539 =======================
    // O(N);
    public int findKthPositive(int[] arr, int k) {
        int idx = -1;

        for(int i=0; i<arr.length; i++){
            int expected_num = (i+1);

            int diff = arr[i] - expected_num;

            if(diff < k){ 
                idx = i; // the index till which our missing number is not present
            } else {
                break;
            }
        }

        return idx + 1 + k;
    }

    public int findKthPositive(int[] arr, int k) {
        int left = 0;
        int right = arr.length - 1;

        while(left <= right){
            int mid = (left + right)/2;

            int expected_num = (mid+1);
            int diff = arr[mid] - expected_num;

            if(diff < k){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right + 1 + k;
    }

    // Leetcode 33 (Search in rotated sorted Array)
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while(left <= right){
            int mid = (left + right)/2;

            if(nums[mid] == target){
                return mid;
            } else if(nums[left] <= nums[mid]){ // left to mid is sorted
                if(nums[left] <= target && target < nums[mid]){
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else { // mid to right is sorted
                if(nums[mid] < target && target <= nums[right]){
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }

    // Leetcode 81 ==================================
    // Average case = log(N), worst case = O(N)
    public boolean search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while(left <= right){
            int mid = (left + right)/2;

            if(nums[mid] == target || nums[right] == target){ // we may remove right from search range 
                return true;
            } else if(nums[left] < nums[mid]){ // left to mid is sorted
                if(nums[left] <= target && target < nums[mid]){
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else if(nums[mid] < nums[right]) { // mid to right is sorted
                if(nums[mid] < target && target <= nums[right]){
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                right--; // or left++;
            }
        }

        return false;
    }

    // Leetcode 4. Median of Two Sorted Arrays
    public double findMedianSortedArrays(int[] A, int[] B) {
        if(A.length > B.length){ // swap
            int[] temp = A;
            A = B;
            B = temp;
        }

        int n = A.length;
        int m = B.length;

        int total_ele = n + m;

        int left = 0;
        int right = n;

        while(left <= right){
            int mid = (left+right)/2; // left numbers of "A" arrays is from 0 to mid

            int secondArrayEle = ((total_ele+1)/2) - (mid); // +1 makes sure left will have one more element

            int aleft = (mid == 0) ? Integer.MIN_VALUE : A[mid-1] ;
            int aright = (mid == n) ? Integer.MAX_VALUE: A[mid];
            int bleft = (secondArrayEle == 0) ? Integer.MIN_VALUE : B[secondArrayEle-1];
            int bright = (secondArrayEle == m) ? Integer.MAX_VALUE : B[secondArrayEle];

            if(aleft <= bright && bleft <= aright){
                // find mid according to even or odd elements
                if(total_ele % 2 == 0){
                    return ((Math.max(aleft, bleft) + Math.min(aright, bright))*1.0)/2.0;
                } else {
                    return Math.max(aleft, bleft) * 1.0;
                }
            } else if(bleft > aright){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return 0.0;   
    }

    // Leetcode 56 (Merge Intervals)
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        ArrayList<int[]> merged = new ArrayList<>();

        Arrays.sort(intervals, (int[] a, int[] b) -> {
            return a[0] - b[0];
        });

        int[] last = intervals[0];

        for(int i=1; i<n; i++){
            if(last[1] >= intervals[i][0]){
                last[1] = Math.max(last[1], intervals[i][1]);
            } else {
                merged.add(last);
                last = intervals[i];
            }
        }

        merged.add(last);
        return merged.toArray(new int[merged.size()][]);
    }

    
}