#include<bits/stdc++.h>
using namespace std;

// word ladder 2 (leetcode 126)
    bool isSimilar(string& a, string& b){
        if(a.size() != b.size()) return false;

        int diff_char = 0;
        for(int i=0; i<a.size() && diff_char < 2; i++){
            if(a[i]  != b[i]){
                diff_char++;
            }
        }

        return diff_char == 1;
    }

    vector<vector<string>> findLadders(string beginWord, string endWord, vector<string>& wordList) {
        unordered_set<string> allWords;
        for(string word: wordList){
            allWords.insert(word);
        }

        queue<vector<string>> que;
        unordered_set<string> vis;
        vector<vector<string>> ans;


        que.push({beginWord});
        int level = 1;
        int min_path_length = -1;

        while(que.size() > 0){
            int size = que.size();

            while(size--){
                vector<string> top = que.front(); que.pop();

                string lastWord = top.back();
                vis.insert(lastWord);
                allWords.erase(lastWord);
                
                for(auto &nextWord: allWords){
                    if(isSimilar(lastWord, w)){
                        if(vis.find(nextWord) == vis.end()){
                            vector<string> nextPath = top;
                            nextPath.push_back(nextWord);
                            if(nextWord == endWord){
                                if(min_path_length == -1){
                                    min_path_length = level + 1;
                                    ans.push_back(nextPath);
                                } else if(nextPath.size() == min_path_length){
                                    ans.push_back(nextPath);
                                }
                            } else {
                                que.push(nextPath);
                            }   
                        }
                    }
                }
            }
            level++;
        }

        return ans;
    }


int main(){
    return 0;
}