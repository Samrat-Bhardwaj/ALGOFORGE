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

    // Aggressive cows ==================================================
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
}