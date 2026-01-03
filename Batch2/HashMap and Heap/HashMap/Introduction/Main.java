import java.util.HashMap;
class Main {
    public static void main(String[] args){
        HashMap<String,Integer> map = new HashMap<>();

        map.put("India", 100);
        map.put("China", 90);
        map.put("USA", 50);
        map.put("Ireland", 86);

        System.out.println(map.containsKey("China"));
        System.out.println(map.get("China"));

        map.put("China", 45);
        System.out.println(map.get("China"));
        System.out.println(map);
    }
}