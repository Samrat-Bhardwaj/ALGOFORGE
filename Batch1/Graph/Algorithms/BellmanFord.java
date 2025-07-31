import java.util.*;

class BellmanFord {
    public static int[] findShortestDistance(int src, int[][] edges, int N){
        int[] dis = new int[N];
        Arrays.fill(dis, (int)(1e8));

        dis[src] = 0;

        boolean isNegativeCycle = false;

        for(int i=1; i<=N; i++){
            boolean isAnyUpdate = false;
            
            int[] ndis = Arrays.copyOf(dis, N);

            for(int[] edge: edges){
                int u = edge[0];
                int v = edge[1];
                int w = edge[2];

                if(ndis[v] > dis[u] + w){
                    ndis[v] = dis[u] + w;
                    isAnyUpdate = true;
                }
            }

            // if we couldn't find a better path in this iteration, we won't find better in next
            if(isAnyUpdate == false){ 
                break;
            }

            if(i==N && isAnyUpdate){
                isNegativeCycle = true;
            }

            dis = ndis;
        }

        if(isNegativeCycle){
            System.out.println("There is no correct answer for this graph");
        }

        return dis;
    }

    public static void main(String[] args){
        int N = 7;
        int[][] edges = {{0,1,5},{0,6,4},{1,2,6},{6,2,1},{2,4,7},{2,3,3},{3,4,3},{4,5,10}};
        // int[][] edges = {{0,1,5},{0,6,4},{1,2,6},{6,2,1},{4,2,-7},{2,3,3},{3,4,3},{4,5,10}}; // -> negative cycle
        int src = 0;
        int[] dis = findShortestDistance(src,edges,N);

        for(int i=0; i<dis.length; i++){
            System.out.println("Distance from " + src + " to " + i + " is " + dis[i]);
        }
    }
}