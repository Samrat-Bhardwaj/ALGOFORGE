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

int main(){
    return 0;
}