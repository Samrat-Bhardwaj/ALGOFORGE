import java.util.Scanner;
class Node {
    int data;
    Node next;

    public Node(){}

    public Node(int data){
        this.data = data;
    }
}


class Question {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        Node n1 = new Node(scn.nextInt());
        Node n2 = new Node(scn.nextInt());
        Node n3 = new Node(scn.nextInt());
        Node n4 = new Node(scn.nextInt());

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        System.out.println(n1.data);
        System.out.println(n1.next.data);
        System.out.println(n1.next.next.data);
        System.out.println(n1.next.next.next.data);
    }
}