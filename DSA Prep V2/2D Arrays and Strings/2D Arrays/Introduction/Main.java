class Main {
    public static void fillValues(int[][] arr){
        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr[0].length; j++){
                arr[i][j] = (i+j);
            }
        }
    }


    public static void main(String[] args){
        int[][] arr = new int[5][3];
        fillRandomValues(arr);

        // for(int i=0; i<arr.length; i++){
        //     int[] anotherArray = arr[i];

        //     for(int j=0; j<anotherArray.length; j++){
        //         int ele = anotherArray[j];
        //         System.out.print("(i: " + i + ", j: " + j + ", ele value: " + ele + "),");
        //     }

        //     System.out.println();
        // }

        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr[0].length; j++){
                System.out.print(arr[i][j] + ",");
            }
            System.out.println();
        }
    }
}