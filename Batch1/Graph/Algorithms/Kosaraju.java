import java.util.ArrayList;
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

class Kosaraju {
    // number of strongly connected components, assuming there is only one connected component
    public static void dfs_fill_stack(int src,boolean[] vis,ArrayList<Edge>[] graph,Stack<Integer> st){
        vis[src] = true;

        for(Edge e: graph[src]){
            int nbr = e.v;
            if(!vis[nbr]){
                dfs_fill_stack(nbr,vis,graph,st);
            }
        }

        st.push(src);
    }
    // just traverse through the component
    public static void dfs(int src, boolean[] vis,ArrayList<Edge>[] reversedGraph){
        vis[src] = true;
        System.out.print(src+ " , ");

        for(Edge e: reversedGraph[src]){
            int nbr = e.v;
            if(!vis[nbr]){
                dfs(nbr,vis,reversedGraph);
            }
        }
    }

    public static int findSCC(ArrayList<Edge>[] graph, int N){
        Stack<Integer> st = new Stack<>();
        boolean[] vis = new boolean[N];
        
        dfs_fill_stack(5,vis,graph,st);

        // reverse the graph
        ArrayList<Edge>[] reversedGraph = new ArrayList[N];
        for(int i=0; i<N; i++){
            reversedGraph[i] = new ArrayList<>();
        }

        for(int vtx=0; vtx<N; vtx++){
            for(Edge e: graph[vtx]){
                int u = e.u;
                int v = e.v;
                int w = e.w;

                addEdge(reversedGraph,v,u,w); // reversed the edge
            }
        }

        int num_scc = 0;
        vis = new boolean[N];
        while(st.size()>0){
            int vtx = st.pop();
            if(vis[vtx] == false){
                num_scc++;
                System.out.print("SCC is: ");
                dfs(vtx,vis,reversedGraph);
                System.out.println();
            }
        }

        return num_scc;
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
    }

    public static void main(String[] args){
        int N = 12;

        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] graph = new ArrayList[N];

        for(int i=0; i<N; i++){
            graph[i] = new ArrayList<>();
        }

        addEdge(graph,5,0,10);
        addEdge(graph,0,1,11);
        addEdge(graph,1,2,7);
        addEdge(graph,2,3,12);
        addEdge(graph,3,0,9);
        addEdge(graph,2,4,3);
        addEdge(graph,4,6,6);
        addEdge(graph,6,7,-1);
        addEdge(graph,7,4,-1);
        addEdge(graph,7,8,-1);
        addEdge(graph,8,9,-1);
        addEdge(graph,9,10,-1);
        addEdge(graph,8,11,-1);
        addEdge(graph,11,10,-1);


        // displayGraph(graph);
        System.out.println(findSCC(graph,N));
    }
}