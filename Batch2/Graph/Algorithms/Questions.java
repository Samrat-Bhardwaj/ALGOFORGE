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
}