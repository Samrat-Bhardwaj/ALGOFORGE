class Main {
    public static void floodFill(int row, int col, int n, int m, int[][] matrix, boolean[][] visited, String psf){
        if(row < 0 ||col < 0  || row >= n || col >=m || visited[row][col] == true || matrix[row][col] == 1){
            return;
        }

        if(row == n-1 && col == m-1){
            System.out.println(psf);
            return;
        }

        visited[row][col] = true; // so that we are not visiting any cells of current recursive path again

        floodFill(row-1,col,n,m,matrix,visited,psf+"t");
        floodFill(row,col-1,n,m,matrix,visited,psf+"l");
        floodFill(row+1,col,n,m,matrix,visited,psf+"d");
        floodFill(row,col+1,n,m,matrix,visited,psf+"r");

        visited[row][col] = false; // backtracking -> will allow current cell to be part of some other recursive path 
    }

    public static void main(String[] args){
        int[][] matrix = {{0,0,0,1,0},{0,1,0,1,0},{0,0,0,0,0},{0,1,0,1,0},{0,1,0,1,0},{0,0,0,1,0}};
        
        int n = matrix.length;
        int m = matrix[0].length;

        boolean[][] visited = new boolean[n][m];
        floodFill(0,0,n,m,matrix,visited,"");
    }
}