class Questions {
    // Kth largest element (Leetcode 215) (NlogN) ==========
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for(int e: nums){
            pq.add(e);
        }

        k--;
        while(k > 0){
            pq.remove();
            k--;
        }

        return pq.peek();
    }

    // Kth largest element (Leetcode 215) (NlogK) ==========
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int e: nums){
            pq.add(e);

            if(pq.size() > k){
                pq.remove();
            }
        }

        return pq.peek();
    }

    // Sort k-sorted array (https://www.geeksforgeeks.org/problems/nearly-sorted-1587115620/1)
    public void nearlySorted(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        int ansIdx = 0;
    
        for(int i=0; i<arr.length; i++){
            pq.add(arr[i]);
            
            if(pq.size() > k){ // if (i >= k)
                arr[ansIdx] = pq.poll();
                ansIdx++;
            }
        }
        
        while(pq.size() > 0){
            arr[ansIdx++] = pq.poll();
        }
    }
}