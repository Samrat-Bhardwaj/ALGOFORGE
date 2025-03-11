class Questions {
    public static void print2DArray(int[][] arr){
        int n = arr.length;
        int m = arr[0].length;

        for(int i=0; i<n; i++){

            for(int j=0; j<m; j++){
                System.out.print(arr[i][j] + "\t");
            }

            System.out.println();
        }
    }

    public static void printSpiral(int[][] arr){
        int n = arr.length;
        int m = arr[0].length;

        int sr = 0;
        int sc = 0;
        int er = n-1;
        int ec = m-1;
        int row=-1, col=-1;

        while(sr<=er && sc<=ec){
            // print left wall -> starting columm
            col = sc;
            for(row=sr; row<=er; row++){
                System.out.print(arr[row][col]+" ,");
            }
            sc++;

            // print bottom wall -> ending row
            row = er;
            for(col=sc; col<=ec; col++){
                System.out.print(arr[row][col]+" ,");
            }
            er--;

            // print right wall -> ending col
            col = ec;
            for(row=er; row>=sr; row--){
                System.out.print(arr[row][col]+" ,");
            }
            ec--;

            // print top wall
            row = sr;
            for(col = ec; col>=sc; col--){
                System.out.print(arr[row][col]+" ,");
            }
            sr++;

            System.out.println();
        }
    }

    public static void transpose(int[][] arr){ // this is only for square matrix
        int n = arr.length;

        for(int i=0; i<n; i++){
            for(int j=0; j<=i; j++){ // for(int j=i; j<n; j++)
                int temp = arr[i][j];

                arr[i][j] = arr[j][i];
                arr[j][i] = temp;
            }
        }
    }

    public static void main(String[] args){
        // int[][] arr = {{11,22,4,45,110}, {33,9,7,5,89}, {66,77,6,55,99}, {13,1,43,88,44}};
        int[][] arr2 = {{1,2,3,4,5}, {6,7,8,9,10}, {11,12,13,14,15}, {16,17,18,19,20}, {21,22,23,24,25}};

        // printSpiral(arr);
        print2DArray(arr2);

        transpose(arr2);

        print2DArray(arr2);
    }
}