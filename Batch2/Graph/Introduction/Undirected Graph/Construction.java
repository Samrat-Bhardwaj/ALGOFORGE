import java.util.ArrayList;
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

class Construction {
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
            // ArrayList<Edge> edgeList = graph[i];
            // for(int j=0; j<edgeList.size(); j++){
            //     Edge e = edgeList.get(j);
            //     System.out.print(e + ",");
            // }

            System.out.println();
            // System.out.println(graph[i]);
        }
    }

    public static void main(String[] args){
        int N = 7;

        ArrayList<Edge>[] graph = new ArrayList[N];

        for(int i=0; i<N; i++){
            graph[i] = new ArrayList<>();
        }

        addEdge(graph,0,1,2);
        addEdge(graph,1,2,5);
        addEdge(graph,1,4,3);
        addEdge(graph,3,4,4);
        addEdge(graph,3,5,7);
        addEdge(graph,3,6,9);
        addEdge(graph,5,6,8);

        displayGraph(graph);
    }
}