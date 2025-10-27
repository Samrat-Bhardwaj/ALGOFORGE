class Questions {
    public static void printTargetSumSubsets(int[] arr, int idx, int tar, String sub){
        if(idx == arr.length){
            if(tar == 0){
                System.out.println(sub);
            }
            return;
        }

        printTargetSumSubsets(arr, idx+1, tar-arr[idx], sub + arr[idx] + ","); // yes call
        printTargetSumSubsets(arr, idx+1, tar, sub); // no call
    }

    public static void printSolution(boolean[][] board){
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board.length; j++){
                if(board[i][j] == true){
                    System.out.print("Q");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }

        System.out.println("==================");
    }

    public static boolean isSafeToPlaceHere(boolean[][] board, int row, int col,int n){
        // int[][] dirs = {{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1}};

        // We only need to look on top-left, top and top-right
        int[][] dirs = {{-1,-1},{-1,0},{-1,1}};
 
        for(int dis=1; dis<=n; dis++){ // dis <= row
            for(int[] dir: dirs){
                int nRow = row + dis * dir[0];
                int nCol = col + dis * dir[1];

                if(nRow >=0 && nCol >=0 && nRow < n && nCol < n && board[nRow][nCol] == true){
                    return false;
                }
            }
        }

        return true;
    }

    public static void nQueens(boolean[][] board, int n, int row){
        if(row == n){
            printSolution(board);
            return;
        }

        for(int col=0; col<n; col++){
            if(isSafeToPlaceHere(board,row,col,n)){
                board[row][col] = true;

                nQueens(board, n, row+1);

                board[row][col] = false;
            }
        }
    }
    public static void printBoard(int[][] board){
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                System.out.print(board[i][j] + ",\t" );
            }
            System.out.println();
        }
    }

    public static void knightTour(int row, int col, int[][] board, int currentStep){
        if(row < 0 || col < 0 || row >= board.length || col >= board.length || board[row][col] != 0){
            return;
        }

        if(currentStep == 65){
            printBoard(board);
            return;
        }

        board[row][col] = currentStep;

        knightTour(row-2,col-1,board,currentStep+1);
        knightTour(row-2,col+1,board,currentStep+1);
        knightTour(row-1,col+2,board,currentStep+1);
        knightTour(row+1,col+2,board,currentStep+1);
        knightTour(row+2,col+1,board,currentStep+1);
        knightTour(row+2,col-1,board,currentStep+1);
        knightTour(row+1,col-2,board,currentStep+1);
        knightTour(row-1,col-2,board,currentStep+1);

        board[row][col] = 0;
    }

    // Homwork -> Leetcode 51, 52 

    // Sudoku solver (Leetcode 37) ========================================
    public boolean canPlaceNum(char[][] board, int row, int col, int num){
        // checking all the rows in "col" column
        for(int i=0; i<9; i++){
            if(board[i][col] - '0' == num){
                return false;
            }
        }

        // check all the columns in "row" row
        for(int j=0; j<board.length; j++){
            if(board[row][j] - '0' == num){
                return false;
            }
        }

        // checking 3x3 cells
        int start_row = (row/3)*3;
        int start_col = (col/3)*3;

        for(int i=start_row; i < start_row + 3; i++){
            for(int j=start_col; j<start_col + 3; j++){
                if(board[i][j] - '0' == num) return false;
            }
        }

        return true;
    }

    public boolean sudokuSolver(ArrayList<int[]> emptyCells, char[][] board, int idx){
        if(idx == emptyCells.size()){
            return true;
        }

        int row = emptyCells.get(idx)[0];
        int col = emptyCells.get(idx)[1];

        for(int num=1; num <= 9; num++){
            if(canPlaceNum(board,row,col,num)){
                board[row][col] = (char)(num + '0');

                if(sudokuSolver(emptyCells, board, idx+1)) return true;

                board[row][col] = '.';
            }
        }

        return false;
    }

    public void solveSudoku(char[][] board) {
        ArrayList<int[]> emptyCells = new ArrayList<>();

        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(board[i][j] == '.'){
                    emptyCells.add(new int[]{i,j});
                }
            }
        }

        sudokuSolver(emptyCells, board, 0);
    }

    // leetcode 52 ===================================
    public static boolean isSafeToPlaceHere(boolean[][] board, int row, int col,int n){
        // int[][] dirs = {{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1}};

        // We only need to look on top-left, top and top-right
        int[][] dirs = {{-1,-1},{-1,0},{-1,1}};
 
        for(int dis=1; dis<=n; dis++){ // dis <= row
            for(int[] dir: dirs){
                int nRow = row + dis * dir[0];
                int nCol = col + dis * dir[1];

                if(nRow >=0 && nCol >=0 && nRow < n && nCol < n && board[nRow][nCol] == true){
                    return false;
                }
            }
        }

        return true;
    }

    public static int nQueens(boolean[][] board, int n, int row){
        if(row == n){
            return 1;
        }

        int ans = 0;
        for(int col=0; col<n; col++){
            // if(isSafeToPlaceHere(board,row,col,n)){ // takes time of O(N)
                // board[row][col] = true;
                colVis[col] == false;
                colVis[col] = true;

                ans += nQueens(board, n, row+1);

                // board[row][col] = false;
                colVis[col] = false;
            }
        }

        return ans;
    }

    // nQueen Optimised using col, diag and Anti-diagonal visited arrays =============================
    public int nQueensOptimised(int row, boolean[] colVis, boolean[] diagVis, boolean[] aDiagVis, int n){
        if(row == n){
            return 1;
        }   

        int ans = 0;

        for(int col = 0; col < n; col++){
            if(colVis[col] == false && diagVis[col-row + n-1] == false && aDiagVis[row+col] == false){

                colVis[col] = true;
                diagVis[col - row + n - 1] = true;
                aDiagVis[row+col] = true;

                ans += nQueensOptimised(row+1, colVis, diagVis, aDiagVis, n);

                colVis[col] = false;
                diagVis[col - row + n - 1] = false;
                aDiagVis[row+col] = false;
            }
        }

        return ans;
    }

    public int totalNQueens(int n) {
        // return nQueens(new boolean[n][n],n,0);

        boolean[] colVis = new boolean[n];
        boolean[] diagVis = new boolean[2*n-1];
        boolean[] aDiagVis = new boolean[2*n-1];

        return nQueensOptimised(0,colVis,diagVis,aDiagVis,n);
    }


    // N - Queens Most Optimized O(1 + rec) space =================================================

    // how to set Kth Bit true
    public int setKthBitTrue(int num, int k){
        int mask = (1 << k);
        
        int newNum = num | mask;
        return newNum;
    } 

    // how to set Kth Bit false
    public int setKthBitFalse(int num, int k){
        int mask = ~(1 << k);

        int newNum = num & mask;
        return newNum;
    }

    // how to check Kth Bit is true or false
    public boolean checkKthBit(int num, int k){
        int mask = (1 << k);

        return num & mask > 0;
    }

    public int NQueensMostOptimised(int row, int n, int colNum, int diagNum, int aDiagNum){
        if(row == n){
            return 1;
        }

        int ans=0;
        for(int col = 0; col < n; col++){
            if(checkKthBit(colNum,col) == false && (diagNum & (1 << (col-row + n-1)) > 0) && !checkKthBit(aDiagNum, row + col)){

                colNum = colNum | (1 << col);
                diagNum = diagNum | (1 << (col-row + n-1));
                aDiagNum = setKthBitTrue(aDiagNum, row+col); // aDiagNum | (1 << (row+col))

                NQueensMostOptimised(row+1,n,colNum,diagNum,aDiagNum);

                colNum = colNum & ~(1 << col);
                diagNum = diagNum & ~(1 << (col-row + n-1));
                aDiagNum = aDiagNum & ~(1 << (row+col))
            }
        }
        return ans;
    }

    public int totalNQueens(int n) {
        int colNum = 0;
        int diagNum = 0;
        int aDiagNum = 0;

        return NQueensMostOptimised(0,n,colNum,diagNum,aDiagNum);
    }
    
    // HomeWORK  ================================================================
    // leetcode 22 (IMPORTANT)

    // leetcode 79

    // leetcode 526 

    // leetcode 1947

    // leetcode 1986

    // leetcode 282

    // leetcode 301

    // leetcode 980
    















    public static void main(String[] args){
        // int[] arr = {2,5,3,1,4,6,0,-2,8};
        // printTargetSumSubsets(arr,0,8,"");

        // int n = 4;
        // boolean[][] board = new boolean[n][n];
        // nQueens(board,n,0);

        int[][] board = new int[8][8];
        knightTour(0,0,board,1);
    }
}