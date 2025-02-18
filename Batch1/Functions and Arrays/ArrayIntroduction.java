class ArrayIntroduction {
    public static void main(String[] args){
        int[] prices = new int[5];

        prices[3] = 7;
        prices[4] = 17;
        prices[0] = 27;

        System.out.println(prices);
        System.out.println(prices[1]);
    
        System.out.println(prices.length);
    }
}