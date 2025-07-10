class Questions {
    // Get connected components (https://www.geeksforgeeks.org/problems/connected-components-in-an-undirected-graph/1)
    public static void dfs(int vtx, boolean[] vis,ArrayList<Integer> currComponent,ArrayList<Integer>[] graph){
        vis[vtx] = true;
        currComponent.add(vtx);

        for(int nbr: graph[vtx]){
            if(!vis[nbr]){
                dfs(nbr,vis,currComponent,graph);
            }
        }
    }

    public ArrayList<ArrayList<Integer>> getComponents(int V, int[][] edges) {
        ArrayList<Integer>[] graph = new ArrayList[V];

        for(int i=0; i<V; i++){
            graph[i] = new ArrayList<>();
        }

        for(int [] edge: edges){
            int u = edge[0];
            int v = edge[1];

            graph[u].add(v);
            graph[v].add(u);
        } 

        // GCC
        ArrayList<ArrayList<Integer>> components = new ArrayList<>();
        boolean[] vis = new boolean[V];

        for(int vertex=0; vertex<V; vertex++){
            if(vis[vertex] == false){
                ArrayList<Integer> currComponent = new ArrayList<>();
                dfs(vertex,vis,currComponent,graph);

                components.add(currComponent);
            }
        }   

        return components;
    }
}