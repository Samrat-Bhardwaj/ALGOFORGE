    // return all strongly connected component
class KosaRaju {
    // make directed graph
    public ArrayList<Integer>[] makeDirectedGraph(int N, int[][] edges){
        ArrayList<Integer>[] graph = new ArrayList[N];

        for(int i=0; i<N; i++){
            graph[i] = new ArrayList<>();
        }

        for(int[] edge: edges){
            int u = edge[0];
            int v = edge[1];

            graph[u].add(v);
        }

        return graph;
    }

    // dfs to fill order of dfs
    public void dfs_fillStack(int src, ArrayList<Integer>[] graph, boolean[] vis, Stack<Integer> st){
        vis[src] = true;

        for(int nbr: graph[src]){
            if(!vis[nbr]){
                dfs_fillStack(nbr,graph,vis,st);
            }
        }

        st.push(src);
    }

    // reverse graph
    public ArrayList<Integer>[] reverseGraph(int N, ArrayList<Integer>[] graph){
        ArrayList<Integer>[] reversedGraph = new ArrayList[N];

        for(int i=0; i<N; i++){
            reversedGraph[i] = new ArrayList<>();
        }

        for(int u=0; u<N; u++){
            for(int v: graph[u]){
                reversedGraph[v].add(u);
            }
        }

        return reversedGraph;
    }
    
    // traverse on reversed graph
    public void dfs_findSCC(int src, ArrayList<Integer> scc, boolean[] vis,ArrayList<Integer>[] reversedGraph){
        vis[src] = true;
        scc.add(src);

        for(int nbr: graph[src]){
            if(!vis[nbr]){
                dfs_findSCC(nbr,scc,vis,reversedGraph);
            }
        }
    }

    // Main function to get strongly connected components
    public ArrayList<ArrayList<Integer>> getSCC(int N, int[][] edges){
        ArrayList<Integer>[] graph = makeDirectedGraph(N,edges);
        Stack<Integer> st = new Stack<>();
        boolean[] vis = new boolean[N];

        // step 1: random order dfs
        for(int i=0; i<N; i++){
            if(!vis[i]){
                // step 2: note the order of dfs in stack
                dfs_fillStack(i,graph,vis,st);
            }
        }

        // step 3, reverse edge
        ArrayList<Integer>[] reversedGraph = reverseGraph(N, graph);

        ArrayList<ArrayList<Integer>> stronglyConnectedComponents = new ArrayList<>();

        vis = new boolean[N];
        while(st.size() > 0){
            int vtx = st.pop();

            if(vis[vtx]) continue;
            
            ArrayList<Integer> scc = new ArrayList<>();
            dfs_findSCC(vtx,scc,vis,reversedGraph);
            stronglyConnectedComponents.add(scc);         
        }

        return stronglyConnectedComponents;
    }   
}