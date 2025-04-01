class Main {
    public static void floodFill(int row, int col, int n, int m, int[][] matrix, boolean[][] visited, String asf){
        if(row == n-1 && col == m-1){
            System.out.println(asf);
            return;
        }

        if(row<0 || col<0 || row>=n || col >=m || matrix[row][col] == 1 || visited[row][col] == true){
            return;
        }

        visited[row][col] = true;
        
        floodFill(row-1,col,n,m,matrix,visited,asf+"u");
        floodFill(row,col-1,n,m,matrix,visited,asf+"l");
        floodFill(row+1,col,n,m,matrix,visited,asf+"d");
        floodFill(row,col+1,n,m,matrix,visited,asf+"r");

        visited[row][col] = false; // backtracking 
    }
    
    public static void main(String[] args){
        int[][] matrix = {{0, 0, 0, 0, 0, 0}, {0, 1, 0, 1, 1, 0}, 
                        {0, 1, 0, 1, 1, 0}, {0, 0, 0, 0, 0, 0,}, 
                        {0, 1, 0, 1, 1, 0}, {0, 0, 0, 1, 1, 0}};;

        int n = matrix.length;
        int m = matrix[0].length;

        boolean[][] visited = new boolean[n][m];

        floodFill(0,0,n,m,matrix,visited,"");
    }
}