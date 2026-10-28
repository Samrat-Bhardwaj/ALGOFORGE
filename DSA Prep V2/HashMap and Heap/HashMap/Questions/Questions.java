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

    // Longest subarray with zero sum (https://www.geeksforgeeks.org/problems/largest-subarray-with-0-sum/1)
    int maxLength(int arr[]) {
        HashMap<Integer,Integer> sumVsIdx = new HashMap<>();

        int csum = 0;
        int res = 0;
        sumVsIdx.put(0,-1);

        for(int idx=0; idx<arr.length; idx++){
            csum += arr[idx];

            if(sumVsIdx.containsKey(csum)){
                res = Math.max(res, idx - sumVsIdx.get(csum));
            } else {
                sumVsIdx.put(csum,idx);
            }
        }

        return res;
    }

    // Number of subarrays with zero sum (https://www.geeksforgeeks.org/problems/zero-sum-subarrays1825/1)
    public int findSubarray(int[] arr) {
        HashMap<Long, Integer> sumFre = new HashMap<>();

        sumFre.put(0l,1); // we have seen zero sum once even before starting
        long csum = 0; // maximum sum can be equal to 10^15
        int count = 0;

        for(int idx=0; idx<arr.length; idx++){
            csum += arr[idx];

            count += sumFre.getOrDefault(csum,0);
            sumFre.put(csum, sumFre.getOrDefault(csum,0) + 1);
        }

        return count;
    }

    // Longest subarray with k Sum  (https://www.geeksforgeeks.org/problems/longest-sub-array-with-sum-k0809/1)
    public int longestSubarray(int[] arr, int k) {
        HashMap<Integer,Integer> sumVsIdx = new HashMap<>();

        int csum = 0;
        int res = 0;
        sumVsIdx.put(0,-1);

        for(int idx=0; idx<arr.length; idx++){
            csum += arr[idx];

            if(sumVsIdx.containsKey(csum - k)){
                res = Math.max(res, idx - sumVsIdx.get(csum - k));
            } 
            
            if(sumVsIdx.containsKey(csum) == false) {
                sumVsIdx.put(csum,idx);
            }
        }

        return res;
    }

    // LRU Cache (Leetcode 146)
    class LRUCache {
        
        class Node {
            int key;
            int value;
            Node prev;
            Node next;

            public Node(int key, int value){
                this.key = key;
                this.value = value;
            }
        }

        HashMap<Integer, Node> map;

        Node head;
        Node tail;
        int size;
        int maxCapacity;

        private void addLast(Node newNode){
            if(head == null){
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            }

            size++;
        }

        private void removeNode(Node toRemove){
            if(head == tail){
                head = null;
                tail = null;
            } else if(toRemove == head){
                head = head.next;
                head.prev = null;
            } else if(toRemove == tail){
                tail = tail.prev;
                tail.next = null;
            } else {
                Node prevNode = toRemove.prev;
                Node nextNode = toRemove.next;

                prevNode.next = nextNode;
                nextNode.prev = prevNode;

                toRemove.prev = null;
                toRemove.next = null;
            }

            size--;
        }

        public LRUCache(int capacity) {
            maxCapacity = capacity;
            map = new HashMap<>();
            head = null;
            tail = null;
            size = 0;
        }
        
        public int get(int key) {
            if(map.containsKey(key) == false){
                return -1;
            }

            Node nodeWithKey = map.get(key);
            removeNode(nodeWithKey);
            addLast(nodeWithKey);

            return nodeWithKey.value;
        }
        
        public void put(int key, int value) {
            if(map.containsKey(key)){
                Node nodeWithKey = map.get(key);
                nodeWithKey.value = value;

                get(key); // we accessed this node, so position should update;
            } else {
    
                Node newNode = new Node(key, value);
                addLast(newNode);
                map.put(key, newNode);

                if(size > maxCapacity){
                    int headKey = head.key;
                    map.remove(headKey);
                    removeNode(head); // removing least recently used
                }
            }
        }
    }































    public static void main(String[] args){

    }
}