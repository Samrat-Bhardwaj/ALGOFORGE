import java.util.*;
class Bill {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        System.out.println("Enter the price of pen: ");
        int pen = scn.nextInt();

        System.out.println("Enter the price of notebook: ");
        int notebook = scn.nextInt();

        System.out.println("Enter the price of book: ");
        int book = scn.nextInt();

        int total_cost = pen + notebook + book;

        System.out.println("The total cost of your items is: " + total_cost);

        double gst = total_cost*0.18;
        double total_bill = total_cost + (gst);

        System.out.println("The total bill of your items is: " + total_bill);
    }
}