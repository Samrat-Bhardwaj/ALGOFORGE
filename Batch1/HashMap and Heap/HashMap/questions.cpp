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

// leetcode 1497 ====================================
bool canArrange(vector<int>& arr, int k) {
    vector<int> remainderFre(k,0);

    for(int e: arr){
        int rem = ((e%k) + k)%k;

        remainderFre[rem]++;
    }

    for(int rem=0; rem<k; rem++){
        if(rem == 0){
            if(remainderFre[0]%2 != 0) return false;
        } else if(remainderFre[rem] != remainderFre[k-rem]){ // rem 1 = 3, rem 4 = 3
            return false;
        }
    }

    return true;
}

// https://www.geeksforgeeks.org/problems/count-distinct-elements-in-every-window/1
vector<int> countDistinct(vector<int> &arr, int k) {
    unordered_map<int,int> freMap;
    vector<int> ans;

    for(int i=0; i<arr.size(); i++){
        freMap[arr[i]]++;

        if(i == k - 1){
            ans.push_back(freMap.size());
        } else if(i >= k){
            freMap[arr[i-k]]--;
            if(freMap[arr[i-k]] == 0) freMap.erase(arr[i-k]);
            ans.push_back(freMap.size());
        }
    }

    return ans;
}

// largest subarray with zero sum (https://www.geeksforgeeks.org/problems/largest-subarray-with-0-sum/1) ================
int maxLen(vector<int>& arr) {
    unordered_map<int,int> sumVsIndex;
    int csum = 0;
    int maxLength = 0;
    sumVsIndex[0] = -1; // to handle when csum  = 0;

    for(int i=0; i<arr.size(); i++){
        csum += arr[i];

        if(sumVsIndex.find(csum) != sumVsIndex.end()){
            maxLength = max(maxLength, i - sumVsIndex[csum]);
        } else {
            sumVsIndex[csum] = i;
        }
    }

    return maxLength;    
}


// number of subarray with zero sum (https://www.geeksforgeeks.org/problems/zero-sum-subarrays1825/1)
int findSubarray(vector<int> &arr) {
    unordered_map<int,int> sumFrequency;
    int csum=0, count=0;

    sumFrequency[0] = 1;

    for(int e: arr){
        csum += e;
        count += sumFrequency[csum];
        sumFrequency[csum]++;
    }

    return count;
}

// longest subaray with sum K (https://www.geeksforgeeks.org/problems/longest-sub-array-with-sum-k0809/1)
int longestSubarray(vector<int>& arr, int k) {
    unordered_map<int,int> sumVsIndex;
    int res = 0;
    int csum = 0;

    sumVsIndex[csum] = -1;  

    for(int i=0; i<arr.size(); i++){
        csum += arr[i];

        if(sumVsIndex.find(csum - k) != sumVsIndex.end()){
            res = max(res, i - sumVsIndex[csum-k]);
        }

        if(sumVsIndex.find(csum) == sumVsIndex.end()){
            sumVsIndex[csum] = i;
        }
    }
    return res;
}

// Leetcode 560 , Number of subarrays with sum K
int subarraySum(vector<int>& nums, int k) {
    unordered_map<int,int> sumFrequency;
    int csum=0, count=0;

    sumFrequency[0] = 1;

    for(int e: nums){
        csum += e;
        count += sumFrequency[csum-k];
        sumFrequency[csum]++;
    }

    return count;
}


// leetcode 974 , Number of subarrays divisible by k
int subarraysDivByK(vector<int>& nums, int k) {
    unordered_map<int,int> remFrequency;
    int res = 0, csum = 0;
    remFrequency[0] = 1;

    for(int e: nums){
        csum += e;

        int remainder = csum % k;

        if (remainder < 0){
            remainder += k;
        }
        

        res += remFrequency[remainder];
        remFrequency[remainder]++;
    }        

    return res;
}


// leetcode 525, Longest subarray with equal 0s and 1s
int findMaxLength(vector<int>& nums) {
    unordered_map<int, int> sumVsIndex;
    int res = 0, csum  = 0;

    sumVsIndex[0] = -1;
    for(int i=0; i<nums.size(); i++){
        csum = csum + (nums[i] == 0 ? -1 : nums[i]);

        if(sumVsIndex.find(csum) != sumVsIndex.end()){
            res = max(res, i - sumVsIndex[csum]);
        } else {
            sumVsIndex[csum] = i;
        }
    }        

    return res;
}




























int main(){
    return 0;
}