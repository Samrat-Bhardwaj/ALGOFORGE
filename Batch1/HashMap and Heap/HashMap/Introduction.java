import java.util.HashMap;
import java.util.HashSet;
class Introduction {
    public static void main(String[] args){
        HashMap<String, Integer> map = new HashMap<>();
        HashSet<String> set = new HashSet<>();

        map.put("India", 100);
        map.put("China", 60);
        map.put("England", 140);
        map.put("Russia", 120);

        System.out.println(map); // toString

        // map.remove("India");

        // // if(map.containsKey("India"))
        //     System.out.println(map.get("India"));

        set.add("India");
        set.add("India");
        set.add("India");
        set.add("China");
        System.out.println(set);
    }
}