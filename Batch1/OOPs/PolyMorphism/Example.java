class Bank {
    String rules;
    public int rateOfInterest(){
        return 5;
    }
}

class SBI extends Bank {
    public int rateOfInterest(){
        return 6;
    }
}

class HDFC extends Bank {
    public int rateOfInterest(){
        return 7;
    }
}

class ICICI extends Bank {
    public int rateOfInterest(){
        return 4;
    }
}

class Example {
    public static void main(String[] args){
        Bank b; // compile time = Bank type 
        
        b = new ICICI(); // run time , b = ICICI property , late binding 
        System.out.println(b.rateOfInterest());

        b = new HDFC();
        System.out.println(b.rateOfInterest());
    }
}