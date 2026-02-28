class Questions {
    // rotten oranges (Leetcode 994) ====================================
    public int orangesRotting(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int[][] dirs = {{0,1},{0,-1},{-1,0},{1,0}};
        LinkedList<Integer> que = new LinkedList<>();

        int freshOranges = 0;

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(grid[i][j] == 1){
                    freshOranges++;
                } else if(grid[i][j] == 2){ // src
                    que.addLast(i*m + j); // converted to 1d
                }
            }
        }

        if(freshOranges == 0) return 0;

        int time = 0;
        while(que.size() > 0){
            int size = que.size();

            while(size-- > 0){
                int cell = que.removeFirst();
                
                int i = cell/m;
                int j = cell%m;

                for(int[] dir: dirs){
                    int x = i + dir[0];
                    int y = j + dir[1];

                    if(x>=0 && y>=0 && x<n && y<m && grid[x][y] == 1){
                        que.addLast(x*m + y); 
                        grid[x][y] = 2; 
                        freshOranges--;

                        // if(freshOranges == 0){
                        //     return time + 1; // why time+1?
                        // }
                    }
                }
            }
            time++;
        }

        return freshOranges == 0 ? time - 1 : -1;  // why time - 1?
    }
}