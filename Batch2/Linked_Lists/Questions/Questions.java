class Questions {
    // leetcode 206 (Reverse LL) ======================================
    public ListNode reverseList(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;

        while(curr != null){
            // find curr ka Next
            ListNode currKaNext = curr.next;

            // change connections -> point to prev node
            curr.next = prev;

            // move pointers for next iteration
            prev = curr;
            curr = currKaNext;
        }

        // prev pointer is not at new head;
        return prev; 
    }

    // Kth node from end of linked list (https://www.geeksforgeeks.org/problems/nth-node-from-end-of-linked-list/1)
    int getKthFromLast(Node head, int k) {
        Node slow = head;
        Node fast = head;

        // moving fast k steps ahead of slow
        while(k-- > 0){
            if(fast == null){ // moving k steps ahead is not possible -> list size is smaller than k
                return -1;
            }

            Node fastKaNext = fast.next;
            fast = fastKaNext;
        }

        // moving slow and fast together until fast is at end (null)
        while(fast != null){
            Node slowKaNext = slow.next;
            slow = slowKaNext;

            Node fastKaNext = fast.next;
            fast = fastKaNext;
        }

        return slow.data;
    }

    // generic Middle of linkedList (in case of even, return first mid)
    public ListNode getMiddleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while(fast.next != null && fast.next.next != null){ // first condition for odd-size LL, second for even
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    // Leetcode 876, Middle of linkedList (in case of even, return second mid)
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null){ // first condition for even-size LL, second for odd
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }


    // Leetcode 234 = Palindrome Linked List =======================================
    public boolean isPalindrome(ListNode head) {
        ListNode mid = getMiddleNode(head);
        ListNode secondHead = mid.next;

        // breaking two halves
        mid.next = null;

        secondHead = reverseList(secondHead);

        ListNode ptr1 = head, ptr2 = secondHead;
        boolean isPall = true;
        
        while(ptr1 != null && ptr2 != null){
            if(ptr1.val != ptr2.val){
                isPall = false;
                break;
            }

            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }

        // fixing the given LL again
        secondHead = reverseList(secondHead); // reversing again to get to original state
        mid.next = secondHead; // attaching both the halves of linkedlist
        
        return isPall;
    }
}