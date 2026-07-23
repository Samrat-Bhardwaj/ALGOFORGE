import java.util.*;
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
        int n = arr.length;
        int m = arr[0].length;

        for(int col = 0; col<m; col++){
            
            if(col % 2  == 0){ // even columns
                for(int row=0; row<n; row++){
                    System.out.print(arr[row][col] + ",");
                }
            } else { // odd columns
                for(int row = n-1; row>=0; row--){
                    System.out.print(arr[row][col] + ",");
                }
            }

            System.out.println();
        }
    }

    public static void spiralTraversal(int[][] arr){
        int n = arr.length;
        int m = arr[0].length;

        int sr = 0; // starting row
        int sc = 0; // starting col
        int er = n-1; // ending row
        int ec = m-1; // ending col

        while(sr <= er && sc <= ec){
            // left wall
            for(int col=sc, row = sr; row <=er; row++){
                System.out.print(arr[row][col] + ",");
            }
            sc++; // removing first column from start

            // bottom wall
            for(int row=er, col=sc; col <= ec; col++){
                System.out.print(arr[row][col] + ",");
            }
            er--;

            // right wall
            for(int col=ec, row=er; row >= sr; row--){
                System.out.print(arr[row][col] + ",");
            }
            ec--;

            // top wall
            for(int row=sr, col=ec; col>=sc; col--){
                System.out.print(arr[row][col] + ",");
            }
            sr++;
        }
    }


























    public static void main(String[] args){
        int[][] arr = {{1,2,3,4,5,6},{17,19,13,11,9,7},{22,15,12,10,8,20},{18,21,23,14,24,16}};

        waveTraversal(arr);
    }
}