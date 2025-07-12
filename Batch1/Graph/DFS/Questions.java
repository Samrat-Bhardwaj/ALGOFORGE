class Questions {
    // Get connected components (https://www.geeksforgeeks.org/problems/connected-components-in-an-undirected-graph/1)
    public static void dfs(int vtx, boolean[] vis,ArrayList<Integer> currComponent,ArrayList<Integer>[] graph){
        vis[vtx] = true;
        currComponent.add(vtx);

        for(int nbr: graph[vtx]){
            if(!vis[nbr]){
                dfs(nbr,vis,currComponent,graph);
            }
        }
    }

    public ArrayList<ArrayList<Integer>> getComponents(int V, int[][] edges) {
        ArrayList<Integer>[] graph = new ArrayList[V];

        for(int i=0; i<V; i++){
            graph[i] = new ArrayList<>();
        }

        for(int [] edge: edges){
            int u = edge[0];
            int v = edge[1];

            graph[u].add(v);
            graph[v].add(u);
        } 

        // GCC
        ArrayList<ArrayList<Integer>> components = new ArrayList<>();
        boolean[] vis = new boolean[V];

        for(int vertex=0; vertex<V; vertex++){
            if(vis[vertex] == false){
                ArrayList<Integer> currComponent = new ArrayList<>();
                dfs(vertex,vis,currComponent,graph);

                components.add(currComponent);
            }
        }   

        return components;
    }

    // Number of islands (Leetcode 200) ===============================
    public void visitIsland(int r, int c, int n, int m,char[][] grid,boolean[][] vis){
        vis[r][c] = true;

        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};

        for(int[] dir: dirs){
            int x = r + dir[0];
            int y = c + dir[1];

            if(x>=0 && y>=0 && x<n && y<m && !vis[x][y] && grid[x][y] == '1'){
                visitIsland(x,y,n,m,grid,vis);
            }
        }
    }

    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        boolean[][] vis = new boolean[n][m];

        int count = 0;

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(!vis[i][j] && grid[i][j] == '1'){ // unvisited land elements
                    visitIsland(i,j,n,m,grid,vis);
                    count++;
                }
            }
        }

        return count;
    }

    // Area of island (Leetcode 695) ============================================
    public int findIslandArea(int r, int c, int n, int m, int[][] grid){
        grid[r][c] = 0; // visited 

        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};
        int area = 0;

        for(int[] dir: dirs){
            int x = r + dir[0];
            int y = c + dir[1];

            if(x>=0 && y>=0 && x<n && y<m && grid[x][y] == 1){
                area += findIslandArea(x,y,n,m,grid);
            }
        }

        return area + 1;
    }

    public int maxAreaOfIsland(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int maxArea = 0;

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(grid[i][j] == 1){
                    int currIslandArea = findIslandArea(i,j,n,m,grid);
                    maxArea = Math.max(maxArea, currIslandArea);
                }
            }    
        }

        return maxArea;
    }

    // Surrounded regions (leetcode 130) ====================================================
    public void convertBoundaryO(int r, int c, int n, int m, char[][] board){
        board[r][c] = '#';

        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};

        for(int[] dir: dirs){
            int x = r + dir[0];
            int y = c + dir[1];

            if(x>=0 && y>=0 && x<n && y<m && board[x][y] == 'O'){
                convertBoundaryO(x,y,n,m,board);
            }
        }
    }

    public void solve(char[][] board) {
        int n = board.length;
        int m = board[0].length;

        // convert boundary 'O' to '#'
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(i==0 || i==n-1 || j==0 || j==m-1){
                    if(board[i][j] == 'O'){
                        convertBoundaryO(i,j,n,m,board);
                    }
                }
            }
        }

        // Convert all the 'O' to 'X' and '#' to 'O'
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                } else if(board[i][j] == '#'){
                    board[i][j] = 'O';
                }
            }
        }
    }

    // Perimeter of Island (only one island is present)(Leetcode 463) ==================
    public int islandPerimeter(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int oneCount = 0;
        int nbrCount = 0;

        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(grid[i][j] == 1){
                    oneCount++;

                    for(int[] dir: dirs){
                        int x = i + dir[0];
                        int y = j + dir[1];

                        if(x>=0 && y>=0 && x<n && y<m && grid[x][y] == 1){
                            nbrCount++;
                        }
                    }
                }
            }
        }

        return 4*oneCount - nbrCount;
    }

    // Perimeter of all Islands ====================
    public int dfs(int r, int c, int n, int m, int[][] grid){
        grid[r][c] = 2; // i have already visited, not 1 

        int nbrCount = 0;
        int totalPerimeter = 0;
        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};
        
        for(int[] dir: dirs){
            int x = r + dir[0];
            int y = c + dir[1];

            if(x>=0 && y>=0 && x<n && y<m){
                if(grid[x][y] == 1 || grid[x][y] == 2){ // counting all nbrs (visited, not visited)
                    nbrCount++;
                } 
                if(grid[x][y] == 1){ // ask the univisted nbr for its perimeter
                    totalPerimeter += dfs(x,y,n,m, grid);
                }   
            }
        }

        return totalPerimeter + (4 - nbrCount);
    }

    public int AllIslandPerimeter(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int totalPerimeter = 0;

        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(grid[i][j] == 1){
                    totalPerimeter += dfs(i,j,n,m,grid);
                }
            }
        }

        return totalPerimeter;
    }
}