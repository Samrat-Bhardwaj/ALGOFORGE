class Main {
    public static void printArray(int[] arr, int idx){
        if(idx == arr.length){
            return;
        }

        System.out.println(arr[idx]);
        printArray(arr, idx+1);
    }

    public static void printArrayReverse(int[] arr, int idx){
        if(idx == arr.length){
            return;
        }

        printArrayReverse(arr, idx+1);
        System.out.println(arr[idx]);
    }

    public static int maxOfArray(int[] arr, int idx){
        if(idx == arr.length){
            return Integer.MIN_VALUE;
        }

        int smallerAns = maxOfArray(arr, idx+1);

        int ans = Math.max(smallerAns, arr[idx]);

        return ans;
    }

    public static int findFirstIndex(int[] arr, int target, int idx){
        if(idx == arr.length){
            return -1;
        }

        int furtherFirstIndex = findFirstIndex(arr, target, idx+1);

        if(arr[idx] == target){ // idx is better answer
            return idx;
        } else {
            return furtherFirstIndex;
        }
    }

    public static int findLastIndex(int[] arr, int target, int idx){
        if(idx == arr.length){
            return -1;
        }

        int lastIndex = findLastIndex(arr,target,idx+1);

        if(lastIndex == -1 && arr[idx] == target){
            return idx;
        }

        return lastIndex;
    }

    public static int[] findAllIndices(int[] arr, int target, int idx, int fsf){
        if(idx == arr.length){
            int[] baseArray = new int[fsf];
            return baseArray;
        }

        int[] ans;
        if(arr[idx] == target){
            ans = findAllIndices(arr, target, idx+1, fsf+1);
        } else {
            ans = findAllIndices(arr, target, idx+1, fsf);
        }

        // fill Answer array
        if(arr[idx] == target){
            ans[fsf] = idx;
        }

        return ans;
    }

    public static void main(String[] args){
        // int[] arr = {2,5,7,1,9};
        // printArray(arr,0);
        // printArrayReverse(arr,0);
        // System.out.println(maxOfArray(arr,0));

        int[] arr = {10,4,19,4,4,4,4,4,19};
        // System.out.println(findLastIndex(arr,19,0));
        int[] ans = findAllIndices(arr,4,0,0);
        for(int i=0; i<ans.length; i++){
            System.out.print(ans[i] + " ");
        }
    }
}