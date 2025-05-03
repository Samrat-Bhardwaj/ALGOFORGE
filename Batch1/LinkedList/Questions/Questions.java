// GFG follow this node structure
class Node {
    int data;
    Node next;
    Node(int d) {data = d; next = null; }
} 

// Leetcode follow this node structure
public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Questions {



    // https://www.geeksforgeeks.org/problems/nth-node-from-end-of-linked-list/0
    int getKthFromLast(Node head, int k) {
        Node slow = head;
        Node fast = head;

        // moving fast node k steps ahead
        while(k-- > 0){
            if(fast == null){ // we were not able to move fast k steps -> we have less than k nodes
                return -1;
            }
            Node fastKaNext = fast.next;
            fast = fastKaNext;
        }

        while(fast != null){
            Node slowKaNext = slow.next;
            slow = slowKaNext;

            fast = fast.next;
        }

        return slow.data;
    }

    // leetcode 876 ================================
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    // leetcode 234 =================================
    public ListNode getMiddleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public ListNode reverseLinkedList(ListNode head){
        ListNode curr = head;
        ListNode prev = null;

        while(curr != null){
            ListNode currKaNext = curr.next;

            curr.next = prev;

            prev = curr;
            curr = currKaNext;
        }

        return prev;
    }

    public boolean isPalindrome(ListNode head) {
        // find mid 
        ListNode mid = getMiddleNode(head);

        // break into two linkedlist around mid
        ListNode secondHead = mid.next;
        mid.next = null;

        // reverse second half
        secondHead = reverseLinkedList(secondHead);

        ListNode ptr1 = head;
        ListNode ptr2 = secondHead;
        boolean ans = true;

        // compare both linkedlist
        while(ptr1 != null && ptr2 != null){
            if(ptr1.val != ptr2.val){
                ans = false;
            }

            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }

        // reverse again
        secondHead = reverseLinkedList(secondHead);

        // attach both halves
        mid.next = secondHead;

        return ans;
    }

    // leetcode 143 =====================================
    public void reorderList(ListNode head) {
        ListNode mid = getMiddleNode(head);

        // dividing into two halves
        ListNode secondHead = mid.next;
        mid.next = null;

        secondHead = reverseLinkedList(secondHead);

        ListNode temp1 = head;
        ListNode temp2 = secondHead;

        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;

        while(temp1 != null && temp2 != null){ // temp!=null is a redundant condition
            // attach first list ka node
            curr.next = temp1;
            curr = curr.next;
            temp1 = temp1.next;

            // attach second list ka node
            curr.next = temp2;
            curr = curr.next;
            temp2 = temp2.next;
        }

        curr.next = temp1;
    }

    // Unfold Linkedlist (Undo leetcode 143) ======================================
    public void unfoldLinkedList(ListNode head){
        ListNode oddPositionDummy = new ListNode(-1);
        ListNode evenPositionDummy = new ListNode(-1);

        ListNode oddCurr = oddPositionDummy;
        ListNode evenCurr = evenPositionDummy;

        int pos = 0;
        ListNode curr = head;

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

        ListNode oddPositionActualHead = oddPositionDummy.next;
        oddPositionDummy.next = null;
        oddPositionActualHead = reverseLinkedList(oddPositionActualHead);

        evenCurr.next = oddPositionActualHead;
    }


    // leetcode 328 (HW) =============================================================


    // https://www.geeksforgeeks.org/problems/segregate-even-and-odd-nodes-in-a-linked-list5035/1
    Node divide(Node head) {
        Node evenDummy = new Node(-1);
        Node oddDummy = new Node(-1);

        Node evenCurr = evenDummy;
        Node oddCurr = oddDummy;

        Node curr = head;

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

    // leetcode 25 ====================================
    class Solution {
        ListNode ohead;
        ListNode otail;
        ListNode thead;
        ListNode ttail;

        public int getSize(ListNode head){
            int size = 0;

            ListNode temp = head;
            while(temp != null){
                temp = temp.next;
                size++;
            }

            return size;
        }

        public void addFirst(ListNode node){
            if(thead == null){
                thead = node;
                ttail = node;
            } else {
                node.next = thead;
                thead = node;
            }
        }

        public ListNode reverseKGroup(ListNode head, int k) {
            ohead = null;
            otail = null;
            thead = null;
            ttail = null;

            int size = getSize(head);
            ListNode curr = head;
            
            while(size >= k){
                int K = k;
                
                while(K-- > 0){ // making temporary linkedlist
                    ListNode currKaNext = curr.next;
                    curr.next = null;

                    addFirst(curr);
                    curr = currKaNext;
                    size--;
                }

                if(ohead == null){
                    ohead = thead;
                    otail = ttail;
                } else {
                    otail.next = thead;
                    otail = ttail;
                }
                thead = null;
                ttail = null;
            }

            otail.next = curr;
            return ohead;
        }
    }

    // leetcode 138 =============================================
    public void attachCopyNodesInBetween(Node head){
        Node curr = head;

        while(curr != null){
            Node copy = new Node(curr.val);

            Node currKaNext = curr.next;
            curr.next = copy;
            copy.next = currKaNext;

            curr = currKaNext;
        }
    }

    public Node detachInBetweenList(Node head){
        Node copyHead = head.next;

        Node curr = head;
        while(curr != null){
            Node currKaNext = curr.next.next; // originalNext

            Node copy = curr.next;
            if(currKaNext != null){
                copy.next = currKaNext.next;
            }
            
            curr.next = currKaNext;
            curr = currKaNext;
        }

        return copyHead;
    }

    public Node copyRandomList(Node head) {
        if(head == null){
            return null;
        }
        attachCopyNodesInBetween(head);

        Node curr = head;

        // assign random node values
        while(curr != null){
            Node copy = curr.next;

            if(curr.random != null){
                copy.random = curr.random.next;
            }

            curr = curr.next.next;
        }

        return detachInBetweenList(head);
    }

    // leetcode 2 (HW)


    // leetcode 83 ==================================================== 
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }

        ListNode curr = head;

        while(curr != null && curr.next != null){
            if(curr.val == curr.next.val){
                ListNode currKaNext = curr.next;

                curr.next = currKaNext.next;

                currKaNext.next = null; // not required
            } else {    
                curr = curr.next;
            }
        }

        return head;
    }

    // leetcode 82 ========================================================
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode curr = dummy;

        while(curr!=null && curr.next!=null && curr.next.next!=null){
            if(curr.next.val == curr.next.next.val){ // not moving, just updating connections
                ListNode temp = curr.next;
                int duplicateValue = temp.val;

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

    // leetcode 141 (Check linkedlist cycle) ======================================================
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast){
                return true;
            }
        }

        return false;
    }

    // starting point of cycle in linkedList -> https://www.geeksforgeeks.org/problems/find-the-first-node-of-loop-in-linked-list--170645/1
    public static Node findFirstNode(Node head) {
        if(head == null || head.next==null){
            return null;
        }

        Node slow = head;
        Node fast = head;

        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast){
                break;
            }
        }

        if(slow != fast){ // no cycle
            return null;
        }

        slow = head;
        while(slow != fast){
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }

    // leetcode 21 ===================================================== 
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode p1 = list1;
        ListNode p2 = list2;

        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;

        while(p1 != null && p2 != null){
            if(p1.val < p2.val){
                // isolating the node
                ListNode p1KaNext = p1.next;
                p1.next = null;
                // making connection 
                curr.next = p1;
                // moving to next
                p1 = p1KaNext;
            } else {
                // isolating the node
                ListNode p2KaNext = p2.next;
                p2.next = null;
                // making connection 
                curr.next = p2;
                // moving to next
                p2 = p2KaNext;
            }

            curr = curr.next;
        }

        if(p1 != null){
            curr.next = p1;
        } else {
            curr.next = p2;
        }

        return dummy.next;
    }   

    // leetcode 160 ======================================================
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode temp = headA;

        while(temp.next != null){
            temp = temp.next;
        }

        temp.next = headB; // headA is starting of tail now

        ListNode startOfCylceNode = findCycleFirstNode(headA);

        temp.next = null;

        return startOfCylceNode;
    }

    public static ListNode findCycleFirstNode(ListNode head) {
        if(head == null || head.next==null){
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;

        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast){
                break;
            }
        }

        if(slow != fast){ // no cycle
            return null;
        }

        slow = head;
        while(slow != fast){
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }

    // leetcode 1472 ============================================
    class BrowserHistory {
        class ListNode {
            String data;
            ListNode prev;
            ListNode next;

            public ListNode(String data){
                this.data = data;
            }
        }

        ListNode curr;

        public BrowserHistory(String homepage) {
            curr = new ListNode(homepage);
        }
        
        public void visit(String url) {
            ListNode newNode = new ListNode(url);

            curr.next = newNode;
            newNode.prev = curr;

            curr = newNode;
        }
        
        public String back(int steps) {
            while(curr.prev != null && steps -- > 0){
                curr = curr.prev;
            }

            return curr.data;
        }
        
        public String forward(int steps) {
            while(curr.next!=null && steps -- > 0){
                curr = curr.next;
            }

            return curr.data;
        }
    }

    // leetcode 2074 ===========================================
    class Solution {
        ListNode ohead;
        ListNode otail;
        ListNode thead;
        ListNode ttail;

        public int getSize(ListNode head){
            int size = 0;

            ListNode temp = head;
            while(temp != null){
                temp = temp.next;
                size++;
            }

            return size;
        }

        public void addLast(ListNode node){
            if(thead == null){
                thead = node;
                ttail = node;
            } else {
                ttail.next = node;
                ttail = node;
            }
        }

        public void addFirst(ListNode node){
            if(thead == null){
                thead = node;
                ttail = node;
            } else {
                node.next = thead;
                thead = node;
            }
        }

        public ListNode reverseEvenLengthGroups(ListNode head) {
            if(head == null || head.next == null){
                return head;
            }

            ohead = null;
            otail = null;
            thead = null;
            ttail = null;

            int size = getSize(head);
            ListNode curr = head;

            int curr_group_size = 1;

            while(size >= curr_group_size){
                int K = curr_group_size;
                
                while(K-- > 0){
                    ListNode currKaNext = curr.next;
                    curr.next = null;

                    if(curr_group_size % 2 == 0){
                        addFirst(curr);
                    } else {
                        addLast(curr);
                    }

                    curr = currKaNext;
                }

                if(ohead == null){
                    ohead = thead;
                    otail = ttail;
                } else {
                    otail.next = thead;
                    otail = ttail;
                }

                thead = null;
                ttail = null;

                size -= curr_group_size;
                curr_group_size++;
            }

            while(curr != null){
                ListNode currKaNext = curr.next;
                curr.next = null;

                if(size % 2 == 0){
                    addFirst(curr);
                } else {
                    addLast(curr);
                }

                curr = currKaNext;
            }

            if(ohead == null){
                ohead = thead;
                otail = ttail;
            } else {
                otail.next = thead;
                otail = ttail;
            }

            return ohead;
        }
    }
    
    // leetcode 61 =================================================

    // leetcode 1669 =======================================
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode prev = list1;
        ListNode curr = list1;

        for(int i=0; i<=b; i++){
            if(i < a-1){
                prev = prev.next;
            }
            curr = curr.next;
        }

        prev.next = list2;

        ListNode list2Tail = list2;

        while(list2Tail.next != null){
            list2Tail = list2Tail.next;
        }

        list2Tail.next = curr;
    }


























    public static void main(String[] args){

    }
}