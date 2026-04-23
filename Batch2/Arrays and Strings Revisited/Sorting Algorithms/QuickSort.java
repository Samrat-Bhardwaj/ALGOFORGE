class QuickSort {
    public static void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void sortWithPivot(int[] nums, int pivotEle){
        int i = 0;
        int j = nums.length-1;

        while(i<j){
            if(nums[i] <= pivotEle){
                i++;
            } else {
                swap(nums,i,j);
                j--;
            }
        }
    }

    public static int sortAccordingToPivot(int[] nums, int si, int ei){
        int pivotEle = nums[ei];

        int i = si;
        int j = ei-1;

        while(i <= j){
            if(nums[i] <= pivotEle){
                i++;
            } else {
                swap(nums, i, j);
                j--;
            }
        }

        // start of larger elements = j+1
        swap(nums,j+1,ei); // pivotEle is now at the start of larger elements
        return j+1;
    }

    public static void quickSort(int[] nums, int si, int ei){
        if(si >= ei){
            return;
        }

        int pivotIdx = sortAccordingToPivot(nums, si, ei);

        quickSort(nums, si, pivotIdx-1);
        quickSort(nums, pivotIdx+1, ei);
    }

    public static void main(String[] args){
        int[] nums = {1,9,5,3,11,7,4};

        quickSort(nums,0,nums.length-1);

        for(int i=0; i<nums.length; i++){
            System.out.print(nums[i] + ",");
        }
    }
}