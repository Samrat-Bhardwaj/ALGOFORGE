import java.util.ArrayList;
class Heap <T> {
    ArrayList<T> data;

    public Heap(){
        data = new ArrayList<>();
    }

    private void swap(int i, int j){
        T dataAtI = data.get(i);
        T dataAtJ = data.get(j);

        data.set(i,dataAtJ);
        data.set(j,dataAtI);
    }

    private boolean isMorePrior(int i, int j){
        T child = data.get(i);
        T parent = data.get(j);

        Comparable childComparable = (Comparable)(child);
        Comparable parentComparable = (Comparable)(parent);

        if(childComparable.compareTo(parentComparable) < 0){
            return true;
        } else {
            return false;
        }
    }

    private void upHeapify(int childIdx){
        int parentIdx = (childIdx - 1)/2;

        if(isMorePrior(childIdx, parentIdx)){
            swap(parentIdx, childIdx);
            upHeapify(parentIdx);
        }
    }

    public void add(T val){
        data.add(val);
        upHeapify(data.size()-1);
    }

    private void downHeapify(int parentIdx){
        int leftChildIdx = 2*parentIdx + 1;
        int rightChildIdx = 2*parentIdx + 2;

        int minIdx = parentIdx;

        if(leftChildIdx < data.size() && isMorePrior(leftChildIdx, minIdx)){
            minIdx = leftChildIdx;
        }

        if(rightChildIdx < data.size() && isMorePrior(rightChildIdx, minIdx)){
            minIdx = rightChildIdx;
        }

        if(parentIdx != minIdx){
            swap(parentIdx, minIdx);
            downHeapify(minIdx);
        }
    }

    public T remove(){
        if(data.size() == 0){
            System.out.println("PriorityQueue is empty!!");
            return null;
        }

        swap(0,data.size()-1);
        T topElement = data.remove(data.size() -1);

        downHeapify(0);

        return topElement;
    }

    public T peek(){
        if(data.size() == 0){
            System.out.println("PriorityQueue is empty!!");
            return null;
        }

        return data.get(0);
    }

    public int size(){
        return data.size();
    }
}
class Student implements Comparable {
    String name;
    int roll_no;
    int marks;

    public Student(String name, int roll_no, int marks){
        this.name = name;
        this.roll_no = roll_no;
        this.marks = marks;
    }

    @Override
    public int compareTo(Object other){ // negative (this), positive (other) or zero (random)
        Student ot = (Student)(other);
        return this.marks - ot.marks; // -> minPQ
    }

    public String toString(){
        return this.name + ": " + this.roll_no + "," + this.marks;
    }
}


class GenericHeap {
    public static void main(String[] args){
        Heap<Student> pq = new Heap<>(); 

        pq.add(new Student("Samrat", 1, 100));
        pq.add(new Student("Ajay", 3, 10));
        pq.add(new Student("Hemant", 2, 0));
        

        while(pq.size() > 0){
            System.out.println(pq.remove());
        }
    }
}