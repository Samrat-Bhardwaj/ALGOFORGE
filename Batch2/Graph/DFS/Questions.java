class Questions {
    // find all connected components 
    public void addEdge(int u, int v, ArrayList<Integer>[] graph){
        graph[u].add(v);
        graph[v].add(u);
    }

    public ArrayList<Integer>[] makeGraph(int V, int[][] edges){
        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] graph = new ArrayList[V];

        for(int i=0; i<V; i++){
            graph[i] = new ArrayList<>();
        }

        for(int[] edge: edges){
            int u = edge[0];
            int v = edge[1];

            addEdge(u,v,graph);
        }

        return graph;
    }

    public static void dfs(int src, ArrayList<Integer>[] graph, boolean[] vis, ArrayList<Integer> currComponent){
        vis[src] = true;

        currComponent.add(src);

        for(int nbr: graph[src]){
            if(!vis[nbr]){
                dfs(nbr,graph,vis,currComponent);
            }
        }
    }

    public ArrayList<ArrayList<Integer>> getComponents(int V, int[][] edges) {
        ArrayList<Integer>[] graph = makeGraph(V, edges);

        boolean[] vis = new boolean[V];

        ArrayList<ArrayList<Integer>> allComponents = new ArrayList<>();

        for(int i=0; i<V; i++){
            if(vis[i] == false){
                ArrayList<Integer> currComponent = new ArrayList<>();

                dfs(i,graph,vis,currComponent);

                allComponents.add(currComponent);
            }
        }

        return allComponents;
    }

    // Leetcode 200, number of islands ====================================
    public void dfs(int row, int col, int n, int m, char[][] grid, boolean[][] vis){
        vis[row][col] = true;

        int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}};

        for(int[] dir: dirs){
            int nRow = row + dir[0];
            int nCol = col + dir[1];

            if(nRow >=0 && nRow < n && nCol >=0 && nCol < m && grid[nRow][nCol] == '1' && !vis[nRow][nCol]){
                dfs(nRow, nCol, n, m, grid, vis);
            }
        }
    }

    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        boolean[][] vis = new boolean[n][m];

        int components = 0;

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(grid[i][j] == '1' && vis[i][j] == false){
                    dfs(i,j,n,m,grid,vis);

                    components++;
                }
            }
        }

        return components;
    }

    // Leetcode 695, Max Area of island ===================================
    public int getArea(int row, int col, int n, int m, int[][] grid){
        grid[row][col] = 0; // replacement of visited

        int totalArea = 0;

        int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}};

        for(int[] dir: dirs){
            int nRow = row + dir[0];
            int nCol = col + dir[1];

            if(nRow >=0 && nRow < n && nCol >=0 && nCol < m && grid[nRow][nCol] == 1){
                totalArea += getArea(nRow,nCol,n,m,grid);
            }
        }

        return totalArea + 1;
    }

    public int maxAreaOfIsland(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int maxArea = 0;

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(grid[i][j] == 1){
                    int currentArea = getArea(i,j,n,m,grid);

                    maxArea = Math.max(maxArea, currentArea);
                }
            }
        }    

        return maxArea;
    }

    // Leetcode 130 (Surrounded regions) =============================================
    public void convertRegionToHash(int row, int col, int n, int m, char[][] board){
        board[row][col] = '#';

        int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}};

        for(int[] dir: dirs){
            int nRow = row + dir[0];
            int nCol = col + dir[1];

            if(nRow >=0 && nRow < n && nCol >=0 && nCol < m && board[nRow][nCol] == 'O'){
                convertRegionToHash(nRow,nCol,n,m,board);
            }
        }
    }

    public void solve(char[][] board) {
        int n = board.length;
        int m = board[0].length;

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(i==0 || j==0 || i==n-1 || j==m-1){
                    if(board[i][j] == 'O'){
                        convertRegionToHash(i,j,n,m,board);
                    }
                }
            }
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(board[i][j] == 'O'){ // surrounded region
                    board[i][j] = 'X';
                } else if(board[i][j] == '#'){
                    board[i][j] = 'O';
                }
            }
        }
    }

    // Perimeter of island (Leetcode 463) ========================================
    public int islandPerimeter(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int totalOnes = 0;
        int totalNbr = 0;
        int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}};

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(grid[i][j] == 1){
                    totalOnes++;

                    for(int[] dir: dirs){
                        int x = i + dir[0];
                        int y = j + dir[1];

                        if(x >=0 && y>=0 && x<n && y<m && grid[x][y] == 1){
                            totalNbr++;
                        }
                    }
                }
            }
        }


        return 4*totalOnes - totalNbr;
    }

    // Topological Sort Questions ======================================================================

    // Is course schedule possible (leetcode 207) =============
    class Solution {
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

        public boolean checkIfCycle(int src, ArrayList<Integer>[] graph, int[] vis){
            vis[src] = 1;

            for(int nbr: graph[src]){
                if(vis[nbr] == 1){
                    return true;
                } else if(vis[nbr] == 2){
                    continue;
                } else {
                    if(checkIfCycle(nbr,graph,vis)) return true;
                }
            }

            vis[src] = 2;
            return false;
        }

        public boolean canFinish(int numCourses, int[][] prerequisites) {
            ArrayList<Integer>[] graph = makeGraph(numCourses, prerequisites);

            int[] vis = new int[numCourses];

            for(int i=0; i<numCourses; i++){
                if(vis[i] == 0){
                    if(checkIfCycle(i,graph,vis)) return false;
                }
            }

            return true;
        }
    }

    // Course schedule 2 (Leetcode 210) ==================================================
    class Solution {
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

        public boolean topo_dfs_isCycle(int src, ArrayList<Integer>[] graph, int[] vis, ArrayList<Integer> topologicalOrder){
            vis[src] = 1;
            
            for(int nbr: graph[src]){
                if(vis[nbr] == 1){
                    return true;
                } else if(vis[nbr] == 2){
                    continue;
                } else {
                    if(topo_dfs_isCycle(nbr,graph,vis,topologicalOrder)){
                        return true;
                    }
                }
            }


            vis[src] = 2;
            topologicalOrder.add(src);

            return false;
        }

        public int[] findOrder(int numCourses, int[][] prerequisites) {
            ArrayList<Integer>[] graph = makeGraph(numCourses, prerequisites);

            int[] vis = new int[numCourses];
            ArrayList<Integer> topologicalOrder = new ArrayList<>();

            for(int i=0; i<numCourses; i++){
                if(vis[i] == 0){
                    if(topo_dfs_isCycle(i,graph,vis,topologicalOrder)){
                        return new int[]{};
                    }
                }
            }

            // converting to array before returning 
            return arrayList.stream().mapToInt(Integer::intValue).toArray();
        }
    }

    // Leetcode 802, find eventual safe states  ===================================
    public boolean checkIfCycle(int src, int[][] graph, int[] vis){
        vis[src] = 1;

        for(int nbr: graph[src]){
            if(vis[nbr] == 1){
                return true;
            } else if(vis[nbr] == 2){
                continue;
            } else {
                if(checkIfCycle(nbr, graph, vis)){
                    return true;
                }
            }
        }

        vis[src] = 2;
        return false;
    }

    public List<Integer> eventualSafeNodes(int[][] graph) {
        int N = graph.length;

        int[] vis = new int[N];

        ArrayList<Integer> safeStates = new ArrayList<>();

        for(int i=0; i<N; i++){
            if(vis[i] == 0){
                checkIfCycle(i,graph,vis);
            }
        }

        for(int i=0; i<N; i++){
            if(vis[i] == 2){
                safeStates.add(i);
            }
        }

        return safeStates;
    }




























    public static void main(String[] args){

    }
}