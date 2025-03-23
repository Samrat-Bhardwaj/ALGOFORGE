class Main {
    public static void main(String[] args){
        // int a = 5;
        

        // while(a < 10){
        //     System.out.println("Hello");
        //     a++;
        // }

        // System.out.println(a);

        // for(int count = 1; count<=5; count++){
        //     System.out.println(count);
        // }

        // for(int i=0; i<5; i+=2){
        //     System.out.println("Hello");
        //     i++;
        // }
    
        // for(int i=0; i<5; i++){
        //     System.out.println(i);
        // }

        // System.out.println(i+43);


        for(int i=1; i<=10; i++){
            // System.out.println("above statement");
            if(i == 7){
                continue;
            }

            System.out.println(i);
        }

        System.out.println("outside for loop");
    }
}