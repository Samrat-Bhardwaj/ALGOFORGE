class QuickSort {
    public static void swap(int[]nums, int x, int y){
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }

    public static void partition(int[] nums, int pivotElement){
        int i=0;
        int j=0;

        while(j<nums.length){
            if(nums[j] <= pivotElement){
                swap(nums,i,j);
                i++;
            }
            j++; // should increase everytime whether we find a smaller or larger element
        }
    }

    public static int partitionArray(int[] nums, int si, int ei){
        int pivotElement = nums[ei];
        int i=si;
        int j=si;

        while(j <= ei-1){
            if(nums[j] <= pivotElement){
                swap(nums,i,j);
                i++;
            }
            j++; // should increase everytime whether we find a smaller or larger element
        }

        swap(nums,i,ei);
        return i; // pivotIndex
    }

    public static void quickSort(int[] nums, int si, int ei){
        if(si > ei){
            return;
        }

        int pivotIndex = partitionArray(nums,si, ei);

        quickSort(nums, si, pivotIndex-1);
        quickSort(nums, pivotIndex + 1, ei);
    }

    public static void main(String[] args){
        int[] arr = {19,16,15,4,3,11,5};
        quickSort(arr, 0, arr.length-1);
        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }
}