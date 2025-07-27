import java.util.*;
import java.util.Stack;
class Edge {
    int u;
    int v;
    int w;

    public Edge(int u, int v, int w){
        this.u = u;
        this.v = v;
        this.w = w;
    }

    @Override 
    public String toString(){
        return "{" + this.u + " -> " + this.v + ": " + this.w + "}";
    }
}

class Prims {
    static class Pair {
        int par;
        int vtx;
        int wt;

        public Pair(int par, int vtx, int wt){
            this.par = par;
            this.vtx = vtx;
            this.wt = wt;
        }
    }

    public static ArrayList<Edge>[] getMST_ByPrims(ArrayList<Edge>[] graph, int N){
        ArrayList<Edge>[] mst = new ArrayList[N];
        for(int i=0; i<N; i++){
            mst[i] = new ArrayList<>();
        }

        boolean[] vis = new boolean[N];

        PriorityQueue<Pair> pq = new PriorityQueue<>((Pair a, Pair b) -> {
            return a.wt - b.wt;
        });

        pq.add(new Pair(-1,0,0));
        int edgeCount = 0;

        while(edgeCount < n-1){ // spanning tree -> (E+V)LogV
            Pair top = pq.remove();
            if(vis[top.vtx]) continue;
            vis[top.vtx] = true;

            if(top.par != -1){
                addEdge(mst,top.par,top.vtx,top.wt);
                edgeCount++;
            }

            for(Edge e: graph[top.vtx]){
                int nbr = e.v;
                if(!vis[nbr]){
                    pq.add(new Pair(top.vtx, nbr, e.w));
                }
            }
        }

        return mst;
    }

    // https://www.geeksforgeeks.org/problems/minimum-spanning-tree/1
    static int spanningTree(int V, int E, List<List<int[]>> edges) {
        boolean[] vis = new boolean[V];
        PriorityQueue<int[]> pq = new PriorityQueue<>((int[] a, int [] b) ->{
            return a[1] - b[1]; // wt
        });

        pq.add(new int[]{0,0}); // vtx, wt
        int edgeCount = 0;
        int total_mst_weight = 0;

        while(edgeCount < V - 1){
            int[] top = pq.remove();

            int vtx = top[0];
            int wt = top[1];

            if(vis[vtx]) continue;

            vis[vtx] = true;
            total_mst_weight += wt;

            if(vtx != 0){
                edgeCount++;
            }
            
            for(int[] nbr: edges.get(vtx)){
                int v = nbr[0], w = nbr[1];
                if(!vis[v]){
                    pq.add(new int[]{v,w});
                }
            }
        }

        return total_mst_weight;
    }













    // utils ==========================================
    public static void displayGraph(ArrayList<Edge>[] graph){
        for(int i=0; i<graph.length; i++){
            System.out.print("Edges on vertex " + i + " -> ");
            for(Edge e: graph[i]){
                System.out.print(e +",");
            }
            System.out.println();
        }
    }

    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w){
        graph[u].add(new Edge(u,v,w));
        graph[v].add(new Edge(v,u,w));
    }

    public static void main(String[] args){
        int N = 9;

        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] graph = new ArrayList[N];
        

        int[][] edges = {{0,6,9},{6,2,11},{1,2,4},{0,1,8},{2,3,5},{3,4,9},{3,5,13},{4,5,7},{5,7,41},{7,8,53}};
        

        for(int i=0; i<N; i++){
            graph[i] = new ArrayList<>();
        }

        for(int[] edge: edges){
            addEdge(graph, edge[0], edge[1], edge[2]);
        }

        ArrayList<Edge>[] mst = getMST_ByPrims(graph,N);
        displayGraph(mst);
    }
}