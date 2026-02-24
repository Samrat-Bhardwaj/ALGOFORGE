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





















    public static void main(String[] args){

    }
}