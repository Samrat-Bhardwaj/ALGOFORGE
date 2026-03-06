
// all acyclic and even length cycle graph are bipartite 
// we need to check if we have any odd length cycle in the graph 

class BipartiteGraph {
    public boolean checkIfOddCycle(ArrayList<Integer>[] graph, int[] vis, int src){
        LinkedList<Integer> que = new LinkedList<>();

        int color = 0;
        que.addLast(src);

        while(que.size() > 0){
            int size = que.size();

            while(size-- > 0){
                int vtx = que.removeFirst();

                if(vis[vtx] != -1 && vis[vtx] != color){ // already visited with some other color -> odd length cycle
                    return true;
                }

                vis[vtx] = color;

                for(int nbr: graph[vtx]){
                    if(vis[nbr] == -1){
                        que.addLast(nbr);
                    }
                }
            }

            color = (color+1)%2;
        }

        return false;
    }

    public boolean checkIfBipartite(int N, int[][] edges){
        ArrayList<Integer>[] graph = new ArrayList[N];

        for(int i=0; i<N; i++){
            graph[i] = new ArrayList<>();
        }

        for(int[] edge: edges){
            graph[u].add(v);
            graph[v].add(u);
        }

        int[] vis = new int[N];
        Arrays.fill(vis, -1); // vector<int> vis(n,-1);

        for(int i=0; i<N; i++){
            if(vis[i] == -1){
                if(checkIfOddCycle(graph,vis,i)) return false;
            }
        }

        return true;
    }
}