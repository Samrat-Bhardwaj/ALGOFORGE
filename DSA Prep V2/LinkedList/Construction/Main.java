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
        Node newNode = new Node(val);
        
        if(head == null){
            head = newNode;
            tail = newNode; 
        } else {
            tail.next = newNode;
            tail = newNode;
        }

        this.size++;
    }

    // add a node at the start of the linkedlist
    public void addFirst(int val){
        Node newNode = new Node(val);

        if(head == null){
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }

        this.size++;
    }
    
    // dont read this function yet
    public void display(){
        Node temp = head;

        while(temp != null){
            // print 
            System.out.print(temp.data + ", ");
            // find next
            Node tempKaNext = temp.next;
            // move to next
            temp = tempKaNext;
        }
    }
}

class Main {
    public static void main(String[] args){
        LinkedList ll = new LinkedList();

        ll.addFirst(5);
        ll.addFirst(10);
        ll.addFirst(13);
        ll.addFirst(15);
        ll.addFirst(20);

        ll.display();
    }
}