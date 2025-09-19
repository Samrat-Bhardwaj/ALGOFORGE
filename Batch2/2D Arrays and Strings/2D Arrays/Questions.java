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

    public static void main(String[] args){
        // int[][] A = new int[4][3];
        // int[][] B = new int[3][2];
        // fillValues(A); fillValues(B);
        
        // int[][] res = multiplyMatrices(A, B);

        // print2DArray(res);
        
        int[][] binaryMatrix = {{0,0,0,1,0,0},{0,1,0,0,0,0},{1,0,1,0,1,0},{0,1,0,1,1,0},{0,0,0,0,0,1}};
        printExitPoint(binaryMatrix);
    }
}