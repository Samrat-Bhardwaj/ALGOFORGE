class XSumProblems {
    // Leetcode 167
    public int[] twoSum(int[] nums, int target) {
        int i = 0;
        int j = nums.length-1;

        while(i < j){
            int sum = nums[i] + nums[j];

            if(sum == target){
                return new int[]{i+1,j+1};
            } else if(sum < target){
                i++;
            } else {
                j--;
            }
        }

        return new int[]{};
    }

    // https://www.geeksforgeeks.org/problems/all-distinct-pairs-with-given-sum/1
    public List<List<Integer>> distinctPairs(int[] arr, int target) {
        Arrays.sort(arr);
        int n = arr.length;

        int i = 0;
        int j = n-1;

        List<List<Integer>> ans = new ArrayList<>();

        while(i < j){
            int sum = arr[i] + arr[j];

            if(sum == target){
                ans.add(Arrays.asList(arr[i],arr[j]));
                i++;
                j--;
            } else if(sum < target){
                i++;
            } else {
                j--;
            }

            // skip over duplicate elements
            while(i>0 && i<n && arr[i-1] == arr[i]) i++;
            while(j<n-1 && j>=0 && arr[j+1] == arr[j]) j--;
        }

        return ans;
    }

    // Three sum (Leetcode 15) =============================
    public void addFixedElement(List<List<Integer>> ans, List<List<Integer>> allPairs, int fixedEle){
        for(List<Integer> pair: allPairs){
            List<Integer> smallAns = new ArrayList<>();
            smallAns.add(fixedEle);
            smallAns.addAll(pair);

            ans.add(smallAns);
        }
    }

    public List<List<Integer>> getPairs(int[] nums, int si, int ei, int target){
        List<List<Integer>> ans = new ArrayList<>();

        int i = si;
        int j = ei;

        while(i < j){
            int sum = nums[i] + nums[j];

            if(sum == target){
                ans.add(Arrays.asList(nums[i],nums[j]));
                i++;
                j--;
            } else if(sum < target){
                i++;
            } else {
                j--;
            }

            while(i > si && i <= ei && nums[i-1] == nums[i]) i++;
            while(j < ei && j >= si && nums[j+1] == nums[j]) j--;
        }

        return ans;
    }

    public List<List<Integer>> threeSum_generic(int[] nums, int si, int ei, int target){
        List<List<Integer>> ans = new ArrayList<>();

        for(int i=si; i<=ei; i++){
            if(i > si && nums[i-1] == nums[i]) continue;

            int fixedEle = nums[i];

            int updatedTarget = target - fixedEle;

            List<List<Integer>> allPairs = getPairs(nums,i+1,ei,updatedTarget);

            if(allPairs.size() > 0){
                addFixedElement(ans, allPairs, fixedEle);
            }
        }
        return ans;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        return threeSum_generic(nums,0,nums.length-1,0);
    }
}