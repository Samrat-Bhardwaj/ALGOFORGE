import java.util.ArrayList;
import java.util.LinkedList;
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


class KahnsAlgorithm {
    public static ArrayList<Integer> findTopologicalOrder(ArrayList<Edge>[] graph, int N){
        int[] indegree = new int[N];

        for(int u=0; u<N; u++){
            for(Edge e: graph[u]){
                int v = e.v;
                indegree[v]++;
            }
        }

        LinkedList<Integer> que = new LinkedList<>();
        ArrayList<Integer> topologicalOrder = new ArrayList<>();

        for(int i=0; i<N; i++){
            if(indegree[i] == 0){
                que.addLast(i);
            }
        }

        while(que.size() > 0){
            int u = que.removeFirst();

            topologicalOrder.add(u);

            for(Edge e: graph[u]){
                int v = e.v;
                indegree[v]--;

                if(indegree[v] == 0){ // all the U for this V (u->v) is added in the answer
                    que.addLast(v);
                }
            }
        }

        if(topologicalOrder.size() != N){
            System.out.println("No topological order possible, cycle is there");
            return new ArrayList<>();
        }

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


        System.out.println(findTopologicalOrder(graph,N));
    }
}