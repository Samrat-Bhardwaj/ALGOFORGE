import java.util.Scanner;
class Questions {
    public static int countElementsGreaterThanX(int[] arr, int x){
        int count = 0;

        for(int i=0; i<arr.length; i++){
            int ele = arr[i];

            if(ele > x){
                count++;
            }
        }

        return count;
    }
    
    // span of array
    public static int findSpan(int[] arr){
        if(arr.length == 0){ // edge case
            return -1;
        }

        int max = Integer.MIN_VALUE; //-10^9 OR int max = arr[0];
        int min = Integer.MAX_VALUE;

        for(int i=0; i<arr.length; i++){
            int ele = arr[i];

            if(ele > max){
                max = ele;
            }
            if(ele < min){
                min = ele;
            }
        }

        int span = max - min;
        return span;
    }

    // Search in array (https://www.geeksforgeeks.org/problems/search-an-element-in-an-array-1587115621/1)
    public int search(int arr[], int tar) {
        int ansIdx = -1;

        for(int i=0; i<arr.length; i++){
            int ele = arr[i];

            if(ele == tar){
                ansIdx = i;
                break;
            }
        }

        return ansIdx;
    }

    // Search in array (https://www.geeksforgeeks.org/problems/search-an-element-in-an-array-1587115621/1)
    public int search(int arr[], int tar) {

        for(int i=0; i<arr.length; i++){
            int ele = arr[i];

            if(ele == tar){
                return i;
            }
        }

        return -1;
    }

    public static void printBuildings(int[] heights){
        int n = heights.length;

        int maxHeight = Integer.MIN_VALUE;
        for(int i=0; i<n; i++){
            int currBuildingHeight = heights[i];

            maxHeight = Math.max(maxHeight, currBuildingHeight);
        }

        int total_number_of_lines = maxHeight;
        int currFloor = maxHeight;

        while(currFloor > 0){
            // go to every building and check if currFloor for that building exists or not
            for(int i=0; i<n; i++){
                int currBuildingHeight = heights[i];

                if(currFloor <= currBuildingHeight){ // currFloor exists
                    System.out.print("* ");
                } else {
                    System.out.print("  ");
                }
            }

            // prepare for next line
            System.out.println();
            currFloor--;
        }
    }

    public static int[] sumOfTwoArrays(int[] arr1, int[] arr2){
        int n1 = arr1.length;
        int n2 = arr2.length;

        int resSize = Math.max(n1, n2) + 1;

        int[] res = new int[resSize];

        int i = arr1.length - 1;
        int j = arr2.length - 1;
        int k = res.length - 1;

        int carry = 0;

        while(k >= 0){
            int csum = 0;

            if(i >= 0){
                csum += arr1[i];
            }
            if(j >= 0){
                csum += arr2[j];
            }
            csum += carry;

            if(csum > 9){
                csum = csum % 10;
                carry = 1;
            } else {
                carry = 0;
            }

            res[k] = csum;

            i--;
            j--;
            k--;
        }

        return res;
    }

    // Reverse array (https://www.geeksforgeeks.org/problems/reverse-an-array/1)
    public void swap(int[] arr, int left, int right){
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    public void reverseArray(int arr[]) {
        int left = 0;
        int right = arr.length - 1;

        while(left < right){
            swap(arr, left, right);
            left++;
            right--;
        }
    }

    public void reverseArray(int[] arr){
        int n = arr.length;

        for(int i=0; i<n/2; i++){
            // swap i <-> n - 1 - i

            int temp = arr[i];
            arr[i] = arr[n-1-i]; // logic | formula
            arr[n-1-i] = temp;
        }
    }

    public void reverse(int[] arr, int left, int right){

        while(left < right){
            swap(arr, left, right);
            left++;
            right--;
        }

    }

    // Rotate an array (Leetcode 189)
    public void rotate(int arr[], int k) {
        int n = arr.length;
        
        k = k % n;

        if(k < 0){
            k = k + n;
        }

        reverse(arr,0,n-1);
        reverse(arr,0,k-1);
        reverse(arr,k,n-1);
    }

    public static void printAllSubArrays(int[] arr){
        int n = arr.length;
        
        for(int si = 0; si<n; si++){
            for(int ei=si; ei<n; ei++){
                // print array from si to ei

                for(int k=si; k<=ei; k++){
                    System.out.print(arr[k] + " ");
                }

                System.out.println();
            }
        }
    }

    // Leetcode O(N^3)
    public int maxSubArray(int[] arr) {
        int n = arr.length;

        int maxSum = Integer.MIN_VALUE;
        
        for(int si = 0; si<n; si++){
            for(int ei=si; ei<n; ei++){
                // find sum from si to ei
                int currSum = 0;
                for(int k=si; k<=ei; k++){
                    currSum += arr[k];
                }

                maxSum = Math.max(maxSum, currSum);
            }
        }

        return maxSum;
    }

    // O(N^2)
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int maxSum = Integer.MIN_VALUE;

        int maxSubSi = -1;
        int maxSubEi = -1;

        for(int si=0; si<n; si++){
            int csum = 0;
            
            for(int ei=si; ei<n; ei++){
                csum += nums[ei];

                if(maxSum < csum){
                    maxSum = csum;
                    maxSubSi = si;
                    maxSubEi = ei;
                }
            }
        }   

        return maxSum;
    }

    // O(N) Kadane's Algorithm (Leetcode 53)
    public int maxSubArray(int[] nums){
        int meh = 0; // max_ending_here => max sum ending at currentIdx

        int maxSum = Integer.MIN_VALUE;

        for(int i=0; i<nums.length; i++){
            meh += nums[i];

            maxSum = Math.max(maxSum, meh);

            if(meh < 0){ // new subarray from next idx
                meh = 0;
            }
        }

        return maxSum;
    }

    public int maxSubArray(int[] nums){
        int meh = 0; // max_ending_here => max sum ending at currentIdx

        int maxSum = Integer.MIN_VALUE;
        int maxSumSi = -1;
        int maxSumEi = -1;

        int currentStart = 0;

        for(int i=0; i<nums.length; i++){
            meh += nums[i];

            if(maxSum < meh){
                maxSum = meh;
                maxSumSi = currentStart;
                maxSumEi = i;
            }

            if(meh < 0){ // new subarray from next idx
                meh = 0;
                currentStart = i+1;
            }
        }

        System.out.println("Maximum subbarray start and end point is: " + maxSumSi + "," + maxSumEi);

        return maxSum;
    }






















    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        System.out.print("Enter the size of array: ");
        int size = scn.nextInt();

        int[] arr = new int[size];

        System.out.print("Enter " + size + " elements of array: ");
        
        for(int i=0; i<arr.length; i++){
            arr[i] = scn.nextInt();
        }

        // System.out.print("Enter the value of x: ");
        // int x = scn.nextInt();

        // int res = countElementsGreaterThanX(arr, x);

        // System.out.println("Number of elements greater than x: " + res);

        int span = findSpan(arr);
        System.out.println("Span of array is: " + span);
    }
}