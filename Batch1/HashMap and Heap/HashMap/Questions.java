class Questions {

// Most frequent character (https://www.geeksforgeeks.org/problems/maximum-occuring-character-1587115620/1) ================

public static char getMaxOccuringChar(String s) {
    HashMap<Character, Integer> map = new HashMap<>();
    int maxFre = 0;
    char maxFreCharacter = '&';

    for(int i=0; i<s.length(); i++){
        char ch = s.charAt(i);

        if(map.containsKey(ch) == false){
            map.put(ch,1);
        } else {
            int olderFrequency = map.get(ch);
            map.put(ch, olderFrequency + 1);
        }
    }

    for(int i=0; i<s.length(); i++){
        char ch = s.charAt(i);

        if(map.get(ch) > maxFre){
            maxFre = map.get(ch);
            maxFreCharacter = ch;
        } else if(map.get(ch) == maxFre && ch < maxFreCharacter){
            maxFreCharacter = ch;
        }
    }

    return maxFreCharacter;
}


// leetcode 3005 ==========================
public int maxFrequencyElements(int[] nums) {
    HashMap<Integer, Integer> freMap = new HashMap<>();
    int maxFre = 0;
    int count = 0;

    for(int e: nums){
        if(freMap.containsKey(e) == false){
            freMap.put(e,1);
        } else {
            int olderFrequency = freMap.get(e);
            freMap.put(e, olderFrequency + 1);
        }

        maxFre = Math.max(maxFre, freMap.get(e));
    }

    for(int e: nums){
        if(freMap.get(e) == maxFre){
            count++;
        }
    }

    return count;
}


// leetcode 349 using map ===========================================
public int[] intersection(int[] nums1, int[] nums2) {
    HashMap<Integer, Integer> map = new HashMap<>();

    for(int e: nums1){
        map.put(e,1); // we don't care about frequency of elements

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

    return res;
}

// leetcode 349 using set ===========================================
public int[] intersection(int[] nums1, int[] nums2) {
    HashSet<Integer> set = new HashSet<>();

    for(int e: nums1){
        set.add(e);
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

    return res;
}

// http://geeksforgeeks.org/problems/common-elements5420/1
public static ArrayList<Integer> commonElements(int a[], int b[]) {
    HashMap<Integer, Integer> freMap = new HashMap<>();

    for(int e: a){
        if(freMap.containsKey(e) == false){
            freMap.put(e,1);
        } else {
            int olderFrequency = freMap.get(e);
            freMap.put(e, olderFrequency + 1);
        }
        // int olderFrequency = freMap.getOrDefault(e, 0);
        // freMap.put(e, olderFrequency + 1);
    }        

    ArrayList<Integer> ans = new ArrayList<>();
    for(int e: b){
        if(freMap.containsKey(e) && freMap.get(e) > 0){
            ans.add(e);

            int olderFrequency = freMap.get(e);
            freMap.put(e, olderFrequency - 1);
        }
    }

    Collections.sort(ans);
    return ans;
}

// leetcode 128 ===============================================
public int longestConsecutive(int[] nums) {
    HashSet<Integer> set = new HashSet<>();
    int ans = 0;

    for(int e: nums){
        set.add(e);
    }

    for(int currentVal: nums){
        if(set.contains(currentVal) == false){
            continue;
        }

        int prevVal = currentVal - 1;
        int nextVal = currentVal + 1;

        while(set.contains(prevVal)){
            set.remove(prevVal);
            prevVal--;
        }

        while(set.contains(nextVal)){
            set.remove(nextVal);
            nextVal++;
        }

        ans = Math.max(ans, nextVal - prevVal - 1);
        set.remove(currentVal);
    }

    return ans;
}

// leetcode 1497 ===========================================
public boolean canArrange(int[] arr, int k) {
    HashMap<Integer, Integer> map = new HashMap<>();

    for(int i=0; i<arr.length; i++){
        int remainder = arr[i] % k;
        remainder = (remainder + k) % k; // to handle negative numbers
        int remainderRequired = (k - remainder) % k; // %k will handle rem == 0

        if(map.containsKey(remainderRequired) && map.get(remainderRequired) > 0){
            map.put(remainderRequired, map.get(remainderRequired) - 1);
        } else {
            map.put(remainder, map.getOrDefault(remainder, 0) + 1);
        }
    }

    for(int x: map.keySet()){
        if(map.get(x) > 0) return false;
    }

    return true;
}

// https://www.geeksforgeeks.org/problems/count-distinct-elements-in-every-window/1
ArrayList<Integer> countDistinct(int arr[], int k) {
    ArrayList<Integer> ans = new ArrayList<>();
    
    HashMap<Integer, Integer> freMap = new HashMap<>();

    for(int i=0; i<arr.length; i++){
        freMap.put(arr[i], freMap.getOrDefault(arr[i], 0) + 1);

        if(i == k-1){ // first window
            ans.add(freMap.size());
        } else if(i >= k){
            freMap.put(arr[i-k], freMap.get(arr[i-k]) - 1);

            if(freMap.get(arr[i-k]) == 0) freMap.remove(arr[i-k]);

            ans.add(freMap.size());
        }
    }

    return ans;
}

// largest subarray with zero sum (https://www.geeksforgeeks.org/problems/largest-subarray-with-0-sum/1) ================
int maxLen(int arr[]) {
    HashMap<Integer, Integer> sumVsIndex = new HashMap<>();
    int csum = 0;
    int maxLength = 0;
    sumVsIndex.put(0, -1); // to handle when csum  = 0;

    for(int i=0; i<arr.length; i++){
        csum += arr[i];

        if(sumVsIndex.containsKey(csum)){
            maxLength = Math.max(maxLength, i - sumVsIndex.get(csum));
        } else {
            sumVsIndex.put(csum, i);
        }
    }

    return maxLength;    
}

// number of subarray with zero sum (https://www.geeksforgeeks.org/problems/zero-sum-subarrays1825/1)
int findSubarray(int[] arr) {
    HashMap<Integer, Integer> sumFrequency = new HashMap<>();
    int csum  = 0;
    int count = 0;
    sumFrequency.put(0,1);

    for(int e: arr){
        csum += e;

        if(sumFrequency.containsKey(csum)){
            count += sumFrequency.get(csum);
        }

        sumFrequency.put(csum, sumFrequency.getOrDefault(csum,0) + 1);
    }

    return count;
}

// longest subaray with sum K (https://www.geeksforgeeks.org/problems/longest-sub-array-with-sum-k0809/1)
public int longestSubarray(int[] arr, int k) {
    HashMap<Integer,Integer> sumVsIndex = new HashMap<>();
    int ans = 0;
    int csum = 0;

    sumVsIndex.put(csum , -1);

    for(int i=0; i<arr.length; i++){
        csum += arr[i];

        if(sumVsIndex.containsKey(csum-k)){
            ans = Math.max(ans, i - sumVsIndex.get(csum - k));
        }

        // sumVsIndex.put(sumVsIndex.getOrDefault(csum,i));
        if(sumVsIndex.containsKey(csum) == false){
            sumVsIndex.put(csum, i);
        }
    }

    return ans;
}

// Leetcode 560 , Number of subarrays with sum K
public int subarraySum(int[] nums, int k) {
    HashMap<Integer,Integer> sumFrequency = new HashMap<>();
    int csum = 0;
    int res = 0;
    sumFrequency.put(0,1);

    for(int e: nums){
        csum += e;

        // res += sumFrequency.getOrDefault(csum-k, 0);
        if(sumFrequency.containsKey(csum-k)){
            res += sumFrequency.get(csum-k);
        }

        sumFrequency.put(csum, sumFrequency.getOrDefault(csum, 0) + 1);
    }

    return res;
}

// leetcode 974 , Number of subarrays divisible by k
public int subarraysDivByK(int[] nums, int k) {
    HashMap<Integer, Integer> remFrequency = new HashMap<>();
    int res = 0, csum = 0;

    remFrequency.put(0,1);

    for(int e: nums){
        csum += e;

        int remainder = csum % k;
        if(remainder < 0){
            remainder += k;
        }

        res += remFrequency.getOrDefault(remainder, 0);
        remFrequency.put(remainder, remFrequency.getOrDefault(remainder,0) + 1);
    }  

    return res;      
}

// leetcode 525, Longest subarray with equal 0s and 1s
public int findMaxLength(int[] arr) {
    HashMap<Integer, Integer> sumVsIndex = new HashMap<>();
    int csum = 0;
    int maxLength = 0;
    sumVsIndex.put(0, -1); // to handle when csum  = 0;

    for(int i=0; i<arr.length; i++){
        csum += (arr[i] == 0 ? -1 : arr[i]);

        if(sumVsIndex.containsKey(csum)){
            maxLength = Math.max(maxLength, i - sumVsIndex.get(csum));
        } else {
            sumVsIndex.put(csum, i);
        }
    }

    return maxLength; 
}

// leetcode 146 (LRU cache) ===================================
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

    Node head;
    Node tail;

    HashMap<Integer, Node> keyVsNode;
    int maxCapacity;
    int size;

    private void addLast(Node nn){
        if(head == null){
            head = tail = nn;
        } else {
            tail.next = nn;
            nn.prev = tail;
            tail = nn;
        }
        size++;
    }

    private void removeNode(Node nn){
        if(head == tail){
            head = tail = null;
        } else if(head == nn){
            head = head.next;
        } else if(tail == nn){
            tail = tail.prev;
        } else {
            Node nodeKaPrev = nn.prev;
            Node nodeKaNext = nn.next;

            nodeKaPrev.next = nodeKaNext;
            nodeKaNext.prev = nodeKaPrev;
        }
        size--;
    }

    public LRUCache(int capacity) {
        head = null;
        tail = null;
        size = 0;
        maxCapacity = capacity;
        keyVsNode = new HashMap<>();
    }
    
    public int get(int key) { // get address, and add this node to the end of LL
        if(keyVsNode.containsKey(key) == false){
            return -1;
        }
        // get node with given key
        Node keyNode = keyVsNode.get(key);

        // remove node from between the list
        removeNode(keyNode);
        // add to the last as this the most recently used n
        addLast(keyNode);

        return keyNode.value;
    }
    
    public void put(int key, int value) { // add new node at the end
        // if key does not exist
        if(keyVsNode.containsKey(key) == false){
            // create a new node
            Node nn = new Node(key, value);
            keyVsNode.put(key, nn);

            // add at last 
            addLast(nn);

            // remove LRU node if capacity exceeds maxCapacity 
            if(size > maxCapacity){
                // get LRU node
                Node headNode = head;
                int headKey = headNode.key;

                // remove LRU node
                keyVsNode.remove(headKey); // removed from map
                removeNode(headNode); // removed from List
            }
        } else {
            // update the node to most recently used
            int givenKeyValue = get(key);

            // find this node
            Node givenKeyNode = keyVsNode(key);

            // update the value
            givenKeyNode.value = value;
        }
    }
}

// leetcode 895 (Max frequency stack) =========================
class FreqStack {
    HashMap<Integer, Integer> freMap;
    HashMap<Integer, LinkedList<Integer>> frequencyStack;
    int maxFrequency;

    public FreqStack() {
        freMap = new HashMap<>();
        frequencyStack = new HashMap<>();
        maxFrequency = 0;
    }
    
    public void push(int val) {
        int currFre = freMap.getOrDefault(val, 0);
        int updatedFrequency = currFre + 1;

        freMap.put(val, updatedFrequency);
        
        if(frequencyStack.containsKey(updatedFrequency) == false){
            frequencyStack.put(updatedFrequency, new LinkedList<>());
        }

        frequencyStack.get(updatedFrequency).addFirst(val);

        maxFrequency = Math.max(maxFrequency, updatedFrequency);
    }
    
    public int pop() {
        int topValue = frequencyStack.get(maxFrequency).removeFirst();
        
        freMap.put(topValue, freMap.get(topValue) - 1);
        
        if(frequencyStack.get(maxFrequency).size() == 0){
            maxFrequency--;
        }

        return topValue;
    }
}

// leetcode 380 (Insert, delete, getRandom in O(1))
class RandomizedSet {
    HashMap<Integer,Integer> map;
    ArrayList<Integer> data;

    public RandomizedSet() {
        map = new HashMap<>();
        data = new ArrayList<>();
    }
    
    public boolean insert(int val) {
        if(map.containsKey(val)){
            return false;
        }

        map.put(val, data.size());
        data.add(val);

        return true;
    }
    
    public boolean remove(int val) {
        if(map.containsKey(val) == false){
            return false;
        }

        int valIdx = map.get(val);
        int dataAtLast = data.get(data.size()-1);

        data.set(valIdx, dataAtLast);
        map.put(dataAtLast, valIdx);

        map.remove(val);
        data.remove(data.size()-1);

        return true;
    }
    
    public int getRandom() {
        int idx = new Random().nextInt(data.size());
        return data.get(idx);
    }
}

// Rest of the hasmap Heap problems are solved with A&S.

















    public static void main(String[] args){
    
    }
}

