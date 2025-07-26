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

    // Longest Increasing Path(leetcode 329) ========================
    public int longestIncreasingPath(int[][] matrix) {
        int n = matrix.length;
        int m= matrix[0].length;

        int[][] indegree = new int[n][m];
        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                for(int[] dir: dirs){ // O(4)
                    int x = i + dir[0];
                    int y = j + dir[1];

                    if(x>=0 && y>=0 && x<n && y<m && matrix[x][y] < matrix[i][j]){
                        indegree[i][j]++;
                    }
                }
            }
        }

        LinkedList<Integer> que = new LinkedList<>();
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(indegree[i][j] == 0){
                    que.addLast(i*m + j);
                }
            }
        }

        int level = 1;
        while(que.size() > 0){
            int size = que.size();

            while(size-- > 0){
                int idx = que.removeFirst();

                int i = idx/m;
                int j = idx%m;

                for(int[] dir: dirs){ // O(4)
                    int x = i + dir[0];
                    int y = j + dir[1];

                    if(x>=0 && y>=0 && x<n && y<m && matrix[x][y] > matrix[i][j]){
                        indegree[x][y]--;
                        if(indegree[x][y] == 0){ // all smaller neigbour elements already discovered
                            que.addLast(x*m + y);
                        }
                    }
                }
            } 
            level++;
        }

        return level - 1;
    }

    // Bipartie Graph ==============================================
    // All acyclic and even length cycle graph are bipartite
    public boolean isCycleEven(int[][] graph, int src, int[] vis){
        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(src);

        int color = 0; // even level, color = 0, odd level, color = 1
        
        while(que.size() > 0){
            int size = que.size();

            while(size-- > 0){
                int vtx = que.removeFirst();

                if(vis[vtx] != -1 && vis[vtx] != color){ // already but not equal to current level
                    return false; // odd cycle
                }

                vis[vtx] = color;

                for(int nbr: graph[vtx]){
                    if(vis[nbr] == -1){
                        que.addLast(nbr);
                    }
                }
            }
            color = (color + 1) % 2; // color = 0->1, 1->0
        }

        return true;
    }

    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] vis = new int[n];
        Arrays.fill(vis, -1); // not visited = -1

        for(int i=0; i<n; i++){
            if(vis[i] == -1){
                if(isCycleEven(graph,i,vis) == false){
                    return false;
                }
            }
        }

        return true;
    }

    // Leetcode 886 (Possible bipartition) =============================================
    public boolean isCycleEven(ArrayList<Integer>[] graph, int src, int[] vis){
        LinkedList<Integer> que = new LinkedList<>();
        que.addLast(src);

        int color = 0; // even level, color = 0, odd level, color = 1
        
        while(que.size() > 0){
            int size = que.size();

            while(size-- > 0){
                int vtx = que.removeFirst();

                if(vis[vtx] != -1 && vis[vtx] != color){ // already but not equal to current level
                    return false; // odd cycle
                }

                vis[vtx] = color;

                for(int nbr: graph[vtx]){
                    if(vis[nbr] == -1){
                        que.addLast(nbr);
                    }
                }
            }
            color = (color + 1) % 2; // color = 0->1, 1->0
        }

        return true;
    }

    public boolean possibleBipartition(int n, int[][] dislikes) {
        ArrayList<Integer>[] graph = new ArrayList[n];

        for(int i=0; i<n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int[] edge: dislikes){
            int u = edge[0] - 1; // vertices are from 1,n -> changing them to 0,n
            int v = edge[1] - 1;

            graph[u].add(v);
            graph[v].add(u);
        }

        int[] vis = new int[n];
        Arrays.fill(vis, -1); // not visited = -1

        for(int i=0; i<n; i++){
            if(vis[i] == -1){
                if(isCycleEven(graph,i,vis) == false){
                    return false;
                }
            }
        }

        return true;
    }

    // Word ladder without graph (Leetcode 127) =============================================
    class Solution {
        public boolean isSimilar(String a, String b){
            if(a.length() != b.length()) return false;

            int diff_char = 0;
            for(int i=0; i<a.length() && diff_char < 2; i++){
                if(a.charAt(i)  != b.charAt(i)){
                    diff_char++;
                }
            }

            return diff_char == 1;
        }

        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            LinkedList<String> que = new LinkedList<>();
            HashSet<String> vis = new HashSet<>();

            que.addLast(beginWord);
            vis.add(beginWord);

            int level = 1;
            while(que.size() > 0){
                int size = que.size();
                
                while(size-- > 0){
                    String top = que.removeFirst();

                    for(String word: wordList){
                        if(vis.contains(word) == false && isSimilar(top,word)){
                            if(word.equals(endWord)) return level + 1;

                            que.addLast(word);
                            vis.add(word);
                        }
                    }
                }
                level++;
            }

            return 0;
        }
    }

    // Word ladder With graph (Leetcode 127) =============================================
    class Solution {
        public boolean isSimilar(String a, String b){
            if(a.length() != b.length()) return false;

            int diff_char = 0;
            for(int i=0; i<a.length() && diff_char < 2; i++){
                if(a.charAt(i)  != b.charAt(i)){
                    diff_char++;
                }
            }

            return diff_char == 1;
        }

        public void addEdges(String u, HashMap<String, ArrayList<String>> graph, List<String> wordList){
            if(!graph.containsKey(u)){
                graph.put(u, new ArrayList<>());
            }

            for(String v: wordList){
                if(isSimilar(u,v)){
                    graph.get(u).add(v);
                }
            }
        }

        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            LinkedList<String> que = new LinkedList<>();
            HashMap<String, ArrayList<String>> graph = new HashMap<>();
            HashSet<String> vis = new HashSet<>();

            addEdges(beginWord, graph, wordList);
            for(String w: wordList){
                addEdges(w,graph,wordList);
            }

            que.add(beginWord);
            vis.add(beginWord);

            int level = 1;

            while(que.size() > 0){
                int size = que.size();

                while(size-- > 0){
                    String top = que.removeFirst();

                    for(String nbr: graph.get(top)){
                        if(!vis.contains(nbr)){
                            if(nbr.equals(endWord)) return level + 1;

                            que.addLast(nbr);
                            vis.add(nbr);
                        }
                    }
                }
                level++;
            }

            return 0;
        }
    }

    // word ladder 2 (leetcode 126)
    public boolean isSimilar(String a, String b){
        if(a.length() != b.length()) return false;

        int diff_char = 0;
        for(int i=0; i<a.length() && diff_char < 2; i++){
            if(a.charAt(i)  != b.charAt(i)){
                diff_char++;
            }
        }

        return diff_char == 1;
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ans = new ArrayList<>();
        HashSet<String> vis = new HashSet<>();

        LinkedList<List<String>> que = new LinkedList<>();

        int min_path_length = -1;
        int level = 1;

        List<String> start = new ArrayList<>();
        start.add(beginWord);
        que.add(start);

        while(que.size() > 0){
            int size = que.size();

            while(size-- > 0){
                List<String> top = que.removeFirst();

                String lastString = top.get(top.size()-1);

                if(vis.contains(lastString)) continue;
                if(min_path_length != -1 && top.size() >= min_path_length) continue;

                vis.add(lastString);

                for(String word: wordList){
                    if(!vis.contains(word) && isSimilar(lastString, word)){
                        List<String> nextPath = new ArrayList<>(top);
                        nextPath.add(word);
                        
                        if(word.equals(endWord)){
                            if(min_path_length == -1){
                                min_path_length = level + 1;
                                ans.add(nextPath);
                            } else if(nextPath.size() == min_path_length){
                                ans.add(nextPath);
                            }  
                        } 
                    }

                    que.add(nextPath);
                }
            }
            level++;
        }

        return ans;
    }
}