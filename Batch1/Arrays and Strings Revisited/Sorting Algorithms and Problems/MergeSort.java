class MergeSort {
    // merge two sorted arrays
    public static int[] mergeSortedArrays(int[] a, int[] b){
        int n1 = a.length;
        int n2 = b.length;

        int[] res = new int[n1+n2]; 
        int i = 0; // to traverse a
        int j = 0; // to traverse b
        int k = 0; // to traverse res

        while(i < n1 && j< n2){
            if(a[i] <= b[j]){
                res[k] = a[i];
                i++;
            } else {
                res[k] = b[j];
                j++;
            }
            k++;
        }

        while(i < n1){
            res[k] = a[i];
            i++;
            k++;
        }

        while(j < n2){
            res[k] = b[j];
            j++;
            k++;
        }

        return res;
    }

    public static int[] mergeSort(int[] arr, int si, int ei){
        if(si == ei){
            int[] ba = new int[1];
            ba[0] = arr[si];
            return ba;
        }

        int mid = (si + ei)/2;

        int[] left = mergeSort(arr, si, mid);
        int[] right = mergeSort(arr, mid+1, ei);

        int[] sortedArray = mergeSortedArrays(left, right);
        return sortedArray;
    }

    public static void main(String[] args){
        // int[] a = {1,2,5,8,9,13};
        // int[] b = {3,4,6,10,12,15,16};

        int[] arr = {19,16,15,4,3,11,5};
        int[] res = mergeSort(arr,0,arr.length-1);
        for(int i=0; i<res.length; i++){
            System.out.print(res[i] + " ");
        }
    }
}