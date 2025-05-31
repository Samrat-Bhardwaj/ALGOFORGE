#include <iostream>
#include <queue>
#include<vector>

using namespace std;

// leetcode 215 (Kth Largest element) ============================
int findKthLargest(vector<int>& nums, int k) {
    priority_queue<int,vector<int>,greater<int>> pq;

    for(int e: nums){
        pq.push(e);

        if(pq.size() > k){
            pq.pop();
        }
    }

    return pq.top();
}

// Sort nearly(K-sorted) sorted array, https://www.geeksforgeeks.org/problems/nearly-sorted-1587115620/1 
void nearlySorted(vector<int>& arr, int k) {
    priority_queue<int,vector<int>,greater<int>> pq;
    int j = 0;
    for(int i=0; i<arr.size(); i++){
        pq.push(arr[i]);

        if(pq.size() > k){
            arr[j] = pq.top(); pq.pop();
            j++;
        }
    }

    while(pq.size() > 0){
        arr[j++] = pq.top(); pq.pop();
    }
}

// leetcode 295 (Median in data-stream) ==============================
class MedianFinder {
public:
    priority_queue<int> left;
    priority_queue<int,vector<int>,greater<int>> right;
    MedianFinder() {
        
    }
    
    void addNum(int num) {
        if(right.size() ==0 || num >= right.top()){
            right.push(num);
        } else {
            left.push(num);
        }

        int ls = left.size();
        int rs = right.size();

        if(rs < ls){ // right can have one extra element, left can't
            right.push(left.top()); left.pop();
        } else if(rs - ls == 2){
            left.push(right.top()); right.pop();
        }
    }
    
    double findMedian() {
        if(left.size() == right.size()){
            return (left.top()*1.0 + right.top()*1.0)/(2.0);
        } else {
            return right.top()*1.0;
        }
    }
};



// leetcode 23 (Merge K sorted lists) ======================================
struct ListNode {
    int val;
    ListNode* next;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode* next) : val(x), next(next) {}
};

class compareListNodeClass {
    public:
    bool operator()(ListNode* tthis, ListNode* other){
        return tthis->val > other->val;
    }
};

ListNode* mergeKLists(vector<ListNode*>& lists) {
    priority_queue<ListNode*,vector<ListNode*>, compareListNodeClass> pq;

    for(ListNode* head: lists){
        if(head){
            pq.push(head);
        }
    }
    ListNode* dummy = new ListNode(-1);
    ListNode* curr = dummy;

    while(pq.size()){
        ListNode* top = pq.top(); pq.pop();
        ListNode* topKaNext = top->next;
        top->next = NULL;

        curr->next = top;
        curr = curr->next;

        if(topKaNext){
            pq.push(topKaNext);
        }
    }

    return dummy->next;
}




















int main(){
    return 0;
}