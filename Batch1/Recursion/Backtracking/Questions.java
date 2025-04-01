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






















    public static void main(String[] args){
        // int[] arr = {2,5,3,4,6,8,-4};

        // targetSumSubsets(arr,0,8,"");
        int n = 4;

        boolean[][] vis = new boolean[n][n];

        NQueens(0,n,vis);
    }
}