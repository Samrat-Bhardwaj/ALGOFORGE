import java.util.ArrayList;
import java.util.PriorityQueue;
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

class Prims {
    static class Pair {
        int vtx;
        int par;
        int wt;

        public Pair(int vtx, int par, int wt){
            this.vtx = vtx;
            this.par = par;
            this.wt = wt;
        }
    }
    // assumption is input graph is completely connected
    public static ArrayList<Edge>[] makeMST_Prims(ArrayList<Edge>[] graph, int N){
        PriorityQueue<Pair> pq = new PriorityQueue<>((Pair a, Pair b) -> {
            return a.wt - b.wt;
        });

        boolean[] vis = new boolean[N];
        ArrayList<Edge>[] mstGraph = new ArrayList[N];

        for(int i=0; i<N; i++){
            mstGraph[i] = new ArrayList<Edge>();
        }

        int edgeCount = 0;
        pq.add(new Pair(0,-1,0)); // par initially is -1;

        while(edgeCount < N-1){ // pq.size()>0
            Pair top = pq.poll();
            int vtx = top.vtx;
            int par = top.par;
            int wt = top.wt;

            if(vis[vtx] == true) continue;
            vis[vtx] = true;

            if(par != -1){
                addEdge(mstGraph, par, vtx, wt); // adding edge from parent to vtx with "wt"
                edgeCount++;
            }

            for(Edge e: graph[vtx]){
                int nbr = e.v, edgeWeight = e.w;
                if(!vis[nbr]){
                    pq.add(new Pair(nbr, vtx, edgeWeight));
                }
            }
        }

        return mstGraph;
    }

























    // Construction utils ==========================
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
        int[][] edges = {{0,1,11},{0,2,8},{2,3,5},{1,3,12},{1,5,13},{5,4,5},{5,6,9},{4,6,6},{6,7,43},{6,8,23},{7,8,19}};

        ArrayList<Edge>[] graph = new ArrayList[N];

        for(int i=0; i<N; i++){
            graph[i] = new ArrayList<>();
        }

        for(int[] e: edges){
            addEdge(graph, e[0], e[1], e[2]);
        }

        ArrayList<Edge>[] mst = makeMST_Prims(graph, N);
        displayGraph(mst);
    }
}