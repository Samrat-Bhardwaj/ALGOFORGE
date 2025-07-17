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

class Kahns {
    // Topological Sort for DG (Directed Graph) (Kahns Algorithm) =======================================
    public static ArrayList<Integer> topo_bfs(ArrayList<Edge>[] graph, int N){
        int[] indegree = new int[N];

        for(int i=0; i<N; i++){
            for(Edge e: graph[i]){
                int u = e.u;
                int v = e.v;
                indegree[v]++;
            }
        }

        LinkedList<Integer> que = new LinkedList<>();
        ArrayList<Integer> topologicalOrder = new ArrayList<>();
        for(int u=0; u<N; u++){
            if(indegree[u] == 0){
                topologicalOrder.add(u);
                que.addLast(u); // vertex with no dependency
            }
        }   

        while(que.size() > 0){
            int u = que.removeFirst();

            for(Edge e: graph[u]){
                int nbr = e.v;
                indegree[nbr]--;
                if(indegree[nbr] == 0){
                    topologicalOrder.add(nbr);
                    que.addLast(nbr);
                }
            }
        }

        if(topologicalOrder.size() != N){
            System.out.println("There is a cycle");
        }

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
        System.out.println(topo_bfs(graph,N));
    }
}