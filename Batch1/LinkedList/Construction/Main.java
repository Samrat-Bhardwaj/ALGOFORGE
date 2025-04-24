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

    public void addLast(int data){
        Node nn = new Node(data);

        if(head == null){ // size can be 0
            head = nn;
            tail = nn;
        } else {
            tail.next = nn;
            tail = nn;
        }

        this.size++;
    }

    public void addFirst(int data){
        Node nn = new Node(data);

        if(tail == null){
            head = nn;
            tail = nn;
        } else {
            nn.next = head;
            head = nn;
        }

        this.size++;
    }

    public void display(){ // you don't have access to size
        Node temp = head;

        while(temp != null){
            System.out.print(temp.data + " ");
            
            Node tempKaNext = temp.next;
            temp = tempKaNext;
            // temp = temp.next;
        }

        System.out.println();
    }

    public void removeLast(){
        if(head == null){
            System.out.println("There is nothing to remove!!!");
            return;
        }

        if(head.next == null){ // size is 1
            head = null;
            tail = null;
            size = 0;
            return;
        }

        Node temp = head;

        while(temp.next != tail){
            Node tempKaNext = temp.next;
            temp = temp.next;
        }

        temp.next = null;
        tail = temp;
        size--;
    }

    public void removeFirst(){
        if(head == null){ // size == 0, tail == null
            System.out.println("There is nothing to remove");
            return;
        }
        if(head.next == null){ // size = 1, head == tail
            head = null;
            tail = null;
        } else {
            Node headKaNext = head.next;
            head = headKaNext;
        }
        size--;
    }

    public Node getNodeAt(int idx){
        if(idx < 0 || idx >= size){
            System.out.println("Index out of bounds");
            return null;
        }

        Node temp = head;
        for(int i=0; i<idx; i++){
            Node tempKaNext = temp.next;
            temp = tempKaNext;
        }

        return temp;
    }

    public void addNodeAt(int idx, int data){
        if(idx < 0 || idx > size){
            System.out.println("Index out of bounds");
            return;
        }

        if(idx == 0){
            addFirst(data);
            return;
        } else if(idx == size){
            addLast(data);
            return;
        }

        Node nn = new Node(data);
        Node previousNode = getNodeAt(idx-1);
        Node nextNode = previousNode.next;

        previousNode.next = null; // breaking connection with rest of LL
        previousNode.next = nn; // connecting previous node to new node

        nn.next = nextNode; // connecting new node with rest of LL
        this.size++;
    }

    public void removeNodeAt(int idx){
        if(idx < 0 || idx >= size){
            System.out.println("Index out of bounds");
            return;
        }

        if(idx == 0){
            removeFirst();
            return;
        }
        if(idx == size - 1){
            removeLast();
            return;
        }

        Node prevNode = getNodeAt(idx-1);
        Node toRemove = prevNode.next;
        Node nextNode = toRemove.next;

        prevNode.next = null; // breaking the connection with the node to be removed, removing connection from rest of LL
        prevNode.next = nextNode; // connecting previous node with next of to be removed Node, thus connecting to rest of LL
        this.size--;
    }

    public void reverseLLData(){
        int i = 0;
        int j = size-1;

        while(i < j){
            Node nodeAtI = getNodeAt(i);
            Node nodeAtJ = getNodeAt(j);

            int dataAtI = nodeAtI.data;
            int dataAtJ = nodeAtJ.data;

            nodeAtI.data = dataAtJ;
            nodeAtJ.data = dataAtI;

            i++;
            j--;
        }
    }

    public void reverseLLPointers(){
        Node prev = null;
        Node curr = head;
        Node originalHead = head;

        while(curr != null){
            // saving next node
            Node currKaNext = curr.next;

            // changing connections
            curr.next = null; // breaking connection with next
            curr.next = prev; // making connection with previous node

            // moving to next node
            prev = curr;
            curr = currKaNext;
        }

        head = prev;
        tail = originalHead;
    }
}

class Main {
    public static void main(String[] args){
        LinkedList ll = new LinkedList();
        
        for(int i=1; i<=4; i++){
            ll.addLast(i);
            // ll.display();
        }
        // System.out.println(ll);
        ll.addFirst(55);
        // ll.display();
        // System.out.println(ll);

        ll.addFirst(10);
        ll.display();
        // System.out.println(ll);

        // while(ll.size != 0){
        //     ll.removeFirst();
        //     ll.display();
        // }

        // ll.removeFirst();
        // Node n = ll.getNodeAt(4);
        // System.out.println(n.data);
        // System.out.println(n.next);

        // ll.removeNodeAt(2);
        // ll.display();

        // ll.removeNodeAt(0);
        // ll.display();

        ll.reverseLLPointers();
        ll.display();

        ll.reverseLLPointers();
        ll.display();
    }
}