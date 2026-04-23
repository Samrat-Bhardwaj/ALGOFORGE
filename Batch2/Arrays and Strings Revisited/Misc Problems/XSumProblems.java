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
}