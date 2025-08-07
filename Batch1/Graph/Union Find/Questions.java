class Questions {
    // Redundant connections (Leetcode 684) ======================
    class Solution {
        int[] par;
        int[] size;
        public static int findPar(int u){
            if(par[u] == u){
                return u;
            }

            return par[u] = findPar(par[u]);
        }

        public static void merge(int p1, int p2){
            if(size[p1] < size[p2]){ 
                par[p1] = p2;
                size[p2] += size[p1];
            } else {
                par[p2] = p1;
                size[p1] += size[p2];
            }
        }
        public int[] findRedundantConnection(int[][] edges) {
            int n = edges.length;

            par = new int[n];
            size = new int[n];

            for(int i=0; i<n; i++){
                par[i] = i;
                size[i] = 1;
            }

            for(int[] edge: edges){
                int u = edge[0] - 1;
                int v = edge[1] - 1;

                int p1 = findPar(u);
                int p2 = findPar(v);

                if(p1 == p2){ // redundance edge, they are already connected
                    return edge;
                } else {
                    merge(p1,p2);
                }
            }

            return new int[]{};
        }
    }

    // Leetcode 1061 (Make lexiographically smallest equivalent string) ================
    class Solution {
        int[] par;
        public int findPar(int u){
            if(par[u] == u){
                return u;
            }

            return par[u] = findPar(par[u]);
        }

        public void merge(int p1, int p2){
            if(p1 < p2){ // smaller character will be parent
                par[p2] = p1;
            } else {
                par[p1] = p2;
            }
        }

        public String smallestEquivalentString(String s1, String s2, String baseStr) {
            par = new int[26];

            for(int i=0; i<26; i++){
                par[i] = i;
            }

            for(int i=0; i<s1.length(); i++){
                int u = s1.charAt(i) - 'a';
                int v = s2.charAt(i) - 'a';

                int p1 = findPar(u);
                int p2 = findPar(v);

                if(p1 != p2){
                    merge(p1,p2);
                }
            }

            StringBuilder sb = new StringBuilder();

            for(int i=0; i<baseStr.length(); i++){
                int u = baseStr.charAt(i) - 'a';

                int p = findPar(u);
                sb.append((char)(p+'a'));
            }

            return sb.toString();
        }
    }

    // Leetcode 547 (Number of Provinces) ================================
    class Solution {
        int[] par;
        int[] size;
        public int findPar(int u){
            if(par[u] == u){
                return u;
            }

            return par[u] = findPar(par[u]);
        }

        public void merge(int p1, int p2){
            if(size[p1] < size[p2]){
                par[p1] = p2;
                size[p2] += size[p1];
            } else {
                par[p2] = p1;
                size[p1] += size[p2];
            }
        }

        public int findCircleNum(int[][] isConnected) {
            int n = isConnected.length;
            par = new int[n];
            size = new int[n];

            for(int i=0; i<n; i++){
                par[i] = i;
                size[i] = 1;
            }

            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    if(i!=j && isConnected[i][j] == 1){
                        int p1 = findPar(i);
                        int p2 = findPar(j);

                        if(p1 != p2){
                            merge(p1,p2);
                        }
                    }
                }
            }

            int total_comps = 0;
            for(int i=0; i<n; i++){
                if(par[i] == i){ // unique leader;
                    total_comps++;
                }
            }
            return total_comps;
        }
    }

    // Leetcode 547 Method 2 (Number of Provinces) ================================
    class Solution {
        int[] par;
        int[] size;
        public int findPar(int u){
            if(par[u] == u){
                return u;
            }

            return par[u] = findPar(par[u]);
        }

        public void merge(int p1, int p2){
            if(size[p1] < size[p2]){
                par[p1] = p2;
                size[p2] += size[p1];
            } else {
                par[p2] = p1;
                size[p1] += size[p2];
            }
        }

        public int findCircleNum(int[][] isConnected) {
            int n = isConnected.length;
            par = new int[n];
            size = new int[n];

            int total_comps = n;

            for(int i=0; i<n; i++){
                par[i] = i;
                size[i] = 1;
            }

            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    if(i!=j && isConnected[i][j] == 1){
                        int p1 = findPar(i);
                        int p2 = findPar(j);

                        if(p1 != p2){
                            merge(p1,p2);
                            total_comps--;
                        }
                    }
                }
            }

            return total_comps;
        }
    }

    // Leetcode 695 (Max area of island) ======================================
    class Solution {
        int[] par;
        int[] size;
        public int findPar(int u){
            if(par[u] == u){
                return u;
            }

            return par[u] = findPar(par[u]);
        }

        public int maxAreaOfIsland(int[][] grid) {
            int n = grid.length;
            int m = grid[0].length;

            par = new int[n*m];
            size = new int[n*m];

            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    if(grid[i][j] == 1){ // initially, an island of one size
                        par[i*m + j] = i*m + j;
                        size[i*m + j] = 1;
                    } else { // not an island
                        par[i*m + j] = -1;
                        size[i*m + j] = 0;
                    }
                }
            }

            int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};

            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    if(grid[i][j] == 1){
                        int p1 = findPar(i*m + j);
                        for(int[] dir: dirs){
                            int x = i + dir[0];
                            int y = j + dir[1];

                            if(x>=0 && y>=0 && x<n && y<m && grid[x][y] == 1){
                                int p2 = findPar(x*m + y);
                                // merge(p1,p2);
                                if(p1 != p2){
                                    par[p2] = p1;
                                    size[p1] += size[p2];
                                }   
                            }
                        }
                    }
                }
            }

            int max_area = 0;
            for(int i=0; i<n*m; i++){
                if(par[i] == i){
                    max_area = Math.max(max_area, size[i]);
                }
            }

            return max_area;
        }
    }

    // Number of islands 2 (https://leetcode.ca/all/305.html)
    class Solution {
        int[] par;
        public int findPar(int u){
            if(par[u] == u){
                return u;
            }

            return par[u] = findPar(par[u]);
        }

        public List<Integer> numIslands2(int n, int m, int[][] positions) {
            par = new int[n*m];
            Arrays.fill(par, -1);

            List<Integer> res = new ArrayList<>();
            int count = 0;

            int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};
            for(int[] pos: positions){
                int i = pos[0];
                int j = pos[1];

                if(par[i*m + j] != -1){ // there was already a land here
                    res.add(count);
                    continue;
                }
                par[i*m + j] = i*m + j;
                count++; // a new island is created
                int p1 = i*m + j;
                for(int[] dir: dirs){
                    int x = i + dir[0];
                    int y = j + dir[1];

                    if(x>=0 && y>=0 && x<n && y<m && par[x*m + y] != -1){
                        int p2 = findPar(x*m + y);
                        if(p1 != p2){
                            par[p2] = p1;
                            count--;
                        }
                    }
                }

                res.add(count);
            }

            return res;
        }
    }

    // Similar String groups (Leetcode 839) =======================================
    class Solution {
        int[] par;
        public int findPar(int u){
            return par[u] = par[u] == u ? u : findPar(par[u]);
        }

        public boolean isSimilar(String a, String b){
            int diff_characters = 0;
            
            for(int i=0; i<a.length; i++){
                if(a.charAt(i) != b.charAt(i)){
                    diff_characters++;
                }
            }

            return diff_characters <= 2;
        }

        public int numSimilarGroups(String[] strs) {
            int n = strs.length;
            int total_comps = n;

            par = new int[n];
            for(int i=0; i<n; i++){
                par[i] = i;
            }

            for(int i=0; i<n; i++){
                int p1 = findPar(i);

                for(int j=i+1; j<n; j++){
                    if(isSimilar(strs[i], strs[j])){
                        int p2 = findPar(j);

                        if(p1 != p2){
                            par[p2] = p1;
                            total_comps--;
                        }
                    }
                }
            }

            return total_comps;
        }
    }

    // Questions After Kruskal Algorithm Implementation =============================================
    

    // Leetcode 1584 (Min cost to connect all points) ============
    public int findCost(int[] x, int[] y){
        return Math.abs(x[0] - y[0]) + Math.abs(x[1] - y[1]);
    }

    public int[][] createEdges(int[][] points){
        int n = points.length;
        int total_edges = n*(n-1)/2;

        int[][] edges = new int[total_edges][3];
        int idx = 0;

        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
                int u = i;
                int v = j;
                int w = findCost(points[i], points[j]);

                edges[idx][0] = u;
                edges[idx][1] = v;
                edges[idx][2] = w;
                idx++;
            }
        }

        return edges;
    }

    int[] par;
    int[] size;
    public int findPar(int u){
        if(par[u] == u) return u;

        return par[u] = findPar(par[u]);
    }

    public void merge(int p1, int p2){
        if(size[p1] < size[p2]){
            par[p1] = p2;
            size[p2] += size[p1];
        } else {
            par[p2] = p1;
            size[p1] += size[p2];
        }
    }

    public int find_mst_kruskal(int N, int[][] edges){
        int total_cost = 0;
        par = new int[N];
        size = new int[N];

        for(int i=0; i<N; i++){
            par[i] = i;
            size[i] = 1;
        }   

        Arrays.sort(edges, (int[] a, int[] b) -> {
            return a[2] - b[2];
        });

        for(int[] edge: edges){
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            int p1 = findPar(u);
            int p2 = findPar(v);

            if(p1 != p2){
                merge(p1,p2);
                total_cost += w;
            }
        }

        return total_cost;
    }

    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        int[][] edges = createEdges(points);

        return find_mst_kruskal(n, edges);
    }

    // Leetcode 1168 (Optimize Water Distribution in a Village) =====================
    public int minCost(int n, int[] wells, int[] pipes){
        int new_edges_length = pipes.length + n;
        int[][] edges = new int[new_edges_length][3];

        int idx = 0;
        for(int i=0; i<n; i++){
            edges[idx][0] = 0;
            edges[idx][1] = i+1;
            edges[idx][2] = wells[i]; // edge is created from 0 to (i+1)th house with cost = wells[i];
            idx++;
        }

        for(int[] pipe: pipes){
            edges[idx][0] = pipe[0];
            edges[idx][1] = pipe[1];
            edges[idx][2] = pipe[2];
            idx++;
        }

        return find_mst_kruskal(n+1,edges);
    }

    // Leetcode 721 (Accounts merge)
    String findPar(String u, HashMap<String,String> par){
        if(par.get(u).equals(u)) return u;

        String leader = findPar(par.get(u), par);
        par.put(u, leader); // path compression

        return leader;
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        HashMap<String,Integer> emailUID = new HashMap<>();
        HashMap<String,String> par = new HashMap<>();

        for(int uid=0; uid<accounts.size(); uid++){
            List<String> account = accounts.get(uid);
            String owner = account.get(0);
            String firstEmail = account.get(1);

            for(int i=1; i<account.size(); i++){
                String email = account.get(i);

                if(emailUID.containsKey(email) == false){
                    emailUID.put(email, uid);
                }
                if(par.containsKey(email) == false){
                    par.put(email, email);
                }

                String p1 = findPar(firstEmail, par);
                String p2 = findPar(email, par);

                if(!p1.equals(p2)){
                    par.put(p2,p1); // merging two emails
                }
            }
        }

        HashMap<Integer, List<String>> UIDsVsEmails = new HashMap<>();

        for(String email: emailUID.keySet()){
            String leader = findPar(email, par);
            int uid = emailUID.get(leader);

            if(UIDsVsEmails.containsKey(uid) == false){
                UIDsVsEmails.put(uid, new ArrayList<>());
            }
            UIDsVsEmails.get(uid).add(email);
        }

        // sort the emails against any owner
        List<List<String>> ans = new ArrayList<>();
        for(int uid: UIDsVsEmails.keySet()){
            List<String> emails = UIDsVsEmails.get(uid);
            Collections.sort(emails);

            List<String> currOwnerEmails = new ArrayList<>();
            currOwnerEmails.add(accounts.get(uid).get(0));

            for(String email: emails){
                currOwnerEmails.add(email);
            }
            ans.add(currOwnerEmails);
        }

        return ans;
    }

    // Making a large island (Leetcode 827) =================================
    class Solution {
        int[] par;
        int[] size;
        public int findPar(int u){
            if(par[u] == u) return u;

            return par[u] = findPar(par[u]);
        }

        public void merge(int p1, int p2){
            if(size[p1] < size[p2]){
                par[p1] = p2;
                size[p2] += size[p1];
            } else {
                par[p2] = p1;
                size[p1] += size[p2];
            }
        }

        public int largestIsland(int[][] grid) {
            int n = grid.length;
            int m = grid[0].length;

            par = new int[n*m];
            size = new int[n*m];

            for(int i=0; i<n*m; i++){
                par[i] = i;
                size[i] = 1;
            }

            int[][] dirs = {{-1,0},{0,-1},{1,0},{0,1}};
            // make par and size arrays
            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    if(grid[i][j] == 1){

                        for(int[] dir: dirs){
                            int x = dir[0] + i;
                            int y = dir[1] + j;

                            if(x>=0 && y>=0 && x<n && y<m && grid[x][y] == 1){
                                int p1 = findPar(i*m + j);
                                int p2 = findPar(x*m + y);
                                if(p1 != p2){
                                    merge(p1,p2);
                                }
                            }
                        }
                    }
                }
            }

            int maxArea = 0;
            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    if(grid[i][j] == 1){
                        int p = findPar(i*m + j);
                        maxArea = Math.max(maxArea, size[p]);
                    } else {
                        HashSet<Integer> set = new HashSet<>();
                        int currArea = 0;
                        for(int[] dir: dirs){
                            int x = dir[0] + i;
                            int y = dir[1] + j;

                            if(x>=0 && y>=0 && x<n && y<m && grid[x][y] == 1){
                                int p = findPar(x*m + y);
                                
                                if(set.containsKey(p) == false){
                                    currArea += size[p];
                                    set.add(p);
                                }
                            }
                        }

                        maxArea = Math.max(maxArea, currArea + 1);
                    }
                }
            }

            return maxArea;
        }
    }
}