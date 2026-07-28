import java.util.ArrayList;
class ArrayListExample {
    public static void main(String[] args){
        ArrayList<Integer> al = new ArrayList<>();

        al.add(1);
        al.add(12);
        al.add(34);
        al.add(23);

        System.out.println(al);
        al.add(2, 45);
        System.out.println(al);

    }
}