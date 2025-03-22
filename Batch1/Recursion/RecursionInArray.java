class RecursionInArray {
    public static void printArray(int[] arr, int idx){
        if(idx == arr.length){
            return;
        }

        System.out.println(arr[idx]);
        printArray(arr, idx+1);
    }

    public static void printReverseArray(int[] arr, int idx){
        if(idx == arr.length){
            return;
        }

	
        printReverseArray(arr, idx+1);
        System.out.println(arr[idx]);
    }

    public static int findMax(int[] arr, int idx){
        if(idx == arr.length){
            return Integer.MIN_VALUE;
        }

        int maxSoFar = findMax(arr, idx + 1);
        
        int ans = Math.max(maxSoFar, arr[idx]);

        return ans;
    }

    public static int firstIndex(int[] arr, int idx, int tar){
        if(idx == arr.length){
            return -1;
        }

        int furtherFirstIndex = firstIndex(arr, idx+1, tar);

        if(arr[idx] == tar){
            return idx;
        } else {
            return furtherFirstIndex;
        }
    }

    public static int lastIndex(int[] arr, int idx, int tar){
        if(idx == arr.length){
            return -1;
        }

        int furtherLastIndex = lastIndex(arr, idx+1, tar);

        if(furtherLastIndex == -1 && arr[idx] == tar){
            return idx;
        } else {
            return furtherLastIndex;
        }

    }

    public static int[] allIndices(int[] arr, int idx, int tar, int fsf){
        if(idx == arr.length){
            int[] baseArray = new int[fsf];
            return baseArray;
        }
        

        int[] ans;
        if(arr[idx] == tar){
            ans = allIndices(arr, idx+1, tar, fsf+1);
        } else {
            ans = allIndices(arr, idx+1, tar, fsf);
        }

        // fill ans array
        if(arr[idx] == tar){
            ans[fsf] = idx;
        }

        return ans;
    }


    public static void main(String[] args){
        int[] arr = {3, 5, 7, 9, 5, 5, 11, 5, 8};

        int[] ans = allIndices(arr, 0, 5, 0);

        for(int i=0; i<ans.length; i++){
            System.out.print(ans[i] + " ");
        }
    }
}