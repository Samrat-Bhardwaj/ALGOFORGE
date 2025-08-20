import java.util.Scanner;
class SwitchExample {
    public static void main(String[] args){
        Scanner scn = new Scanner(System.in);
        int day = scn.nextInt();

        switch(day){
            case 1:
                System.out.println("Day is Monday");
                break;
            case 2: 
                System.out.println("Day is Tuesday");
                break;
            case 3: 
                System.out.println("Day is Wednesday");
                break;
            case 4: 
                System.out.println("Day is Thursday");
                break;
            case 5: 
                System.out.println("Day is Friday");
                break;
            case 6: 
                System.out.println("Day is Saturday");
                break;
            case 7: 
                System.out.println("Day is Sunday");
                break;
            default: 
                System.out.println("No day of week");
        }
    }
}