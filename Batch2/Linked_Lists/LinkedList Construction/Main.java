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

    }

    public String toString(){
        String list = "";

        Node temp = head;
        while(temp != null){
            list += temp.data + ", ";
            temp = temp.next;
        }

        return list;
    }
}

class Main {
    public static void main(String[] args){
        LinkedList ll = new LinkedList();

        ll.addLast(23);
        ll.addLast(13);

        System.out.println(ll);
    }
}