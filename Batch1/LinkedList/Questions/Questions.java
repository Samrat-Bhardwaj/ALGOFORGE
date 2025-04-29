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

























    public static void main(String[] args){

    }
}