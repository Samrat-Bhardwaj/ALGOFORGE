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