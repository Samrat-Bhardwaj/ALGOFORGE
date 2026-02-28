import java.util.ArrayList;
import java.util.LinkedList;
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

    // mark visited at removal
    public static void BFS_1(int src, ArrayList<Edge>[] graph, int N){
        boolean[] vis = new boolean[N];

        LinkedList<Integer> que = new LinkedList<>();
        que.add(src);

        int level = 0;
        boolean isCycle = false;

        while(que.size() > 0){
            System.out.print("Nodes at distance " + level + "-> ");

            int size =  que.size();

            while(size-- > 0){
                int vtx = que.removeFirst();

                if(vis[vtx] == true){
                    isCycle = true; // only true for undirected graph
                    continue;
                }

                vis[vtx] = true;
                System.out.print(vtx + ",");

                for(Edge e: graph[vtx]){
                    int nbr = e.v;

                    if(!vis[nbr]){
                        que.addLast(nbr);
                    }
                }
            }

            level++;
            System.out.println();
        }

        if(isCycle){
            System.out.println("There is a cycle in this graph!!");
        }
    }



    // Construction utils
    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w){
        graph[u].add(new Edge(u,v,w));
        graph[v].add(new Edge(v,u,w));
    }

    public static void main(String[] args){
        int N = 8;

        ArrayList<Edge>[] graph = new ArrayList[N];

        for(int i=0; i<N; i++){
            graph[i] = new ArrayList<>();
        }

        addEdge(graph,0,1,2);
        addEdge(graph,1,2,5);
        addEdge(graph,1,3,5);
        addEdge(graph,2,4,4);
        addEdge(graph,3,4,4);
        addEdge(graph,4,5,7);
        addEdge(graph,5,6,8);
        addEdge(graph,5,7,8);
        addEdge(graph,6,7,8);

        BFS_1(0,graph,N);
    }
}