import java.util.ArrayList;
class HeapImplementation {
    static class Heap { // minHeap
        private ArrayList<Integer> data;

        public Heap(){
            data = new ArrayList<>();
        }

        private void swap(int i, int j){
            int valueAti = data.get(i);
            int valueAtj = data.get(j);

            data.set(i, valueAtj);
            data.set(j, valueAti);
        }

        private void upHeapify(int childIndex){
            int parentIndex = (childIndex-1)/2;

            if(parentIndex >=0 && data.get(parentIndex) > data.get(childIndex)){
                swap(parentIndex,childIndex);
                upHeapify(parentIndex);
            }
        }

        private void downHeapify(int parentIndex){
            int leftChildIndex = parentIndex*2 + 1;
            int rightChildIndex = parentIndex*2 + 2;

            int minElementIndex = parentIndex;
            if(leftChildIndex < data.size() && data.get(leftChildIndex) < data.get(minElementIndex)){
                minElementIndex = leftChildIndex;
            }

            if(rightChildIndex < data.size() && data.get(rightChildIndex) < data.get(minElementIndex)){
                minElementIndex = rightChildIndex;
            }

            if(minElementIndex != parentIndex){
                swap(parentIndex, minElementIndex);
                downHeapify(minElementIndex);
            }
        }

        public void add(int value){ // logN
            data.add(value); // adding at the end;
            upHeapify(data.size()-1);
        }


        public int peek(){ // O(1);
            if(data.size() == 0){
                System.out.println("Nothing in priorityQueue");
                return -1;
            }

            return data.get(0);
        }

        public int remove(){ // logN
            if(data.size() == 0){
                System.out.println("Nothing in priorityQueue");
                return -1;
            }

            swap(0, data.size()-1);
            int value = data.remove(data.size()-1);
        
            downHeapify(0);

            return value;
        }

        public int size(){ // O(1)
            return this.data.size();
        }
    }

    public static void main(String[] args){
        Heap pq = new Heap();

        pq.add(23);
        pq.add(234);
        pq.add(13);

        // System.out.println(pq.peek());

        pq.add(-10);
        pq.add(12);
        pq.add(14);
        // System.out.println(pq.peek());

        while(pq.size()>0){
            System.out.println(pq.remove());
        }
    }
}