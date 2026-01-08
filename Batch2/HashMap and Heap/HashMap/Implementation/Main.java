import java.util.LinkedList;
import java.util.ArrayList;
class Main {

    static class HashMap<K,V> {

        class HashMapNode {
            K key;
            V value;

            public HashMapNode(K key, V value){
                this.key = key;
                this.value = value;
            }
        }

        private LinkedList<HashMapNode>[] buckets;
        private int size;
        private int number_of_buckets = 4;

        public HashMap(){
            size = 0;
            initializeBuckets();
        }

        private void initializeBuckets(){
            buckets = new LinkedList[number_of_buckets];

            for(int i=0; i<buckets.length; i++){
                buckets[i] = new LinkedList<>();
            }
        }

        private int findBucketIndex(K key){
            int hc = key.hashCode(); // -16 || 5;
            int bucketIndex = Math.abs(hc) % number_of_buckets;

            return bucketIndex;
        }

        private int findKeyIndex(K key, int bucketIndex){
            int keyIndex = 0;

            for(HashMapNode node : buckets[bucketIndex]){
                if(node.key.equals(key)){
                    return keyIndex;
                }
                keyIndex++;
            }

            return -1;
        }        

        public void put(K key, V value){
            int bucketIndex = findBucketIndex(key);
            int keyIndex = findKeyIndex(key, bucketIndex);

            if(keyIndex == -1){ // no such key in our hashmap
                HashMapNode newNode = new HashMapNode(key,value);

                buckets[bucketIndex].add(newNode);
                this.size++;
            } else { // update existing key,value pair
                HashMapNode node = buckets[bucketIndex].get(keyIndex);

                node.value = value;
            } 

            // check if you want to rehash
            double lambda = this.size * 1.0 / number_of_buckets;
            if(lambda >= 2.0){
                rehash();
            }
        }

        private void rehash(){
            this.size = 0;
            this.number_of_buckets *= 2;
            // save old data
            LinkedList<HashMapNode>[] oldBuckets = buckets;
            // create new data
            LinkedList<HashMapNode>[] newBuckets = new LinkedList[number_of_buckets];
            // buckets should be referring to new data
            this.buckets = newBuckets;
            initializeBuckets();
            
            for(int i=0; i<oldBuckets.length; i++){
                for(HashMapNode node: oldBuckets[i]){
                    this.put(node.key, node.value);
                }
            }
        }

        public V get(K key){
            int bucketIndex = findBucketIndex(key);
            int keyIndex = findKeyIndex(key, bucketIndex);

            if(keyIndex == -1){
                return null;
            } else {
                HashMapNode node = buckets[bucketIndex].get(keyIndex);

                return node.value;
            }
        }

        public boolean containsKey(K key){
            int bucketIndex = findBucketIndex(key);
            int keyIndex = findKeyIndex(key, bucketIndex);

            if(keyIndex == -1){
                return false;
            } else {
                return true;
            }

            // return keyIndex != -1;
        }

        public V remove(K key){
            int bucketIndex = findBucketIndex(key);
            int keyIndex = findKeyIndex(key, bucketIndex);

            if(keyIndex == -1){
                return null;
            } else {
                HashMapNode node = buckets[bucketIndex].remove(keyIndex);
                return node.value;
            }
        }

        public ArrayList<K> keySet(){
            ArrayList<K> keys = new ArrayList<>();

            for(int i=0; i<buckets.length; i++){
                for(HashMapNode node: buckets[i]){
                    keys.add(node.key);
                }
            }

            return keys;
        }
    }
    
    public static void main(String[] args){
        HashMap<String,Integer> map = new HashMap<>();

        map.put("India",100);
        map.put("China",50);
        map.put("Ireland",54);
        map.put("Indonesia",51);

        System.out.println(map.keySet());     
        System.out.println(map.get("India"));     
        System.out.println(map.remove("China"));     
        System.out.println(map.containsKey("China"));     
        System.out.println(map.get("XYZ")); 

        map.put("India",101);    
        System.out.println(map.get("India"));   

        map.put("Indiaaaaa",100);
        map.put("Chinaaaaa",50);
        map.put("Irelandddd",54);
        map.put("Indonesiaaaa",51);  
        map.put("Indonesiaa",51);  
        System.out.println(map.keySet());     
    }
}