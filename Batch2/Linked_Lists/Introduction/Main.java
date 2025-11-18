class Node {
    int data;
    Node next;

    public Node(){}

    public Node(int data){
        this.data = data;
    }
}

class Main {
    public static void main(String[] args){
        Node n1 = new Node(10); // n1 = address of node with data = 10
        Node n2 = new Node(20); // n2 = address of node with data = 20
        Node n3 = new Node(30); // n3 = address of node with data = 30

        n1.next = n2;
        n2.next = n3;

        System.out.println(n1.data);

        Node addressOfSecondNode = n1.next;
        System.out.println(addressOfSecondNode.data);

        Node addressOfThirdNode = addressOfSecondNode.next;
        System.out.println(addressOfThirdNode.data);
    }
}