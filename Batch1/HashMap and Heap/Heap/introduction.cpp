#include <iostream>
#include <queue>
#include<vector>

using namespace std;

int main(){
    priority_queue<int> pq; // maxPQ;
    
    pq.push(23);
    pq.push(13);pq.push(232);
    pq.push(2123);
    
    // while(pq.size()){
    //     cout<<pq.top()<<endl;
    //     pq.pop();
    // }
    
    priority_queue<int,vector<int>,greater<int>> minPQ;
    
    minPQ.push(23);
    minPQ.push(13);minPQ.push(232);
    minPQ.push(2123);
    
    while(minPQ.size()){
        cout<<minPQ.top()<<endl;
        minPQ.pop();
    }
}