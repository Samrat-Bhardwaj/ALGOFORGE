class Questions {
    // Segregate negative and positive numbers
    public static void swap(int i, int j, int[] arr){
        int temp = arr[i];
        
        arr[i] = arr[j];
        arr[j] = temp;
    }


    // 0 <-> i => (-)ve numbers
    // (i+1) <-> j => unexplored
    // j <-> n-1 => (+)ve numbers
    public static void segregatePosNeg(int[] arr){
        int n = arr.length;

        int i = -1;
        int j = n-1;

        while(i < j){
            if(arr[i+1] < 0){
                i++;
            } else {
                swap(i+1,j,arr);
                j--;
            }
        }
    }

    // Segregate 0s and 1s (https://www.geeksforgeeks.org/problems/segregate-0s-and-1s5106/1)
    void segregate0and1(int[] arr) {
        int n = arr.length;

        int i = -1;
        int j = n-1;

        while(i < j){
            if(arr[i+1] == 0){
                i++;
            } else {
                swap(i+1,j,arr);
                j--;
            }
        }
    }

    // Leetcode 75
    // (0-p1) => 0s
    // (p1 + 1 <-> p2 - 1) => 1s
    // (p2 <-> p3) => unexplored
    // p3+1 <-> n-1 => 2s
    public void sortColors(int[] nums) {
        int n = nums.length;

        int p1 = -1;
        int p2 = 0;
        int p3 = n-1;

        while(p2 <= p3){
            if(nums[p2] == 1){
                p2++; // increase area of 1
            } else if(nums[p2] == 2){
                swap(p2,p3,nums); // shift to end of unexplored area
                p3--;   // last of unexplored area is now under 2 ka area
            } else {
                swap(p1+1, p2, nums);
                p1++; // increasing area of zero
                p2++; // p2 now will have a one (came from p1+1)
            }
        }
    }

    public static void main(String[] args){
        int[] arr = {-8,14,15,-11,-12,13,-7,8,-19};

        segregatePosNeg(arr);

        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i] + ", ");
        }
    }
}