class Questions {
    // redundant connections (Leetcode 684)
    class Solution {
        int[] par;
        int[] size;

        public int findPar(int u){
            if(par[u] == u) return u;

            return par[u] = findPar(par[u]);
        }

        public void merge(int p1, int p2){
            if(size[p1] > size[p2]){
                par[p2] = p1;
                size[p1] += size[p2];
            } else {
                par[p1] = p2;
                size[p2] += size[p1];
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
                int u = edge[0]-1;
                int v = edge[1]-1;

                int p1 = findPar(u);
                int p2 = findPar(v);

                if(p1 == p2){ // u and v are already connected, no need of this edge
                    return edge;
                } else {
                    merge(p1,p2);
                }
            }

            return new int[]{};
        }
    }

    // Lexiographically smallest string (Leetcode 1061) =========================
    class Solution {
        int[] par;
        public int findPar(int u){
            if(par[u] == u) return u;

            return par[u] = findPar(par[u]);
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
                    if(p1 < p2){ // smaller element will be parent
                        par[p2] = p1;
                    } else {
                        par[p1] = p2;
                    }
                }
            }

            StringBuilder sb = new StringBuilder();
            for(int i=0; i<baseStr.length(); i++){
                int vtx = baseStr.charAt(i) - 'a';

                int p = findPar(vtx);

                sb.append((char)(p + 'a'));
            }

