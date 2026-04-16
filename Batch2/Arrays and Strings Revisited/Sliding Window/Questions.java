class Questions {
    // Leetcode 3 (Longest substring with 0 duplicates )
    public int lengthOfLongestSubstring(String s) {
        int n = s.length;

        int si=0, ei=0, maxLen = 0;
        int count = 0; // count of duplicate characters

        int[] freq = new int[128];

        while(ei < n){
            if(freq[s.charAt(ei)] == 1){ // found duplicate character at ei
                count++;
            }
            freq[s.charAt(ei)]++;
            ei++;

            // fix window
            while(count > 0){
                if(freq[s.charAt(si)] == 2){ // found duplicate character at si, will remove
                    count--;
                }
                freq[s.charAt(si)]--;
                si++;
            }

            maxLen = Math.max(maxLen, ei - si);
        }

        return maxLen;
    }

    // Longest substring with maximum 2 distinct characters
    public static int lengthOfLongestSubstring(String s) {
		int n = s.length();
        
        int si=0, ei=0, maxLen=0;
        int count = 0; // count of distinct characters
        int[] freq = new int[128];

        while(ei < n){
            if(freq[s.charAt(ei)] == 0){ // new distinct character
                count++;
            }
            freq[s.charAt(ei)]++;
            ei++;

            // fix window
            while(count > 2){
                if(freq[s.charAt(si)] == 1){ // last occurrence of this character which will be removed now
                    count--;
                }
                freq[s.charAt(si)]--;
                si++;
            }

            maxLen = Math.max(maxLen, ei-si);
        }

        return maxLen;
	}
}