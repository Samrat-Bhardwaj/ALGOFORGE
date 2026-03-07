class Introduction {
    static int[] par;
    static int[] size;

    public static int findPar(int u){
        if(par[u] == u) return u;

        return par[u] = findPar(par[u]);
    }

    public static void merge(int p1, int p2){
        if(size[p1] > size[p2]){
            par[p2] = p1;
            size[p1] += size[p2];
            // rank[p1]++;
        } else {
            par[p1] = p2;
            size[p2] += size[p1];
            // rank[p2]++;
        }
    }

    public static void mergeSimilarVertex(int[][] similarVertices, int N){
        par = new int[N];
        size = new int[N];

        for(int i=0; i<N; i++){
            par[i] = i;
            size[i] = 1;
        }

        for(int[] similarVertex: similarVertices){
            int u = similarVertex[0];
            int v = similarVertex[1];

            int p1 = findPar(u); 
            int p2 = findPar(v);

            if(p1 != p2){
                merge(p1,p2);
            }
        }
    }

    public static void main(String[] args){
        int[][] similarVertices = {{0,5},{3,8},{6,4},{7,6},{0,6}};
        int N = 9;
        mergeSimilarVertex(similarVertices, N);
    }
}