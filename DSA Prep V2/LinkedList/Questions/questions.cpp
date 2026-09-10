#include<bits/stdc++.h>

// Leetcode 876 -> Find middle node 
ListNode* middleNode(ListNode* head) {
    ListNode* slow = head;
    ListNode* fast = head;
    
    while(fast!= nullptr && fast->next != NULL){
        slow = slow->next;
        fast = fast->next->next;
    }

    return slow;
}

// helper function to find middle of linkedList (first middle in case of even)
ListNode* findMiddleNode(ListNode* head){
    ListNode* slow = head;
    ListNode* fast = head;
    
    while(fast->next && fast->next->next){
        slow = slow->next;
        fast = fast->next->next;
    }

    return slow;
}

// helper function to reverse linkedlist and return new head
ListNode* revereseLinkedList(ListNode* head){
    ListNode* curr = head;
    ListNode* prev = nullptr;

    while(curr){
        ListNode* currKaNext = curr->next;

        curr->next = prev;

        prev = curr;
        curr = currKaNext;
    }

    return prev; // new head
}

// Leetcode 234 (Pallindrome LinkedList)
bool isPalindrome(ListNode* head) {
    // divide into two halves
    ListNode* midNode = findMiddleNode(head);

    ListNode* secondHalfHead = midNode->next;
    midNode->next = NULL;

    // reverse second half
    secondHalfHead = revereseLinkedList(secondHalfHead);

    // compare two halves
    ListNode* temp1 = head;  
    ListNode* temp2 = secondHalfHead;

    while(temp1 && temp2){
        if(temp1->val != temp2->val){
            return false;
        }

        temp1 = temp1->next;
        temp2 = temp2->next;
    }

    return true;
}

void main(){
    
}