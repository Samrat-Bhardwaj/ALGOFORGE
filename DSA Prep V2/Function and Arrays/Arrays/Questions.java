import java.util.Scanner;
class Questions {
    // span of array
    public static int findSpan(int[] arr){

    }

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