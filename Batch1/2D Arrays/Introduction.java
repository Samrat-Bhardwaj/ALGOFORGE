class Introduction {
    public static void fillValues(int[][] arr){
        int n = arr.length;

        for(int idx=0; idx<n; idx++){
            int[] anotherArray = arr[idx];

            int m = anotherArray.length;

            for(int j=0; j<m; j++){
                int ele = anotherArray[j];
                anotherArray[j] = idx + j;
            }
        }
    }

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

    public static int[][] multiplyTwoMatrices(int[][] A, int[][] B){
        int x = A.length;
        int y = A[0].length; // int y = B.length;

        int z = B[0].length;

        int[][] ans = new int[x][z];

        for(int i=0; i<x; i++){
            for(int j=0; j<z; j++){

                int currCellAns = 0;
                for(int k=0; k<y; k++){
                    currCellAns = currCellAns + A[i][k]*B[k][j];
                }

                ans[i][j]  = currCellAns;
            }
        }

        return ans;
    }

    public static void waveTraversal(int[][] arr){
        int n = arr.length;
        int m = arr[0].length;

        for(int j=0; j<m; j++){
            if(j % 2 == 0){
                for(int i=0; i<n; i++){
                    System.out.print(arr[i][j] + "\t");
                }
            } else {
                for(int i=n-1; i>=0; i--){
                    System.out.print(arr[i][j] + "\t");
                }
            }

            System.out.println();
        }
    }

    public static void exitPoint(int[][] arr, int entryRow){
        int n = arr.length;
        int m = arr[0].length;

        int dir = 0;
        int row = entryRow;
        int col = 0;

        while(row>=0 && row<n && col>=0 && col<m){
            if(arr[row][col] == 1) { // on gfg you need to convert arr[row][col] to 0 
                dir++;
            }
            dir = dir%4;

            if(dir == 0){
                col++;
            } else if(dir==1){
                row++;
            } else if(dir==2){
                col--;
            } else {
                row--;
            }
        }

        // we exited our matrix, we need to take a step back to fix this
        if(dir == 0){
            col--;
        } else if(dir == 1){
            row--;
        } else if(dir==2){
            col++;
        } else {
            row++;
        }

        System.out.println(row);
        System.out.println(col);
    }

    public static void main(String[] args){
        // int[][] arr = new int[6][3];
        int[][] arr = {{11,22,4,45,110}, {33,9,7,5,89}, {66,77,6,55,99}, {13,1,43,88,44}};

        // fillValues(arr);
        // int[][] A = {{9,4,5},{6,8,-1},{11,5,3},{7,14,5}};
        // int[][] B = {{5,-2},{4,5},{11,9}};

        // int[][] ans = multiplyTwoMatrices(A,B);

        // print2DArray(ans);

        waveTraversal(arr);
    }
}