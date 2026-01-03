#include<bits/stdc++>


// Character with maximum frequence (https://www.geeksforgeeks.org/problems/maximum-occuring-character-1587115620/1)
char getMaxOccuringChar(string& s) {
    unordered_map<char,int> mp;
    int maxFre = 0;

    for(int i=0; i<s.size(); i++){
        char ch = s[i];
        mp[ch]++;

        maxFre = max(maxFre, mp[ch]);
    }

    char ans = '\0';
    for(int i=0; i<s.size(); i++){
        char ch = s[i];

        if(mp[ch] == maxFre){
            if(ans == '\0' || ans > ch){
                ans = ch;
            }
        }
    }

    return ans;
}


// Leetcode 349 (Intersection of two arrays)
vector<int> intersection(vector<int>& nums1, vector<int>& nums2) {
    unordered_map<int,int> mp;

    for(int ele: nums1){
        mp[ele] = 1;
    }        

    vector<int> ans;
    for(int ele: nums2){
        if(mp.find(ele) != mp.end()){
            ans.push_back(ele);
            mp.erase(ele);
        }
    }

    return ans;
}

// Leetcode 349 (Intersection of two arrays) using HashSet ==================================================
vector<int> intersection(vector<int>& nums1, vector<int>& nums2) {
    unordered_set<int> se;

    for(int ele: nums1){
        se.insert(ele);
    }

    vector<int> ans;
    for(int ele: nums2){
        if(se.find(ele) != se.end()){
            ans.push_back(ele);
            se.erase(ele);
        }
    }

    return ans;
}


























int main(){
    return 0;
}