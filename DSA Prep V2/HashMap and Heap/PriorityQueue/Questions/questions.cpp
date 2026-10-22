// Sort k-sorted array (https://www.geeksforgeeks.org/problems/nearly-sorted-1587115620/1)
void nearlySorted(vector<int>& arr, int k) {
    priority_queue<int, vector<int>, greater<int>> pq;
    
    int ansIdx = 0;
    
    for(int i=0; i<arr.size(); i++){
        pq.push(arr[i]);
        
        if(pq.size() > k){ // if (i >= k)
            arr[ansIdx] = pq.top();
            ansIdx++;
            pq.pop();
        }
    }
    
    while(pq.size() > 0){
        arr[ansIdx++] = pq.top();
        pq.pop();
    }
    
}











void main(){

}