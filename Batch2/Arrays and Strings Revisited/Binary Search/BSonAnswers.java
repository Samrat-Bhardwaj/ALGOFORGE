class BSonAnswers {
    // Aggressive cows (https://www.geeksforgeeks.org/problems/aggressive-cows/1)
    class Solution {
        // O(N)
        public boolean isPossible(int[] stalls, int k, int dis){
            int lastPlacedCow = stalls[0];
            k--;

            for(int i=1; i<stalls.length; i++){
                if(stalls[i] - lastPlacedCow >= dis){
                    k--;
                    lastPlacedCow = stalls[i];
                }
                if(k==0) return true;
            }

            return false;
        }

        public int aggressiveCows(int[] stalls, int k) {
            Arrays.sort(stalls);

            int n = stalls.length;

            int left = 1;
            int right = (int)(1e9);  // stalls[n-1] - stalls[0];

            int res = -1;
            while(left <= right){
                int mid = (left + right)/2;

                if(isPossible(stalls,k,mid)){
                    res = mid;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            return res;
        }
    }

    // Koko Eating Bananas (Leetcode 875) =================================
    class Solution {
        public boolean isPossible(int[] piles, int maximumHoursToEat, int speed){
            int totalHours = 0;

            for(int i=0; i<piles.length; i++){
                totalHours += Math.ceil(piles[i]*1.0 / speed);

                if(totalHours > maximumHoursToEat) return false;
            }

            return true;
        }

        public int minEatingSpeed(int[] piles, int h) {
            int left = 0;
            int right = (int)(1e9); // Maximum of piles array

            int minSpeed = -1;
            while(left <= right){
                int mid = (left + right)/2;

                if(isPossible(piles, h, mid)){
                    minSpeed = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            return minSpeed;
        }
    }

    // Leetcode 2226 
    class Solution {
        public boolean isPossible(int[] candies, int maxCandies, long totalChildren){
            for(int i=0; i<candies.length; i++){
                totalChildren -= (candies[i]/maxCandies);

                if(totalChildren <= 0) return true;
            }

            return false;
        }

        public int maximumCandies(int[] candies, long k) {
            int left = 1;
            int right = (int)(1e9);

            int maxCandies = 0; // if not possible to give candies, maxCandies is 0
            while(left <= right){
                int mid = (left + right)/2;

                if(isPossible(candies, mid, k)){
                    maxCandies = mid;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            return maxCandies;
        }
    }

    // Leetcode 1482. Minimum Number of Days to Make m Bouquets
    class Solution {
        public boolean isPossible(int[] bloomDay, int m, int k, int day){
            int count = 0;

            for(int i=0; i<bloomDay.length; i++){
                if(bloomDay[i] <= day){
                    count++; 

                    if(count == k){ // we were able to find k adjacent flowers
                        m--;
                        count = 0;
                    }
                } else {
                    count = 0;
                }

                if(m == 0) return true;
            }

            return false;
        }

        public int minDays(int[] bloomDay, int m, int k) {
            int left = 1;
            int right = (int)(1e9);

            int minDay = -1;
            while(left <= right){
                int mid = (left + right)/2;

                if(isPossible(bloomDay,m,k,mid)){
                    minDay = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            return minDay;
        }
    }

    // Leetcode 1011. Capacity To Ship Packages Within D Days
    class Solution {
        public boolean isPossible(int[] weights, int days, int capacity){
            int currWeight = 0;

            for(int i=0; i<weights.length; i++){
                currWeight += weights[i];

                if(currWeight > capacity){
                    days--;
                    currWeight = weights[i]; // this will be shipped next day
                }

                if(days <= 0) return false; // we should atleast have one day to ship last items
            }

            return true;
        }
        
        public int shipWithinDays(int[] weights, int days) {
            int maxWeight = 0;
            int sumWeight = 0;

            for(int e: weights){
                maxWeight = Math.max(maxWeight, e);
                sumWeight += e;
            }

            // or initialise with 1 to 1e9
            int left = maxWeight; // minimum capacity is this
            int right = sumWeight;  // maximum capacity to ship in 1 day is sum

            int minCapacity = 0;
            while(left <= right){
                int mid = (left + right)/2;

                if(isPossible(weights, days, mid)){
                    minCapacity = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            return minCapacity;
        }
    }

    // Leetcode 410 (Split Array largest sum)
    class Solution {
        public boolean isPossible(int[] nums, int k, int maxSubarraySum){
            int csum = 0;
            for(int i=0; i<nums.length; i++){
                csum += nums[i];

                if(csum > maxSubarraySum){
                    csum = nums[i];
                    k--;
                }

                if(k <= 0) return false;
            }

            return true;
        }

        public int splitArray(int[] nums, int k) {
            int max = 0;
            int sum = 0;

            for(int e: nums){
                max = Math.max(max, e);
                sum += e;
            }

            int left = max;
            int right = sum;

            int minSubarraySum = -1;

            while(left <= right){
                int mid = (left + right)/2;

                if(isPossible(nums, k, mid)){
                    minSubarraySum = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            return minSubarraySum;
        }
    }
}