class Node {
    int data;
    Node next;

    public Node(){}

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

    public void addLast(int val){
        Node newNode = new Node(val);

        if(head == null){ // size = 0;
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }

        this.size++;
    }

    public void addFirst(int val){
        Node newNode = new Node(val);

        if(this.head == null){ // size = 0;
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }

        this.size++;
    }

    public void removeLast(){
        if(head == null){ // size == 0;
            System.out.println("Nothing to remove, LinkedList is empty!!");
            return;
        } else if(head == tail){ // size = 1
            head = null;
            tail = null;
        } else {
            // reach second last node
            Node temp = head;
            while(temp.next != tail){
                Node tempKaNext = temp.next;
                temp = tempKaNext;
            }

            temp.next = null; // removing last node
            tail = temp; // shifting tail
        }

        this.size--;
    }

    public void display(){
        Node temp = head;

        while(temp != null){
            System.out.print(temp.data + ", ");

            Node tempKaNext = temp.next;
            temp = tempKaNext;
        }
        System.out.println();
    }    
}

class Main {
    public static void main(String[] args){
        LinkedList ll = new LinkedList();

        ll.addLast(23);
        ll.addLast(43);
        ll.addLast(13);
        ll.addFirst(10);
        ll.addFirst(5);
        ll.removeLast();

        ll.display();
    }
}