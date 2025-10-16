class Main {
    public static void floodFill(int row, int col, int[][] mat, boolean[][] visited, int n, int m, String psf){
        if(row<0 || col<0 || row>=n || col>=m || mat[row][col] == 1 || visited[row][col] == true){
            return;
        }

        if(row == n-1 && col == m-1){
            System.out.println(psf);
            return;
        }

        visited[row][col] = true;

        floodFill(row-1,col,mat,visited,n,m,psf+"t");
        floodFill(row,col-1,mat,visited,n,m,psf+"l");
        floodFill(row+1,col,mat,visited,n,m,psf+"d");
        floodFill(row,col+1,mat,visited,n,m,psf+"r");

        visited[row][col] = false; // backtracking
    }

    public static void main(String[] args){
        int[][] mat = {{0, 0, 0, 0, 0, 0}, {0, 1, 0, 1, 1, 0}, 
                        {0, 0, 0, 0, 0, 0,}, {0, 1, 0, 1, 1, 0},  
                        {0, 1, 0, 1, 1, 0}, {0, 0, 0, 1, 1, 0}};
        int n = mat.length;
        int m = mat[0].length;
        boolean[][] visited = new boolean[n][m];

        floodFill(0,0,mat,visited,n,m,"");
    }
}