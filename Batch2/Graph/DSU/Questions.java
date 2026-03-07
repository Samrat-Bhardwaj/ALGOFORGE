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
}