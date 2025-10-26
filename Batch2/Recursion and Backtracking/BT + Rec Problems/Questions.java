class Questions {
    // https://www.geeksforgeeks.org/problems/friends-pairing-problem5425/1
    public long countFriendsPairings(int n) {
        if(n <= 2) return n;

        long single = countFriendsPairings(n-1);
        long pairUp = countFriendsPairings(n-2);

        long allPairings = single + (n-1)*pairUp;

        return allPairings;
    }

    // https://www.geeksforgeeks.org/problems/josephus-problem/1

    // calculating josephus in 0 base indexing
    public int josephus_rec(int n, int k){
        if(n == 0){
            return 0;
        }
        
        int smallAns = josephus_rec(n-1,k);
        int ans = (smallAns + k) % n;

        return ans;
    }
    
    // calculating in zero base indexing, converting to 1 base
    public int josephus(int n, int k) {
        return josephus_rec(n,k) + 1;
    }
}