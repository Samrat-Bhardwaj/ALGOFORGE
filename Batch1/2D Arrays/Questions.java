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

    public void reverseEveryRow(int[][] matrix){
        int n = matrix.length;

        for(int row=0; row<n; row++){

            int si = 0;
            int ei = n-1; // arr[row].length - 1;

            while(si < ei){
                int temp = arr[row][si];

                arr[row][si] = arr[row][ei];
                arr[row][ei] = temp;

                si++;
                ei--;
            }
        }
    }

    // leetcode 48 ============================
    public void rotate(int[][] matrix) {
        transpose(matrix);
        reverseEveryRow(matrix);
    }

    // leetcode 74 ===================================
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;
        
        int total_elements = n*m;
        
        int si = 0;
        int ei = total_elements - 1;

        while(si <= ei){
            int mid = (si+ei)/2;

            int i = mid/m;
            int j = mid%m;

            if(matrix[i][j] < target){
                si = mid + 1;
            } else if(matrix[i][j] > target){
                ei = mid - 1;
            } else {
                // System.out.println("Element found at " + i + "," + j);
                return true;
            }
        }

        return false;
    }

    // leetcode 240 =======================================
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;

        int row = 0;
        int col = m-1;

        while(row<n && col>=0){
            if(matrix[row][col] < target){
                row++;
            } else if(matrix[row][col] > target){
                col--;
            } else {
                return true;
            }
        }

        return false;
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