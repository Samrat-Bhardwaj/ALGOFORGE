class ArrayExample {
    public static void main(String[] args){
        int[] arr;
        arr = new int[5];

        arr[0] = 24;
        arr[3] = 45;

        // System.out.println(arr[0]);
        // System.out.println(arr[1]);
        // System.out.println(arr[2]);
        // System.out.println(arr[3]);
        // System.out.println(arr[4]);

        for(int i=0; i<arr.length; i++){
            int ele = arr[i];
            System.out.println(ele);
        }
    }
}