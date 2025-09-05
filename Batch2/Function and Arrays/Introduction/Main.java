class Main {
    public static void doNothing(char x, int d){
        System.out.println("Doing nothing");
    }

    public static int findSum(int a, int b){
        int sum = a + b;

        return sum;
    }

    public static void main(String[] args){
        int a = 55;
        char b = 'a';

        doNothing(b,a);

        int sum = findSum(5,11);
        System.out.println(sum);
    }
}