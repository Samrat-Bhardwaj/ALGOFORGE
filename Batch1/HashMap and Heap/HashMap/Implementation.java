class Implementation {
    class HashMap<K,V> {
        class HashMapNode {
            K key;
            V value;
        }

        private LinkedList<HashMapNode>[] data;
        private int size;
        private number_of_buckets = 4;
        
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
                HashMapNode newNode = new HashMapNode(K,V);
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
            ListNode<HashMapNode>[] newData = new LinkedList[number_of_buckets];
            this.data = newData;
            this.size = 0;

            for(int i=0; i<oldData.length; i++){
                for(HashMapNode node: oldData[i]){
                    this.put(node.key, node.value);
                }
            }
        }

        public V get(K key){

        }

        public V remove(K key){

        }

        public boolean containsKey(K key){

        }

        public ArrayList<K> keySet(){

        }

        public int size(){
            return this.size;
        }
    }
    public static void main(String[] args){

    }
}