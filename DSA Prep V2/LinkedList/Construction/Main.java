class Node {
    int data;
    Node next;

    public Node(int data){
        this.data = data;
    }
}
class LinkedList {
    Node head;
    Node tail;
    int size;

    public LinkedList(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // add a node at the end of linkedList
    public void addLast(int val){

    }
    
    // dont read this function yet
    public void displayList(){
        Node temp = head;
        while(temp != null){
            System.out.print(temp.data + ", ");
            temp = temp.next;
        }
    }
}

class Main {
    public static void main(String[] args){
        LinkedList ll = new LinkedList();

        ll.addLast(5);
        ll.addLast(10);
        ll.addLast(15);
        ll.addLast(20);

        ll.displayList();
    }
}