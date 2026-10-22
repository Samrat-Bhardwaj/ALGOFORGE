import java.util.HashMap;

class Questions {

    // Most Frequent Character (https://www.geeksforgeeks.org/problems/maximum-occuring-character-1587115620/1)
    public static char getMaxOccuringChar(String s) {
        HashMap <Character, Integer> freMap = new HashMap<>();

        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);

            if(freMap.containsKey(ch) == false){
                freMap.put(ch, 1);
            } else {
                int olderFre = freMap.get(ch);
                freMap.put(ch, olderFre + 1);
            }
        }

        char ans = '\0'; // initialised with null
        int maxFre = 0;

        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);

            if(freMap.get(ch) > maxFre){
                ans = ch;
                maxFre = freMap.get(ch);
            } else if(freMap.get(ch) == maxFre && ans > ch){
                ans = ch; // replacing with lexiographically smaller character
            }
        }

        return ans;
    }

    // Leetcode 349 (Intersection of two arrays)
    public int[] intersection(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();

        // add nums1 to map
        for(int e: nums1){
            map.put(e, 1); // we dont care about frequency
        }

        ArrayList<Integer> ans = new ArrayList<>();

        for(int e: nums2){
            if(map.containsKey(e)){
                ans.add(e);
                map.remove(e);
            }
        }

        int[] res = new int[ans.size()];
        for(int i=0; i<res.length; i++){
            res[i] = ans.get(i);
        }

        // return ans.stream().mapToInt(i -> i).toArray();
        return res;
    }

    // Leetcode 349 using HashSet (Intersection of two arrays)
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set = new HashSet<>();

        // add nums1 to map
        for(int e: nums1){
            set.add(e); // we dont care about frequency
        }

        ArrayList<Integer> ans = new ArrayList<>();

        for(int e: nums2){
            if(set.contains(e)){
                ans.add(e);
                set.remove(e);
            }
        }

        int[] res = new int[ans.size()];
        for(int i=0; i<res.length; i++){
            res[i] = ans.get(i);
        }

        // return ans.stream().mapToInt(i -> i).toArray();
        return res;
    }

    public static ArrayList<Integer> commonElements(int array1[], int array2[]) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int e: array1){
            if(map.containsKey(e) == false){
                map.put(e, 1);
            } else {
                int olderFre = map.get(e);
                map.put(e, olderFre+1);
            }

            // map.put(e, map.getOrDefault(e,0) + 1);
        }

        ArrayList<Integer> ans = new ArrayList<>();

        for(int e: array2){
            if(map.containsKey(e) && map.get(e) > 0){
                ans.add(e);
                map.put(e, map.get(e) - 1); // reduce frequency
            }
        }

        Collections.sort(ans);
        return ans;
    }

    // Leetcode 128 (Longest Consecutive Sequence)
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();

        for(int e: nums){
            set.add(e);
        }

        int res = 0;

        for(int num: nums){
            int left = num - 1;
            int right = num + 1;

            while(set.contains(left)){
                left--;
            }

            while(set.contains(right)){
                right++;
            }

            res = Math.max(res, right - left - 1);
        }

        return res;
    }
































    public static void main(String[] args){

    }
}