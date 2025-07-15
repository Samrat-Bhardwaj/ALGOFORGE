class Questions {
    // (As Far from Land as Possible)Leetcode 1162 ====================================
    public int maxDistance(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        LinkedList<Integer> que = new LinkedList<>();
        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};

        // insert all land elements
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(grid[i][j] == 1){
                    que.addLast(i*m + j);
                }
            }
        }

        // no land or no water
        if(que.size() == 0 || que.size() == n*m) return -1;

        int level = 0;
        while(que.size() > 0){
            int size = que.size();

            while(size-- > 0){
                int idx = que.removeFirst();

                int x = idx/m;
                int y = idx%m;

                for(int[] dir: dirs){
                    int r = x + dir[0];
                    int c = y + dir[1];

                    if(r>=0 && c>=0 && r<n && c<m && grid[r][c] == 0){
                        grid[r][c] = 1;
                        que.addLast(r*m + c);
                    }
                }
            }
            level++;
        }

        return level - 1;
    }

    // (0-1 Matrix)leetcode 542 ========================================================== 
    public int[][] updateMatrix(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        LinkedList<Integer> que = new LinkedList<>();
        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};

        int[][] res = new int[n][m];

        // add all zeros
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(mat[i][j] == 0){
                    que.addLast(i*m + j);
                }
            }
        }

        int level = 0;
        while(que.size() > 0){
            int size = que.size();

            while(size-- > 0){
                int idx = que.removeFirst();

                int x = idx/m;
                int y = idx%m;

                for(int[] dir: dirs){
                    int r = x + dir[0];
                    int c = y + dir[1];

                    if(r>=0 && c>=0 && r<n && c<m && mat[r][c] == 1){
                        mat[r][c] = 0;
                        res[r][c] = level + 1;
                        que.addLast(r*m + c);
                    }
                }
            }
            level++;
        }

        return res;
    }
}