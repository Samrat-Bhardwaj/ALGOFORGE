import java.util.Scanner;
class ArrayInput {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        int size = 5;

        int[] arr = new int[size];

        for(int i=0; i<size; i++){
            int input = scn.nextInt();

            arr[i] = input;
        }

        for(int i=0; i<arr.length; i++){
            int ele = arr[i];

            System.out.println(ele);
        }
    }
}