import java.util.ArrayList;
import java.util.Collections;
class Edge {
    int u;
    int v;

    public Edge(int u, int v){
        this.u = u;
        this.v = v;
    }

    public String toString(){
        return "(" + this.u + "->" + this.v + ")";
    }
}

class TopologicalSort {
    public static boolean topo_dfs_isCycle(int src, ArrayList<Edge>[] graph, ArrayList<Integer> topologicalOrder, int[] vis){
        vis[src] = 1;

        for(Edge e: graph[src]){
            int nbr = e.v;

            if(vis[nbr] == 1){
                return true;
            } else if(vis[nbr] == 2){
                continue;
            } else {
                if(topo_dfs_isCycle(nbr,graph,topologicalOrder,vis)) return true;
            }
        }

        vis[src] = 2;
        topologicalOrder.add(src);

        return false;
    }

    public static ArrayList<Integer> findTopologicalOrder_generic(ArrayList<Edge>[] graph, int N){
        int[] vis = new int[N];

        ArrayList<Integer> topologicalOrder = new ArrayList<>();

        for(int i=0; i<N; i++){
            if(vis[i] == 0){
                if(topo_dfs_isCycle(i,graph,topologicalOrder, vis)){ // if cycle
                    System.out.println("No Topological order possible");
                    return new ArrayList<>();
                }
            }
        }

        Collections.reverse(topologicalOrder);
        return topologicalOrder;
    }



    public static void topo_dfs(int src, ArrayList<Edge>[] graph, ArrayList<Integer> topologicalOrder, boolean[] vis){
        vis[src] = true;

        for(Edge e: graph[src]){
            int nbr = e.v;
            if(!vis[nbr]){
                topo_dfs(nbr,graph, topologicalOrder, vis);
            }
        }

        // once every v of edge (u->v) is added, then we can add u  
        topologicalOrder.add(src);
    }

    public static ArrayList<Integer> findTopologicalOrder(ArrayList<Edge>[] graph, int N){
        boolean[] vis = new boolean[N];

        ArrayList<Integer> topologicalOrder = new ArrayList<>();

        for(int i=0; i<N; i++){
            if(!vis[i]){
                topo_dfs(i,graph,topologicalOrder, vis);
            }
        }

        Collections.reverse(topologicalOrder);
        return topologicalOrder;
    }


    // construction utils ====================================
    public static void addEdge(ArrayList<Edge>[] graph, int u, int v){
        graph[u].add(new Edge(u,v));
    }

    public static void main(String[] args){
        int N = 12;

        ArrayList<Edge>[] graph = new ArrayList[N];

        for(int i=0; i<N; i++){
            graph[i] = new ArrayList<>();
        }

        addEdge(graph,5,11);
        addEdge(graph,11,2);
        addEdge(graph,7,11);
        addEdge(graph,7,8);
        addEdge(graph,3,8);
        addEdge(graph,11,2);
        // addEdge(graph,2,9); // cycle add

        addEdge(graph,11,9);
        // addEdge(graph,9,11); // cycle add

        addEdge(graph,11,10);
        addEdge(graph,8,9);
        addEdge(graph,3,10);


        System.out.println(findTopologicalOrder_generic(graph,N));
    }
}