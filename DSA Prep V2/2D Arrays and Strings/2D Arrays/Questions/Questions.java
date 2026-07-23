class Questions {

    // https://www.geeksforgeeks.org/problems/exit-point-in-a-matrix0905/1
    public List<Integer> findExitPoint(int[][] mat){
        int n = mat.length;
        int m = mat[0].length;

        int dir = 0;
        int row = 0;
        int col = 0;

        while(row < n && col < m && row >=0 && col >= 0){
            if(mat[row][col] == 1){
                mat[row][col] = 0;
                dir = (dir + 1) % 4;
            }

            if(dir == 0){
                col++;
            } else if(dir == 1){
                row++;
            } else if(dir == 2){
                col--;
            } else {
                row--;
            }
        }

        if(row < 0) row++;
        if(col < 0) col++;
        if(row == n) row--;
        if(col == m) col--;

        List<Integer> al = new ArrayList<>();
        al.add(row);
        al.add(col);
        
        return al;
    }

    public static void waveTraversal(int[][] arr){
        
    }


























    public static void main(String[] args){
        int[][] arr = {{1,2,3,4,5,6},{17,19,13,11,9,7},{22,15,12,10,8,20},{18,21,23,14,23,16}}

        waveTraversal(arr);
    }
}