#include<bits/stdc++.h>
using namespace std;


// Leetcode 300, Longest increasing subsequence ======================================
int lis_rec(vector<int>& nums, int idx, vector<int>& dp){
    if(dp[idx] != 0) return dp[idx];

    int ans = 1;
    for(int j=idx-1; j>=0; j--){
        if(nums[j] < nums[idx]){
            ans = max(ans, lis_rec(nums,j,dp) + 1);
        }
    }

    return dp[idx] = ans;
}

int lis_tab(vector<int>& nums, vector<int>& dp){
    int res = 0;
    for(int idx=0; idx<nums.size(); idx++){
        
        int ans = 1;
        for(int j=idx-1; j>=0; j--){
            if(nums[j] < nums[idx]){
                ans = max(ans, dp[j] + 1);
            }
        }

        dp[idx] = ans;
        res = max(res, dp[idx]);
    }

    return res;
}

int lengthOfLIS(vector<int>& nums) {
    int n = nums.size();

    vector<int> dp(n,0);

    int res = 0;

    // for(int i=n-1; i>=0; i--){
    //     res = max(res, lis_rec(nums,i,dp)); // length of lis ending with nums[i]
    // }

    return lis_tab(nums,dp);
}

// count of lis ===================
// leetcode 673 =========================================
int findNumberOfLIS(vector<int>& nums) {
    int n = nums.size();

    vector<int> dp(n,1);
    vector<int> count(n,1);

    for(int i=1; i<n; i++){
        for(int j=i-1; j>=0; j--){
            if(nums[j] < nums[i]){
                if(dp[i] < dp[j] + 1){
                    dp[i] = dp[j] + 1;
                    count[i] = count[j];
                } else if(dp[i] == dp[j] + 1){
                    count[i] += count[j];
                }
            }
        }
    }

    int lis_length = 0;
    int lis_count = 0;
    for(int i=0; i<n; i++){
        if(dp[i] > lis_length){
            lis_length = dp[i];
            lis_count = count[i];
        } else if(dp[i] == lis_length){
            lis_count += count[i];
        }
    }

    return lis_count;
}
