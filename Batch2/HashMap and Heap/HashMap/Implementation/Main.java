import java.util.LinkedList;
import java.util.ArrayList;
class Main {

    static class HashMap<K,V> {

        class HashMapNode {
            K key;
            V value;

            public HashMapNode(K key, V Value){
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

        public int findKeyIndex(K key, int bucketIndex){
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
        }

        public V get(K key){}

        public boolean containsKey(K key){}

        public V remove(K key){}

        public ArrayList<K> keySet(){}
    }
    
    public static void main(String[] args){
        HashMap<String,Integer> map = new HashMap<>();

        map.put("India",100);
    }
}