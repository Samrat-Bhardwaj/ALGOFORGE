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

    public void removeFirst(){
        if(this.head == null){
            System.out.println("There is nothing to remove!!");
            return;
        }

        if(this.head == this.tail){ // size == 1
            this.head = null;
            this.tail = null;
        } else {
            Node headKaNext = head.next; // saving next node
            head.next = null; // removing connection with the next node

            head = headKaNext; // shifting head to new head (second node)
        }

        this.size--;
    }

    public Node getNodeAt(int idx){
        if(idx < 0 || idx >= size){ // invalid index
            System.out.println("Index out of bounds!!");
            return null;
        }

        Node temp = head;

        for(int i=0; i<idx; i++){
            // temp = temp.next;
            Node tempKaNext = temp.next;
            temp = tempKaNext;
        }

        return temp;
    }

    public void addNodeAt(int idx, int val){
        if(idx < 0 || idx > size){
            System.out.println("Index out of bound");
            return;
        }

        if(idx == 0){
            addFirst(val);
            return;
        } else if(idx == size){
            addLast(val);
            return;
        } 

        Node newNode = new Node(val);

        Node prevNode = getNodeAt(idx - 1);
        Node nextNode = prevNode.next;

        prevNode.next = null; // disconnecting prev node with the rest of the LL
        prevNode.next = newNode; // connecting prev node with newNode

        newNode.next = nextNode; // connecting newNode with rest of linkedList

        this.size++;
    }

    public void removeNodeAt(int idx){
        if(idx < 0 || idx >= size){
            System.out.println("Index out of bound");
            return;
        }

        if(idx == 0){
            removeFirst();
            return;
        } 
        if(idx == size-1){
            removeLast();
            return;
        }

        Node prevNode = getNodeAt(idx - 1); 
        Node nodeToDelete = prevNode.next;
        Node nextNode = nodeToDelete.next;

        prevNode.next = null; // breaking connection of prev node with the rest of the LinkedList
        prevNode.next = nextNode; // attaching prevNode with next of deletedNode

        this.size--;
    }

    public void reverseLinkedListDataIteratively(){
        int i = 0;
        int j = size - 1;

        while(i < j){
            Node NodeAtI = getNodeAt(i);
            Node NodeAtJ = getNodeAt(j);

            int dataAtI = NodeAtI.data;
            int dataAtJ = NodeAtJ.data;

            // swap Data
            NodeAtI.data = dataAtJ;
            NodeAtJ.data = dataAtI;

            i++;
            j--;
        }
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
        ll.display();

        // System.out.println(ll.getNodeAt(4).data);

        ll.reverseLinkedListDataIteratively();
        ll.display();
    }
}