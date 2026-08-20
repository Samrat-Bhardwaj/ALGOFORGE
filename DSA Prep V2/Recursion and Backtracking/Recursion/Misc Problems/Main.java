class Main {
    public static int findMaxSum(int[][] grid, int row, int col, int n, int m){
        if(row >= n || col >= m){ // invalid state
            return Integer.MIN_VALUE;
        }

        if(row == n-1 && col == m-1){
            return grid[row][col];
        }
        
        int rightMaxSum = findMaxSum(grid, row, col+1, n, m);
        int downMaxSum = findMaxSum(grid, row+1, col, n, m);

        int maxSum = Math.max(rightMaxSum, downMaxSum) + grid[row][col];

        return maxSum;
    }

    public static int maxSum(int[][] grid){
        return findMaxSum(grid, 0, 0, grid.length, grid[0].length);
    }

    public static void main(String[] args){
        int[][] grid = {{3, 6, 1}, {2, 3, 4}, {5, 5, 1}};

        System.out.println(maxSum(grid));
    }
}