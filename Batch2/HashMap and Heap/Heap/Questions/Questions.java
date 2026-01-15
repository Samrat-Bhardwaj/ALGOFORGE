import java.util.PriorityQueue;

class Questions {
    

    // Kth largest element (Leetcode 215) =======================================
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int ele: nums){
            pq.add(ele);

            if(pq.size() > k){
                pq.remove(); // (k+1)th largest of current set removed
            }
        }

        return pq.peek(); // minimum of k largest elements is kth largest
    }

    // Sort K-sorted Array (https://www.geeksforgeeks.org/problems/nearly-sorted-1587115620/1)
    public void nearlySorted(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int ansIdx = 0;

        for(int i=0; i<arr.length; i++){
            pq.add(arr[i]);

            if(pq.size() > k){
                arr[ansIdx] = pq.remove();
                ansIdx++;
            }
        }

        while(ansIdx < arr.length){
            arr[ansIdx] = pq.remove();
            ansIdx++;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((ListNode a, ListNode b) ->{
            return a.val - b.val;
        });

        for(ListNode head: lists){
            if(head != null){
                pq.add(head);
            }
        }

        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;

        while(pq.size() > 0){
            // remove minimum value node
            ListNode top = pq.remove();

            // insert its next node
            ListNode topKaNext = top.next;
            top.next = null;
            if(topKaNext != null){
                pq.add(topKaNext);
            }
            
            // attach it to final list
            curr.next = top;
            curr = curr.next;
        }

        return dummy.next;
    }

    class MedianFinder {
        PriorityQueue<Integer> left; // maxPQ
        PriorityQueue<Integer> right; // minPQ

        public MedianFinder() {
            left = new PriorityQueue<>(Collections.reverseOrder());
            right = new PriorityQueue<>();
        }
        
        public void addNum(int num) {
            if(left.size() == 0 || left.peek() >= num){
                left.add(num);
            } else {
                right.add(num);
            }

            int ls = left.size();
            int rs = right.size();

            if(rs > ls){ // if right has even one number extra, shift that to left
                left.add(right.remove());
            }

            if(ls - rs == 2){ 
                right.add(left.remove());
            } 
        }
        
        public double findMedian() {
            if(left.size() == right.size()){
                return (left.peek() + right.peek())*1.0/2;
            } else {
                return left.peek()*1.0;
            }
        }
    }

    // Merge k sorted arrays ( https://www.geeksforgeeks.org/problems/merge-k-sorted-arrays/1 )
    class Pair {
        int row;
        int col;
        int val;

        Public Pair(int row, int col, int val){
            this.row = row;
            this.col = col;
            this.val = val;
        }
    }
    public ArrayList<Integer> mergeArrays(int[][] mat) {
        // {row, col, mat[row][col]}
        // PriorityQueue<int[]> pq = new PriorityQueue<>((int[] a, int[] b) -> {
        //     return a[2] - b[2];
        // });

        PriorityQueue<Pair> pq = new PriorityQueue<>((Pair a, Pair b) -> {
            return a.val - b.val;
        });

        ArrayList<Integer> ans = new ArrayList<>();

        for(int i=0; i<mat.length; i++){
            // pq.add(new int[]{i,0,mat[i][0]});
            pq.add(new Pair(i,0,mat[i][0]));
        }

        while(pq.size() > 0){
            Pair top = pq.remove();

            int row = top.row;
            int col = top.col;
            int val = top.val;

            ans.add(val);
            
            if(col + 1 < mat[row].length){
                pq.add(new Pair(row, col+1, mat[row][col+1]));
            }
        }

        return ans;
    }






























    public static void main(String[] args){

    }
}