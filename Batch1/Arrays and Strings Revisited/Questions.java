class Questions {
    // Segregate positive and negative numbers
    public static void swap(int[] arr, int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void Rearrange(int arr[], int n) {
        int i = -1;
        int j = n-1;

        while(i < j){
            if(arr[i+1] < 0){
                i++;
            } else {
                swap(arr,i+1,j);
                j--;
            }
        }
    }

    // Segregate 0s and 1s (https://www.geeksforgeeks.org/problems/segregate-0s-and-1s5106/1) ======================
    // https://www.geeksforgeeks.org/problems/segregate-0s-and-1s5106/1
    void segregate0and1(int[] arr) {
        int n = arr.length;
        int i = -1;
        int j = n-1;

        while(i < j){
            if(arr[i+1] == 0){
                i++;
            } else {
                swap(arr,i+1,j);
                j--;
            }
        }
    }

    // Leetcode 75 (https://leetcode.com/problems/sort-colors/description/) ================================
    public void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void sortColors(int[] nums) {
        // {0,p1} -> zeros
        // {p1+1,p2-1} -> ones
        // {p2, p3} -> unexplored
        // {p3, n-1} -> twos
        int n = nums.length;

        int p1 = -1;
        int p2 = 0;
        int p3 = n-1;

        while(p2 <= p3){
            if(nums[p2] == 0){
                p1++; // increasing zero area, found one more zero
                swap(nums, p1, p2); // getting one of new p1 and sending zero at new one
                p2++; // area of one shifted
            } else if(nums[p2] == 1){
                p2++; // area of one inc
            } else {
                swap(nums,p2,p3);
                p3--;
            }
        }
    }


    // SLIDING WINDOW ===========================================================

    // Leetcode 3 (Longest substring with no repeating characters) ==========================
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int si = 0, ei =0, count=0, len=0;

        int[] fre = new int[128];

        while(ei < n){
            if(fre[s.charAt(ei++)]++ == 1){
                count++;
            }

            while(count > 0){
                if(fre[s.charAt(si++)]-- == 2){
                    count--;
                }
            }

            len = Math.max(len, ei-si); 
        }

        return len;
    }

    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int si = 0, ei =0, count=0, len=0;

        int[] fre = new int[128];

        while(ei < n){
            if(fre[s.charAt(ei)] == 1){
                count++;
            }
            fre[s.charAt(ei)]++;
            ei++;
            

            while(count > 0){
                if(fre[s.charAt(si)] == 2){
                    count--;
                }
                fre[s.charAt(si)]--;
                si++;
            }

            len = Math.max(len, ei-si); 
        }

        return len;
    }


    // Length of longest substring with at most 2 distinct characters =========================
    public static int lengthOfLongestSubstring(String s) {
		int n = s.length();
        int si = 0, ei =0, count=0, len=0;

        int[] fre = new int[128];

        while(ei < n){
            if(fre[s.charAt(ei++)]++ == 0){
                count++;
            }

            while(count > 2){
                if(fre[s.charAt(si++)]-- == 1){
                    count--;
                }
            }

            len = Math.max(len, ei-si); 
        }

        return len;
	}

    // Longest substring with exact K distinct (https://www.geeksforgeeks.org/problems/longest-k-unique-characters-substring0853/1)
    public int longestKSubstr(String s, int k) {
        int n = s.length();
        int si = 0, ei =0, count=0, len=0;

        int[] fre = new int[128];

        while(ei < n){
            if(fre[s.charAt(ei++)]++ == 0){
                count++;
            }

            while(count > k){
                if(fre[s.charAt(si++)]-- == 1){
                    count--;
                }
            }
            
            if(count == k){
                len = Math.max(len, ei-si); 
            }
        }

        return len;
    }

    // Maximum number of vowels in substring of length k
    public boolean isVowel(char ch){
        if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u'){
            return true;
        }

        return false;
    }

    public int maxVowels(String s, int k) {
        int n = s.length();
        int si = 0;
        int ei = 0;

        int currCount = 0;
        int maxCount = 0;

        while(ei < n){
            if(isVowel(s.charAt(ei))){
                currCount++;
            }
            ei++;

            while(ei - si > k){ // every time there will be only one iteration, while can be replaced with if
                if(isVowel(s.charAt(si))){
                    currCount--;
                }
                si++;
            }

            if(ei - si == k){
                maxCount = Math.max(currCount, maxCount);
            }
        }

        return maxCount; 
    }

    // Maximum of a k size window (Better approaches in Stack & Queue)
    // Leetcode 239 =================================================
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;

        int[] ans = new int[n-k+1];

        // { nums[i], i }
        PriorityQueue<int[]> pq = new PriorityQueue<>((int[] a, int[] b) -> {
            return b[0] - a[0]; // max priorityQueue
        });

        for(int i=0; i<n; i++){
            
            while(pq.size() > 0 && pq.peek()[1] <= i-k){
                pq.remove();
            }
            pq.add(new int[]{nums[i], i});

            if(i >= k-1)
                ans[i-k+1] = pq.peek()[0];
        }

        return ans;
    }

    // Longest subarray with sum = k
    public int longestSubarray(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        int csum = 0;
        map.put(csum, -1);

        for(int i=0; i<arr.length; i++){    
            csum += arr[i];

            if(map.containsKey(csum - k)){
                ans = Math.max(ans, i - map.get(csum - k));
            }
            
            if(map.containsKey(csum) == false){
                map.put(csum, i);
            }
        }

        return ans;
    }

    // Number of subarrays with sum = k
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        int csum = 0;
        map.put(csum, 1);

        for(int i=0; i<nums.length; i++){
            csum += nums[i];

            ans += map.getOrDefault(csum-k,0);

            map.put(csum, map.getOrDefault(csum,0) + 1);
        }

        return ans;
    }

    // leetcode 525 (Longest subarray with equal 0s and 1s)
    public int findMaxLength(int[] nums) {
        // longest subarray with sum  = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        int csum = 0;
        map.put(csum, -1);

        for(int i=0; i<arr.length; i++){    
            if(arr[i] == 0) csum--; // converting 0 to -1
            else csum++; 

            if(map.containsKey(csum)){
                ans = Math.max(ans, i - map.get(csum));
            }
            
            if(map.containsKey(csum) == false){
                map.put(csum, i);
            }
        }

        return ans;
    }

    // Count of subarray with equal 0s and 1s
    // https://www.geeksforgeeks.org/problems/count-subarrays-with-equal-number-of-1s-and-0s-1587115620/1
    public int countSubarray(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        int csum = 0;
        map.put(csum, 1);

        for(int i=0; i<arr.length; i++){
            if(arr[i] == 0) csum--; // converting 0 to -1
            else csum++; 

            ans += map.getOrDefault(csum,0);

            map.put(csum, map.getOrDefault(csum,0) + 1);
        }

        return ans;
    }

    // https://leetcode.com/problems/fruit-into-baskets/description/
    public int totalFruit(int[] fruits) {
        int n = fruits.length;
        int si = 0, ei =0, count=0, len=0;

        int[] fre = new int[100001];

        while(ei < n){
            if(fre[fruits[ei]] == 0){
                count++;
            }
            fre[fruits[ei]]++;
            ei++;

            while(count > 2){
                if(fre[fruits[si]] == 1){
                    count--;
                }
                fre[fruits[si]]--;
                si++;
            }

            len = Math.max(len, ei-si); 
        }

        return len;
    }

    // ========================================= SUM PROBLEMS ====================================

    // Leetcode 1 (Two Sum) ===========
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i=0; i<nums.length; i++){
            int diff = target - nums[i];

            if(map.containsKey(diff)){
                return new int[]{map.get(diff), i};
            }

            map.put(nums[i],i);
        }

        return new int[]{};
    }

    // https://www.geeksforgeeks.org/problems/all-distinct-pairs-with-given-sum/1
    public List<List<Integer>> distinctPairs(int[] arr, int target) {
        Arrays.sort(arr);

        int n = arr.length;
        int i=0; int j=n-1;

        List<List<Integer>> ans = new ArrayList<>();

        while(i < j){
            int sum = arr[i] + arr[j];

            if(sum == target){
                ans.add(Arrays.asList(arr[i],arr[j]));
                i++;
                j--;
            } else if(sum < target){
                i++;
            } else {
                j--;
            }

            // skipping over already seen elements
            while(i>0 && i<j && arr[i-1] == arr[i]) i++;
            while(j<n-1 && j>i && arr[j+1] == arr[j]) j--;
        }

        return ans;
    }

    // Generic function to return 2sum pairs from si to ei
    public List<List<Integer>> makePairs(int[] arr, int target, int si, int ei) {
        int i=si; int j=ei;
        List<List<Integer>> ans = new ArrayList<>();
        while(i < j){
            int sum = arr[i] + arr[j];

            if(sum == target){
                ArrayList<Integer> smallAns = new ArrayList<>();
                smallAns.add(arr[i]);
                smallAns.add(arr[j]);
                ans.add(smallAns);
                // ans.add(Arrays.asList(arr[i],arr[j]));
                i++;
                j--;

                // skipping over already seen elements to remove duplicate pairs
                while(i<j && arr[i-1] == arr[i]) i++;
                while(i<j && arr[j+1] == arr[j]) j--;
            } else if(sum < target){
                i++;
            } else {
                j--;
            }            
        }

        return ans;
    }
    
    // generic function to get triplets
    public List<List<Integer>> threesum(int[] nums, int target, int si, int ei){
        List<List<Integer>> ans = new ArrayList<>();
        
        for(int i=si; i<=ei; i++){
            // removing duplicate elements
            if(i>si && nums[i-1] == nums[i]) continue;

            int fixedElement = nums[i];

            int updatedTarget = target - fixedElement;

            // returns all two sum pairs
            List<List<Integer>> smallAns = makePairs(nums,updatedTarget,i+1,ei);
            addFixedElement(ans,smallAns,fixedElement);
        }

        return ans;
    }

    public static void addFixedElement(List<List<Integer>> ans, List<List<Integer>> smallAns, int fixedElement){
        for(List<Integer> sAns: smallAns){
            sAns.add(fixedElement);
            ans.add(sAns);
        }
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        return threesum(nums,0,0,nums.length-1);
    }

    public List<List<Integer>> foursum(int[] nums, int target, int si, int ei){
        List<List<Integer>> ans = new ArrayList<>();
        
        for(int i=si; i<=ei; i++){
            // removing duplicate elements
            if(i>si && nums[i-1] == nums[i]) continue;

            int fixedElement = nums[i];

            int updatedTarget = target - fixedElement;

            // returns all two sum pairs
            List<List<Integer>> smallAns = threeSum(nums,updatedTarget,i+1,ei);
            addFixedElement(ans,smallAns,fixedElement);
        }

        return ans;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);

        return fourSum(nums,target,0,nums-length-1);
    }

    public List<List<Integer>> kSum(int[] nums, int target){
        
    }































    public static void main(String[] args){
        int[] arr = {-1,-2,-8,11,40,-19,20,-10,90,-34};

        Rearrange(arr, arr.length);
        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i] +" ");
        }
    }
}