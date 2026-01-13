import java.util.HashMap;
class Questions {

    // Character with maximum frequence (https://www.geeksforgeeks.org/problems/maximum-occuring-character-1587115620/1)
    public char getMaxOccuringChar(String s) {
        HashMap<Character,Integer> map = new HashMap<>();
        int maxFre = 0;

        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);

            if(map.containsKey(ch) == false){
                map.put(ch, 1); // seeing ch for the first time
            } else {
                int olderFre = map.get(ch);
                map.put(ch, olderFre + 1);
            }

            maxFre = Math.max(maxFre, map.get(ch));
        }

        char ans = '\0'; // initialising with null
        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);

            if(map.get(ch) == maxFre){
                if(ans == '\0'){
                    ans = ch;
                } else if(ans > ch){
                    ans = ch;
                }
            }
        }

        return ans;
    }

    // Leetcode 3005 (Elements with maximum frequence) ============================================================
    public int maxFrequencyElements(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int maxFre = 0;

        for(int ele: nums){
            if(map.containsKey(ele) == false){
                map.put(ele, 1);
            } else {
                int olderFre = map.get(ele);
                map.put(ele, olderFre + 1);
            }

            maxFre = Math.max(maxFre, map.get(ele));
        }

        int count = 0;
        for(int ele: nums){
            if(map.get(ele) == maxFre){ // count of all the elements with max Fre
                count++;
            }
        }

        return count;
    }

    // Leetcode 349 (Intersection of two arrays) ==================================================
    public int[] intersection(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int ele: nums1){ // O(N)
            map.put(ele, 1); // we don't care about frequency
        }

        ArrayList<Integer> ans = new ArrayList<>();

        for(int ele: nums2){ // O(M)
            if(map.containsKey(ele) == true){ // O(1)
                ans.add(ele);
                map.remove(ele);
            }
        }

        int[] res = new int[ans.size()];
        for(int i=0; i<ans.size(); i++){
            res[i] = ans.get(i);
        }

        // return ans.stream().mapToInt(i -> i).toArray();
        return res;
    }

    // Leetcode 349 (Intersection of two arrays) using HashSet ==================================================
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set = new HashSet<>();

        for(int ele: nums1){
            set.add(ele);
        }

        ArrayList<Integer> ans = new ArrayList<>();
        for(int ele: nums2){
            if(set.contains(ele)){
                ans.add(ele);
                set.remove(ele);
            }
        }

        int[] res = new int[ans.size()];
        for(int i=0; i<ans.size(); i++){
            res[i] = ans.get(i);
        }

        return res;
    }

    // All common elements (https://www.geeksforgeeks.org/problems/common-elements5420/1)
    public static ArrayList<Integer> commonElements(int a[], int b[]) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int ele: a){
            if(map.containsKey(ele) == false){
                map.put(ele, 1);
            } else {
                int olderFre = map.get(ele);
                map.put(ele, olderFre + 1);
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();
        for(int ele: b){
            if(map.containsKey(ele) && map.get(ele) > 0){
                ans.add(ele);

                // reduce frequence
                int currentFre = map.get(ele);
                map.put(ele, currentFre - 1);
            }
        }
        // result should be sorted
        Collections.sort(ans);

        return ans;
    }

    // Leetcode 128 (Longest consecutive sequence) =========================================
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();

        for(int ele: nums){
            set.add(ele);
        }    

        int maxLen = 0;

        for(int ele: nums){
            if(set.contains(ele) == false) continue;

            int left = ele - 1;
            int right = ele + 1;

            while(set.contains(left)){
                set.remove(left);
                left--;
            }

            while(set.contains(right)){
                set.remove(right);
                right++;
            }

            maxLen = Math.max(maxLen, right - left - 1);
        }

        return maxLen;
    }

    // Leetcode 1497 (Can array be divided into pairs divisible by k)
    public boolean canArrange(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i=0; i<arr.length; i++){
            // int rem = (arr[i]%k + k) % k; -> handles both positive and negative number 
            
            int rem = arr[i] % k;
            if(rem < 0){
                rem += k;
            }

            int requiredRem = (k - rem)%k;
            if(map.containsKey(requiredRem) && map.get(requiredRem) > 0){
                map.put(requiredRem, map.get(requiredRem) - 1);
            } else {
                map.put(rem, map.getOrDefault(rem,0) + 1);
            }
        }

        for(int key: map.keySet()){
            if(map.get(key) != 0){
                return false;
            }
        }

        return true;
    }

    // Leetcode 1497 (Can array be divided into pairs divisible by k)
    public boolean canArrange(int[] arr, int k){
        int[] remFre = new int[k];

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

    // Longest subarray with sum = 0 (https://www.geeksforgeeks.org/problems/largest-subarray-with-0-sum/1)
    int maxLength(int arr[]) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int ans = 0;

        int csum = 0; 
        map.put(csum, -1);

        for(int i=0; i<arr.length; i++){
            csum += arr[i];

            if(map.containsKey(csum)){
                ans = Math.max(ans, i - map.get(csum));
            } else {
                map.put(csum, i);
            }
        }

        return ans;
    }
    
    // Number of subarrays with sum = 0 (https://www.geeksforgeeks.org/problems/zero-sum-subarrays1825/1)
    public int findSubarray(int[] arr) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int count = 0;

        int csum = 0;
        map.put(csum,1);

        for(int e: arr){
            csum += e;

            // count += map.getOrDefault(csum,0);
            // map.put(csum, map.getOrDefault(csum,0) + 1);

            if(map.containsKey(csum)){
                count += map.get(csum);
                map.put(csum, map.get(csum) + 1);
            } else {
                map.put(csum,1);
            }
        }

        return count;
    }

    // Longest subarray with sum = k (https://www.geeksforgeeks.org/problems/longest-sub-array-with-sum-k0809/1)
    public int longestSubarray(int[] arr, int k) {
        HashMap<Integer,Integer> map = new HashMap<>();
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

    // Number of subarrays with sum = k (Leetcode 560)
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int count = 0;

        int csum = 0;
        map.put(csum,1);

        for(int e: nums){
            csum += e;

            // count += map.getOrDefault(csum-k,0);
            // map.put(csum, map.getOrDefault(csum,0) + 1);
            if (map.containsKey(csum - k)) { 
                count += map.get(csum - k);
            }

            if (!map.containsKey(csum)) {
                map.put(csum, 1); 
            } else {
                map.put(csum, map.get(csum) + 1);
            }
        }

        return count;
    }

    // leetcode 974 (Number of subarrays divisible by k)
    public int subarraysDivByK(int[] nums, int k) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int count = 0;

        int csum = 0;
        map.put(csum,1);

        for(int e: nums){
            csum += e;

            int rem = csum%k;
            if(rem < 0){
                rem += k;
            }

            count += map.getOrDefault(rem,0);
            map.put(rem, map.getOrDefault(rem,0) + 1);
        }

        return count;
    }

    // Leetcode 525 =====================================
    public int findMaxLength(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int ans = 0;

        int csum = 0; 
        map.put(csum, -1);

        for(int i=0; i<nums.length; i++){
            csum += (nums[i] == 0 ? -1 : nums[i]);

            if(map.containsKey(csum)){
                ans = Math.max(ans, i - map.get(csum));
            } else {
                map.put(csum, i);
            }
        }

        return ans;
    }

    class RandomizedSet {
        ArrayList<Integer> data;
        HashMap<Integer,Integer> map; // valVsIndex

        public RandomizedSet() {
            data = new ArrayList<>();
            map = new HashMap<>();
        }
        
        public boolean insert(int val) {
            if(map.containsKey(val)){
                return false;
            }

            map.put(val, data.size()); // value is inserted at data.size() index
            data.add(val);

            return true;
        }
        
        public boolean remove(int val) {
            if(map.containsKey(val) == false){
                return false;
            } 

            // find which idx this value is at
            int valIdx =  map.get(val);
            
            int lastIdxValue = data.get(data.size()-1);
            
            // swap with last index
            swap(valIdx,data.size()-1); 
            map.put(lastIdxValue, valIdx);

            // remove last index(val)
            data.remove(data.size()-1);
            map.remove(val);

            return true;       
        }
        
        public int getRandom() {
            int randomIdx = (int)(Math.random() * data.size());
            return data.get(randomIdx);
        }
    }

























    public static void main(String[] args){

    }
}