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

    // Rotting oranges (leetcode 994) ===========================================================
    public int orangesRotting(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        LinkedList<Integer> que = new LinkedList<>();
        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};

        int freshOranges = 0;

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(grid[i][j] == 1){
                    freshOranges++;
                } else if(grid[i][j] == 2){
                    que.addLast(i*m + j);
                }
            }
        }

        if(freshOranges == 0) return 0;

        int time = 0;
        while(que.size()>0){
            int size = que.size();
            while(size-->0){
                int idx = que.removeFirst();

                int i = idx/m;
                int j = idx%m;

                for(int[] dir: dirs){
                    int x = i + dir[0];
                    int y = j + dir[1];

                    if(x>=0 && y>=0 && x<n && y<m && grid[x][y] == 1){
                        freshOranges--; // this fresh orange is rotten now
                        if(freshOranges == 0) return time + 1; // during the next minute, freshOranges will be 0
                        grid[x][y] = 2; // this fresh orange is rotten(2) now
                        que.addLast(x*m + y);
                    }
                }
            }
            time++;
        }

        return -1; 
    }

    // leetcode 1091 (Shortest Path in Binary Matrix) =============================
    // Flood fill but be want shortest path only
    // Why not DFS -> because we care about only the shortest path and not all paths
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        LinkedList<Integer> que = new LinkedList<>();
        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0},{1,1},{-1,1},{1,-1},{-1,-1}};

        if(grid[0][0] == 0){
            que.addLast(0);
            grid[0][0] = 1;
        }

        int dis = 1;

        while(que.size() > 0){
            int size = que.size();
            
            while(size-- > 0){
                int idx = que.removeFirst();
                int i = idx/m;
                int j = idx%m;

                if(i==n-1 && j==m-1) return dis;

                for(int[] dir: dirs){
                    int x = i + dir[0];
                    int y = j + dir[1];

                    if(x>=0 && y>=0 && x<n && y<m && grid[x][y] == 0){
                        if(x == n-1 && y == m-1) return dis + 1;

                        grid[x][y] = 1;
                        que.addLast(x*m + y);
                    }
                }
            }
            dis++;
        }

        return -1;
    }

    // Walls and gates (https://www.lintcode.com/problem/663/) ===========================
    public void wallsAndGates(int[][] rooms) {
        int inf = Integer.MAX_VALUE;

        int n = rooms.length;
        int m = rooms[0].length;

        LinkedList<Integer> que = new LinkedList<>();
        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(rooms[i][j] == 0){ // gate
                    que.addLast(i*m + j);
                }
            }
        }

        int level = 0;
        while(que.size() > 0){
            int size = que.size();

            while(size-- > 0){
                int idx = que.removeFirst();
                int i = idx/m;
                int j = idx%m;

                for(int[] dir: dirs){
                    int x = i + dir[0];
                    int y = j + dir[1];

                    if(x>=0 && y>=0 && x<n && y<m && rooms[x][y] == inf){
                        rooms[x][y] = level + 1;
                        que.addLast(x*m + y);
                    }
                }
            }
            level++;
        }
    }

    // Questions based on Kahns
    // leetcode 207 ==================================================
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];

        ArrayList<Integer>[] graph = new ArrayList[numCourses];
        for(int i=0; i<numCourses; i++){
            graph[i] = new ArrayList<>();
        }

        for(int[] edge: prerequisites){
            int u = edge[1];
            int v = edge[0];

            indegree[v]++;
            graph[u].add(v);
        }

        LinkedList<Integer> que = new LinkedList<>();
        for(int i=0; i<numCourses; i++){
            if(indegree[i] == 0){
                que.addLast(i);
            }
        }

        while(que.size() > 0){
            int u = que.removeFirst();

            for(int nbr: graph[u]){
                indegree[nbr]--;
                if(indegree[nbr] == 0){
                    que.addLast(nbr);
                }
            }
        }

        // if there was a topological order possible, indegree of all the elements should be 0
        for(int i=0; i<numCourses; i++){
            if(indegree[i] > 0) return false; // no possible solution
        }

        return true;
    }

    // leetcode 210 ========================
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];

        ArrayList<Integer>[] graph = new ArrayList[numCourses];
        for(int i=0; i<numCourses; i++){
            graph[i] = new ArrayList<>();
        }

        for(int[] edge: prerequisites){
            int u = edge[1];
            int v = edge[0];

            indegree[v]++;
            graph[u].add(v);
        }

        LinkedList<Integer> que = new LinkedList<>();
        ArrayList<Integer> order = new ArrayList<>();
        for(int i=0; i<numCourses; i++){
            if(indegree[i] == 0){
                que.addLast(i);
                order.add(i);
            }
        }

        while(que.size() > 0){
            int u = que.removeFirst();

            for(int nbr: graph[u]){
                indegree[nbr]--;
                if(indegree[nbr] == 0){
                    que.addLast(nbr);
                    order.add(nbr);
                }
            }
        }

        // if there was a topological order possible,topological sorted elements will be of N size
        if(order.size() != numCourses){
            return new int[]{};
        }

        int[] ansArray = order.stream().mapToInt(Integer::intValue).toArray(); // converting arraylist to array
        return ansArray;
    }
}