import java.util.Scanner;
class Main {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        System.out.println("Enter the number of elements: ");
        int size = scn.nextInt();

        int[] arr = new int[size];

        System.out.println("Enter elements: ");
        for(int idx=0; idx<size; idx++){
            int input = scn.nextInt();
            arr[idx] = input;
        }

        System.out.println("The elements you provided are: ");

        for(int idx=0; idx<arr.length; idx++){
            System.out.println("Element at index " + idx + " is " + arr[idx]);
        }

        System.out.println(arr);
    }
}