class Questions {

    public static void targetSumSubsets(int[] arr, int idx, int tar, String sub){
        if(idx == arr.length){
            if(tar == 0){
                System.out.println(sub);
            }
            return;
        }

        targetSumSubsets(arr, idx+1, tar - arr[idx], sub + arr[idx] +",");
        targetSumSubsets(arr, idx+1, tar, sub);
    }

    public static void printVisitedMatrix(boolean[][] vis){
        for(int i=0; i<vis.length; i++){
            for(int j=0; j<vis[0].length; j++){
                if(vis[i][j] == true){
                    System.out.print("( " + i + "," + j + " ) ");
                }
            }
        }

        System.out.println("");
    }

    public static boolean isPossibleToPlaceHere(int row, int col, boolean[][] vis){
        int n = vis.length;

        int[][] dirs = {{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1}};
        // {-1,-1},{-1,0},{-1,1} only these directions are required

        for(int rad=1; rad<n; rad++){
            for(int[] dir: dirs){
                int nRow = row + rad*dir[0];
                int nCol = col + rad*dir[1];

                if(nRow >=0 && nCol >=0 && nRow < n && nCol < n && vis[nRow][nCol] == true){
                    return false;
                }
            }
        }

        return true;
    }


    public static void NQueens(int row, int n, boolean[][] vis){
        if(row == n){
            printVisitedMatrix(vis);
            return;
        }

        for(int col=0; col<n; col++){
            if(isPossibleToPlaceHere(row,col,vis) == true){
                vis[row][col] = true;

                NQueens(row + 1, n, vis);

                vis[row][col] = false;
            }
        }
    }


    // knight Tour
    public static void printKnightTour(int sr, int sc){
        int[][] board = new int[8][8];

        knightTour(sr,sc,0,board);
    }

    public static void knightTour(int row, int col,int moveNumber, int[][] board){
        if(row < 0 || col < 0 || row >= board.length || col >= board[0].length || board[row][col] != 0){
            return;
        }

        if(moveNumber == board.length * board.length){ // moveNumber == 64
            printChessBoard(board);
            return;
        }

        board[row][col] = moveNumber;
        // int[][] dirs = {{-2,-1},{-2,1},{-1,2},{1,2},{2,1},{2,-1},{1,-2},{-1,-2}};
        // for(int[] dir: dirs){
        //     int nRow = row + dir[0];
        //     int nCol = col + dir[1];

        //     knightTour(nRow, nCol, moveNumber+1, board);
        // }

        knightTour(row-2, col-1, moveNumber+1, board);
        knightTour(row-2, col+1, moveNumber+1, board);
        knightTour(row-1, col+2, moveNumber+1, board);
        knightTour(row+1, col+2, moveNumber+1, board);
        knightTour(row+2, col+1, moveNumber+1, board);
        knightTour(row+2, col-1, moveNumber+1, board);
        knightTour(row+1, col-2, moveNumber+1, board);
        knightTour(row-1, col-2, moveNumber+1, board);

        board[row][col] = 0;
    }

    public static void printChessBoard(int[][] board){
        System.out.println("Printing a solution ============== ");

        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }

    // sudoku solver
    public void solveSudoku(char[][] board) {
        ArrayList<int[]> emptyPlaces = new ArrayList<>();

        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(board[i][j] == '.'){
                    emptyPlaces.add(new int[]{i,j});
                }
            }
        }

        fillSudoku(0,emptyPlaces,board);
    }

    public boolean fillSudoku(int idx, ArrayList<int[]> emptyPlaces,char[][] board){
        if(idx == emptyPlaces.size()){
            return true;
        }

        int row = emptyPlaces.get(idx)[0];
        int col = emptyPlaces.get(idx)[1];

        for(char num = '1'; num<='9'; num++){
            if(canPlace(row,col,board,num) == true){
                board[row][col] = num;

                boolean isSudokuFilled = fillSudoku(idx+1, emptyPlaces, board);
                if(isSudokuFilled == true){
                    return true;
                }

                board[row][col] = '.';
            }
        }

        return false;
    }

    public boolean canPlace(int row, int col,char[][] board, char num){
        // row check
        for(int i=row,j=0; j<9; j++){
            if(board[i][j] == num){
                return false;
            }
        }

        // col check
        for(int i=0, j=col; i<9; i++){
            if(board[i][j] == num){
                return false;
            }
        }

        // 3x3 cell check
        int cellStartingRow = (row/3)*3;
        int cellStartingCol = (col/3)*3;

        for(i=cellStartingRow; i<cellStartingRow+3; i++){
            for(j=cellStartingCol; j<cellStartingCol+3; j++){
                if(board[i][j] == num){
                    return false;
                }
            }
        }

        return true;
    }

























    public static void main(String[] args){
        // int[] arr = {2,5,3,4,6,8,-4};

        // targetSumSubsets(arr,0,8,"");
        // int n = 4;

        // boolean[][] vis = new boolean[n][n];

        // NQueens(0,n,vis);
        printKnightTour(0,0);
        board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]
    }
}