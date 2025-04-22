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
        
    }
}

class Main {
    public static void main(String[] args){
        LinkedList ll = new LinkedList();
        
        for(int i=1; i<=4; i++){
            ll.addLast(i);
        }

        System.out.println(ll.head.data);
        System.out.println(ll.head.next.data);
        System.out.println(ll.head.next.next.data);
        System.out.println(ll.head.next.next.next.data);
    }
}