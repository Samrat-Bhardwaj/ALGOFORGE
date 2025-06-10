import java.io.*;
import java.util.*;
class LinkedListStackContest {
    static class DoublyLinkedList {
        class Node {
            int data;
            Node prev;
            Node next;

            public Node(int data){
                this.data = data;
            }
        }

        Node head;
        Node tail;
        int size;

        public DoublyLinkedList(){
            head = null;
            tail = null;
            size = 0;
        }

        public void addLast(int val){
            Node nn = new Node(val);

            if(head == null){
                head = tail = nn;
            } else {
                tail.next = nn;
                nn.prev = tail;
                tail = nn;
            }
            size++;
        }

        public void display(Node currentNode){
            Node temp = currentNode;

            while(temp != null){
                System.out.print(temp.data + " ");
                temp = temp.next;
            }

            System.out.println();
        }

        public void displayFromTail(Node head){
            Node temp = head;
            while(temp.next != null){
                temp = temp.next;
            }

            while(temp != null){
                System.out.print(temp.data + " ");
                temp = temp.prev;
            }
            System.out.println();
        }

        public Node getMiddleNode(Node head){
            if(head == null || head.next == null){
                return head;
            }

            Node slow = head;
            Node fast = head;

            while(fast.next != null && fast.next.next != null){
                slow = slow.next;
                fast = fast.next.next;
            }

            return slow;
        }

        public Node reverseList(Node head){
            Node prev = null;
            Node curr = head;

            while(curr != null){
                Node currKaNext = curr.next;

                curr.next = prev;
                curr.prev = currKaNext;
                
                prev = curr;
                curr = currKaNext;
            }
            
            return prev;
        }

        public void reorder(Node head){
            if(head == null || head.next == null){
                return;
            }
            Node mid = getMiddleNode(head);
            Node secondHead = mid.next;

            mid.next = null;
            secondHead.prev = null;

            secondHead = reverseList(secondHead);

            Node dummy = new Node(-1);
            Node curr = dummy;

            Node temp1 = head;
            Node temp2 = secondHead;

            while(temp1 != null && temp2 != null){
                // attaching list 1

                // -> finding temp1 ka next
                Node temp1KaNext = temp1.next;

                // -> isolating temp1 node
                temp1.next = null;
                if(temp1KaNext != null)
                    temp1KaNext.prev = null;

                // -> attaching temp 1 node
                curr.next = temp1;
                temp1.prev = curr;

                // -> moving temp1 to its next
                temp1 = temp1KaNext;

                // -> moving curr to its next
                curr = curr.next;


                // attaching list 2
                // -> finding temp1 ka next
                Node temp2KaNext = temp2.next;

                // -> isolating temp1 node
                temp2.next = null;
                if(temp2KaNext != null)
                    temp2KaNext.prev = null;

                // -> attaching temp 1 node
                curr.next = temp2;
                temp2.prev = curr;

                // -> moving temp1 to its next
                temp2 = temp2KaNext;

                // -> moving curr to its next
                curr = curr.next;
            }

            curr.next = temp1;
            if(temp1 != null)
                temp1.prev = curr;
            
            // removing dummy node
            dummy.next.prev = null;
            dummy.next = null;
        }
    }

    public static int poisonousPlants(List<Integer> p) {
        int maxDays = 0;

        Stack<int[]> st = new Stack<>(); // {element, deathDay}

        for(int i=0; i<p.size(); i++){
            int currEle = p.get(i);
            int deathDay = 0;

            while(st.size()>0 && st.peek()[0] >= currEle){
                deathDay = Math.max(deathDay, st.pop()[1]); // can die after left elements death day
            }

            if(st.size() == 0){
                deathDay = 0; // you can;t die
            } else {
                deathDay++; // will die the next day 
            }

            maxDays = Math.max(maxDays, deathDay);
            st.push(new int[]{currEle, deathDay});
        }

        return maxDays;
    }

    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        int N = scn.nextInt();
        DoublyLinkedList ll = new DoublyLinkedList();

        for(int i=0; i<N; i++){
            int x = scn.nextInt();
            ll.addLast(x);
        }

        ll.reorder(ll.head);
        ll.display(ll.head);
        ll.displayFromTail(ll.head);
    }
}