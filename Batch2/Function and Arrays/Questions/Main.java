import java.util.Scanner;
class Main {
    public static int factorial(int n){
        int res = 1;

        for(int mul=1; mul<=n; mul++){
            res = res*mul;
        }

        return res;
    }

    public static int findNCR(int n, int r){
        int nFact = factorial(n);
        int rFact = factorial(r);
        int nMrFact = factorial(n-r);

        int ncr = nFact/(rFact * nMrFact);

        return ncr;
    }

    public static int binaryToDecimal(int num){
        int currBase = 1;
        int decimalNum = 0;

        while(num > 0){
            int lastDigit = num%10;

            decimalNum += lastDigit*currBase;
            currBase *= 2;
            num /= 10;
        }

        return decimalNum;
    }

    public static int elementsGreaterThanX(int[] arr, int x){
        // write your code here
        int n = arr.length;
        int count = 0;

        for(int i=0; i<n; i++){
            int ele = arr[i];
            if(ele > x){
                count++;
            }
        }

        return count;
    }

    public static int spanOfArray(int[] arr){
        int n = arr.length;

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for(int i=0; i<n; i++){
            int ele = arr[i];

            max = Math.max(max, ele);
            // if(ele > max){
            //     max = ele;
            // }
            min = Math.min(min, ele);
            // if(ele < min){
            //     min = ele;
            // }
        }

        int span = max - min;
        return span;
    }

    public static int findTarget(int[] arr, int tar){
        int n = arr.length;

        for(int i=0; i<n; i++){
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

    public static void swapValues(int[] arr, int x, int y){
        int valueAtX = arr[x];
        int valueAtY = arr[y];

        arr[x] = valueAtY;
        arr[y] = valueAtX;
    }

    public static void reverseArray(int[] arr){
        int n = arr.length;
        int start = 0;
        int end = n-1;

        while(start < end){
            swapValues(arr,start,end);

            start++;
            end--;
        }
    }

    public static void reverseArray2(int[] arr){
        int n = arr.length;
        
        for(int i=0; i<n/2; i++){
            // swapValues(arr, i, n-i-1);

            int temp = arr[i];
            arr[i] = arr[n-i-1];
            arr[n-i-1] = temp;
        }
    }

    // rotate Array =============================================
    public static void reverseArray(int[] arr, int start, int end){
        while(start < end){
            int temp = arr[start];

            arr[start] = arr[end];
            arr[end] = temp;

            start++;
            end--;
        }
    }

    public static void rotateArray(int[] arr, int k){
        int n = arr.length;

        k = k % n;

        if(k<0){
            k = k + n;
        }

        reverseArray(arr, 0 , n-k-1); // reversing A
        reverseArray(arr, n-k, n-1); // reversing B
        reverseArray(arr, 0, n-1); // reversing A`B`
    }

    // Find inverse of array =============================================
    public static int[] findInverseArray(int[] arr){
        int n = arr.length;

        int[] res = new int[n];

        for(int i=0; i<n; i++){
            int pos = arr[i];

            res[pos] = i;
        }

        return res;
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

    // Find max sum of any subarray (N^3) =======================================
    public static int maxSumSubArray(int[] nums){
        int n = nums.length;

        int maxSum = Integer.MIN_VALUE;

        for(int si=0; si<n; si++){
            for(int ei=si; ei<n; ei++){
                // si to ei
                int currSum = 0;
                for(int k=si; k<=ei; k++){
                    currSum += nums[k];
                }
                maxSum = Math.max(maxSum, currSum);
            }
        }

        return maxSum;
    }


    // Find max sum of any subarray (N^2) =======================================
    public static int maxSumSubArray2(int[] nums){
        int n = nums.length;

        int maxSum = Integer.MIN_VALUE;

        for(int si=0; si<n; si++){
            int currSum = 0;
            for(int ei=si; ei<n; ei++){
                currSum += nums[ei];
                maxSum = Math.max(maxSum, currSum);
            }
        }

        return maxSum;
    }

    public static int maxSumSubArrayWithIndices(int[] nums){
        int n = nums.length;

        int maxSum = Integer.MIN_VALUE;
        int ansSI = -1;
        int ansEI = -1;

        for(int si=0; si<n; si++){
            int currSum = 0;
            for(int ei=si; ei<n; ei++){
                currSum += nums[ei];
                if(maxSum < currSum){
                    ansSI = si;
                    ansEI = ei;
                    maxSum = currSum;
                }
            }
        }

        System.out.println("Subarray indices with largest sum is " + ansSI + "," + ansEI);
        return maxSum;
    }

    // Max sum subarray using Kadane's algorithm (Leetcode 53) ==============================
    public int maxSubArray(int[] nums) {
        int n = nums.length;

        int maxSum = Integer.MIN_VALUE;
        int meh = 0;

        for(int i=0; i<n; i++){
            meh += nums[i];

            maxSum = Math.max(maxSum, meh);

            if(meh < 0){
                meh = 0;
            }
        }

        return maxSum;
    }

    // Max sum subarray using Kadane's algorithm (Leetcode 53) ==============================
    public int maxSubArrayWithIndices(int[] nums) {
        int n = nums.length;

        int maxSum = Integer.MIN_VALUE;
        int meh = 0;

        int maxSum_si = -1;
        int maxSum_ei = -1;

        int curr_si = 0;

        for(int idx=0; idx<n; idx++){
            meh += nums[idx];

            if(meh > maxSum){
                maxSum = meh;

                maxSum_si = curr_si;
                maxSum_ei = idx; // today is when my subarray ends
            }

            if(meh < 0){
                meh = 0;
                curr_si = idx + 1; // tomorrow is when we start a new subarray
            }
        }

        System.out.println("Subarray indices with largest sum is " + maxSum_si + "," + maxSum_ei);
        return maxSum;
    }

    // print all substets 
    public static void printSubsets(int[] nums){
        int n = nums.length;

        int totalSubsets = (int)Math.pow(2,n);

        for(int i=0; i<totalSubsets; i++){
            
            int current_num = i;
            String sub = "";

            // convert current_num to binary
            for(int idx = n-1; idx>=0; idx--){
                int rem = current_num % 2;

                if(rem == 0){ // nums[idx] said no
                    sub = "_," + sub;
                } else { // nums[idx] said yes
                    sub = nums[idx] + "," + sub;
                }

                current_num = current_num/2;
            }

            System.out.println(sub);
        }
    }





























    public static int[] takeArrayInput(){
        Scanner scn = new Scanner(System.in);

        System.out.println("What should be the size of array?");

        int size = scn.nextInt();

        int[] arr = new int[size];

        System.out.println("Enter values for your array");

        for(int idx=0; idx < size; idx++){
            int input = scn.nextInt();
            arr[idx] = input;
        }

        return arr;
    }

    public static void printArray(int[] arr){
        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i] + ", ");
        }
    }

    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        // int n = scn.nextInt();
        // int r = scn.nextInt();

        // int ncr = findNCR(n,r);
        // System.out.println(ncr);

        // int num = scn.nextInt();
        // System.out.println(binaryToDecimal(num));

        int[] arr = takeArrayInput();
        // int k = scn.nextInt();
        // int[] arr2 = takeArrayInput();

        // int[] res = findInverseArray(arr);
        // (arr,k);
        printSubsets(arr);
        // System.out.println(maxSumSubArrayWithIndices(arr));

        // int x = scn.nextInt();
        // printBuildings(arr);
    }
}