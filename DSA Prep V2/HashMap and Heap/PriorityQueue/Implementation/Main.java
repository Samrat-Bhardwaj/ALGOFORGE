import java.util.ArrayList;
class MyHeap {
    ArrayList<Integer> data; 

    public MyHeap(){
        data = new ArrayList<>();
    }

    private void swap(int i, int j){
        int dataAtI = data.get(i);
        int dataAtJ = data.get(j);

        data.set(i, dataAtJ);
        data.set(j, dataAtI);
    }

    private void upHeapify(int idx){
        int parentIdx = (idx - 1)/2;

        if(data.get(parentIdx) > data.get(idx)){
            swap(parentIdx, idx);
            upHeapify(parentIdx);
        }
    }

    private void downHeapify(int idx){
        
    }

    public void add(int value){
        data.add(value);
        upHeapify(data.size() - 1);
    }

    public int remove(){
        if(data.size() == 0){
            return -1;
        }

        int topEle = data.get(0);

        swap(0, data.size()-1); // swap 0th element with last element
        data.remove(data.size() - 1); // remove 0th element which is now at last position

        downHeapify(0);

        return topEle;
    }

    public int peek(){
        if(data.size() == 0){
            return -1;
        }

        return data.get(0);
    }
}

class Main {
    public static void main(String[] args){
        MyHeap minPQ = new MyHeap(); // min pq 

        minPQ.add(10); 
        minPQ.add(101);
        minPQ.add(7);
        minPQ.add(15);

        System.out.println(minPQ.peek()); 

        // System.out.println(minPQ.poll()); 
        // System.out.println(minPQ.poll()); 
        // System.out.println(minPQ.poll()); 
        // System.out.println(minPQ.poll()); 
        // System.out.println(minPQ.poll()); 
    }
}