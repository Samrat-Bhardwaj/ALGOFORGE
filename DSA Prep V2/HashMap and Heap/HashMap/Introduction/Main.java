import java.util.HashMap;
class Main {
    public static void main(String[] args){
        HashMap<String, Integer> map = new HashMap<>();

        map.put("India", 100);
        map.put("China", 200);
        map.put("India", 110);

        System.out.println(map);

        map.remove("China");
        System.out.println(map.containsKey("India"));
    }
}