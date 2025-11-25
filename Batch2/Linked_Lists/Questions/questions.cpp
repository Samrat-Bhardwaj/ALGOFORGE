#include<bits/stdc++.h>


// leetcode 206 ============================================================
ListNode* reverseList(ListNode* head) {
    ListNode* curr = head;
    ListNode* prev = nullptr;

    while(curr != NULL){
        // find curr ka Next
        ListNode* currKaNext = curr->next;

        // change connections -> point to prev node
        curr->next = prev;

        // move pointers for next iteration
        prev = curr;
        curr = currKaNext;
    }

    // prev pointer is not at new head;
    return prev; 
}

    // Kth node from end of linked list (https://www.geeksforgeeks.org/problems/nth-node-from-end-of-linked-list/1)
    int getKthFromLast(Node* head, int k) {
        Node* slow = head;
        Node* fast = head;

        // moving fast k steps ahead of slow
        while(k--){
            if(fast == nullptr){ // moving k steps ahead is not possible -> list size is smaller than k
                return -1;
            }

            Node* fastKaNext = fast->next;
            fast = fastKaNext;
        }

        // moving slow and fast together until fast is at end (nullptr)
        while(fast != nullptr){
            Node* slowKaNext = slow->next;
            slow = slowKaNext;

            Node* fastKaNext = fast->next;
            fast = fastKaNext;
        }

        return slow->data;    
    }

    ListNode* getMiddleNode(ListNode* head) {
        ListNode* slow = head;
        ListNode* fast = head;

        while(fast->next != NULL && fast->next->next != NULL){ // first condition for odd-size LL, second for even
            slow = slow->next;
            fast = fast->next->next;
        }

        return slow;
    }

    // leetcode 143 (Re-order list)
    void reorderList(ListNode* head) {
        if(head == nullptr || head->next == nullptr){
            return;
        }

        ListNode* mid = getMiddleNode(head);

        // extracting second half as list
        ListNode* secondHead = mid->next;
        mid->next = nullptr;

        secondHead = reverseList(secondHead);

        // Creating answer list
        ListNode* dummy = new ListNode(-1);
        ListNode* curr = dummy;
        ListNode* ptr1 = head;
        ListNode* ptr2 = secondHead;

        while(ptr1!= nullptr && ptr2 != nullptr){
            // attaching ptr1
            curr->next = ptr1;
            ptr1 = ptr1->next;
            curr = curr->next;

            // attaching ptr2 to answer list
            curr->next = ptr2;
            ptr2 = ptr2->next;
            curr = curr->next;
        }

        if(ptr1 != nullptr){
            curr->next = ptr1;
        }
    }


























int main(){
    return 0;
}