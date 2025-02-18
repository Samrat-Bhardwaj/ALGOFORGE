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
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        System.out.println("What should be the size of array?");

        int size = scn.nextInt();

        int[] arr = new int[size];

        System.out.println("Enter values for your array");

        for(int idx=0; idx < size; idx++){
            int input = scn.nextInt();
            arr[idx] = input;
        }

        // System.out.println("Printing the array");

        // for(int idx=0; idx<size; idx++){
        //     System.out.println("Value at index " + idx + " is " + arr[idx]);
        // }

        int count = findElementsGreaterThan40(arr);
        System.out.println("Count of elements greater than 40: " + count);
    }
}