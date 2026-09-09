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

void main(){
    
}