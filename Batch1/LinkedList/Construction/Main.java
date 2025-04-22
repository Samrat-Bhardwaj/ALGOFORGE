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
}

class Main {
    public static void main(String[] args){
        LinkedList ll = new LinkedList();
        
        for(int i=1; i<=4; i++){
            ll.addLast(i);
            ll.display();
        }
        // System.out.println(ll);
        ll.addFirst(55);
        ll.display();
        // System.out.println(ll);

        ll.addFirst(10);
        ll.display();
        // System.out.println(ll);

        while(ll.size != 0){
            ll.removeLast();
            ll.display();
        }

        ll.removeLast();
    }
}