            return sb.toString();
        }
    }

    // Number of provinces (Leetcode 547) ===================================
    class Solution {
        int[] par;
        int[] size;

        public int findPar(int u){
            if(par[u] == u) return u;

            return par[u] = findPar(par[u]);
        }

        public void merge(int p1, int p2){
            if(size[p1] > size[p2]){
                par[p2] = p1;
                size[p1] += size[p2];
            } else {
                par[p1] = p2;
                size[p2] += size[p1];
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

            int totalComps = n; // initially no one is connected

            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    if(isConnected[i][j] == 1){
                        int p1 = findPar(i);
                        int p2 = findPar(j);

                        if(p1 != p2){
                            merge(p1, p2);
                            totalComps--; // two different components are merging
                        }
                    }
                }
            }   

            // int totalComps = 0;
            // for(int i=0; i<n; i++){
            //     if(par[i] == i){
            //         totalComps++;
            //     }
            // }

            return totalComps;
        }
    }

    // Max Area of Island (Leetcode 695) ==================================
    class Solution {
        int[] par;
        int[] size;

        public int findPar(int u){
            if(par[u] == u) return u;

            return par[u] = findPar(par[u]);
        }        

        public int maxAreaOfIsland(int[][] grid) {
            int n = grid.length;
            int m = grid[0].length;

            par = new int[n*m];
            size = new int[n*m];
            int[][] dirs = {{0,1},{0,-1},{-1,0},{1,0}};


            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    if(grid[i][j] == 1){
                        par[i*m+j] = i*m+j;
                        size[i*m+j] = 1;
                    } else {
                        par[i*m+j] = -1;
                        size[i*m+j] = 0;
                    }
                }
            }

            // merge neighbouring cells
            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    if(grid[i][j] == 1){
                        int p1 = findPar(i*m+j);

                        for(int[] dir: dirs){
                            int x = i + dir[0];
                            int y = j + dir[1];

                            if(x>=0 && y>=0 && x<n && y<m && grid[x][y] == 1){
                                int p2 = findPar(x*m+y);

                                if(p1 != p2){
                                    par[p2] = p1; // making parent p1 so that we dont need to recalculate parent of (i,j)
                                    size[p1] += size[p2];
                                }
                            }
                        }
                    }
                }
            }

            int maxArea = 0;
            for(int i=0; i<n*m; i++){
                if(par[i] == i){
                    maxArea = Math.max(maxArea, size[i]);
                }
            }

            return maxArea;
        }
    }

    // Leetcode 200 (Number of islands) =================================
    class Solution {
        int[] par;

        public int findPar(int u){
            if(par[u] == u) return u;

            return par[u] = findPar(par[u]);
        }

        public int numIslands(char[][] grid) {
            int n = grid.length;
            int m = grid[0].length;

            par = new int[n*m];
            int[][] dirs = {{0,1},{0,-1},{-1,0},{1,0}};


            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    if(grid[i][j] == '1'){
                        par[i*m+j] = i*m+j;
                    } else {
                        par[i*m+j] = -1;
                    }
                }
            }

            // merge neighbouring cells
            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    if(grid[i][j] == '1'){
                        int p1 = findPar(i*m+j);

                        for(int[] dir: dirs){
                            int x = i + dir[0];
                            int y = j + dir[1];

                            if(x>=0 && y>=0 && x<n && y<m && grid[x][y] == '1'){
                                int p2 = findPar(x*m+y);

                                if(p1 != p2){
                                    par[p2] = p1; // making parent p1 so that we dont need to recalculate parent of (i,j)
                                }
                            }
                        }
                    }
                }
            }

            int count = 0;
            for(int i=0; i<n*m; i++){
                if(par[i] == i){
                    count++;
                }
            }

            return count;
        }
    }

    // Group Similar strings (Leetcode 839) =====================================
    // Method 1 (replacing string with respective array indices)
    // check cpp file for method 2 (hashmap)
    class Solution {
        int[] par;
        int[] size;

        public int findPar(int u){
            if(par[u] == u) return u;

            return par[u] = findPar(par[u]);
        }

        public void merge(int p1, int p2){
            if(size[p1] > size[p2]){
                par[p2] = p1;
                size[p1] += size[p2];
            } else {
                par[p1] = p2;
                size[p2] += size[p1];
            }
        }

        public boolean isSimilar(String a, String b){
            int diff_char = 0;

            for(int i=0; i<a.length(); i++){
                if(a.charAt(i) != b.charAt(i)){
                    diff_char++;
                }
            }

            return diff_char <= 2; // It can only be 0,2 or bigger numbers
        }

        public int numSimilarGroups(String[] strs) {
            int n = strs.length;

            par = new int[n];
            size = new int[n];
            int totalComps = n;

            for(int i=0; i<n; i++){
                par[i] = i;
                size[i] = 1;
            }

            for(int i=0; i<n; i++){
                for(int j=i+1; j<n; j++){
                    if(isSimilar(strs[i], strs[j])){
                        int p1 = findPar(i);
                        int p2 = findPar(j);

                        if(p1 != p2){
                            merge(p1,p2);
                            totalComps--;
                        }
                    }
                }
            }

            return totalComps;
        }
    }

    // Leetcode 990 ====================
    class Solution {
        int[] par;
        int[] size;

        public int findPar(int u){
            if(par[u] == u) return u;

            return par[u] = findPar(par[u]);
        }

        public void merge(int p1, int p2){
            if(size[p1] > size[p2]){
                par[p2] = p1;
                size[p1] += size[p2];
            } else {
                par[p1] = p2;
                size[p2] += size[p1];
            }
        }

        public boolean equationsPossible(String[] equations) {
            par = new int[26];
            size = new int[26];

            for(int i=0; i<26; i++){
                par[i] = i;
                size[i] = 1;
            }

            // merging equal equations
            for(String equation: equations){
                if(equation.charAt(1) == '='){
                    int p1 = findPar(equation.charAt(0) - 'a');
                    int p2 = findPar(equation.charAt(3) - 'a');

                    if(p1 != p2){
                        merge(p1, p2);
                    }
                }
            }

            for(String equation: equations){
                if(equation.charAt(1) == '!'){
                    int p1 = findPar(equation.charAt(0) - 'a');
                    int p2 = findPar(equation.charAt(3) - 'a');

                    if(p1 == p2){
                        return false; // they should not be from same group if ! is equation
                    }
                }
            }

            return true;
        }
    }

    // Questions on MST (Kruskal) ==================================
    class Solution {
        int[] par;
        int[] size;

        public int findPar(int u){
            if(par[u] == u) return u;

            return par[u] = findPar(par[u]);
        }

        public void merge(int p1, int p2){
            if(size[p1] > size[p2]){
                par[p2] = p1;
                size[p1] += size[p2];
            } else {
                par[p1] = p2;
                size[p2] += size[p1];
            }
        }

        public int minCostConnectPoints(int[][] points) {
            int n = points.length;

            par = new int[n];
            size = new int[n];

            for(int i=0; i<n; i++){
                par[i] = i;
                size[i] = 1;
            }

            int[][] edges = new int[(n*(n-1))/2][3];
            int k = 0;

            for(int i=0; i<n; i++){
                for(int j=i+1; j<n; j++){
                    edges[k][0] = i;
                    edges[k][1] = j;
                    edges[k][2] = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                    k++;
                }
            }

            // Kruskal
            Arrays.sort(edges, (int[] a, int[] b)->{
                return a[2] - b[2];
            });

            int totalCost = 0;
            for(int[] edge: edges){
                int u = edge[0];
                int v = edge[1];
                int w = edge[2];

                int p1 = findPar(u);
                int p2 = findPar(v);

                if(p1 != p2){
                    merge(p1,p2);
                    totalCost += w;
                }
            }

            return totalCost;
        }
    }

    // Leetcode 1168 (https://leetcode.ca/all/1168.html) ==================================
    class Solution {
        int[] par;
        int[] size;

        public int findPar(int u){
            if(par[u] == u) return u;

            return par[u] = findPar(par[u]);
        }

        public void merge(int p1, int p2){
            if(size[p1] > size[p2]){
                par[p2] = p1;
                size[p1] += size[p2];
            } else {
                par[p1] = p2;
                size[p2] += size[p1];
            }
        }

        public int minCostToSupplyWater(int[] wells, int[][] pipes, int n){
            par = new int[n];
            size = new int[n];

            for(int i=0; i<n; i++){
                par[i] = i;
                size[i] = 1;
            }

            int[][] edges = new int[pipes.length + n][3];
            int k = 0;
            for(int[] pipe: pipes){
                edges[k] = pipe;
                k++;
            }

            // changings wells to edges => wells[2] = {0,3,wells[2]}
            for(int i=0; i<n; i++){
                edges[k][0] = 0;
                edges[k][1] = i+1;
                edges[k][2] = wells[i];
                k++;
            }

            Arrays.sort(edges, (int[] a, int[] b)->{
                return a[2] - b[2];
            });

            int totalCost = 0;
            for(int[] edge: edges){
                int u = edge[0];
                int v = edge[1];
                int w = edge[2];

                int p1 = findPar(u);
                int p2 = findPar(v);

                if(p1 != p2){
                    merge(p1,p2);
                    totalCost += w;
                }
            }

            return totalCost;
        }
    }
}