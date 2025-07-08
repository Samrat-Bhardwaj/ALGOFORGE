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

    @Override 
    public String toString(){
        return "{" + this.u + " -> " + this.v + ": " + this.w + "}";
    }
}

class Questions {
    // hasPath =====================================
    public static boolean hasPath_rec(int src, int des, ArrayList<Edge>[] graph, boolean[] vis){
        if(src == des){
            return true;
        }

        vis[src] = true;

        boolean hasPath = false;

        for(Edge e: graph[src]){
            int nbr = e.v;
            if(vis[nbr] == false){
                hasPath = hasPath || hasPath_rec(nbr, des, graph, vis);
            }   
        }

        // vis[src] = false; // not necessory as undirected 
        return hasPath;
    }

    public static boolean hasPath(int src, int des, ArrayList<Edge>[] graph, int N){
        boolean[] vis = new boolean[N];
        return hasPath_rec(src, des, graph, vis);
    }


    // Print all paths between source and dest ======================================
    public static void allPaths(int src, int des, ArrayList<Edge>[] graph, String psf,boolean[] vis){
        if(src == des){
            System.out.println(psf);
            return;
        }
        vis[src] = true;

        for(Edge e: graph[src]){
            int nbr = e.v;
            if(!vis[nbr]){
                allPaths(nbr, des, graph, psf + "->" + nbr, vis);
            }
        }

        vis[src] = false;
    }

    public static void printAllPaths(int src, int des, ArrayList<Edge>[] graph, int N){
        allPaths(src,des,graph,""+src,new boolean[N]);
    }



























    // Graph construction utils =====================================================
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
        int N = 8;

        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] graph = new ArrayList[N];

        for(int i=0; i<N; i++){
            graph[i] = new ArrayList<>();
        }

        addEdge(graph,0,1,10);
        addEdge(graph,1,2,11);
        addEdge(graph,1,3,7);
        addEdge(graph,1,4,12);
        addEdge(graph,2,4,9);
        addEdge(graph,2,5,3);
        addEdge(graph,5,6,6);
        addEdge(graph,5,7,5);
        addEdge(graph,6,7,4);

        // displayGraph(graph);
        // System.out.println(hasPath(0,7, graph, N));
        printAllPaths(0,7, graph, N);
    }
}