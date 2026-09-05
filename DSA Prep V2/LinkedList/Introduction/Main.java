class Node {
    int data;
    Node next;

    public Node(int data){
        this.data = data;
    }
}

class Main {
    public static void main(String[] args){
        Node n1 = new Node(5);
        Node n2 = new Node(10);
        Node n3 = new Node(15);
        Node n4 = new Node(20);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        System.out.println(n1.data);
        System.out.println(n1.next.data);
        System.out.println(n1.next.next.data);
        System.out.println(n1.next.next.next.data);
    }
}