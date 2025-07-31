class Questions {
    // leetcode 743 (Network delay time) ==============================
    public int networkDelayTime(int[][] times, int n, int k) {
        ArrayList<int[]>[] graph = new ArrayList[n];

        for(int i=0; i<n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int[] edge: times){
            int u = edge[0] - 1; // changing one base indexing to 0-base
            int v = edge[1] - 1;
            int w = edge[2];

            graph[u].add(new int[]{v,w});
        }
        // vtx, wsf
        PriorityQueue<int[]> pq = new PriorityQueue<>((int[] a, int[] b)->{
            return a[1] - b[1];
        });
        int[] dis = new int[n];
        Arrays.fill(dis, (int)(1e8));

        pq.add(new int[]{k-1,0});
        dis[k-1] = 0;

        while(pq.size() > 0){
            int[] top = pq.remove();
            int vtx = top[0];
            int wsf = top[1];

            if(dis[vtx] < wsf) continue;

            for(int[] nbrEdges: graph[vtx]){
                int nbrVtx = nbrEdges[0], wt = nbrEdges[1];
                if(dis[nbrVtx] > wsf + wt){
                    dis[nbrVtx] = wsf + wt;
                    pq.add(new int[]{nbrVtx, wsf + wt});
                }
            }
        }

        int maxTime = 0;
        for(int i=0; i<n; i++){
            if(dis[i] == (int)(1e8)){ // we never reached this position
                return -1; 
            }
            maxTime = Math.max(maxTime, dis[i]);
        }

        return maxTime;
    }

    // Leetcode 1928 (Minimum Cost to Reach Destination in Time)
    class Solution {
        class Pair {
            int vtx;
            int feesSoFar;
            int timeSoFar;

            public Pair(int vtx, int feesSoFar, int timeSoFar){
                this.vtx = vtx;
                this.feesSoFar = feesSoFar;
                this.timeSoFar = timeSoFar;
            }
        }

        public int minCost(int maxTime, int[][] edges, int[] passingFees) {
            int n = passingFees.length;
            ArrayList<int[]>[] graph = new ArrayList[n];

            for(int i=0; i<n; i++){
                graph[i] = new ArrayList<>();
            }

            for(int[] edge: edges){
                int u = edge[0];
                int v = edge[1];
                int w = edge[2];

                graph[u].add(new int[]{v,w});
                graph[v].add(new int[]{u,w});
            }

            PriorityQueue<Pair> pq = new PriorityQueue<>((Pair a, Pair b)->{
                if(a.feesSoFar == b.feesSoFar){ // in case fees so far is same, return path with less time
                    return a.timeSoFar - b.timeSoFar;
                }
                return a.feesSoFar - b.feesSoFar; // return path with less fees
            });

            int[] feesCost = new int[n]; // dis
            int[] timeCost = new int[n];

            Arrays.fill(feesCost, (int)(1e8)); Arrays.fill(timeCost, (int)(1e8));

            pq.add(new Pair(0,passingFees[0],0));
            feesCost[0] = passingFees[0];
            timeCost[0] = 0;

            while(pq.size() > 0){
                Pair top = pq.remove();
                int vtx = top.vtx;
                int feesSoFar = top.feesSoFar;
                int timeSoFar = top.timeSoFar;

                // Need to travel at worse fees path to reach a path with time <= maxTime
                // if(feesCost[vtx] < feesSoFar) continue;
                if(vtx == n-1) return feesSoFar;

                for(int[] nbrEdges: graph[vtx]){
                    int nbrVtx = nbrEdges[0], time = nbrEdges[1];
                    int passFee = passingFees[nbrVtx];



                    if(timeSoFar + time <= maxTime){
                        if(feesCost[nbrVtx] > feesSoFar + passFee){
                            feesCost[nbrVtx] = feesSoFar + passFee;
                            timeCost[nbrVtx] = timeSoFar + time;
                            pq.add(new Pair(nbrVtx, feesCost[nbrVtx], timeCost[nbrVtx]));
                        } else if(timeCost[nbrVtx] > timeSoFar + time){
                            timeCost[nbrVtx] = timeSoFar + time;
                            pq.add(new Pair(nbrVtx, feesSoFar + passFee, timeCost[nbrVtx]));
                        }
                    }
                }
            }

            return feesCost[n-1] == (int)(1e8) ? -1 : feesCost[n-1];
        }
    }

    // Leetcode 1631 (Path with minimum effort)
    public int minimumEffortPath(int[][] heights) {
        int n = heights.length;
        int m = heights[0].length;

        // cell position, max abs diff so far
        PriorityQueue<int[]> pq = new PriorityQueue<>((int[] a, int[] b) ->{
            return a[1] - b[1];
        });

        int[][] dirs = {{0,1},{-1,0},{0,-1},{1,0}};

        int[][] dis = new int[n][m];
        for(int[] d : dis){
            Arrays.fill(d, (int)(1e8));
        }

        pq.add(new int[]{0,0});
        dis[0][0] = 0;

        while(pq.size() > 0){
            int[] top = pq.remove();
            int i = top[0]/m;
            int j = top[0]%m;

            int maxWeightSoFar = top[1];

            if(i==n-1 && j==m-1) return maxWeightSoFar;

            if(dis[i][j] < maxWeightSoFar) continue;

            for(int[] dir: dirs){
                int x = i + dir[0];
                int y = j + dir[1];

                if(x>=0 && x<n && y>=0 && y<m){
                    int currentWeight = Math.abs(heights[i][j] - heights[x][y]);

                    if(dis[x][y] > Math.max(maxWeightSoFar, currentWeight)){
                        dis[x][y] = Math.max(maxWeightSoFar, currentWeight);
                        pq.add(new int[]{x*m + y, dis[x][y]});
                    }
                }
            }
        }

        return dis[n-1][m-1];
    }

    // leetcode 1976 (Number of ways to arrive at destination)
    public int countPaths(int n, int[][] roads) {
        int mod = (int)(1e9 + 7);
        ArrayList<int[]>[] graph = new ArrayList[n];

        for(int i=0; i<n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int[] edge: roads){
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            graph[u].add(new int[]{v,w});
            graph[v].add(new int[]{u,w});
        }

        PriorityQueue<long[]> pq = new PriorityQueue<long[]>((long[] a, long[] b) ->{
            return (int)(a[1] - b[1]);
        });

        long[] dis = new long[n];
        Arrays.fill(dis,(long)(1e15) + 1);
        int[] paths = new int[n];

        dis[0] = 0;
        paths[0] = 1;
        pq.add(new long[]{0,0});

        while(pq.size() > 0){
            long[] top = pq.remove();
            int u = (int)top[0];
            long wsf = top[1];

            if(dis[u] < wsf) continue;

            for(int[] nbrVtx: graph[u]){
                int v = nbrVtx[0]; int wt = nbrVtx[1];

                if(dis[v] > dis[u] + wt){ // dis[u] == wsf;
                    dis[v] = wsf + wt;
                    paths[v] = paths[u];
                    pq.add(new long[]{v, dis[v]});
                } else if(dis[v] == dis[u] + wt){
                    paths[v] = (paths[v] + paths[u])%mod;
                }
            }
        }

        return paths[n-1];
    }
}