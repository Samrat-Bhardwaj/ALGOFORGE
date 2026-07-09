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