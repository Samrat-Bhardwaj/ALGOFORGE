import java.util.ArrayList;
import java.util.PriorityQueue;
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

    // Multisolver ================================================================
    static class Pair {
        int pathWeight;
        String path;

        public Pair(int pathWeight, String path){
            this.pathWeight = pathWeight;
            this.path = path;
        }
    }

    static String shortestPath = "";
    static int shortestPathWt = Integer.MAX_VALUE;
    static String longestPath = "";
    static int longestPathWt = Integer.MIN_VALUE;
    static String ceilPath = "";
    static int ceilPathWt = Integer.MAX_VALUE;
    static String floorPath = "";
    static int floorPathWt = Integer.MIN_VALUE;
    public static void traverseAllPaths(int src,int des,String currPath,int currentPathWt, boolean[] vis,ArrayList<Edge>[] graph,PriorityQueue<Pair> pq,int targetWt,int k){
        if(src == des){
            if(currentPathWt < shortestPathWt){
                shortestPathWt  = currentPathWt;
                shortestPath = currPath;
            }
            if(currentPathWt > longestPathWt){
                longestPathWt = currentPathWt;
                longestPath = currPath;
            }
            // ceilPath
            if(currentPathWt >= targetWt && currentPathWt < ceilPathWt){
                ceilPathWt = currentPathWt;
                ceilPath = currPath;
            }
            //floor
            if(currentPathWt <= targetWt && currentPathWt > floorPathWt){
                floorPathWt = currentPathWt;
                floorPath = currPath;
            }

            pq.add(new Pair(currentPathWt, currPath));
            if(pq.size() > k){
                pq.remove();
            }
        }

        vis[src] = true;

        for(Edge e: graph[src]){
            int nbr = e.v;
            int edgeWt = e.w;
            if(!vis[nbr]){
                traverseAllPaths(nbr, des , currPath+"->"+nbr ,currentPathWt+edgeWt, vis, graph,pq,targetWt,k);
            }
        }

        vis[src] = false;
    }

    public static void multisolver(int src, int des, int k, int targetWt, ArrayList<Edge>[] graph, int N){
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>((Pair a, Pair b)->{
            return a.pathWeight - b.pathWeight;
        });

        traverseAllPaths(src,des,""+src,0,new boolean[N],graph,pq,targetWt,k);

        System.out.println("Shortest path is " + shortestPath + " with weight " + shortestPathWt);
        System.out.println("Longest path is " + longestPath + " with weight " + longestPathWt);
        System.out.println("Ceil path is " + ceilPath + " with weight " + ceilPathWt);
        System.out.println("Floor path is " + floorPath + " with weight " + floorPathWt);
        if(pq.size() > 0)
            System.out.println("Kth longest path is " + pq.peek().path + " with weight " + pq.peek().pathWeight);
    }

    // get connected components 
    public static void dfs(int vtx, boolean[] vis,ArrayList<Integer> currComponent,ArrayList<Edge>[] graph){
        vis[vtx] = true;
        currComponent.add(vtx);

        for(Edge e: graph[vtx]){
            int nbr = e.v;

            if(!vis[nbr]){
                dfs(nbr,vis,currComponent,graph);
            }
        }
    }

    public static ArrayList<ArrayList<Integer>> getConnectedComponents(ArrayList<Edge>[] graph, int N){
        ArrayList<ArrayList<Integer>> components = new ArrayList<>();
        boolean[] vis = new boolean[N];

        for(int vertex=0; vertex<N; vertex++){
            if(vis[vertex] == false){
                ArrayList<Integer> currComponent = new ArrayList<>();
                dfs(vertex,vis,currComponent,graph);

                components.add(currComponent);
            }
        }   

        return components;
    }

    // check if the whole graph is connected
    public static void dfs_gcc(int vtx, boolean[] vis,ArrayList<Edge>[] graph){
        vis[vtx] = true;

        for(Edge e: graph[vtx]){
            int nbr = e.v;

            if(!vis[nbr]){
                dfs_gcc(nbr,vis,graph);
            }
        }
    }

    public static boolean count_gcc(ArrayList<Edge>[] graph, int N){
        boolean[] vis = new boolean[N];
        int totalNumberOfComponents = 0;

        for(int vertex=0; vertex<N; vertex++){
            if(vis[vertex] == false){
                dfs_gcc(vertex,vis,graph);
                totalNumberOfComponents++;
            }
        }   

        return totalNumberOfComponents == 1;
    }

    // print hamiltonian path and cycle
    public static boolean checkIfEdge(int a, int b, ArrayList<Edge>[] graph){
        for(Edge e: graph[a]){
            if(e.v == b){
                return true;
            }
        }

        return false;
    }

    public static void dfs_hamiltonian(int currVtx, int osrc, int edgesVisited,ArrayList<Edge>[] graph,String cpath,boolean[] vis,int N){
        if(edgesVisited == N-1){
            if(checkIfEdge(currVtx, osrc, graph) == true){
                System.out.println("Hamiltonian Cycle: " + cpath);
            } else {
                System.out.println("Hamiltonian Path: " + cpath);
            }
            return;
        }

        vis[currVtx] = true;

        for(Edge e: graph[currVtx]){
            int nbr = e.v;
            if(!vis[nbr]){ 
                dfs_hamiltonian(nbr,osrc,edgesVisited+1,graph,cpath+"->"+nbr,vis,N);
            }
        }

        vis[currVtx] = false;
    }

    public static void printHamiltonianPathCycle(ArrayList<Edge>[] graph, int src, int N){
        dfs_hamiltonian(src,src,0,graph,""+src,new boolean[N],N);
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
        // int N = 11;

        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] graph = new ArrayList[N];

        for(int i=0; i<N; i++){
            graph[i] = new ArrayList<>();
        }

        // addEdge(graph,0,1,10);
        // addEdge(graph,1,2,11);
        // addEdge(graph,1,3,7);
        // addEdge(graph,1,4,2);
        // addEdge(graph,2,4,8);
        // addEdge(graph,2,5,13);
        // addEdge(graph,5,6,6);
        // addEdge(graph,5,7,5);
        // addEdge(graph,6,7,4);
        // for connected comp
        // addEdge(graph,8,9,130);

        // displayGraph(graph);
        // System.out.println(hasPath(0,7, graph, N));
        // printAllPaths(0,7, graph, N);
        // multisolver(3,7,3,39,graph,N);
        // System.out.println(getConnectedComponents(graph,N));

        addEdge(graph,0,1,10);
        addEdge(graph,0,2,10);
        addEdge(graph,2,3,10);
        addEdge(graph,3,4,10);
        // addEdge(graph,4,5,10);
        addEdge(graph,1,3,10);
        addEdge(graph,1,5,10);
        addEdge(graph,5,6,10);
        addEdge(graph,6,7,10);
        addEdge(graph,4,7,10);



        printHamiltonianPathCycle(graph,0,N);
    }
}