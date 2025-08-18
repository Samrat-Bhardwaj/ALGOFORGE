import java.util.*;
class ShoppingCart {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);

        // write your code here
        System.out.print("Enter the price of pen: ");
        int penPrice = scn.nextInt();
        System.out.println();

        System.out.print("Enter the price of pencil: ");
        int pencilPrice = scn.nextInt();
        System.out.println();

        System.out.print("Enter the price of notebook: ");
        int notebookPrice = scn.nextInt();
        System.out.println();

        int totalPriceWithoutGST = penPrice + pencilPrice + notebookPrice;
        System.out.println("The total price is: " + totalPriceWithoutGST);

        double gstCost = totalPriceWithoutGST*0.18;
        System.out.println("GST for your item is: " + gstCost);

        double totalCost = totalPriceWithoutGST + gstCost;
        System.out.println("The total price to pay is: " + totalCost);
    }
}