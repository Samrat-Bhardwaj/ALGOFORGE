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

    // Leetcode 83 Delete duplicates keeping the first one ================================
    public ListNode deleteDuplicates(ListNode head) {
        ListNode curr = head;

        while(curr != null && curr.next != null){
            if(curr.val == curr.next.val){ // in case of duplicate, remove the next node by changing curr.next
                curr.next = curr.next.next;
            } else { // in case no duplicate, move to next 
                curr = curr.next;
            }
        }

        return head;
    }

    // leetcode 82 Delete all the duplicates ==============================================
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode curr = dummy;

        while(curr.next != null && curr.next.next != null){
            if(curr.next.val == curr.next.next.val){
                ListNode temp = curr.next;
                int duplicateValue = curr.next.val;

                while(temp!=null && temp.val == duplicateValue){
                    temp = temp.next;
                }

                curr.next = temp;
            } else {
                curr = curr.next;
            }
        }

        return dummy.next;
    }

    // Leetcode 237 (How to virtually delete a node) =====================================
    public void deleteNode(ListNode node) {
        ListNode nodeKaNext = node.next;

        // delete the next node
        node.next = node.next.next;
        
        // copy nodeKaNext data here
        node.val = nodeKaNext.val;
    }

    // Leetcode 114 (LinkedList cycle)======================================
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null){
            return false;
        }

        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast){
                return true;
            }
        }

        return false;
    }

    // Find the starting point of LinkedList cycle (Leetcode 142)
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null){
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast){
                break;
            }
        }

        if(slow != fast){ // no cycle exist
            return null;
        }

        // Find starting point
        fast = head;
        while(slow != fast){
            slow = slow.next;
            fast = fast.next;
        }

        return slow; // or return fast;
    }

    // Get intersection point of Y Linkedlist (Leetcode 160) ===================
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode temp = headA;

        while(temp.next != null){
            temp = temp.next;
        }

        ListNode tailA = temp;

        tailA.next = headA; // or headB
        ListNode intersectionNode = detectCycle(headB); // if tail is connected to headB, send headA here

        tailA.next = null; // fixing the list again

        return intersectionNode;
    }

    // Leetcode 21 (Merge two sorted lists) ================================
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode temp1 = list1;
        ListNode temp2 = list2;

        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;

        while(temp1 != null && temp2 != null){
            if(temp1.val < temp2.val){
                curr.next = temp1;
                temp1 = temp1.next;
            } else {
                curr.next = temp2;
                temp2 = temp2.next;
            }

            curr = curr.next;
        }

        // connect rest of the lists
        if(temp2 == null){
            curr.next = temp1;
        } else {
            curr.next = temp2;
        }

        return dummy.next;
    }
    
    // Create browser History (Leetcode 1472) ======================================
    class BrowserHistory {
        class Node {
            String data;
            Node prev;
            Node next;

            public Node(String data){
                this.data = data;
            }
        }

        Node curr;
        public BrowserHistory(String homepage) {
            curr = new Node(homepage);
        }
        
        public void visit(String url) {
            Node newNode = new Node(url);

            // attach this next to curr
            curr.next = newNode;
            newNode.prev = curr;

            // move curr to newNode
            curr = newNode;
        }
        
        public String back(int steps) {
            while(steps > 0 && curr.prev != null){
                curr = curr.prev;
                steps--;
            }

            return curr.data;
        }
        
        public String forward(int steps) {
            while(steps > 0 && curr.next != null){
                curr = curr.next;
                steps--;
            }

            return curr.data;
        }
    }

    // Leetcode 1669. Merge In Between Linked Lists ==============================
    class Solution {
        public ListNode getNodeAt(ListNode head, int idx){
            ListNode temp = head;

            for(int i=0; i<idx; i++){
                temp = temp.next;
            }

            return temp;
        }

        public ListNode getTail(ListNode head){
            ListNode temp = head;

            while(temp.next != null){
                temp = temp.next;
            }

            return temp;
        }

        public ListNode mergeInBetween(ListNode head1, int a, int b, ListNode head2) {
            ListNode prevNode = getNodeAt(head1, a-1);
            ListNode nextNode = getNodeAt(head1, b+1);

            ListNode tailOfList2 = getTail(head2);

            prevNode.next = head2; // attaching head;
            tailOfList2.next = nextNode; // attaching tail of list 2 with list 1 

            return head1;
        }
    }

    // Leetcode 92 ======================
    class Solution {
        public ListNode getNodeAt(ListNode head, int idx){
            ListNode temp = head;

            for(int i=0; i<idx; i++){
                temp = temp.next;
            }

            return temp;
        }
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

        public ListNode reverseBetween(ListNode head, int left, int right) {
            ListNode newHead = new ListNode(-1);
            newHead.next = head;

            ListNode prevNode = getNodeAt(newHead, left-1);
            ListNode endOfList2 = getNodeAt(newHead, right);
            ListNode nextNode = endOfList2.next;

            ListNode head2 = prevNode.next;

            // get the list out
            prevNode.next = null;
            endOfList2.next = null;

            ListNode reversedHead = revereseLinkedList(head2);

            prevNode.next = reversedHead;
            if(head2 != null)
            head2.next = nextNode;

            return newHead.next;
        }
    }


    




















    public static void main(String[] args){
        //
    }
}