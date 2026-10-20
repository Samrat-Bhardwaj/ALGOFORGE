#include<bits/stdc++.h>


// Most Frequent Character (https://www.geeksforgeeks.org/problems/maximum-occuring-character-1587115620/1)
char getMaxOccuringChar(string& s) {
    unordered_map<char, int> freMap;
    
    for(int i=0; i<s.size(); i++){
        char ch = s[i];
        
        freMap[ch]++;
    }
        
    char ans = '\0';
    int maxFre = 0;
    
    for(int i=0; i<s.size(); i++){
        char ch = s[i];
        
        if(maxFre < freMap[ch]){
            ans = ch;
            maxFre = freMap[ch];
        } else if(freMap[ch] == maxFre && ans > ch){
            ans = ch;
        }
    }
    
    return ans;
}

// Leetcode 349 using HashSet (Intersection of two arrays)
vector<int> intersection(vector<int>& nums1, vector<int>& nums2) {
    unordered_set<int> stt; 
       
    for(int e: nums1){
        stt.insert(e);
    }

    vector<int> ans;
    for(int e: nums2){
        if(stt.find(e) != stt.end()){
            ans.push_back(e);
            stt.erase(e);
        }
    }

    return ans;
}

// All common elements (https://www.geeksforgeeks.org/problems/common-elements5420/1)
vector<int> commonElements(vector<int> &a, vector<int> &b) {
    unordered_map<int, int> freMap;

    for(int e: a){
        freMap[e]++;
    }

    vector<int> ans;
    for(int e: b){
        if(freMap[e] > 0){
            ans.push_back(e);
            freMap[e]--;
        }
    }

    sort(ans.begin(), ans.end());
    return ans;
}
























void main(){

}