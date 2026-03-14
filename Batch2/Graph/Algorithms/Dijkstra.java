import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Arrays;
class Edge {
    int u;
    int v;
    int w;

    public Edge(int u, int v, int w){
        this.u = u;
        this.v = v;
        this.w = w;
    }

    public String toString(){
        return "(" + this.u + "->" + this.v + ":" + this.w + ")";
    }
}
class Dijkstra {

    static class Pair {
        int vtx;
        int par;
        int wsf;

        public Pair(int vtx, int par, int wsf){
            this.vtx = vtx;
            this.par = par;
            this.wsf = wsf;
        }
    }

    public static int[] findMinDis_Dijkstra(int src, ArrayList<Edge>[] graph, int N){
        PriorityQueue<Pair> pq = new PriorityQueue<>((Pair a, Pair b) -> {
            return a.wsf - b.wsf;
        });

        int[] dis = new int[N];
        int[] parent = new int[N];

        Arrays.fill(dis,(int)(1e8)); 
        Arrays.fill(parent,-1);

        dis[src] = 0;
        pq.add(new Pair(src,-1,0));
    
        while(pq.size() > 0){
            Pair top = pq.remove();
            int u = top.vtx;
            int par = top.par;
            int wsf = top.wsf;

            if(dis[u] < wsf) continue;

            if(par != -1){
                parent[u] = par;
            }

            for(Edge e: graph[u]){
                int v = e.v; int wt = e.w;

                if(dis[v] > wsf + wt){ // dis[v] > dis[u] + wt
                    dis[v] = wsf + wt;
                    pq.add(new Pair(v,u,dis[v]));
                }
            }
        }

        return dis;
    }

































    // Construction utils
    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w){
        graph[u].add(new Edge(u,v,w));
        graph[v].add(new Edge(v,u,w));
    }

    public static void displayGraph(ArrayList<Edge>[] graph){
        for(int i=0; i<graph.length; i++){
            System.out.print("Edges coming out of " + i + ": ");

            for(Edge e: graph[i]){
                System.out.print(e + ",");
            }
            
            System.out.println();
        }
    }

    public static void main(String[] args){
        int N = 9;
        int[][] edges = {{0,1,11},{0,2,8},{2,3,5},{1,3,12},{1,5,13},{5,4,2},{5,6,9},{4,6,6},{6,7,43},{6,8,23},{7,8,19}};

        ArrayList<Edge>[] graph = new ArrayList[N];

        for(int i=0; i<N; i++){
            graph[i] = new ArrayList<>();
        }

        for(int[] e: edges){
            addEdge(graph, e[0], e[1], e[2]);
        }

        int src = 0;
        int[] dis = findMinDis_Dijkstra(src, graph, N);
        for(int i=0; i<N; i++){
            System.out.println("Minimum distance from " + src + " to " + i + " is " + dis[i]);
        }
    }
}