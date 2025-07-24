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
}