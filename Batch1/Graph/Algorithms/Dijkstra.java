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

class Dijkstra {
    static class Pair {
        int par;
        int vtx;
        int wsf;

        public Pair(int par, int vtx, int wsf){
            this.par = par;
            this.vtx = vtx;
            this.wsf = wsf;
        }
    }

    public static int[] dijkstra(ArrayList<Edge>[] graph, int N, int src){
        int[] dis = new int[N];
        Arrays.fill(dis, (int)(1e8));
        dis[src] = 0;

        int[] parent = new int[N];
        Arrays.fill(parent, -1);

        PriorityQueue<Pair> pq = new PriorityQueue<>((Pair a, Pair b) ->{
            return a.wsf - b.wsf;
        });

        pq.add(new Pair(-1,src,0));

        while(pq.size() > 0){
            Pair top = pq.remove();
            int par = top.par;
            int vtx = top.vtx;
            int wsf = top.wsf;

            if(dis[vtx] < wsf) continue;
            if(par != -1){
                parent[vtx] = par; // can be used to find the optimum path from src
            }

            for(Edge e: graph[vtx]){
                int nbr = e.v; int wt = e.w;

                if(dis[nbr] > wsf + wt){
                    dis[nbr] = wsf + wt;
                    pq.add(new Pair(vtx,nbr,wsf+wt));
                }
            }
        }

        return dis;
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
        int src = 0;
        int[] dis = dijkstra(graph, N, src);
        for(int i=0; i<dis.length; i++){
            System.out.println("Distance from " + src + " to " + i + " is " + dis[i]);
        }
    }
}