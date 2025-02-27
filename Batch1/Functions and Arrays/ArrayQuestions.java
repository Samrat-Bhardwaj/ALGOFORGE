import java.util.*;
class ArrayQuestions {
    public static int findElementsGreaterThan40(int[] arr){
        int count = 0;

        for(int idx=0; idx<arr.length; idx++){
            int ele = arr[idx];

            if(ele > 40){
                count++;
            }
        }

        return count;
    }

    public static void printArray(int[] arr){
        System.out.println("Printing the array");

        for(int idx=0; idx<arr.length; idx++){
            System.out.println("Value at index " + idx + " is " + arr[idx]);
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

    public static int spanOfArray(int[] arr){
        int n = arr.length;

        int max = Integer.MIN_VALUE; 
        int min = Integer.MAX_VALUE;   //1000; // greater than everything in my array

        for(int idx=0; idx<n; idx++){
            int ele = arr[idx];

            // if(ele > max){
            //     max = ele;
            // }

            max = Math.max(ele, max);
            // max = Math.max(12,3); // max = 12

            if(ele < min){
                min = ele;
            }
            // min = Math.min(ele,min);
        }

        int span = max - min;

        return span;
    }

    public static int searchInArray(int[] arr, int tar){
        int n = arr.length;

        for(int idx=0; idx<n; idx++){
            int ele = arr[idx];

            if(ele == tar){
                return idx;
            }
        }

        return -1;
    }

    public static void printBuildings(int[] arr){
        int n = arr.length;

        int max = Integer.MIN_VALUE;

        for(int idx=0; idx<n; idx++){
            max = Math.max(max, arr[idx]);
        }

        int total_number_of_lines = max;
        int current_floor = total_number_of_lines;

        while(current_floor > 0){
            for(int idx = 0; idx<n; idx++){
                int currentBuildingHeight = arr[idx];

                if(current_floor <= currentBuildingHeight){
                    System.out.print("* ");
                } else {
                    System.out.print("  ");
                }
            }

            System.out.println();
            current_floor--;
        }
    }







    public static int[] sumOfArrays(int[] arr1, int[] arr2){
        int n = arr1.length;
        int m = arr2.length;

        int resSize = Math.max(n,m) + 1;
        int[] res = new int[resSize];

        int i = n - 1;
        int j = m - 1;
        int k = resSize - 1;
        int carry = 0;


        while(k >= 0){
            int sum = 0;

            if(i >= 0){
                sum += arr1[i];
            }
            if(j >= 0){
                sum += arr2[j];
            }
            sum = sum + carry;

            if(sum > 9){
                sum = sum % 10;
                carry = 1;
            } else {
                carry = 0;
            }

            res[k] = sum;

            i--;
            j--;
            k--;
        }

        return res;
    }

    public static int[] subtractTwoArrays(int[] arr1, int[] arr2){ // arr2 - arr1
        int n = arr1.length;
        int m = arr2.length;

        int[] res = new int[m];

        int i = n-1;
        int j = m-1;
        int k = m-1;

        int borrow = 0;

        while(k >= 0){
            int diff = arr2[j] - borrow; // j will never be negative inside this loop

            if(i >= 0){
                diff = diff - arr1[i];
            }

            if(diff < 0){
                diff = diff + 10;
                borrow = 1;
            } else {
                borrow = 0;
            }

            res[k] = diff;

            i--;
            j--;
            k--;
        }

        return res;
    }

    public static void reverseArray(int[] arr){
        int n = arr.length;

        for(int i=0; i<n/2; i++){
            int frontEle = arr[i];
            int backEle = arr[n-1-i];

            arr[i] = backEle;
            arr[n-i-1] = frontEle;
        }
    }

    public static void swapArrayElements(int[] arr, int i, int j){
        // int temp = arr[i];
        // arr[i] = arr[j];
        // arr[j] = temp;

        int elementAti = arr[i];
        int elementAtj = arr[j];

        arr[i] = elementAtj;
        arr[j] = elementAti;
    }

    public static void reverseArray2(int[] arr){
        int n = arr.length;

        int i = 0;
        int j = n-1;

        while(i < j){
            swapArrayElements(arr,i,j);

            i++;
            j--;
        }
    }

    public static void reverseArray3(int[] arr, int si, int ei){
        while(si < ei){
            swapArrayElements(arr, si, ei);

            si++;
            ei--;
        }
    }

    public static void rotateArray(int[] arr, int k){
        int n = arr.length;

        k = k % n;

        if(k < 0){
            k = k + n;
        }

        reverseArray3(arr,0,n-1);
        reverseArray3(arr,0,k-1);
        reverseArray3(arr,k,n-1);
    }

    public static int[] inverseArray(int[] arr){
        int n = arr.length;

        int[] inv = new int[n];

        for(int idx = 0; idx<n; idx++){
            int pos = arr[idx];
            int ele = idx;

            inv[pos] = ele;
        }

        return inv;
    }

    public static void printSubarrays(int[] arr){
        int n = arr.length;

        for(int si=0; si<n; si++){
            for(int ei = si; ei<n; ei++){
                // print from si to ei
                for(int idx=si; idx<=ei; idx++){
                    System.out.print(arr[idx] + " ");
                }
                System.out.println();
            }
        }
    }






















    public static void main(String[] args){
        int[] arr1 = takeArrayInput();
        // int[] arr2 = takeArrayInput();

        // System.out.println("Enter target to search in your array");
        // int tar = scn.nextInt();

        // int count = findElementsGreaterThan40(arr);
        // System.out.println("Count of elements greater than 40: " + count);

        printSubarrays(arr1);
        // printArray(ans);
        // System.out.println("Answer to your query is " + ans);
    }
}