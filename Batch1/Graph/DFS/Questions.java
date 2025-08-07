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

    // TOPOLOGICAL ORDER ==================================================================
    
    // Is course scheduling possible (Leetcode 207) =============================
    public boolean checkCycle(int src, ArrayList<Integer>[] graph, int[] vis){
        vis[src] = 1;

        for(int nbr: graph[src]){
            if(vis[nbr] == 1){
                return true;
            } else if(vis[nbr] == 0){
                if(checkCycle(nbr,graph, vis)) return true;
            }
        }

        vis[src] = 2;
        return false;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] graph = new ArrayList[numCourses];

        for(int i=0; i<numCourses; i++){
            graph[i] = new ArrayList<>();
        }

        for(int[] edge: prerequisites){
            int u = edge[1];
            int v = edge[0];

            graph[u].add(v);
        }

        int[] vis = new int[numCourses];
        for(int i=0; i<numCourses; i++){
            if(vis[i] == 0){
                if(checkCycle(i,graph,vis)) return false; // no possible topological order
            }
        }

        return true;
    }

    // Find course scheduling order(Leetcode 210) =========================================
    public boolean topo_dfs(int src, ArrayList<Integer>[] graph, int[] vis, ArrayList<Integer> order){
        vis[src] = 1;

        for(int nbr: graph[src]){
            if(vis[nbr] == 1){
                return true;
            } else if(vis[nbr] == 0){
                if(topo_dfs(nbr,graph, vis,order)) return true;
            }
        }

        vis[src] = 2;
        order.add(src);
        return false;
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] graph = new ArrayList[numCourses];
        ArrayList<Integer> order = new ArrayList<>();

        for(int i=0; i<numCourses; i++){
            graph[i] = new ArrayList<>();
        }

        for(int[] edge: prerequisites){
            int u = edge[1];
            int v = edge[0];

            graph[u].add(v);
        }

        int[] vis = new int[numCourses];

        for(int i=0; i<numCourses; i++){
            if(vis[i] == 0){
                if(topo_dfs(i,graph,vis, order)){ // no possible topological order
                    return new int[]{};
                }
            }
        }

        Collections.reverse(order);
        int[] ansArray = order.stream().mapToInt(Integer::intValue).toArray(); // converting arraylist to array

        return ansArray;
    }

    // Find eventual safe states (Leetcode 802) =============================================
    public boolean dfs(int src, int[][] graph, int[] vis){
        vis[src] = 1;

        for(int nbr: graph[src]){
            if(vis[nbr] == 1){
                return true;
            } else if(vis[nbr] == 0) {
                if(dfs(nbr,graph,vis) == true) return true;
            }
        }

        vis[src] = 2;
        return false;
    }

    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;

        int[] vis = new int[n];

        for(int i=0; i<n; i++){
            if(vis[i] == 0){
                dfs(i,graph,vis);
            }
        }

        List<Integer> safeStates = new ArrayList<>();
        for(int i=0; i<n; i++){
            if(vis[i] == 2){
                safeStates.add(i);
            }
        }
        
        return safeStates;
    }

    // Kosaraju, storngly connected comp (https://www.geeksforgeeks.org/problems/strongly-connected-components-kosarajus-algo/1)
    public static void dfs_fill_stack(int src,boolean[] vis,ArrayList<ArrayList<Integer>> graph,Stack<Integer> st){
        vis[src] = true;

        for(int nbr: graph.get(src)){
            if(!vis[nbr]){
                dfs_fill_stack(nbr,vis,graph,st);
            }
        }

        st.push(src);
    }
    // just traverse through the component
    public static void dfs(int src, boolean[] vis,ArrayList<Integer>[] reversedGraph){
        vis[src] = true;

        for(int nbr: reversedGraph[src]){
            if(!vis[nbr]){
                dfs(nbr,vis,reversedGraph);
            }
        }
    }

    public int kosaraju(ArrayList<ArrayList<Integer>> adj) {
        Stack<Integer> st = new Stack<>();
        int N = adj.size();
        boolean[] vis = new boolean[N];

        for(int i=0; i<N; i++){
            if(!vis[i]){
                dfs_fill_stack(i,vis,adj,st);
            }
        }

        ArrayList<Integer>[] reversedGraph = new ArrayList[N];
        for(int i=0; i<N; i++){
            reversedGraph[i] = new ArrayList<>();
        }

        for(int u=0; u<N; u++){
            for(int i=0; i<adj.get(u).size(); i++){
                int v = adj.get(u).get(i);
                reversedGraph[v].add(u);
            }
        }

        int num_scc = 0;
        vis = new boolean[N];
        while(st.size() > 0){
            int vtx = st.pop();

            if(!vis[vtx]){
                dfs(vtx,vis,reversedGraph);
                num_scc++;
            }
        }

        return num_scc;
    }

    // Number of sub-islands (Leetcode 1905) ==============================
    // visit complete of B to convert 1 -> 0 and check if this is a subisland
    public boolean isSubIsland(int[][] A, int[][] B, int i, int j, int n, int m){
        B[i][j] = 0;

        boolean isSubIslandValue = true;
        if(A[i][j] == 0){
            isSubIslandValue = false;
        }

        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};

        for(int[] dir: dirs){
            int x = dir[0] + i;
            int y = dir[1] + j;

            if(x>=0 && y>=0 && x<n && y<m && B[x][y] == 1){
                isSubIslandValue = isSubIsland(A,B,x,y,n,m) && isSubIslandValue;
            }
        }

        return isSubIslandValue;
    }

    public int countSubIslands(int[][] A, int[][] B) {
        int count = 0;
        int n = A.length;
        int m = A[0].length;

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(B[i][j] == 1){
                    if(isSubIsland(A,B,i,j,n,m)){
                        count++;
                    }
                }
            }
        }


        return count;
    }

    // Number of distinct islands (https://www.geeksforgeeks.org/problems/number-of-distinct-islands/1)
    void getShape(int i, int j,int sr, int sc, int n, int m, int[][] grid, ArrayList<String> currShape){
        grid[i][j] = 0;

        int[][] dirs = {{-1,0},{0,-1},{1,0},{0,1}};
        currShape.add("" + (i-sr) + "," + (j-sc));

        for(int[] dir: dirs){
            int x = dir[0] + i;
            int y = dir[1] + j;

            if(x>=0 && y>=0 && x<n && y<m && grid[x][y] == 1){
                getShape(x,y,sr,sc,n,m,grid,currShape);
            }
        }
    }

    int countDistinctIslands(int[][] grid) {
        HashSet<ArrayList<String>> set = new HashSet<>();
        int n = grid.length;
        int m = grid[0].length;

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(grid[i][j] == 1){
                    ArrayList<String> currShape = new ArrayList<>();
                    getShape(i,j,i,j,n,m,grid,currShape);
                    set.add(currShape);
                }
            }
        }

        return set.size();
    }

    // Clone graph (Leetcode 133) =======================================
    class Solution {
        // clone this node
        public Node cloneGraph(Node node, HashMap<Integer,Node> map){
            if(node == null){
                return null;
            }
            // if already cloned, return cloned node
            if(map.containsKey(node.val)){
                return map.get(node.val);
            }

            Node clonedNode = new Node(node.val);
            map.put(node.val, clonedNode);

            for(Node actualNbr: node.neighbors){
                Node clonedNbr = cloneGraph(actualNbr, map);
                clonedNode.neighbors.add(clonedNbr);
            }

            return clonedNode;
        }
        
        public Node cloneGraph(Node node) {
            HashMap<Integer,Node> map = new HashMap<>();

            return cloneGraph(node,map);
        }
    }
}