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

// Leetcode 839 (Group similar string) ===================
class Solution {
    public:
        unordered_map<string,string> par;
        unordered_map<string, int> size;

        string findPar(string& u){
            if(par[u] == u) return u;

            return par[u] = findPar(par[u]);
        }

        void merge(string& p1, string& p2){
            if(size[p1] > size[p2]){
                par[p2] = p1;
                size[p1] += size[p2];
            } else {
                par[p1] = p2;
                size[p2] += size[p1];
            }
        }

        bool isSimilar(string& a, string& b){
            int diff_char = 0;

            for(int i=0; i<a.length(); i++){
                if(a[i] != b[i]){
                    diff_char++;
                }
            }

            return diff_char <= 2; // It can only be 0,2 or bigger numbers
        }

        int numSimilarGroups(vector<string>& strs) {
            int n = strs.size();

            for(int i=0; i<n; i++){
                par[strs[i]] = strs[i]; // par.put(strs[i], strs[i]);
                size[strs[i]] = 1; // size.put(strs[i], 1);
            }

            int totalComps = par.size(); // in case of duplicate strings, we should consider duplicates as one component
            
            for(int i=0; i<n; i++){
                for(int j=i+1; j<n; j++){
                    if(isSimilar(strs[i], strs[j])){
                        string p1 = findPar(strs[i]);
                        string p2 = findPar(strs[j]);

                        if(p1 != p2){
                            merge(p1,p2);
                            totalComps--;
                        }
                    }
                }
            }

            return totalComps;
        }
    };

    // leetcode 1202 (Smallest string with swaps)
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

            string smallestStringWithSwaps(string s, vector<vector<int>>& pairs) {
                int n = s.size();

                par.resize(n);
                size.resize(n,1);

                for(int i=0; i<n; i++){
                    par[i] = i;
                }

                for(vector<int>& edge: pairs){
                    int u = edge[0];
                    int v = edge[1];

                    int p1 = findPar(u);
                    int p2 = findPar(v);

                    if(p1 != p2){
                        merge(p1, p2);
                    }
                }

                unordered_map<int,string> leaderVsString;
                for(int i=0; i<n; i++){
                    int par = findPar(i);
                    leaderVsString[par] += s[i]; // this character can be grouped with "par"
                }

                for(auto& kv: leaderVsString){ //kv = keyValue => {kv.first (key), kv.second(value)}
                    sort(kv.second.begin(), kv.second.end());
                }

                string ans = "";
                vector<int> idxUsed(n,0);

                for(int i=0; i<n; i++){
                    int par = findPar(i);
                    
                    ans += leaderVsString[par][idxUsed[par]];
                    idxUsed[par]++;
                }
                return ans;
            }
        };



























int main(){
    return 0;
}