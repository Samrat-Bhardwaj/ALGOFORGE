#include<iostream>
struct ListNode {
    int val;
    ListNode *next;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode *next) : val(x), next(next) {}
};

struct Node {
    int data;
    struct Node* next;

    Node(int x){
        data = x;
        next = NULL;
    }
};

// leetcode 876 =========================
ListNode* middleNode(ListNode* head) {
    ListNode* slow = head;
    ListNode* fast = head;

    while(fast != nullptr && fast->next != nullptr){
        slow = slow->next;
        fast = fast->next->next;
    }

    return slow;
}

// leetcode 234 =================================
ListNode* getMiddleNode(ListNode* head) {
    ListNode *slow = head;
    ListNode *fast = head;

    while(fast->next && fast->next->next != NULL){
        slow = slow->next;
        fast = fast->next->next;
    }

    return slow;
}

ListNode* reverseLinkedList(ListNode* head){
    ListNode* curr = head;
    ListNode* prev = nullptr;

    while(curr != NULL){
        ListNode* currKaNext = curr->next;

        curr->next = prev;

        prev = curr;
        curr = currKaNext;
    }

    return prev;
}

bool isPalindrome(ListNode* head) {
    // find mid 
    ListNode* mid = getMiddleNode(head);

    // break into two linkedlist around mid
    ListNode* secondHead = mid->next;
    mid->next = nullptr;

    // reverse second half
    secondHead = reverseLinkedList(secondHead);

    ListNode* ptr1 = head;
    ListNode* ptr2 = secondHead;
    bool ans = true;

    // compare both linkedlist
    while(ptr1 != NULL && ptr2 != NULL){
        if(ptr1->val != ptr2->val){
            ans = false;
        }

        ptr1 = ptr1->next;
        ptr2 = ptr2->next;
    }

    // reverse again
    secondHead = reverseLinkedList(secondHead);

    // attach both halves
    mid->next = secondHead;

    return ans;
}


// leetcode 143 =====================================
void reorderList(ListNode* head) {
    ListNode* mid = getMiddleNode(head);

    // dividing into two halves
    ListNode* secondHead = mid->next;
    mid->next = nullptr;

    secondHead = reverseLinkedList(secondHead);

    ListNode* temp1 = head;
    ListNode* temp2 = secondHead;

    ListNode* dummy = new ListNode(-1);
    ListNode* curr = dummy;

    while(temp1 != nullptr && temp2 != NULL){ // temp!=null is a redundant condition
        // attach first list ka node
        curr->next = temp1;
        curr = curr->next;
        temp1 = temp1->next;

        // attach second list ka node
        curr->next = temp2;
        curr = curr->next;
        temp2 = temp2->next;
    }

    curr->next = temp1;
}

// Unfold Linkedlist (Undo leetcode 143) ======================================
void unfoldLinkedList(ListNode* head){
    ListNode* oddPositionDummy = new ListNode(-1);
    ListNode* evenPositionDummy = new ListNode(-1);

    ListNode* oddCurr = oddPositionDummy;
    ListNode* evenCurr = evenPositionDummy;

    int pos = 0;
    ListNode* curr = head;

    while(curr){
        ListNode* currKaNext = curr->next;
        curr->next = NULL;

        if(pos % 2 == 0){
            evenCurr->next = curr;
            evenCurr = evenCurr->next;
        } else {
            oddCurr->next = curr;
            oddCurr = oddCurr->next;
        }

        curr = currKaNext;
        pos++;
    }

    ListNode* oddPositionActualHead = oddPositionDummy->next;
    oddPositionDummy->next = NULL;
    oddPositionActualHead = reverseLinkedList(oddPositionActualHead);

    evenCurr->next = oddPositionActualHead;
}


// leetcode 328(HW) =============================================================

// https://www.geeksforgeeks.org/problems/segregate-even-and-odd-nodes-in-a-linked-list5035/1

Node* divide(Node* head) {
    Node* evenDummy = new Node(-1);
    Node* oddDummy = new Node(-1);

    Node* evenCurr = evenDummy;
    Node* oddCurr = oddDummy;

    Node* curr = head;

    while(curr){
        Node* currKaNext = curr->next;
        curr->next = NULL;

        if(curr->data % 2 == 0){
            evenCurr->next = curr;
            evenCurr = evenCurr->next;
        } else {
            oddCurr->next = curr;
            oddCurr = oddCurr->next;
        }

        curr = currKaNext;
    }

    evenCurr->next = oddDummy->next;
    return evenDummy->next;
}

// leetcode 25 =========================================================



void main(){

}