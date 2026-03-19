class Questions {
    // leetcode 743 =============================
    public ArrayList<int[]>[] makeGraph(int N, int[][] edges){
        ArrayList<int[]>[] graph = new ArrayList[N+1]; // 1-indexed

        for(int i=0; i<=N; i++){
            graph[i] = new ArrayList<>();
        }

        for(int[] edge: edges){
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            graph[u].add(new int[]{v,w});
        }

        return graph;
    }
    
    public int networkDelayTime(int[][] times, int n, int k) {
        ArrayList<int[]>[] graph = makeGraph(n, times);

        int[] dis = new int[n+1];
        Arrays.fill(dis, (int)(1e8));

        // {vtx, wsf};
        PriorityQueue<int[]> pq = new PriorityQueue<>((int[] a,int[] b)->{
            return a[1] - b[1];
        });

        dis[k] = 0;
        pq.add(new int[]{k,0});

        while(pq.size() > 0){
            int[] top = pq.remove();

            int u = top[0];
            int wsf = top[1];

            if(dis[u] < wsf) continue;

            for(int[] edge: graph[u]){
                int v = edge[0]; int wt = edge[1];

                if(dis[v] > dis[u] + wt){
                    dis[v] = dis[u] + wt;
                    pq.add(new int[]{v,dis[v]});
                }
            }
        }

        int minTime = Integer.MIN_VALUE;
        for(int i=1; i<=n; i++){ // 1-indexed
            int d = dis[i];
            if(d == (int)(1e8)) return -1;
            minTime = Math.max(minTime, d);
        }

        return minTime;
    }

    // leetcode 1928 (Dijkstra with two dis array) ===============
    class Solution {
        class Pair {
            int vtx;
            int timeSoFar;
            int feesSoFar;

            public Pair(int vtx, int timeSoFar, int feesSoFar){
                this.vtx = vtx;
                this.timeSoFar = timeSoFar;
                this.feesSoFar = feesSoFar;
            }
        }

        public ArrayList<int[]>[] makeGraph(int N, int[][] edges){
            ArrayList<int[]>[] graph = new ArrayList[N];

            for(int i=0; i<N; i++){
                graph[i] = new ArrayList<>();
            }

            for(int[] edge: edges){
                int u = edge[0];
                int v = edge[1];
                int w = edge[2];

                graph[u].add(new int[]{v,w});
                graph[v].add(new int[]{u,w});
            }

            return graph;
        }

        public int minCost(int maxTime, int[][] edges, int[] passingFees) {
            PriorityQueue<Pair> pq = new PriorityQueue<>((Pair a, Pair b)->{
                if(a.feesSoFar == b.feesSoFar){
                    return a.timeSoFar - b.timeSoFar;
                }

                return a.feesSoFar - b.feesSoFar;
            });

            int n = passingFees.length;
            ArrayList<int[]>[] graph = makeGraph(n,edges);

            int[] timeCost = new int[n];
            int[] feesCost = new int[n];

            Arrays.fill(timeCost, (int)(1e8));
            Arrays.fill(feesCost, (int)(1e8));

            pq.add(new Pair(0,0,passingFees[0]));
            timeCost[0] = 0;
            feesCost[0] = passingFees[0];

            while(pq.size() > 0){
                Pair top = pq.remove();
                int vtx = top.vtx;
                int feesSoFar = top.feesSoFar;
                int timeSoFar = top.timeSoFar;

                // we should explore path with worse feesCost, why? => To explore paths with better timeCost
                // if(feesCost[vtx] < feesSoFar) continue;

                for(int[] edge: graph[vtx]){
                    int nbr = edge[0], edgeTime = edge[1], travellingFees = passingFees[nbr];

                    if(timeSoFar + edgeTime <= maxTime){
                        if(feesCost[nbr] > feesSoFar + travellingFees){
                            feesCost[nbr] = feesSoFar + travellingFees;
                            timeCost[nbr] = timeSoFar + edgeTime;
                            pq.add(new Pair(nbr,timeCost[nbr],feesCost[nbr]));
                        } else if(timeCost[nbr] > timeSoFar + edgeTime){
                            timeCost[nbr] = timeSoFar + edgeTime;
                            pq.add(new Pair(nbr,timeCost[nbr],feesSoFar + travellingFees));
                        }
                    }
                }
            }

            return feesCost[n-1] == (int)(1e8) ? -1 : feesCost[n-1];
        }
    }

    // Leetcode 1631 (Path with minimum effort (minimum absolute diff))
    public int minimumEffortPath(int[][] heights) {
        int n = heights.length;
        int m = heights[0].length;

        int[][] dis = new int[n][m];
        for(int[] d: dis){
            Arrays.fill(d, (int)(1e8));
        }
        // {vtx, abs-diff}
        PriorityQueue<int[]> pq = new PriorityQueue<>((int[] a, int[] b)->{
            return a[1] - b[1];
        });
        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};

        dis[0][0] = 0;
        pq.add(new int[]{0,0});

        while(pq.size() > 0){
            int[] top = pq.remove();
            int vtx = top[0];
            int maxAbsSoFar = top[1];

            int i = vtx/m;
            int j = vtx%m;

            // valid only because no negative weight in this graph
            if(i == n-1 && j == m-1) return maxAbsSoFar;

            if(dis[i][j] < maxAbsSoFar) continue;
            
            for(int[] dir: dirs){
                int x = i + dir[0];
                int y = j + dir[1];

                if(x>=0 && x<n && y>=0 && y<m){
                    int edgeWeight = Math.abs(heights[i][j] - heights[x][y]);

                    if(dis[x][y] > Math.max(maxAbsSoFar,edgeWeight)){
                        dis[x][y] = Math.max(maxAbsSoFar,edgeWeight);
                        pq.add(new int[]{x*m+y, dis[x][y]});
                    }
                }
            }
        }

        return dis[n-1][m-1];
    }

    // Leetcode 1976 (Number of ways to reach n-1 with minimum cost)
    class Solution {
        public ArrayList<int[]>[] makeGraph(int N, int[][] edges){
            ArrayList<int[]>[] graph = new ArrayList[N];
            
            for(int i=0; i<N; i++){
                graph[i] = new ArrayList<>();
            }

            for(int[] edge: edges){
                int u = edge[0];
                int v = edge[1];
                int w = edge[2];

                graph[u].add(new int[]{v,w});
                graph[v].add(new int[]{u,w});
            }

            return graph;
        }

        int mod = (int)(1e9) + 7;
        public int countPaths(int n, int[][] roads) {
            ArrayList<int[]>[] graph = makeGraph(n, roads);
            // {vtx, dis}
            PriorityQueue<long[]> pq = new PriorityQueue<long[]>((long[] a, long[] b)->{
                return (int)a[1] - (int)b[1];
            });
            
            long[] dis = new long[n];
            int[] paths = new int[n];

            Arrays.fill(dis, Long.MAX_VALUE);
            Arrays.fill(paths, 0);

            dis[0] = 0;
            paths[0] = 1;

            pq.add(new long[]{0,0});

            while(pq.size() > 0){
                long[] top = pq.poll();
                int u = (int)top[0];
                long wsf = top[1];

                if(dis[u] < wsf) continue;

                for(int[] edge: graph[u]){
                    int v = edge[0], wt = edge[1];

                    if(dis[v] > dis[u] + wt){
                        dis[v] = dis[u] + wt;
                        paths[v] = paths[u] % mod;
                        pq.add(new long[]{v,dis[v]});
                    } else if(dis[v] == dis[u] + wt){
                        paths[v] = (paths[v] + paths[u])%mod;
                    }
                }
            }

            return paths[n-1];
        }
    }

    // Leetcode 787 (Cheapest flight within k stop (Bellman ford)) ======
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int dis = new int[n];
        Arrays.fill(dis,(int)(1e8));

        dis[src] = 0;

        // k stops => k+1 iterations
        for(int i=1; i<= k+1; i++){
            int[] ndis = Arrays.copyOf(dis,n);

            for(int[] edge: flights){
                int u = edge[0];
                int v = edge[1];
                int w = edge[2];

                if(dis[u] + w < ndis[v]){
                    ndis[v] = dis[u] + w;
                }
            }

            dis = ndis;
        }

        return dis[dst] == (int)(1e8) ? -1 : dis[dst];
    }

    // Leetcode 778 (Swin in rising water)
    public int swimInWater(int[][] grid) {
        int n = grid.length;

        PriorityQueue<int[]> pq = new PriorityQueue<>((int[] a, int[] b)->{
            return a[1] - b[1];
        });
        boolean[][] vis = new boolean[n][n]; // no need of dis, we can't get a better answer if we traverse twice on a same cell, hence will visit only once
        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};

        pq.add(new int[]{0,grid[0][0]});
        vis[0][0] = true;

        while(pq.size() > 0){
            int[] top = pq.remove();
            int vtx = top[0];
            int maxSoFar = top[1];

            int i = vtx/n;
            int j = vtx%n;

            if(i == n-1 && j==n-1){
                return maxSoFar;
            }

            for(int[] dir: dirs){
                int x = i + dir[0];
                int y = j + dir[1];

                if(x>=0 && y>=0 && x<n && y<n && !vis[x][y]){
                    vis[x][y] = true;

                    int cost = Math.max(maxSoFar, grid[x][y]);
                    pq.add(new int[]{x*n + y, cost});
                }
            }
        }

        return -1;
    }
}