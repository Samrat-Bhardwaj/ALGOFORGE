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





















    public static void main(String[] args){
        //
    }
}