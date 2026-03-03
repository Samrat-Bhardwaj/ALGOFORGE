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

    // leetcode 1162 (Max distance from water to land) =====================================================
    public int maxDistance(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int[][] dirs = {{0,1},{0,-1},{-1,0},{1,0}};
        LinkedList<Integer> que = new LinkedList<>();

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(grid[i][j] == 1){
                    que.addLast(i*m + j);
                }
            }
        }

        // if no water or land cell, return -1
        if(que.size() == n*m || que.size() == 0) return -1;

        int dis = 0;
        while(que.size() > 0){
            int size = que.size();

            while(size-- > 0){
                int cell = que.removeFirst();

                int i = cell/m;
                int j = cell%m;

                for(int[] dir: dirs){
                    int x = i + dir[0];
                    int y = j + dir[1];

                    if(x>=0 && y>=0 && x<n && y<m && grid[x][y] == 0){
                        que.addLast(x*m + y);
                        grid[x][y] = 2; // change it to anything except 0 to mark visited
                    }
                }
            }

            dis++;
        }

        return dis - 1;
    }

    // Leetcode 542, 0-1 Matrix ===========================================================
    public int[][] updateMatrix(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        int[][] dirs = {{0,1},{0,-1},{-1,0},{1,0}};
        LinkedList<Integer> que = new LinkedList<>();
        int[][] res = new int[n][m];

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(mat[i][j] == 0){
                    que.addLast(i*m + j);
                }
            }
        }

        int dis = 0;
        while(que.size() > 0){
            int size = que.size();

            while(size-- > 0){
                int cell = que.removeFirst();

                int i = cell/m;
                int j = cell%m;

                for(int[] dir: dirs){
                    int x = i + dir[0];
                    int y = j + dir[1];

                    if(x>=0 && y>=0 && x<n && y<m && mat[x][y] == 1){
                        que.addLast(x*m+y);
                        res[x][y] = dis+1;
                        mat[x][y] = 0; // change it to anything apart from 1
                    }
                }
            }
            dis++;
        }

        return res;
    }

    // Leetcode 1091 (Shortest binary path from 0,0 to n-1,n-1) ===============================
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;

        int[][] dirs = {{0,1},{0,-1},{-1,0},{1,0},{1,1},{1,-1},{-1,1},{-1,-1}};
        LinkedList<Integer> que = new LinkedList<>();

        if(grid[0][0] == 0){
            que.addLast(0);
            grid[0][0] = 1; // marking visited at insertion time
        }
        
        int dis = 1; // dis is number of 0's visited

        while(que.size() > 0){
            int size = que.size();

            while(size-- > 0){
                int idx = que.removeFirst();

                int i = idx/n;
                int j = idx%n;

                if(i == n-1 && j== n-1) return dis;

                for(int[] dir: dirs){
                    int x = i + dir[0];
                    int y = j + dir[1];

                    if(x>=0 && y>=0 && x<n && y<n && grid[x][y] == 0){
                        que.addLast(x*n + y);
                        grid[x][y] = 2;

                        // if(x == n-1 && y == n-1) return dis+1; //destination reached
                    }
                }
            }
            dis++;
        }

        return -1;
    }

    // Walls and gates (https://www.lintcode.com/problem/663/) ==============================
    public void wallsAndGates(int[][] rooms){
        int n = rooms.length;
        int m = rooms[0].length;
        int INF = Integer.MAX_VALUE;

        int[][] dirs = {{0,1},{0,-1},{-1,0},{1,0}};
        LinkedList<Integer> que = new LinkedList<>();

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(rooms[i][j] == 0){ // adding all gates
                    que.addLast(i*m + j);
                }
            }
        }

        int dis = 0;
        while(que.size() > 0){
            int size = que.size();

            while(size-- > 0){
                int idx = que.removeFirst();

                int i = idx/m;
                int j = idx%m;

                for(int[] dir: dirs){
                    int x = i + dir[0];
                    int y = j + dir[1];

                    if(x>=0 && y>=0 && x<n && y<m && rooms[x][y] == INF){
                        que.addLast(x*m + y);
                        rooms[x][y] = dis+1; // setting ans and marking visited (removing inf) as well
                    }
                }
            }
            dis++;
        }
    }

    // Kahn's Algorithm =====================================================================
    // Is topological sort possible (Leetcode 207)
    public ArrayList<Integer>[] makeGraph(int N, int[][] edges){
        ArrayList<Integer>[] graph = new ArrayList[N];

        for(int i=0; i<N; i++){
            graph[i] = new ArrayList<>();
        }

        for(int[] edge: edges){
            int u = edge[0];
            int v = edge[1];

            graph[u].add(v);
        }

        return graph;
    }
        
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] graph = makeGraph(numCourses,prerequisites);

        int[] indegree = new int[numCourses];

        for(int u=0; u<numCourses; u++){
            for(int v: graph[u]){
                indegree[v]++;
            }
        }

        LinkedList<Integer> que = new LinkedList<>();
        ArrayList<Integer> topologicalOrder = new ArrayList<>();

        for(int i=0; i<numCourses; i++){
            if(indegree[i] == 0){
                que.addLast(i);
            }
        }

        while(que.size() > 0){
            int u = que.removeFirst();

            topologicalOrder.add(u);

            for(int v: graph[u]){
                indegree[v]--;

                if(indegree[v] == 0){ // all the U for this V (u->v) is added in the answer
                    que.addLast(v);
                }
            }
        }

        if(topologicalOrder.size() != numCourses){
            return false;
        }

        return true;
    }
}