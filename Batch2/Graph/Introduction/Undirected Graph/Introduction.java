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

    // total number of connected components
    public static void dfs(int src, ArrayList<Edge>[] graph, boolean[] vis){
        vis[src] = true;

        for(Edge e: graph[src]){
            int nbr = e.v;

            if(!vis[nbr]){
                dfs(nbr,graph,vis);
            }
        }
    } 

    public static int getConnectedComponents(ArrayList<Edge>[] graph, int N){
        int numberOfConnectedComponents = 0;
        boolean[] vis = new boolean[N];

        for(int i=0; i<N; i++){
            if(vis[i] == false){
                dfs(i,graph,vis);

                numberOfConnectedComponents++;
            }
        }

        return numberOfConnectedComponents;
    }

    // Print all hamiltonian path and cycle starting from src
    public static boolean checkIfEdge(int a, int b, ArrayList<Edge>[] graph){
        for(Edge e: graph[a]){
            int nbr = e.v;

            if(nbr == b){
                return true;
            }
        }

        return false;
    }

    public static void traverseDFS(int src, int osrc, int edgesVisited, ArrayList<Edge>[] graph, boolean[] vis, int N, String psf){
        if(edgesVisited == N-1){
            if(checkIfEdge(src,osrc,graph)){
                System.out.println("Hamiltonian Cycle is: " + psf);
            } else {
                System.out.println("Hamiltonian Path is: " + psf);
            }
            return;
        }

        vis[src] = true;

        for(Edge e: graph[src]){
            int nbr = e.v;

            if(!vis[nbr]){
                traverseDFS(nbr, osrc, edgesVisited + 1, graph, vis, N, psf + "->" + nbr);
            }
        }

        vis[src] = false;
    }

    public static void printHamiltonianPaths(int src, ArrayList<Edge>[] graph, int N){
        boolean[] vis = new boolean[N];

        traverseDFS(src, src, 0, graph, vis, N, "" + src);
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
        int N = 8;

        ArrayList<Edge>[] graph = new ArrayList[N];

        for(int i=0; i<N; i++){
            graph[i] = new ArrayList<>();
        }

        // addEdge(graph,0,1,2);
        // addEdge(graph,1,2,5);
        // addEdge(graph,2,3,7);
        // addEdge(graph,1,4,3);
        // addEdge(graph,3,4,4);
        // addEdge(graph,3,5,7);
        // addEdge(graph,3,6,9);
        // addEdge(graph,5,6,8);

        // // displayGraph(graph);
        // // System.out.println(hasPath(0,5,graph,N));
        // allPaths(0,5,graph,N);


        // for hamiltonian question
        addEdge(graph,0,1,0);
        addEdge(graph,0,2,0);
        addEdge(graph,1,3,0);
        addEdge(graph,2,3,0);
        addEdge(graph,2,4,0);
        addEdge(graph,4,6,0);
        // addEdge(graph,4,5,0);
        addEdge(graph,3,5,0);
        addEdge(graph,5,7,0);
        addEdge(graph,6,7,0);

        printHamiltonianPaths(0,graph,N);

    }
}