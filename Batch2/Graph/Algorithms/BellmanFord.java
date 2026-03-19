import java.util.Arrays;
class BellmanFord {

    public static int[] findMinDis_BellmanFord(int src, int[][] edges, int N){
        int[] dis = new int[N];
        Arrays.fill(dis, (int)(1e8));
        dis[src] = 0;
        
        boolean isNegativeCycle = false;

        for(int i=1; i<=N; i++){
            boolean isAnyUpdate = false;

            int[] ndis = Arrays.copyOf(dis, N); // new distance array

            for(int[] edge: edges){
                int u = edge[0];
                int v = edge[1];
                int w = edge[2];

                if(dis[u] + w < ndis[v]){
                    ndis[v] = dis[u] + w;
                    isAnyUpdate = true;
                }
            }
            dis = ndis;
            // if there is no update, we can stop traversing on edges again and again
            if(isAnyUpdate == false){
                break;
            }

            // if even after N iterations, there is a better path, then there is a negative cycle
            if(i==N && isAnyUpdate){
                isNegativeCycle = true;
            }
        }

        if(isNegativeCycle){
            System.out.println("No possible solution, there is a negative cycle!!!");
            return new int[]{};
        }

        return dis;
    }

    public static void main(String[] args){
        int N = 7;
        int src = 0;
        int[][] edges = {{0,1,2},{1,2,9},{2,3,3},{0,3,45},{2,5,7},{2,6,5},{5,6,-3},{5,4,8},{4,6,-13}};
        int[] dis = findMinDis_BellmanFord(src,edges,N);

        for(int i=0; i<N; i++){
            System.out.println("Minimum distance from " + src + " to " + i + " is " + dis[i]);
        }
    }
}