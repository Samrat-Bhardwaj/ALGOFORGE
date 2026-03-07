#include<bits/stdc++.h>

class Solution {
public:
    vector<int> par;
    vector<int> size;

    int findPar(int u){
        if(par[u] == u) return u;

        return par[u] = findPar(par[u]);
    }

    void merge(int p1, int p2){
        if(size[p1] > size[p2]){
            par[p2] = p1;
            size[p1] += size[p2];
        } else {
            par[p1] = p2;
            size[p2] += size[p1];
        }
    }

    vector<int> findRedundantConnection(vector<vector<int>>& edges) {
        int n = edges.size();

        par.resize(n);
        size.resize(n,1);

        for(int i=0; i<n; i++){
            par[i] = i;
        }

        for(vector<int>& edge: edges){
            int u = edge[0]-1;
            int v = edge[1]-1;
            
            int p1 = findPar(u);
            int p2 = findPar(v);

            if(p1 == p2){
                return edge;
            } else {
                merge(p1,p2);
            }
        }

        return {};
    }
};

int main(){
    return 0;
}