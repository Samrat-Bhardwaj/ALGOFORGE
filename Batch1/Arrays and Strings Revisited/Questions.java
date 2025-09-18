class Questions {
    // Segregate positive and negative numbers
    public static void swap(int[] arr, int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void Rearrange(int arr[], int n) {
        int i = -1;
        int j = n-1;

        while(i < j){
            if(arr[i+1] < 0){
                i++;
            } else {
                swap(arr,i+1,j);
                j--;
            }
        }
    }

    // Segregate 0s and 1s (https://www.geeksforgeeks.org/problems/segregate-0s-and-1s5106/1) ======================
    // https://www.geeksforgeeks.org/problems/segregate-0s-and-1s5106/1
    void segregate0and1(int[] arr) {
        int n = arr.length;
        int i = -1;
        int j = n-1;

        while(i < j){
            if(arr[i+1] == 0){
                i++;
            } else {
                swap(arr,i+1,j);
                j--;
            }
        }
    }

    // Leetcode 75 (https://leetcode.com/problems/sort-colors/description/) ================================
    public void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void sortColors(int[] nums) {
        // {0,p1} -> zeros
        // {p1+1,p2-1} -> ones
        // {p2, p3} -> unexplored
        // {p3, n-1} -> twos
        int n = nums.length;

        int p1 = -1;
        int p2 = 0;
        int p3 = n-1;

        while(p2 <= p3){
            if(nums[p2] == 0){
                p1++; // increasing zero area, found one more zero
                swap(nums, p1, p2); // getting one of new p1 and sending zero at new one
                p2++; // area of one shifted
            } else if(nums[p2] == 1){
                p2++; // area of one inc
            } else {
                swap(nums,p2,p3);
                p3--;
            }
        }
    }


    // SLIDING WINDOW ===========================================================

    // Leetcode 3 (Longest substring with no repeating characters) ==========================
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int si = 0, ei =0, count=0, len=0;

        int[] fre = new int[128];

        while(ei < n){
            if(fre[s.charAt(ei++)]++ == 1){
                count++;
            }

            while(count > 0){
                if(fre[s.charAt(si++)]-- == 2){
                    count--;
                }
            }

            len = Math.max(len, ei-si); 
        }

        return len;
    }

    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int si = 0, ei =0, count=0, len=0;

        int[] fre = new int[128];

        while(ei < n){
            if(fre[s.charAt(ei)] == 1){
                count++;
            }
            fre[s.charAt(ei)]++;
            ei++;
            

            while(count > 0){
                if(fre[s.charAt(si)] == 2){
                    count--;
                }
                fre[s.charAt(si)]--;
                si++;
            }

            len = Math.max(len, ei-si); 
        }

        return len;
    }


    // Length of longest substring with at most 2 distinct characters =========================
    public static int lengthOfLongestSubstring(String s) {
		int n = s.length();
        int si = 0, ei =0, count=0, len=0;

        int[] fre = new int[128];

        while(ei < n){
            if(fre[s.charAt(ei++)]++ == 0){
                count++;
            }

            while(count > 2){
                if(fre[s.charAt(si++)]-- == 1){
                    count--;
                }
            }

            len = Math.max(len, ei-si); 
        }

        return len;
	}

    // Longest substring with exact K distinct (https://www.geeksforgeeks.org/problems/longest-k-unique-characters-substring0853/1)
    public int longestKSubstr(String s, int k) {
        int n = s.length();
        int si = 0, ei =0, count=0, len=0;

        int[] fre = new int[128];

        while(ei < n){
            if(fre[s.charAt(ei++)]++ == 0){
                count++;
            }

            while(count > k){
                if(fre[s.charAt(si++)]-- == 1){
                    count--;
                }
            }
            
            if(count == k){
                len = Math.max(len, ei-si); 
            }
        }

        return len;
    }
































    public static void main(String[] args){
        int[] arr = {-1,-2,-8,11,40,-19,20,-10,90,-34};

        Rearrange(arr, arr.length);
        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i] +" ");
        }
    }
}