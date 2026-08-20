class Main {
    public static void printAllSubsets(int[] nums, int idx, int targetSum, String asf){
        if(idx == nums.length){
            if(targetSum == 0){
                System.out.println(asf);
            }
            return;
        }

        // if(targetSum < 0){ // this is valid if we only have non-negative numbers
        //     return;
        // }

        printAllSubsets(nums, idx+1, targetSum - nums[idx], asf + nums[idx] + ","); // yes call
        printAllSubsets(nums, idx+1, targetSum, asf); // no call
    }

    public static void main(String[] args){
        int[] nums = {2,2,3,11,1,4,0}; // this can have negative numbers
        int targetSum = 5;

        printAllSubsets(nums,0,targetSum,"");
    }
}