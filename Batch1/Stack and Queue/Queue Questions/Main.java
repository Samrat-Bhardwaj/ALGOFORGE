class Main {
    // leetcode 933 ======================
    class RecentCounter {

        LinkedList<Integer> que;
        public RecentCounter() {
            que = new LinkedList<>();
        }
        
        public int ping(int t) {
            while(que.size()>0 && que.getFirst() < t-3000){
                que.removeFirst();
            }

            que.addLast(t);

            return que.size();
        }
    }

    // lintcode 362 (https://www.lintcode.com/problem/3662/) ==============================
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
            while(que.size()>0 && que.getFirst() <= timestamp - 300){
                que.removeFirst();
            }

            return que.size();
        }
    }

    // lintcode 642 ===============
    public class MovingAverage {
        LinkedList<Integer> que;
        int maxSize;
        long sum;
        public MovingAverage(int size) {
            que = new LinkedList<>();
            maxSize = size;
            sum  = 0;
        }

        public double next(int val) {
            // write your code here
            sum += val;
            que.addLast(val);

            if(que.size() > maxSize){
                int firstVal = que.removeFirst();
                sum -= firstVal;
            }

            return sum/(que.size()*1.0);;
        }
    }

    // leetcode 239 ==========================================
    public int[] maxSlidingWindow(int[] nums, int k) {
        LinkedList<Integer> que = new LinkedList<>();

        int n = nums.length;
        int[] ans = new int[n - k + 1];
        int ansIndex = 0;

        for(int i=0; i<n; i++){
            if(que.size()>0 && que.getFirst() <= i-k){
                que.removeFirst();
            }

            while(que.size()>0 && nums[que.getLast()] < nums[i]){
                que.removeLast();
            }

            que.addLast(i);
            if(i >= k - 1){
                ans[ansIndex] = nums[que.getFirst()];
                ansIndex++;
            }
        }

        return ans;
    }

    // leetcode 862 =======================================
    public int shortestSubarray(int[] nums, int k) {
        LinkedList<Integer> dq = new LinkedList<>();
        int n = nums.length;

        int ans = Integer.MAX_VALUE;

        int[] pre = new int[n];
        pre[0] = nums[0];

        for(int i=1; i<n; i++){
            pre[i] = pre[i-1] + nums[i];
        }

        for(int i=0; i<nums.length; i++){
            if(pre[i] >= k){
                ans = Math.min(i + 1, ans);
            }

            // removing front elements to try and get a better answer
            while(dq.size()>0 && pre[i] - pre[dq.getFirst()] >= k){
                ans = Math.min(ans, i - dq.getFirst());
                dq.pop();
            }

            // if today's sum is smaller, then "i" has a (-)ve number
            while(dq.size()>0 && pre[i] <= pre[dq.getLast()]){
                dq.removeLast();
            }
            dq.addLast(i);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static void main(String[] args){

    }
}