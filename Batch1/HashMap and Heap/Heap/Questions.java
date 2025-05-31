class Questions {

// leetcode 215 (Kth largest element)
public int findKthLargest(int[] nums, int k) { // NlogN
    Arrays.sort(nums);
    return nums[n-k];        
}

public int findKthLargest(int[] nums, int k) { // NlogN
    PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Collections.reverseOrder());

    for(int e: nums){ // nlogn
        maxPQ.add(e); // logn
    }        

    while(k-- > 1){
        maxPQ.remove();
    }

    return maxPQ.peek();
}

public int findKthLargest(int[] nums, int k) { // Nlogk, (worst case k == n)
    PriorityQueue<Integer> pq = new PriorityQueue<>();

    for(int i=0; i<nums.length; i++){
        pq.add(nums[i]); // logK

        if(pq.size() > k){
            // logK
            pq.remove(); // smallest element is removed leaving largest k elements
        }
    }

    return pq.peek();
}

// Sort nearly(K-sorted) sorted array, https://www.geeksforgeeks.org/problems/nearly-sorted-1587115620/1 
public void nearlySorted(int[] arr, int k) {
    PriorityQueue<Integer> pq = new PriorityQueue<>();

    int j = 0;
    for(int i=0; i<arr.length; i++){
        pq.add(arr[i]);

        if(pq.size() > k){
            arr[j] = pq.remove();
            j++;
        }
    }

    while(pq.size() > 0){
        arr[j++] = pq.remove();
    }
}


// leetcode 295 (Median in data-stream) ===========================
class MedianFinder {
    PriorityQueue<Integer> left;
    PriorityQueue<Integer> right;

    public MedianFinder() {
        left = new PriorityQueue<>(Collections.reverseOrder());
        right = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        if(left.size() ==0 || num <= left.peek()){
            left.add(num);
        } else {
            right.add(num);
        }

        int ls = left.size();
        int rs = right.size();

        if(rs > ls){ // left can have one extra element, right can't
            left.add(right.remove());
        } else if(ls - rs == 2){
            right.add(left.remove());
        }
    }
    
    public double findMedian() {
        if(left.size() == right.size()){
            return (left.peek()*1.0 + right.peek()*1.0)/(2.0);
        } else {
            return left.peek()*1.0;
        }
    }
}

// allowing right to have one extra element
class MedianFinder {
    PriorityQueue<Integer> left;
    PriorityQueue<Integer> right;

    public MedianFinder() {
        left = new PriorityQueue<>(Collections.reverseOrder());
        right = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        if(right.size() ==0 || num >= right.peek()){
            right.add(num);
        } else {
            left.add(num);
        }

        int ls = left.size();
        int rs = right.size();

        if(rs < ls){ // right can have one extra element, left can't
            right.add(left.remove());
        } else if(rs - ls == 2){
            left.add(right.remove());
        }
    }
    
    public double findMedian() {
        if(left.size() == right.size()){
            return (left.peek()*1.0 + right.peek()*1.0)/(2.0);
        } else {
            return right.peek()*1.0;
        }
    }
}

// leetcode 23 (Merge k sorted LinkedList)========================
public ListNode mergeKLists(ListNode[] lists) {
    PriorityQueue<ListNode> pq = new PriorityQueue<>((ListNode tthis, ListNode other)->{
        return tthis.val - other.val; // minPQ based on listNode values
        // return other.val - tthis.val; // maxPQ based on listNode values;
    });

    for(ListNode head: lists){
        if(head != null)
            pq.add(head);
    }

    ListNode dummy = new ListNode(-1);
    ListNode curr = dummy;

    while(pq.size()>0){
        ListNode top = pq.remove();
        ListNode topKaNext = top.next;
        top.next= null;

        curr.next = top;
        curr = curr.next;

        if(topKaNext != null){
            pq.add(topKaNext);
        }
    }

    return dummy.next;
}




























    public static void main(String[] args){
    
    }
}

