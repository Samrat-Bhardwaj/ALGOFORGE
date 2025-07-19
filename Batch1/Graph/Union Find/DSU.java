class DSU {
    int[] par;
    int[] size; // rank
    public static int findPar(int u){
        if(par[u] == u){
            return u;
        }

        return par[u] = findPar(par[u]);
    }

    public static void merge(int p1, int p2){
        if(size[p1] < size[p2]){ 
            par[p1] = p2;
            size[p2] += size[p1];
            // rank[p2]++;
        } else {
            par[p2] = p1;
            size[p1] += size[p2];
            // rank[p1]++;
        }
    }

    public static void combineVertex(int[][] similarVertex, int N){
        par = new int[N];
        size = new int[N];

        for(int i=0; i<N; i++){
            par[i] = i;
            size[i] = 1;
        }

        for(int[] similar: similarVertex){
            int u = similar[0];
            int v = similar[1];

            int p1 = findPar(u);
            int p2 = findPar(v);

            if(p1 != p2){
                merge(p1,p2);
            }
        }
    }

    public static void main(String[] args){

    }
}