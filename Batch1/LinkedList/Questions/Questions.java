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


    // leetcode 328 =============================================================



























    public static void main(String[] args){

    }
}