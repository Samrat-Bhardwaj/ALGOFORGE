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


// leetcode 83 ==================================================== 
ListNode* deleteDuplicates(ListNode* head) {
    if(head == NULL || head->next == NULL){
        return head;
    }

    ListNode* curr = head;

    while(curr && curr->next){
        if(curr->val == curr->next->val){
            ListNode* currKaNext = curr->next;

            curr->next = currKaNext->next;

            currKaNext->next = nullptr; // not required
        } else {    
            curr = curr->next;
        }
    }

    return head;
}


// leetcode 82 ========================================================
ListNode* deleteDuplicates(ListNode* head) {
    if(head ==nullptr || head->next ==nullptr){
        return head;
    }

    ListNode* dummy = new ListNode(-1);
    dummy->next = head;

    ListNode* curr = dummy;

    while(curr && curr->next && curr->next->next){
        if(curr->next->val == curr->next->next->val){ // not moving, just updating connections
            ListNode* temp = curr->next;
            int duplicateValue = temp->val;

            while(temp && temp->val == duplicateValue){
                temp = temp->next;
            }

            curr->next = temp;
        } else {
            curr = curr->next;
        }
    }

    return dummy->next;
}


// leetcode 141 (Check linkedlist cycle) ======================================================
bool hasCycle(ListNode* head) {
    ListNode* slow = head;
    ListNode* fast = head;

    while(fast && fast->next){
        slow = slow->next;
        fast = fast->next->next;

        if(slow == fast){
            return true;
        }
    }

    return false;
}

    // https://www.geeksforgeeks.org/problems/find-the-first-node-of-loop-in-linked-list--170645/1
    // leetcode 142
    ListNode *detectCycle(ListNode *head) {
        if(head==nullptr || head->next==nullptr){
            return nullptr;
        }
        
        ListNode* slow=head;
        ListNode* fast=head;

        while(fast && fast->next){
            slow=slow->next;
            fast=fast->next->next;

            if(slow==fast) break;
        } 

        if(slow!=fast) return nullptr;

        fast=head;
        while(slow!=fast){
            slow=slow->next;
            fast=fast->next;
        }

        return slow;
    }

void main(){

}