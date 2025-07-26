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

// Similar string groups (Leetcode 839) ===========================
class Solution {
public:
    bool isSimilar(string &a, string &b){
        int diff_char = 0;

        for(int i=0; i<a.size() && diff_char < 3; i++){
            if(a[i] != b[i]){
                diff_char++;
            }
        }

        return diff_char <= 2;
    }

    unordered_map<string,string> par;
    string findPar(string& u){
        if(par[u] == u) return u;

        return par[u] = findPar(par[u]);
    }
    
    int numSimilarGroups(vector<string>& strs) {
        int n = strs.size();

        for(int i=0; i<strs.size(); i++){
            par[strs[i]] = strs[i];
        }
        int total_comps = par.size(); // so that duplicate elements are not considered twice 

        for(int i=0; i<n; i++){
            string p1 = findPar(strs[i]);

            for(int j=i+1; j<n; j++){
                if(isSimilar(strs[i],strs[j])){
                    string p2 = findPar(string[j]);

                    if(p1 != p2){
                        par[p2] = p1;
                        total_comps--;
                    }
                }
            }
        }

        return total_comps;
    }
};

// leetcode 990 (Satisfiability of Equality Equations) ==============================
class Solution {
public:
    vector<int> par;
    vector<int> size;
    int findPar(int u){
        if(par[u] == u) return u;

        return par[u] = findPar(par[u]);
    }

    void merge(int p1, int p2){
        if(size[p1] < size[p2]){
            par[p1] = p2;
            size[p2] += size[p1];
        } else {
            par[p2] = p1;
            size[p1] += size[p2];
        }
    }

    bool equationsPossible(vector<string>& equations) {
        par.resize(26,0);
        size.resize(26,1);

        for(int i=0; i<26; i++){
            par[i] = i;
        }

        for(string &eq: equations){
            if(eq[1] == '!') continue;

            int u = eq[0] - 'a';
            int v = eq[3] - 'a';

            int p1 = findPar(u);
            int p2 = findPar(v);

            if(p1 != p2){ // combining equations with equal sign
                merge(p1,p2);
            }
        }

        for(string &eq: equations){
            if(eq[1] == '=') continue;

            int u = eq[0] - 'a';
            int v = eq[3] - 'a';

            int p1 = findPar(u);
            int p2 = findPar(v);

            if(p1 == p2) return false; // two variables with ! sign should not have same parent
        }

        return true;
    }
};


// Leetcode 1168 (Optimize Water Distribution in a Village) =====================
vector<int> par;
int findPar(int u){
    return par[u] = par[u] == u ? u : findPar(par[u]);
}

int min_cost_kruskal(int n, vector<vector<int>>& edges){
    int total_cost = 0;
    par.resize(n,0);
    for(int i=0; i<n; i++){
        par[i] = i;
    }

    sort(edges.begin(), edges.end(), (auto a, auto b){
        return a[2] < b[2];
    });

    for(vector<int>& edge: edges){
        int u = edge[0];
        int v = edge[1];
        int w = edge[2];

        int p1 = findPar(u);
        int p2 = findPar(v);

        if(p1 != p2){
            par[p2] = p1;
            total_cost += w;
        }
    }

    return total_cost;
}

int supplyWater(int n, int k, vector<int> &wells, vector<vector<int>> &pipes) {
  	vector<vector<int>> edges;
    for(vector<int> pipe: pipes){
        edges.push_back(pipe);
    }

    for(int i=0; i<n; i++){
        edges.push_back({0,i+1,wells[i]});
    }

    return min_cost_kruskal(n+1,edges);
}




int main(){
    return 0;
}