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

    // leetcode 143 (Re-order list)
    public void reorderList(ListNode head) {
        if(head == null || head.next == null){
            return;
        }

        ListNode mid = getMiddleNode(head);

        // extracting second half as list
        ListNode secondHead = mid.next;
        mid.next = null;

        secondHead = reverseList(secondHead);

        // Creating answer list
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        ListNode ptr1 = head;
        ListNode ptr2 = secondHead;

        while(ptr1!= null && ptr2 != null){
            // isolating ptr1
            ListNode ptr1KaNext = ptr1.next;
            ptr1.next = null;

            // attaching ptr1 to answer list
            curr.next = ptr1;

            // move ptr1 and curr
            ptr1 = ptr1KaNext;
            curr = curr.next;

            // isolating ptr2
            ListNode ptr2KaNext = ptr2.next;
            ptr2.next = null;

            // attaching ptr2 to answer list
            curr.next = ptr2;

            // move ptr2 and curr
            ptr2 = ptr2KaNext;
            curr = curr.next;
        }

        if(ptr1 != null){
            curr.next = ptr1;
        }
    }

    // leetcode 328 (Separate odd and even indexed elements)
    public ListNode oddEvenList(ListNode head) {
        ListNode oddDummy = new ListNode(-1);
        ListNode evenDummy = new ListNode(-1);

        ListNode curr = head;
        ListNode oddCurr = oddDummy;
        ListNode evenCurr = evenDummy;

        int pos = 1;

        while(curr != null){
            ListNode currKaNext = curr.next;
            curr.next = null;

            if(pos % 2 == 0){
                evenCurr.next = curr;
                evenCurr = evenCurr.next;
            } else {
                oddCurr.next = curr;
                oddCurr = oddCurr.next;
            }

            curr = currKaNext;
            pos++;
        }

        ListNode oddListHead = oddDummy.next;
        ListNode evenListHead = evenDummy.next;

        oddCurr.next = evenListHead;
        return oddListHead;
    }

    // https://www.geeksforgeeks.org/problems/segregate-even-and-odd-nodes-in-a-linked-list5035/1
    Node divide(Node head) {
        Node oddDummy = new Node(-1);
        Node evenDummy = new Node(-1);

        Node curr = head;
        Node evenCurr = evenDummy;
        Node oddCurr = oddDummy;

        while(curr != null){
            Node currKaNext = curr.next;
            curr.next = null;

            if(curr.data % 2 == 0){
                evenCurr.next = curr;
                evenCurr = evenCurr.next;
            } else {
                oddCurr.next = curr;
                oddCurr = oddCurr.next;
            }

            curr = currKaNext;
        }

        evenCurr.next = oddDummy.next;
        return evenDummy.next;
    }

    // Leetcode 25 (Reverse nodes in k-group) 
    class Solution {
        public int getSize(ListNode head){
            ListNode temp = head;
            int size = 0;

            while(temp != null){
                size++;
                temp = temp.next;
            }
            
            return size;
        }

        ListNode tHead;
        ListNode tTail;
        public void addFirst(ListNode node){
            if(tHead == null){
                tHead = node;
                tTail = node;
            } else {
                node.next = tHead;
                tHead = node;
            }
        }

        public ListNode reverseKGroup(ListNode head, int k) {
            ListNode oHead = null;
            ListNode oTail = null;
            tHead = null;
            tTail = null;

            int size = getSize(head);
            ListNode curr = head;

            while(size >= k){
                int K = k;

                // remove next K nodes and make smaller list
                while(K-- > 0){
                    ListNode currKaNext = curr.next;
                    curr.next = null;

                    addFirst(curr);

                    curr = currKaNext;
                    size--;
                }

                // attach smaller (temp) list to original list
                if(oHead == null){
                    oHead = tHead;
                    oTail = tTail;
                } else {
                    oTail.next = tHead;
                    oTail = tTail;
                }

                tHead = null;
                tTail = null;
            }

            oTail.next = curr;
            return oHead;
        }
    }
}