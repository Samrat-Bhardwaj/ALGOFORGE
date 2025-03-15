import java.util.*;
class ArrayListIntro {
    public static void main(String[] args){
        ArrayList<Integer> al = new ArrayList<>();

        al.add(1);
        al.add(2);
        al.add(3);
        al.add(4);

        System.out.println(al);
        // al.remove(2);
        // System.out.println(al.get(0));
        al.add(3,45);
        System.out.println(al);
    }
}