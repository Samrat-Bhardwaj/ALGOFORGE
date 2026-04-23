class MergeSort {
    public static int[] mergeSortedArrays(int[] a, int[] b){
        int n = a.length;
        int m = b.length;

        int[] res = new int[n+m]; 

        int i=0; // to traverse on a
        int j=0; // to traverse on b
        int k=0; // to traverse on res

        while(i<n && j<m){
            if(a[i] < b[j]){
                res[k] = a[i];
                i++;
            } else {
                res[k] = b[j];
                j++;
            }
            k++;
        }

        while(i<n){
            res[k] = a[i];
            i++;
            k++;
        }

        while(j<m){
            res[k] = b[j];
            j++;
            k++;
        }

        return res;
    }

    public static int[] mergeSort(int[] nums, int si, int ei){
        if(si == ei){
            int[] ba = new int[1];
            ba[0] = nums[si];
            return ba;
        }

        int mid = (si + ei)/2;

        int[] left = mergeSort(nums, si, mid);
        int[] right = mergeSort(nums, mid+1, ei);

        int[] sortedNums = mergeSortedArrays(left, right);

        return sortedNums;
    }

    // Count inversions 
    class Solution {
        static int totalInversion;
        public static int[] mergeSortedArrays(int[] left, int[] right){
            int n = left.size();
            int m = right.size();

            int[] res = new int[n+m];

            int i=0;
            int j=0;
            int k=0;

            while(i<n && j<m){
                if(left[i] <= right[j]){
                    res[k] = left[i];
                    i++;
                } else {
                    res[k] = right[j];
                    j++;
                    totalInversion += (n-i);
                }
                k++;
            }

            while(i<n){
                res[k] = left[i];
                i++;
                k++;
            }

            while(j<m){
                res[k] = right[j];
                j++;
                k++;
            }
            
            return res;
        }

        public static int[] mergeSort(int[] nums, int si, int ei){
            if(si == ei){
                int[] ba = new int[1];
                ba[0] = nums[si];
                return ba;
            }

            int mid = (si + ei)/2;

            int[] left = mergeSort(nums, si, mid);
            int[] right = mergeSort(nums, mid+1, ei);

            int[] sortedNums = mergeSortedArrays(left, right);

            return sortedNums;
        }

        static int inversionCount(int arr[]) {
            totalInversion = 0;

            mergeSort(arr,0,arr.length-1);
            return totalInversion;
        }
    }

    public static void main(String[] args){
        // int[] a = {1,2,11,19,24,25};
        // int[] b = {3,4,12,20};

        // int[] res = mergeSortedArrays(a,b);

        // for(int i=0; i<res.length; i++){
        //     System.out.print(res[i] + ", ");
        // }

        int[] nums = {9,11,1,4,7,5,21};

        int[] sortedNums = mergeSort(nums,0,nums.length-1);
        for(int i=0; i<sortedNums.length; i++){
            System.out.print(sortedNums[i] + ", ");
        }
    }
}