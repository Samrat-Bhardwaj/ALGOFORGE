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

class Introduction {
    static String longestPath;
    static int longestPathWeight;
    static String shortestPath;
    static int shortestPathWeight;
    // PriorityQueue<Pair<path,weight>> pq;

    public static void traverseAllPaths(int src, int des, ArrayList<Edge>[] graph, boolean[] vis, String psf, int wsf){
        if(src == des){
            if(wsf < shortestPathWeight){
                shortestPathWeight = wsf;
                shortestPath = psf;
            }

            if(wsf > longestPathWeight){
                longestPathWeight = wsf;
                longestPath = psf;
            }

            // pd.add(new Pair(psf,wsf));
            // if(pq.size() > k){
            //     pq.pop();
            // }
            return;
        }

        vis[src] = true;

        for(Edge e: graph[src]){
            int nbr = e.v;
            int wt = e.w;

            if(!vis[nbr]){
                traverseAllPaths(nbr,des,graph,vis,psf+"->"+nbr,wsf+wt);
            }
        }

        vis[src] = false;
    }
    
    public static void printAllPaths(int src, int des, ArrayList<Edge>[] graph, boolean[] vis, String psf){
        if(src == des){
            System.out.println(psf);
            return;
        }

        vis[src] = true;

        for(Edge e: graph[src]){
            int nbr = e.v;

            if(!vis[nbr]){
                printAllPaths(nbr,des,graph,vis,psf+"->"+nbr);
            }
        }

        vis[src] = false;
    }

    public static void allPaths(int src, int des, ArrayList<Edge>[] graph, int N){
        boolean[] vis = new boolean[N];
        printAllPaths(src,des,graph,vis,""+src);
    }

    public static boolean hasPath_rec(int src, int des, ArrayList<Edge>[] graph, boolean[] vis){
        if(src == des){
            return true;
        }

        vis[src] = true;

        boolean ans = false;
        for(Edge e: graph[src]){
            int nbr = e.v;
            if(vis[nbr] == false){
                ans = ans || hasPath_rec(nbr,des,graph,vis);
            }
        }

        return ans;
    }

    public static boolean hasPath(int src, int des, ArrayList<Edge>[] graph, int N){
        boolean[] vis = new boolean[N];
        return hasPath_rec(src,des,graph,vis);
    }






    // Construction utils
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
        addEdge(graph,2,3,7);
        addEdge(graph,1,4,3);
        addEdge(graph,3,4,4);
        addEdge(graph,3,5,7);
        addEdge(graph,3,6,9);
        addEdge(graph,5,6,8);

        // displayGraph(graph);
        // System.out.println(hasPath(0,5,graph,N));
        allPaths(0,5,graph,N);
    }
}