class Main {
    public static void printDecreasing(int n){
        if(n == 0){
            return;
        }

        System.out.println(n);
        printDecreasing(n-1);
    }

    public static void printIncreasing(int n){
        if(n==0){
            return;
        }

        printIncreasing(n-1);
        System.out.println(n);
    }

    public static void printDecInc(int n){
        if(n == 1){
            System.out.println(n);
            return;
        }

        System.out.println(n);

        printDecInc(n-1);

        System.out.println(n);
    }

    public static void main(String[] args){
        int n = 2;
        printDecInc(n);
    }
}