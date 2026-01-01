class Main {
    // Leetcode 933 (Number of recent calls) ================================
    class RecentCounter {
        LinkedList<Integer> que;

        public RecentCounter() {
            que = new LinkedList<>();    
        }
        
        public int ping(int t) {
            que.addLast(t);

            while(que.getFirst() < (t-3000)){
                que.removeFirst();
            }

            return que.size();
        }
    }

    // design hit counter(https://www.lintcode.com/problem/3662/) ===========================================
    public class HitCounter {
        LinkedList<Integer> que;
        public HitCounter() {
            que = new LinkedList<>();
        }

        /**
        * @param timestamp: the timestamp
        * @return: nothing
        */
        public void hit(int timestamp) {
            que.addLast(timestamp);
        }

        /**
        * @param timestamp: the timestamp
        * @return: the count of hits in recent 300 seconds
        */
        public int getHits(int timestamp) {
            while(que.size()>0 && que.getFirst() <= (timestamp - 300)){
                que.removeFirst();
            }

            return que.size();
        }
    }



    // Average of last k integers (https://www.lintcode.com/problem/642/)
    public class MovingAverage {
        LinkedList<Integer> que;
        int maxSize;
        long sum; // to store last sum of last k values

        /*
        * @param size: An integer
        */
        public MovingAverage(int size) {
            que = new LinkedList<>();
            maxSize = size;
            sum = 0;
        }

        /*
        * @param val: An integer
        * @return:  
        */
        public double next(int val) {
            que.addLast(val);
            sum += val;

            if(que.size() > maxSize){
                int frontValue = que.removeFirst();
                sum -= frontValue;
            }

            return (sum*1.0/que.size());
        }
    }

    // Sliding window maximum (Leetcode 239) ============================================
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;

        int[] ans = new int[n-k+1];
        int answerIdx = 0;

        LinkedList<Integer> deque = new LinkedList<>();

        for(int i=0; i<n; i++){
            if(deque.size()>0 && deque.getFirst() <= i-k){
                deque.removeFirst();
            }

            // if nums[i] is greater, than other elements cant be the answer
            while(deque.size()>0 && nums[deque.getLast()] < nums[i]){
                deque.removeLast();
            }

            deque.addLast(i);

            if(i >= k-1){
                ans[answerIdx] = nums[deque.getFirst()];
                answerIdx++;
            }
        }

        return ans;
    }

    // leetcode 862 ===========================================================
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;

        long[] pre = new long[n];
        pre[0] = nums[0];

        for(int i=1; i<n; i++){
            pre[i] = pre[i-1] + nums[i];
        }

        LinkedList<Integer> deque = new LinkedList<>();
        int ans = Integer.MAX_VALUE;

        for(int i=0; i<n; i++){
            if(pre[i] >= k){
                ans = Math.min(i+1, ans);
            }

            // this is to shorten the array
            while(deque.size()>0 && pre[i] - pre[deque.getFirst()] >= k){
                ans = Math.min(ans, i - deque.getFirst());
                deque.removeFirst();
            }

            // this is to remove negative elements
            while(deque.size()>0 && pre[i] <= pre[deque.getLast()]){
                deque.removeLast();
            }

            deque.push(i);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

































    public static void main(String[] args){

    }
}