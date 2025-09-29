class BinarySearchQuestions {
    // Leetcode 34 ================================
    public int findFirstIndex(int[] nums, int target, int n){
        int si = 0;
        int ei = n-1;

        int firstIndex = -1;

        while(si <= ei){
            int mid = (si+ei)/2;

            if(nums[mid] == target){
                firstIndex = mid;
                ei = mid - 1;
            } else if(nums[mid] < target){
                si = mid + 1;
            } else {
                ei = mid - 1;
            }
        }

        return firstIndex;
    }

    public int findLastIndex(int[] nums, int target, int n){
        int si = 0;
        int ei = n-1;

        int lastIndex = -1;

        while(si <= ei){
            int mid = (si+ei)/2;

            if(nums[mid] == target){
                lastIndex = mid;
                si = mid + 1;
            } else if(nums[mid] < target){
                si = mid + 1;
            } else {
                ei = mid - 1;
            }
        }

        return lastIndex;
    }

    public int[] searchRange(int[] nums, int target) {
        int n = nums.length;

        int firstIndex = findFirstIndex(nums,target,n);
        int lastIndex = findLastIndex(nums, target, n);

        return new int[]{firstIndex, lastIndex};
    }

    // Find k closest elements 
    public int binarySearch(int[] arr, int x){
        int si = 0;
        int ei = arr.length - 1;

        while(si < ei){
            int mid = (si + ei)/2;

            if(arr[mid] > x){
                si = mid + 1;
            } else {
                ei = mid;
            }
        }

        return si;
    }

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int n = arr.length;
        List<Integer> ans = new ArrayList<>();
        for(int e: arr) ans.add(e);

        if(x < arr[0]){
            return ans.subList(0,k); // first k elements
        } else if(x > arr[n-1]){
            return ans.subList(n-k, n); // last k elements
        }

        int idx = binarySearch(arr,x);

        int si = Math.max(0,idx-k);
        // if(idx < k){
        //     si = 0;
        // } else {
        //     si = idx - k;
        // }
        int ei = Math.min(n-1, idx+k);

        while((ei - si + 1) > k){
            if((x - arr[si]) <= (arr[ei] - x)){
                ei--;
            } else {
                si++;
            }
        }

        return ans.subList(si,ei+1);
    }

    // Aggressive cows (maximize the minimum distance) ==================================================
    public boolean isPossibleToPlaceCows(int[] stalls, int minDis, int k){
        int lastPlacedCowPos = stalls[0];
        k--;

        for(int i=1; i<stalls.length; i++){
            if(stalls[i] - lastPlacedCowPos >= minDis){ // can place a cow here
                lastPlacedCowPos = stalls[i];
                k--;
            }
            if(k==0) return true;
        }

        return k <= 0;
    }

    public int aggressiveCows(int[] stalls, int k) {
        int n = stalls.length;
        Arrays.sort(stalls);

        int si = 1; // min possible distance
        // int ei = (int)(1e8); // max possible distance 
        int ei = stalls[n-1] - stalls[0];
        int ans = -1;

        while(si <= ei){
            int mid = (si + ei)/2;

            if(isPossibleToPlaceCows(stalls,mid,k)){
                ans = mid;
                si = mid + 1;
            } else {
                ei = mid - 1;
            }
        }

        return ans;
    }

    // Leetcode 875 (Koko Eating bananas) ================================
    public boolean isPossibleToEat(int[] piles, int speed, int maxPossibleHours){
        int totalHours = 0;

        for(int i=0; i<piles.length; i++){
            totalHours += Math.ceil(piles[i]*1.0/speed);

            if(totalHours > maxPossibleHours) return false;
        }

        return true;
    }

    public int minEatingSpeed(int[] piles, int h) {
        int si = 1;
        int ei = (int)(1e9);
        int minSpeed = -1;

        while(si <= ei){
            int mid = (si + ei)/2;

            if(isPossibleToEat(piles, mid, h)){
                minSpeed = mid;
                ei = mid - 1; // possible to eat at this speed, decrease speed to find slower speed
            } else {
                si = mid + 1; // not possible to eat at this speed, increase speed to find possible speed
            }
        }

        return minSpeed;
    }

    // Leetcode 2226 (Maximum candies allocated) ================================
    public boolean isPossibleToGiveXCandies(int[] candies, long k, int X){
        for(int c : candies){
            int childrenCovered = c/X;

            k -= childrenCovered;
        }

        return k <= 0;
    }

    public int maximumCandies(int[] candies, long k) {
        int min = 1; // si, low, left
        int max = (int)(1e7); // ei, high, right

        int maxPossibleCandies = 0;
        while(min <= max){
            int mid = (min + max)/2;

            if(isPossibleToGiveXCandies(candies,k,mid)){ // O(n)
                maxPossibleCandies = mid;
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }

        return maxPossibleCandies;
    }

    // Leetcode 1482 (Minimum Number of Days to Make m Bouquets)
    public boolean isPossibleToMakeBounquetsOnDDay(int[] bloomDay, int m, int k, int day){
        int count = 0;

        for(int i=0; i<bloomDay.length; i++){
            if(bloomDay[i] <= day){
                count++;
                if(count == k){ // we found k adjacent flowers
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
        int right = (int)1e9;

        int minPossibleDay = -1;
        while(left <= right){
            int mid = (left + right)/2;

            if(isPossibleToMakeBounquetsOnDDay(bloomDay, m, k, mid)){
                minPossibleDay = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return minPossibleDay;
    }

    // Leetcode 1011 || Capacity To Ship Packages Within D Days
    public boolean isPossibleToShipWithCapacity(int[] weights, int days, int maxCapacity){
        int currCapacity = 0;

        for(int i=0; i<weights.length; i++){
            currCapacity += weights[i];

            if(currCapacity > maxCapacity){
                days--;
                currCapacity = weights[i];
            }
        }

        // if(currCapacity > 0) days--;
        // return days >= 0;

        return days > 0;
    }

    public int shipWithinDays(int[] weights, int days) {
        int maxWeight = 0;
        int sumOfWeights = 0;

        for(int w: weights){
            maxWeight = Math.max(maxWeight, w);
            sumOfWeights += w;
        }    

        int left = maxWeight; // can start with left = 1 and right = 1e8
        int right = sumOfWeights;

        int minCapacity = 0;
        while(left <= right){
            int mid = (left + right)/2;

            if(isPossibleToShipWithCapacity(weights, days, mid)){
                minCapacity = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return minCapacity;
    }

    // Allocate minimum pages (https://www.geeksforgeeks.org/problems/allocate-minimum-number-of-pages0937/1)
    class Solution {
        // is Possible to divide all books into k or less students such 
        // that no student will get more than maxPages pages
        public boolean isPossible(int[] arr, int maxPages, int k){
            int currentSumOfPages = 0;

            for(int i=0; i<arr.length; i++){
                currentSumOfPages += arr[i];

                if(currentSumOfPages > maxPages){
                    k--;
                    currentSumOfPages = arr[i];
                }
            }

            // need one student for currentSumOfPages 
            return k > 0;
        }
        
        public int findPages(int[] arr, int k) {
            if(arr.length < k){
                return -1;
            }
            int maxPage = 0;
            int sumOfPages = 0;
            for(int pages: arr){
                maxPage = Math.max(pages, maxPage);
                sumOfPages += pages;
            } 
            // code here
            int left = maxPage; // (ans for k == arr.length)
            int right = sumOfPages; // (ans for k == 1)

            int minMaxPages = 0; // minimizing the maximum page
            while(left <= right){
                int mid = (left + right)/2;

                if(isPossible(arr, mid, k)){
                    minMaxPages = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            return minMaxPages;
        }
    }

    // Leetcode 410 (copy paste of above 2) (Split Array Largest Sum)
    public boolean isPossible(int[] arr, int maxSum, int k){
        int currentSum = 0;

        for(int i=0; i<arr.length; i++){
            currentSum += arr[i];

            if(currentSum > maxSum){
                k--;
                currentSum = arr[i];
            }
        }

        // need one subarray for csum 
        return k > 0;
    }

    public int splitArray(int[] nums, int k) {
        int maxSum = 0;
        int sum = 0;
        for(int num: nums){
            maxSum = Math.max(num, maxSum);
            sum += num;
        } 
        // code here
        int left = maxSum;
        int right = sum; 

        int minMaxSum = 0; 
        while(left <= right){
            int mid = (left + right)/2;

            if(isPossible(nums, mid, k)){
                minMaxSum = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return minMaxSum;
    }

    // Minimize max distance to gas station (https://www.geeksforgeeks.org/problems/minimize-max-distance-to-gas-station/1) ==========================
    public boolean isPossible(int[] stations, int k, double mid){
        for(int i=1; i<stations.length; i++){
            int disBetweenLast2 = stations[i] - stations[i-1];
            
            int newStationsRequired = (int)(disBetweenLast2/mid);
            k -= newStationsRequired;
            
        }   
        
        return k >= 0;
    }
    
    public double minMaxDist(int[] stations, int K) {
        double left = 0.0, right = 1e9, mid = 0.0;
        double ans = 0.0;
        while(left <= right){
            mid = (left + right)/2.0;
            
            if(isPossible(stations,K, mid)){
                ans = mid;
                right = mid - 1e-6; // (mid - 0.000001) // we can also move by 0.001 
                // Why? Because we need answer upto 2 decimal points, so we should move by 3 decimal points
            } else {
                left = mid + 1e-6; // (mid + 0.000001)
            }
        }
        
        return mid;
    }

    // leetcode 1539 ======================================
    public int findKthPositive(int[] arr, int k) {
        int idx = -1;

        for(int i=0; i<arr.length; i++){
            int expectedNum = i+1;

            int diff = arr[i] - expectedNum;

            if(diff < k){
                idx = i; // the index till which k positives are not missing
            } else {
                break;
            }
        }

        return idx + k + 1;
    }

    // in log(N);
    public int findKthPositive(int[] arr, int k) {
        int n = arr.length;
        int left = 0;
        int right = n-1;

        while(left <= right){
            int mid = (left + right)/2;

            int diff = arr[mid] - (mid+1);

            if(diff < k){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right + k + 1;
    }

    // Leetcode 33 (Search in rotated sorted array) ============================
    public int search(int[] nums, int target) {
        int n = nums.length;

        int si = 0;
        int ei = n-1;

        while(si <= ei){
            int mid = (si + ei)/2;

            if(nums[mid] == target){
                return mid;
            } else if(nums[si] <= nums[mid]){ // this area is sorted, we can rely on it
                if(nums[si] <= target && nums[mid] > target){
                    ei = mid - 1;
                } else {
                    si = mid + 1;
                }
            } else { // if(nums[mid] < nums[ei])
                if(nums[mid] < target && nums[ei] >= target){
                    si = mid + 1;
                } else {
                    ei = mid - 1;
                }
            }
        }

        return -1;
    }

    // Leetcode 81 (Search in rotated sort array with duplicates)
    public boolean search(int[] nums, int target) {
        int n = nums.length;

        int si = 0;
        int ei = n-1;

        while(si <= ei){
            int mid = (si + ei)/2;

            if(nums[mid] == target || nums[ei] == target){ // because we may eliminate ei, we should check ei as well
                return true;    
            } // removed nums[si] <= nums[mid] as duplicate elements (TC: 1,1,0,1,1,1)
            else if(nums[si] < nums[mid]){ // this area is sorted, we can rely on it, 
                if(nums[si] <= target && nums[mid] > target){
                    ei = mid - 1;
                } else {
                    si = mid + 1;
                }
            } else if(nums[mid] < nums[ei]){ // if(nums[mid] < nums[ei])
                if(nums[mid] < target && nums[ei] >= target){
                    si = mid + 1;
                } else {
                    ei = mid - 1;
                }
            } else {
                ei--; // or si++;
            }
        }

        return false;
    }  

    // Leetcode 153 (Find minimum in rotated sorted array) ==========================
    public int findMin(int[] nums) {
        int n = nums.length;

        int si = 0;
        int ei = n-1;

        while(si < ei){
            int mid = (si + ei)/2;

            if(nums[mid] > nums[ei]){ // right area is unsorted
                si = mid + 1;
            } else { // either both the area sorted, either left area is unsorted
                ei = mid;
            }
        }

        return nums[si];
    }

    // Leetcode 154 (Find minimum in rotated sorted array with duplicate elements) ==========================
    // Worst case O(n), average log(N);
    public int findMin(int[] nums) {
        int n = nums.length;

        int si = 0;
        int ei = n-1;

        while(si < ei){
            int mid = (si + ei)/2;

            if(nums[mid] > nums[ei]){ // right area is unsorted
                si = mid + 1;
            } else if(nums[si] > nums[mid]) { // either left area is unsorted
                ei = mid;
            } else { // both the area are sorted or both are unsorted, removing ei one by one
                ei--;
            }
        }

        return nums[si];
    }
}