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
}