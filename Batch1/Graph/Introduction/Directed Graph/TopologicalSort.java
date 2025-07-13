import java.util.*;
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

class TopologicalSort {
    // Topological Sort for DAG (Directed Acyclic Graph) =======================================
    public static void topo_dfs(int src,boolean[] vis,ArrayList<Edge>[] graph, int N, ArrayList<Integer> topologicalOrder){
        vis[src] = true;

        for(Edge e: graph[src]){
            int nbr = e.v;
            if(!vis[nbr]){
                topo_dfs(nbr,vis,graph,N,topologicalOrder);
            }
        }

        topologicalOrder.add(src);
    }

    public static ArrayList<Integer> topologicalSort(ArrayList<Edge>[] graph, int N){
        ArrayList<Integer> topologicalOrder = new ArrayList<>();
        boolean[] vis = new boolean[N];

        for(int i=0; i<N; i++){
            if(!vis[i]){
                topo_dfs(i,vis,graph,N,topologicalOrder);
            }
        }

        Collections.reverse(topologicalOrder);
        return topologicalOrder;
    }   

    // Topological Sort for Directed Graph =========================
    public static boolean topo_dfs_generic(int src,int[] vis,ArrayList<Edge>[] graph, int N, ArrayList<Integer> topologicalOrder){
        vis[src] = 1;

        for(Edge e: graph[src]){
            int nbr = e.v;
            if(vis[nbr] == 1){ // nbr is part of current path -> cycle presemt
                return false;
            } else if(vis[nbr] == 2){ // nbr is already visited but not part of current path
                continue;
            } else {
                if(topo_dfs_generic(nbr,vis,graph,N,topologicalOrder) == false) return false;
            }
        }

        vis[src] = 2; // all paths exploration done
        topologicalOrder.add(src);
        return true;
    }

    public static ArrayList<Integer> topologicalSort_generic(ArrayList<Edge>[] graph, int N){
        ArrayList<Integer> topologicalOrder = new ArrayList<>();
        int[] vis = new boolean[N];

        for(int i=0; i<N; i++){
            if(vis[i] == 0){
                if(topo_dfs(i,vis,graph,N,topologicalOrder) == false){
                    System.out.println("No Possible Solution"); // there is cycle, so no possible solution
                    return new ArrayList<>();
                }
            }
        }

        Collections.reverse(topologicalOrder);
        return topologicalOrder;
    }
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
    }

    public static void main(String[] args){
        int N = 12;

        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] graph = new ArrayList[N];

        for(int i=0; i<N; i++){
            graph[i] = new ArrayList<>();
        }

        addEdge(graph,5,11,-1);
        addEdge(graph,11,2,-1);
        addEdge(graph,7,11,-1);
        addEdge(graph,7,8,-1);
        addEdge(graph,3,8,-1);
        addEdge(graph,8,9,-1);
        addEdge(graph,11,10,-1);
        addEdge(graph,3,10,-1);
        addEdge(graph,11,9,-1);

        // displayGraph(graph);
        System.out.println(topologicalSort(graph,N));
    }
}