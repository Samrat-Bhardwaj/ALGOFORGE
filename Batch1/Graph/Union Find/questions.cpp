#include<bits/stdc++.h>
using namespace std;

// Leetcode 1061
class Solution {
public:
    vector<int> par;
    int findPar(int u){
        if(par[u] == u) return u;

        return par[u] = findPar(par[u]);
    }

    void merge(int p1, int p2){
        if(p1 < p2){
            par[p2] = p1;
        } else {
            par[p1] = p2;
        }
    }
    string smallestEquivalentString(string s1, string s2, string baseStr) {
        par.resize(26,0);
        for(int i=0; i<n; i++){
            par[i] = i;
        }

        for(int i=0; i<s1.size(); i++){
            int u = s1[i] - 'a';
            int v = s2[i] - 'a';

            int p1 = findPar(u);
            int p2 = findPar(v);

            if(p1 != p2){
                merge(p1,p2);
            }
        }

        string ans = "";
        for(int i=0; i<baseStr.size(); i++){
            int parChar = findPar(baseStr[i] - 'a');
            ans += (parChar + 'a');
        }

        return ans;
    }
};





int main(){
    return 0;
}