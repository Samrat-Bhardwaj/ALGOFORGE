class Node {
    int data;
    Node next;

    public Node(int data){
        this.data = data;
    }
}

class Introduction {
    public static void main(String[] args){
        Node n1 = new Node(10);
        Node n2 = new Node(5);
        Node n3 = new Node(7);

        n1.next = n2;
        n2.next = n3;
        
        System.out.println(n1.data);

        Node addressOfSecondGuy = n1.next;
        System.out.println(addressOfSecondGuy.data);

        Node addressOfThirdGuy = addressOfSecondGuy.next;
        System.out.println(addressOfThirdGuy.data);
    }
}