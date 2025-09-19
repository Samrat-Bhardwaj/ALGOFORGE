import java.util.Arrays;
class Main {
    public static void print2DArray(int[][] arr){
        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr[0].length; j++){
                System.out.print(arr[i][j] + ", ");
            }
            System.out.println();
        }
    }
    
    public static void fillValues(int[][] arr){
        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr[0].length; j++){
                arr[i][j] = (i+j);
            }
        }
    }

    public static void main(String[] args){
        int[][] arr = new int[6][3];
        fillValues(arr);
        print2DArray(arr);

        // System.out.println(arr);

        // for(int i=0; i<arr.length; i++){
        //     int[] oneDArray = arr[i];

        //     for(int j=0; j<oneDArray.length; j++){
        //         System.out.print(i + "," + j + " ->");
        //         System.out.print(oneDArray[j] + ", ");
        //     }

        //     System.out.println();
        // }

        // for(int i=0; i<arr.length; i++){
        //     for(int j=0; j<arr[0].length; j++){
        //         // System.out.print(arr[i][j] + " ");
        //     }

        //     System.out.println();
        // }

        // DO NOT USE THIS FUNCTION OR I WILL HAUNT YOU IN YOUR DREAMS
        // System.out.println(Arrays.deepToString(arr));
    }
}