import java.util.*;
class Main {
    public static void printAllSubsets(int[] nums, int idx, int targetSum, String asf){
        if(idx == nums.length){
            if(targetSum == 0){
                System.out.println(asf);
            }
            return;
        }

        // if(targetSum < 0){ // this is valid if we only have non-negative numbers
        //     return;
        // }

        printAllSubsets(nums, idx+1, targetSum - nums[idx], asf + nums[idx] + ","); // yes call
        printAllSubsets(nums, idx+1, targetSum, asf); // no call
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

    // Leetcode 37 =================
    public boolean canPlaceNumber(int row, int col, char[][] board, int num){
        // check row =====================
        for(int j=0; j<9; j++){
            if(board[row][j] - '0' == num){
                return false;
            }
        }

        // check col =======================
        for(int i=0; i<9; i++){
            if(board[i][col] - '0' == num){
                return false;
            }
        }

        // check 3x3 box
        int startRow = (row/3)*3;
        int startCol = (col/3)*3;

        for(int i=startRow; i < startRow+3; i++){
            for(int j=startCol; j < startCol+3; j++){
                if(board[i][j] - '0' == num){
                    return false;
                }
            }
        }

        return true;
    }

    public boolean fillEmptyCells(ArrayList<int[]> emptyCells, int idx, char[][] board){
        if(idx == emptyCells.size()){
            return true;
        }

        int row = emptyCells.get(idx)[0];
        int col = emptyCells.get(idx)[1];

        for(int num=1; num<=9; num++){
            if(canPlaceNumber(row,col,board,num)){
                board[row][col] = (char)(num + '0');;

                if(fillEmptyCells(emptyCells, idx+1, board)){
                    return true; // dont change it to next num, this is the solution
                }

                board[row][col] = '.'; // go on to next num
            }
        }

        return false;    
    }

    public void solveSudoku(char[][] board) {
        ArrayList<int[]> emptyCells = new ArrayList<>();

        for(int row=0; row<9; row++){
            for(int col=0; col<9; col++){
                if(board[row][col] == '.'){
                    emptyCells.add(new int[]{row,col});
                }
            }
        }

        fillEmptyCells(emptyCells,0,board);
    }

    // N-Queens Optimized
    public static void nQueensOptimized(int row,boolean[] colVis, boolean[]diagVis, boolean[] aDiagVis,String asf, int n){
        if(row == n){
            System.out.println(asf);
            return;
        }

        for(int col = 0; col < n; col++){
            if(colVis[col] == false && diagVis[col-row+n-1]==false && aDiagVis[row+col] == false){
                colVis[col] = true;
                diagVis[col-row+n-1] = true;
                aDiagVis[row+col] = true;

                nQueensOptimized(row+1,colVis,diagVis,aDiagVis,asf+"[" + row +"," + col + "],",n);

                colVis[col] = false;
                diagVis[col-row+n-1] = false;
                aDiagVis[row+col] = false;
            }
        }
    }

    // Leetcode 52 ========================
    public static void printAllSolutions(int n) {
        boolean[] colVis = new boolean[n];
        boolean[] diagVis = new boolean[2*n-1];
        boolean[] aDiagVis = new boolean[2*n-1];

        nQueensOptimized(0,colVis,diagVis,aDiagVis,"",n);
    }
























    public static void main(String[] args){
        // int[] nums = {2,2,3,11,1,4,0}; // this can have negative numbers
        // int targetSum = 5;

        // printAllSubsets(nums,0,targetSum,"");
        printAllSolutions(5);
    }
}