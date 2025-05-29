#include <iostream>
#include<unordered_map>
#include<unordered_set>
#include<vector>

using namespace std;
 
// Most frequent character (https://www.geeksforgeeks.org/problems/maximum-occuring-character-1587115620/1) ================
char getMaxOccuringChar(string& s) {
    unordered_map<char, int> freMap;

    int maxFre = 0;
    char maxFreChar = '$';

    for(int i=0; i<s.size(); i++){
        char ch = s[i];
        // if(freMap.find(ch) == freMap.end()){
        //     freMap.inert(ch,1);
        // } else {
        //     int olderFrequeny = freMap[ch];
        //     freMap[ch] = olderFrequeny + 1;
        // }

        freMap[ch]++; // 0->1 
    }

    for(int i=0; i<s.size(); i++){
        char ch = s[i];

        if(freMap[ch] > maxFre){
            maxFre = freMap[ch];
            maxFreChar = ch;
        } else if(freMap[ch] == maxFre && ch < maxFreChar){
            maxFreChar = ch;
        }
    }

    return maxFreChar;
}

// leetcode 3005 ========================
int maxFrequencyElements(vector<int>& nums) {
    unordered_map<int,int> freMap;
    int maxFre = 0, count = 0;

    for(int e: nums){
        freMap[e]++;
        maxFre = max(maxFre, freMap[e]);
    }

    for(int e: nums){
        if(freMap[e] == maxFre){
            count++;
        }
    }

    return count;
}

// leetcode 349 ===========================================
vector<int> intersection(vector<int>& nums1, vector<int>& nums2) {
    vector<int> ans;
    unordered_map<int,int> freMap;

    for(int e: nums1){
        freMap[e] = 1;
    }

    for(int e: nums2){
        if(freMap.find(e) != freMap.end()){
            ans.push_back(e);
            freMap.erase(e);
        }
    }

    return ans;
}

// leetcode 128 ========================================
int longestConsecutive(vector<int>& nums) {
    int ans = 0;
    unordered_set<int> se;

    for(int e: nums){
        se.insert(e);
    }        

    for(int currentVal: nums){
        if(se.find(currentVal) == se.end()) continue;

        int prevVal = currentVal - 1;
        int nextVal = currentVal + 1;

        while(se.find(prevVal) != se.end()){
            se.erase(prevVal);
            prevVal--;
        }

        while(se.find(nextVal) != se.end()){
            se.erase(nextVal);
            nextVal++;
        }

        ans = max(ans, nextVal - prevVal - 1);
        se.erase(currentVal);
    }

    return ans;
}























int main(){
    return 0;
}