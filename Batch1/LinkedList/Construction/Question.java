import java.util.Scanner;
class Node {
    int data;
    Node next;

    public Node(int data){
        this.data = data;
    }
}

class Question {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        int a = scn.nextInt();
        int b = scn.nextInt();
        int c = scn.nextInt();
        // Node n4 = new Node(scn.nextInt());
        int d = scn.nextInt();

        Node n1 = new Node(a);
        Node n2 = new Node(b);
        Node n3 = new Node(c);
        Node n4 = new Node(d);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        
        System.out.println(n1.data);
        System.out.println(n1.next.data);
        System.out.println(n1.next.next.data);
        System.out.println(n1.next.next.next.data);
        System.out.println(n1.next.next.next.next);
    }
}