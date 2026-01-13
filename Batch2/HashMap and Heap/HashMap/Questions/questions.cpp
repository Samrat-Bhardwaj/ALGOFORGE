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

// Leetcode 1497 (Can array be divided into pairs divisible by k)
bool canArrange(vector<int>& arr, int k) {
    vector<int> remFre(k,0);

    for(int ele: arr){
        int rem = (ele % k + k) % k;
        remFre[rem]++;
    }

    for(int i=0; i<k; i++){
        if(i==0){
            if(remFre[i]%2 != 0){
                return false;
            }
        } else {
            int requiredRem = k - i;
            if(remFre[i] != remFre[requiredRem]){
                return false;
            }
        }
    }

    return true;
}

int findSubarray(vector<int> &arr) {
    unordered_map<int,int> mp;
    int csum = 0, count = 0;
    mp[csum]++;

    for(int e: arr){
        csum += e;
        
        count += mp[csum];
        mp[csum]++;
    }

    return count;
}


























int main(){
    return 0;
}