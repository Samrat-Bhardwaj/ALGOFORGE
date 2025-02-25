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






















    public static void main(String[] args){
        int[] arr1 = takeArrayInput();
        int[] arr2 = takeArrayInput();

        // System.out.println("Enter target to search in your array");
        // int tar = scn.nextInt();

        // int count = findElementsGreaterThan40(arr);
        // System.out.println("Count of elements greater than 40: " + count);

        int[] ans = sumOfArrays(arr1, arr2);
        printArray(ans);
        // System.out.println("Answer to your query is " + ans);
    }
}