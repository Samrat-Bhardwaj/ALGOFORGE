import java.util.LinkedList;
import java.util.ArrayList;
class Implementation {
    static class HashMap<K,V> {
        class HashMapNode {
            K key;
            V value;

            public HashMapNode(K key, V Value){
                this.key = key;
                this.value = value;
            }
        }

        private LinkedList<HashMapNode>[] data;
        private int size;
        private int number_of_buckets = 4;
        
        public HashMap(){
            init();
            this.size = 0;
        }

        private void init(){
            data = new LinkedList[number_of_buckets];

            for(int i=0; i<data.length; i++){
                data[i] = new LinkedList<>();
            }
        }

        private int findKeyIndex(K currentKey, int bucketIndex){
            int keyIndex = 0;
            for(HashMapNode node: data[bucketIndex]){
                if(node.key.equals(currentKey)){
                    return keyIndex;
                }
                keyIndex++;
            }

            return -1;
        }

        private int hashFunction(K key){ 
            int hc = key.hashCode(); // "India" = -16
            int bucketIndex = Math.abs(hc) % number_of_buckets;

            return bucketIndex;
        }

        public void put(K key, V value){
            int bucketIndex = hashFunction(key);
            int keyIndex = findKeyIndex(key, bucketIndex);

            if(keyIndex != -1){ // data exists in HashMap
                HashMapNode node = data[bucketIndex].get(keyIndex);
                node.value = value;
            } else { // new Key Value pair in HashMap
                HashMapNode newNode = new HashMapNode(key,value);
                data[bucketIndex].add(newNode);
                this.size++;
            }

            double lambda = (this.size) * 1.0 / number_of_buckets;
            if(lambda > 2.0){
                rehash();
            }
        }

        private void rehash(){
            this.number_of_buckets *= 2;
            LinkedList<HashMapNode>[] oldData = this.data;
            LinkedList<HashMapNode>[] newData = new LinkedList[number_of_buckets];
            this.data = newData;
            this.size = 0;

            for(int i=0; i<oldData.length; i++){
                for(HashMapNode node: oldData[i]){
                    this.put(node.key, node.value);
                }
            }
        }

        public V get(K key){
            int bucketIndex = hashFunction(key);
            int keyIndex = findKeyIndex(key, bucketIndex);

            if(keyIndex == -1){
                return null;
            } else {
                HashMapNode node = data[bucketIndex].get(keyIndex);
                return node.value;
            }
        }

        public V remove(K key){
            int bucketIndex = hashFunction(key);
            int keyIndex = findKeyIndex(key, bucketIndex);

            if(keyIndex == -1){
                return null;
            } else {
                HashMapNode node = data[bucketIndex].remove(keyIndex);
                this.size--;
                return node.value;
            }
        }

        public boolean containsKey(K key){
            int bucketIndex = hashFunction(key);
            int keyIndex = findKeyIndex(key, bucketIndex);

            return keyIndex != -1;
        }   

        public ArrayList<K> keySet(){
            ArrayList<K> keys = new ArrayList<>();

            for(int i=0; i<data.length; i++){
                for(HashMapNode node: data[i]){
                    keys.add(node.key);
                }
            }

            return keys;
        }

        public int size(){
            return this.size;
        }
    }
    public static void main(String[] args){
        HashMap<String,Integer> map = new HashMap<>();

        map.put("India", 400);
        map.put("China", 300);
        System.out.println(map.keySet());
        // System.out.println(map.remove(232));
        System.out.println(map.get("India"));
        System.out.println(map.get("China"));
        System.out.println(map.containsKey("England"));

        map.put("England", 200);
        System.out.println(map.get("England"));

        map.remove("England");
        System.out.println(map.containsKey("England"));
    }
}