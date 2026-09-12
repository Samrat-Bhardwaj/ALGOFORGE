class Main {
    // Leetcode 876 -> Find middle node 
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        
        while(fast!= null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    // helper function to find middle of linkedList (first middle in case of even)
    public ListNode findMiddleNode(ListNode head){
        ListNode slow = head;
        ListNode fast = head;
        
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
    
    // helper function to reverse linkedlist and return new head
    public ListNode revereseLinkedList(ListNode head){
        ListNode curr = head;
        ListNode prev = null;

        while(curr != null){
            ListNode currKaNext = curr.next;

            curr.next = prev;

            prev = curr;
            curr = currKaNext;
        }

        return prev; // new head
    }

    // Leetcode 234 (Pallindrome LinkedList)
    public boolean isPalindrome(ListNode head) {
        // divide into two halves
        ListNode midNode = findMiddleNode(head);

        ListNode secondHalfHead = midNode.next;
        midNode.next = null;

        // reverse second half
        secondHalfHead = revereseLinkedList(secondHalfHead);

        // compare two halves
        ListNode temp1 = head;  
        ListNode temp2 = secondHalfHead;

        while(temp1 != null && temp2 != null){
            if(temp1.data != temp2.data){
                return false;
            }

            temp1 = temp1.next;
            temp2 = temp2.next;
        }

        return true;
    }

    // Leetcode 143 (Reorder list) ==============================
    public void reorderList(ListNode head) {
        // find two halves
        ListNode midNode = findMiddleNode(head);

        ListNode secondHalfHead = midNode.next;
        midNode.next = null;

        // reverse the second half
        secondHalfHead = revereseLinkedList(secondHalfHead);

        // combine these two
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        ListNode ptr1 = head;
        ListNode ptr2 = secondHalfHead;

        while(ptr1 != null && ptr2 != null){
            // isolate ptr1
            ListNode ptr1KaNext = ptr1.next;
            ptr1.next = null;

            // connect to ans linkedList
            curr.next = ptr1;

            // move ptr1 and curr
            ptr1 = ptr1KaNext;
            curr = curr.next;

            // isolate ptr2
            ListNode ptr2KaNext = ptr2.next;
            ptr2.next = null;

            // connect to ans linkedList
            curr.next = ptr2;

            // move ptr2 and curr
            ptr2 = ptr2KaNext;
            curr = curr.next;
        }

        if(ptr1 != null){ // odd case
            curr.next = ptr1;
        }
    }

    // Leetcode 328 (odd even linked list)
    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }

        ListNode oddDummy = new ListNode(-1);
        ListNode evenDummy = new ListNode(-1);

        ListNode ptr = head;
        ListNode oddTail = oddDummy;
        ListNode evenTail = evenDummy;

        int idx = 1;

        while(ptr != null){
            // isolate ptr
            ListNode ptrKaNext = ptr.next;
            ptr.next = null;

            // connect with even or odd
            if(idx % 2 == 0){
                evenTail.next = ptr;
                evenTail = ptr;
            } else {
                oddTail.next = ptr;
                oddTail = oddTail.next;
            }

            // move to next node
            ptr = ptrKaNext;
            idx++;
        }

        ListNode oddListHead = oddDummy.next;
        ListNode evenListHead = evenDummy.next;

        oddTail.next = evenListHead;

        return oddListHead;
    }

    // Leetcode 25 (Reverse in k groups)
    class Solution {
        public int getSize(ListNode head){
            int size = 0;
            ListNode temp = head;

            while(temp != null){
                temp = temp.next;
                size++;
            }

            return size;
        }

        ListNode oHead = null;
        ListNode oTail = null;
        ListNode tHead = null;
        ListNode tTail = null;

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
            int size = getSize(head);
            ListNode ptr = head;

            while(size >= k){
                int currentK = k;

                while(currentK-- > 0){ // will create a reversed list of K size
                    // isolate ptr
                    ListNode ptrKaNext = ptr.next;
                    ptr.next = null;

                    addFirst(ptr);
                    ptr = ptrKaNext;
                }

                // add to original list (addLast)
                if(oHead == null){
                    oHead = tHead;
                    oTail = tTail;
                } else {
                    oTail.next = tHead;
                    oTail = tTail;
                }

                // prepare for next
                size -= k;
                tHead = null;
                tTail = null;
            }

            oTail.next = ptr;

            return oHead;
        }
    }

    // Leetcode 138 (Copy list with random pointer) =============
    class Solution {
        // add copy nodes in between
        public void addCopyNodes(Node head){
            Node temp = head;

            while(temp != null){
                // create copy
                Node copyOfTemp = new Node(temp.val);

                Node tempKaNext = temp.next;
                // insert copy
                temp.next = copyOfTemp;
                copyOfTemp.next = tempKaNext;

                // move
                temp = tempKaNext; //temp.next.next;
            }
        }

        // Assign random pointers to copy Nodes
        public void assignRandom(Node head){
            Node curr = head;
            
            while(curr != null){
                // Node currKaRandom = curr.random;
                // Node randomKaCopy = currKaRandom.next;
                // Node copyNode = curr.next;

                // copyNode.random = randomKaCopy;

                if(curr.random != null){
                    curr.next.random = curr.random.next; // currKaRandom.next
                }

                curr = curr.next.next;
            }
        }

        // remove Copy Nodes vala list
        public Node removeCopy(Node head){
            Node copyDummy = new Node(-1);
            Node copyTail = copyDummy;
            Node temp = head;

            while(ptr != null){
                Node tempKaCopy = temp.next;

                copyTail.next = tempKaCopy;
                copyTail = tempKaCopy;

                // to fix original list
                temp.next = temp.next.next;

                temp = temp.next;
            }

            return copyDummy.next;
        }

        public Node copyRandomList(Node head) {
            addCopyNodes(head);
            assignRandom(head);

            return removeCopy(head);
        }
    }


    




















    public static void main(String[] args){
        //
    }
}