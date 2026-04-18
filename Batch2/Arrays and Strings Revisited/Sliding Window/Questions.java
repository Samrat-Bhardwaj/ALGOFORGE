class Questions {
    // Leetcode 3 (Longest substring with 0 duplicates )
    public int lengthOfLongestSubstring(String s) {
        int n = s.length;

        int si=0, ei=0, maxLen = 0;
        int count = 0; // count of duplicate characters

        int[] freq = new int[128];

        while(ei < n){
            if(freq[s.charAt(ei)] == 1){ // found duplicate character at ei
                count++;
            }
            freq[s.charAt(ei)]++;
            ei++;

            // fix window
            while(count > 0){
                if(freq[s.charAt(si)] == 2){ // found duplicate character at si, will remove
                    count--;
                }
                freq[s.charAt(si)]--;
                si++;
            }

            maxLen = Math.max(maxLen, ei - si);
        }

        return maxLen;
    }

    // Longest substring with maximum 2 distinct characters
    public static int lengthOfLongestSubstring(String s) {
		int n = s.length();
        
        int si=0, ei=0, maxLen=0;
        int count = 0; // count of distinct characters
        int[] freq = new int[128];

        while(ei < n){
            if(freq[s.charAt(ei)] == 0){ // new distinct character
                count++;
            }
            freq[s.charAt(ei)]++;
            ei++;

            // fix window
            while(count > 2){
                if(freq[s.charAt(si)] == 1){ // last occurrence of this character which will be removed now
                    count--;
                }
                freq[s.charAt(si)]--;
                si++;
            }

            maxLen = Math.max(maxLen, ei-si);
        }

        return maxLen;
	}

    // Leetcode 1456 (Max numbers of vowels in k length window)
    public boolean isVowel(char ch){
        return ch == 'a' || ch == 'e' || ch =='i' || ch == 'o' || ch == 'u';
    }
    
    public int maxVowels(String s, int k) {
        int n = s.length();

        int si = 0;
        int ei = 0;

        int currVowelCount = 0;
        int maxVowelCount = 0;

        while(ei < n){
            if(isVowel(s.charAt(ei))){
                currVowelCount++;
            }
            ei++;

            while(ei - si > k){
                if(isVowel(s.charAt(si))){
                    currVowelCount--;
                }
                si++;
            }

            if(ei - si == k){
                maxVowelCount = Math.max(currVowelCount, maxVowelCount);
            }
        }

        return maxVowelCount;
    }

    // Sliding window maximum (Leetcode 295)
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;

        int[] ans = new int[n-k+1];

        // {nums[i], i}
        PriorityQueue<int[]> pq = new PriorityQueue<>((int[] a, int[] b)->{
            return b[0] - a[0];
        });

        for(int i=0; i<n; i++){
            pq.add(new int[]{nums[i], i});

            while(pq.size()>0 && pq.peek()[1] <= i-k){ // removing maximum element only if its not in the window
                pq.remove();
            }

            if(i >= k-1){
                ans[i-k+1] = pq.peek()[0];
            }
        }

        return ans;
    }

    // HashMap Problem Revision (https://www.geeksforgeeks.org/problems/largest-subarray-with-0-sum/1)
    int maxLength(int arr[]) {
        int n = arr.length;

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int csum = 0;
        int maxLen = 0;

        for(int i=0; i<n; i++){
            csum += arr[i];

            if(map.containsKey(csum)){
                maxLen = Math.max(maxLen, i - map.get(csum));
            } else {
                map.put(csum, i);
            }
        }

        return maxLen;
    }

    // Count of subarrays with zero sum 
    public int findSubarray(int[] arr) {
        int n = arr.length;

        HashMap<Integer,Integer> map = new HashMap<>();

        map.put(0,1);

        int totalCount = 0;
        int csum = 0;

        for(int i=0; i<n; i++){
            csum += arr[i];

            if(map.containsKey(csum)){
                totalCount += map.get(csum);

                map.put(csum, map.get(csum) + 1);
            } else {
                map.put(csum, 1);
            }
        }

        return totalCount;
    }

    //Longest subarray with k sum (https://www.geeksforgeeks.org/problems/longest-sub-array-with-sum-k0809/1)
    public int longestSubarray(int[] arr, int k) {
        int n = arr.length;

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int csum = 0;
        int maxLen = 0;

        for(int i=0; i<n; i++){
            csum += arr[i];

            if(map.containsKey(csum-k)){
                maxLen = Math.max(maxLen, i - map.get(csum-k));
            }  

            if(map.containsKey(csum) == false){
                map.put(csum, i);
            }
        }

        return maxLen;
    }
}