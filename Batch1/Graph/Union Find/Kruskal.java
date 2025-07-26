import java.util.ArrayList;
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

    @Override 
    public String toString(){
        return "{" + this.u + " -> " + this.v + ": " + this.w + "}";
    }
}

class Kruskal {
    static int[] par;
    static int[] size;

    public static int findPar(int u){
        if(par[u] == u) return u;

        return par[u] = findPar(par[u]);
    }

    public static void merge(int p1, int p2){
        if(size[p1] < size[p2]){
            par[p1] = p2;
            size[p2] += size[p1];
        } else {
            par[p2] = p1;
            size[p1] += size[p2];
        }
    }

    public static ArrayList<Edge>[] createMST(int[][] edges, int N){
        ArrayList<Edge>[] graph = new ArrayList[N];
        par = new int[N];
        size = new int[N];

        for(int i=0; i<N; i++){
            graph[i] = new ArrayList<>();
            par[i] = i;
            size[i] = 1;
        }

        // Kruskal
        Arrays.sort(edges,(int[] a, int[] b) -> {
            return a[2] - b[2]; // sort on the basis of weight;
        });


        for(int[] edge: edges){
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            int p1 = findPar(u);
            int p2 = findPar(v);

            if(p1 != p2){
                merge(p1,p2);
                addEdge(graph,u,v,w);
            }
        }

        return graph;
    }


    // Graph utils ========================
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

        int[][] edges = {{0,6,9},{6,2,11},{1,2,4},{0,1,8},{2,3,5},{3,4,9},{3,5,13},{4,5,7},{5,7,41},{7,8,53}};
        
        ArrayList<Edge>[] MST = createMST(edges,N);
        displayGraph(MST);
    }
}