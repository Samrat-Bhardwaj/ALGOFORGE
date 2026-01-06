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


























    public static void main(String[] args){

    }
}