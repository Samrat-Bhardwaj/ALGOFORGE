import java.util.Arrays;
class Questions {
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

    public static int[][] multiplyMatrices(int[][] A, int[][] B){
        int r1 = A.length;
        int c1 = A[0].length;

        int r2 = B.length;
        int c2 = B[0].length;

        if(c1 != r2){
            System.out.println("Multiplication of these two matrices is not possible");
            return new int[][]{};
        }

        int[][] res = new int[r1][c2];

        for(int i=0; i<res.length; i++){
            for(int j=0; j<res[0].length; j++){
                
                int currentAns = 0;
                for(int k=0; k<c1; k++){
                    currentAns += A[i][k] * B[k][j];
                }

                res[i][j] = currentAns;
            }
        }

        return res;
    }

    public static void printExitPoint(int[][] mat){
        int n = mat.length;
        int m = mat[0].length;

        int dir = 0;
        int row = 0;
        int col = 0;

        while(row >=0 && row < n && col >=0 && col < m){
            if(mat[row][col] == 1){
                dir = (dir + 1) % 4;
            }

            if(dir == 0){ // right
                col++;
            } else if(dir == 1){ // down
                row++;
            } else if(dir == 2){ // left
                col--;
            } else { // up
                row--;
            }
        }

        if(dir == 0) col--;
        if(dir == 1) row--;
        if(dir == 2) col++;
        if(dir == 3) row++; // row=0

        System.out.println("Exit Point is: (" + row + "," + col + ")");
    }

    // Wave traversal ====================================================================
    public static void waveTraversal(int[][] arr){
        int n = arr.length;
        int m = arr[0].length;

        for(int j=0; j<m; j++){
            
            if(j%2 == 0){
                for(int i=0; i<n; i++){
                    System.out.print(arr[i][j] + ", ");
                }
            } else {
                for(int i=n-1; i>=0; i--){
                    System.out.print(arr[i][j] + ", ");
                }
            }
            
            System.out.println();
        }
    }

    // Spiral traversal ===============================================================
    public static void spiralTraversal(int[][] arr){
        int n = arr.length;
        int m = arr[0].length;

        int sr = 0;    // start_row
        int sc = 0;    // start_column
        int er = n-1;  // end_row
        int ec = m-1;  // end_column

        while(sr <= er && sc <= ec){
            // left wall
            for(int col=sc, row=sr; row<=er; row++){
                System.out.print(arr[row][col] + ",");
            }
            sc++; 

            // bottom wall
            for(int row=er, col=sc; col<=ec ; col++){
                System.out.print(arr[row][col] + ",");
            }
            er--;

            // right wall
            for(int col=ec, row=er; row>=sr; row--){
                System.out.print(arr[row][col] + ",");
            }
            ec--;

            // top wall
            for(int row=sr, col=ec; col>=sc; col--){
                System.out.print(arr[row][col] + ",");
            }
            sr++;

            System.out.println();
        }
    }

    // Transpose of a matrix ====================================
    public static void transpose(int[][] arr){
        int n = arr.length;

        for(int i=0; i<n; i++){
            for(int j=i; j<n; j++){

                int temp = arr[i][j];
                arr[i][j] = arr[j][i];
                arr[j][i] = temp;
            }
        }
    }

    // rotate by 90 degrees =====================================================
    public void reverseEveryRow(int[][] matrix){
        int n = matrix.length;

        for(int row=0; row<n; row++){
            int[] arr = matrix[row];

            int start = 0, end = n-1;
            while(start < end){
                int temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;

                start++;
                end--;
            }
        }
    }

    public void rotate(int[][] matrix) {
        transpose(matrix);
        reverseEveryRow(matrix);
    }



























    public static void main(String[] args){
        // int[][] A = new int[4][3];
        // int[][] B = new int[3][2];
        // fillValues(A); fillValues(B);
        
        // int[][] res = multiplyMatrices(A, B);

        // print2DArray(res);
        
        // int[][] binaryMatrix = {{0,0,0,1,0,0},{0,1,0,0,0,0},{1,0,1,0,1,0},{0,1,0,1,1,0},{0,0,0,0,0,1}};
        // printExitPoint(binaryMatrix);

        int[][] arr = {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}};
        transpose(arr);

        print2DArray(arr);
    }
}