import java.util.ArrayList;
class Vehicle implements Comparable {
    int mileage;
    int number_of_wheels;
    String color;

    public Vehicle(int mileage, int number_of_wheels, String color){
        this.mileage = mileage;
        this.number_of_wheels = number_of_wheels;
        this.color = color;
    }

    @Override
    public int compareTo(Object ot){
        Vehicle other = (Vehicle)(ot);
        return this.mileage - other.mileage; // 20 - 220 => -200
        // return other.mileage - this.mileage; // 220 - 2220 => -2000
    }

    @Override
    public String toString(){
        return "(Mileage: " + this.mileage + ", number_of_wheels: " + this.number_of_wheels + ", Color: "  + this.color + ")";
    }
}
class GenericHeapImplementation {
    static class Heap < T > { // minHeap
        private ArrayList<T> data;

        public Heap(){
            data = new ArrayList<>();
        }

        // checking if this has more priority than other
        private boolean isSmaller(T child, T parent){
            Comparable childd = (Comparable)(child);
            Comparable parentt = (Comparable)(parent);

            if(childd.compareTo(parentt) < 0){
                return true;
            } else {
                return false;
            }
        }

        private void swap(int i, int j){
            T valueAti = data.get(i);
            T valueAtj = data.get(j);

            data.set(i, valueAtj);
            data.set(j, valueAti);
        }

        private void upHeapify(int childIndex){
            int parentIndex = (childIndex-1)/2;

            if(parentIndex >=0 && isSmaller(data.get(childIndex), data.get(parentIndex))){
                swap(parentIndex,childIndex);
                upHeapify(parentIndex);
            }
        }

        private void downHeapify(int parentIndex){
            int leftChildIndex = parentIndex*2 + 1;
            int rightChildIndex = parentIndex*2 + 2;

            int minElementIndex = parentIndex;
            if(leftChildIndex < data.size() && isSmaller(data.get(leftChildIndex),data.get(minElementIndex))){
                minElementIndex = leftChildIndex;
            }

            if(rightChildIndex < data.size() && isSmaller(data.get(rightChildIndex),data.get(minElementIndex))){
                minElementIndex = rightChildIndex;
            }

            if(minElementIndex != parentIndex){
                swap(parentIndex, minElementIndex);
                downHeapify(minElementIndex);
            }
        }

        public void add(T value){ // logN
            data.add(value); // adding at the end;
            upHeapify(data.size()-1);
        }


        public T peek(){ // O(1);
            if(data.size() == 0){
                System.out.println("Nothing in priorityQueue");
                return null;
            }

            return data.get(0);
        }

        public T remove(){ // logN
            if(data.size() == 0){
                System.out.println("Nothing in priorityQueue");
                return null;
            }

            swap(0, data.size()-1);
            T value = data.remove(data.size()-1);
        
            downHeapify(0);

            return value;
        }

        public int size(){ // O(1)
            return this.data.size();
        }
    }

    public static void main(String[] args){
        Heap<Vehicle> pq = new Heap<>();

        pq.add(new Vehicle(100,2,"Red"));
        pq.add(new Vehicle(1300,3,"Blue"));
        pq.add(new Vehicle(200,1,"Yellow"));
        pq.add(new Vehicle(500,19,"Baigani"));
        // pq.add(1);
        // pq.add(21);
        // pq.add(-1);

        while(pq.size() > 0){
            System.out.println(pq.remove());
        }
    }
}